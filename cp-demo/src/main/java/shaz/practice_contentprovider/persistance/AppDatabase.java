package shaz.practice_contentprovider.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import shaz.practice_contentprovider.persistance.dao.UserDao;
import shaz.practice_contentprovider.persistance.entities.UserEntity;

/**
 * Created by ${Shahbaz} on 16-11-2017
 */

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "ShazContentProviderApp.db";
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME).build();
        return INSTANCE;
    }

    public abstract UserDao getUserDao();
}
