package shaz.contentprovider.demo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import shaz.contentprovider.demo.model.User;
import shaz.contentprovider.demo.viewmodel.DataViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private DataViewModel mDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        mDataViewModel.getData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                showData(users);
            }
        });
    }

    private void showData(List<User> entities) {
        if (textView == null)
            textView = findViewById(R.id.label);
        if (entities == null)
            return;
        Collections.reverse(entities);
        String value = "";
        for (User entity : entities)
            value += entity.getName() + ", " + entity.getEmail() + ", " + entity.getAddress() + "\n\n";
        textView.setText(value);
    }

    public void pull(View view) {
        mDataViewModel.fetchData(this);
    }
}
