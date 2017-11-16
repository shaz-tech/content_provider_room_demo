package shaz.contentprovider.demo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import shaz.contentprovider.demo.model.User;

/**
 * Created by ${Shahbaz} on 16-11-2017
 */

public class DataViewModel extends ViewModel {
    private final String USER_CONTENT_URI = "content://com.shaz.room.user.provider/UserEntity";
    private MutableLiveData<List<User>> mLiveData;

    public DataViewModel() {
        mLiveData = new MutableLiveData<>();
    }

    public LiveData<List<User>> getData() {
        return mLiveData;
    }

    public void fetchData(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                ContentResolver contentResolver = context.getContentResolver();
                final Cursor cursor;
                if (Build.VERSION.SDK_INT >= 26) {
                    cursor = contentResolver.query(Uri.parse(USER_CONTENT_URI), null, null, null);
                } else {
                    cursor = contentResolver.query(Uri.parse(USER_CONTENT_URI), null, null, null, null);
                }
                mLiveData.postValue(getData(cursor));
                return null;
            }
        }.execute();
    }

    private List<User> getData(Cursor cursor) {
        if (cursor == null)
            return null;
        List<User> users = new ArrayList<>();
        if (cursor.moveToFirst())
            do {
                long id = cursor.getLong(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                User user = new User(id, name, email, address);
                users.add(user);
            } while (cursor.moveToNext());
        return users;
    }
}
