package com.rynkbit.smartcoffee;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        /*getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String serverAddress = preferences.getString("server_address", "http://192.168.178.24:5000");
        EditText serverNameTextField = (EditText)findViewById(R.id.server_adress);
        serverNameTextField.setText(serverAddress);

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    public void saveAddress(View view) {
        EditText serverNameTextField = (EditText)findViewById(R.id.server_adress);
        String serverAddress = serverNameTextField.getText().toString();
        SharedPreferences.Editor  editor = preferences.edit();
        editor.putString("server_address", serverAddress);
        editor.commit();

    }
}