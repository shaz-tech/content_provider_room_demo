package shaz.practice_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import shaz.practice_contentprovider.persistance.AppDatabase;
import shaz.practice_contentprovider.persistance.dao.UserDao;
import shaz.practice_contentprovider.persistance.entities.UserEntity;

public class UserContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.shaz.room.user.provider";
    private static final String USERS_BASE_PATH = UserEntity.TABLE_NAME;

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + USERS_BASE_PATH);

    public static final int CODE_USER_DIR = 1;
    public static final int CODE_USER_ITEM = 2;

    private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(AUTHORITY, USERS_BASE_PATH, CODE_USER_DIR);
        mUriMatcher.addURI(AUTHORITY, USERS_BASE_PATH + "/*", CODE_USER_ITEM);
    }

    @Override
    public String getType(Uri uri) {
        final int match = mUriMatcher.match(uri);
        switch (match) {
            case CODE_USER_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + USERS_BASE_PATH;
            case CODE_USER_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + USERS_BASE_PATH;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final int match = mUriMatcher.match(uri);
        if (match == CODE_USER_DIR || match == CODE_USER_ITEM) {
            Context context = getContext();
            if (context == null)
                return null;
            final Cursor cursor;
            UserDao userDao = AppDatabase.getInstance(context).getUserDao();
            if (match == CODE_USER_DIR) {
                cursor = userDao.getRawUsers();
            } else {
                cursor = userDao.getRawUser(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else
            throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
