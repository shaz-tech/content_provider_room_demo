package shaz.practice_contentprovider.persistance.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import shaz.practice_contentprovider.persistance.entities.UserEntity;

/**
 * Created by ${Shahbaz} on 16-11-2017
 */

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity... users);

    @Delete
    void deleteUsers(List<UserEntity> entities);

    @Query(value = "Select * from " + UserEntity.TABLE_NAME)
    List<UserEntity> getUsers();

    @Query(value = "Select * from UserEntity")
    LiveData<List<UserEntity>> getLiveUsers();

    @Query(value = "Select * from " + UserEntity.TABLE_NAME)
    Cursor getRawUsers();

    @Query(value = "Select * from " + UserEntity.TABLE_NAME + " where " + UserEntity.COLUMN_ID + " = :id")
    Cursor getRawUser(long id);
}
