package shaz.practice_contentprovider;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import shaz.practice_contentprovider.persistance.entities.UserEntity;
import shaz.practice_contentprovider.persistance.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getLiveUsers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntities) {
                showData(userEntities);
            }
        });
    }

    public void addUser(View view) {
        Random random = new Random();
        int num = random.nextInt(100);
        UserEntity employee = new UserEntity("Shahbaz Akhtar", "shahbaz" + num + "@gmail.com", "Plot No:" + num);
        mUserViewModel.addUsers(employee);
    }

    private void showData(List<UserEntity> entities) {
        if (textView == null)
            textView = findViewById(R.id.label);
        if (entities == null)
            return;
        Collections.reverse(entities);
        String value = "";
        for (UserEntity entity : entities)
            value += entity.getName() + ", " + entity.getEmail() + ", " + entity.getAddress() + "\n\n";
        textView.setText(value);
    }

    public void deleteAllUsers(View view) {
        mUserViewModel.deleteAllUsers();
    }
}
