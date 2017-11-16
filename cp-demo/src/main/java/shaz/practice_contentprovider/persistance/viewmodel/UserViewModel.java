package shaz.practice_contentprovider.persistance.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import shaz.practice_contentprovider.persistance.AppDatabase;
import shaz.practice_contentprovider.persistance.entities.UserEntity;

/**
 * Created by ${Shahbaz} on 16-11-2017
 */

public class UserViewModel extends AndroidViewModel {
    private AppDatabase mAppDatabase;
    private LiveData<List<UserEntity>> mUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mAppDatabase = AppDatabase.getInstance(this.getApplication());
        mUsers = mAppDatabase.getUserDao().getLiveUsers();
    }

    public LiveData<List<UserEntity>> getLiveUsers() {
        return mUsers;
    }

    public List<UserEntity> getUsers() {
        return mUsers.getValue();
    }

    public void addUsers(final UserEntity... entities) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mAppDatabase.getUserDao().insert(entities);
                return null;
            }
        }.execute();
    }

    public void deleteAllUsers(){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                mAppDatabase.getUserDao().deleteUsers(getUsers());
                return null;
            }
        }.execute();
    }
}
