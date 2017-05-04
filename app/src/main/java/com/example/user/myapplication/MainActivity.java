package com.example.user.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.myapplication.models.UserSettings;
import com.example.user.myapplication.prefs.ObjectPreference;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObjectPreference<UserSettings> userSettingsPref = UserSettingsSingleton.get(this);

        UserSettings userSettings = userSettingsPref.getObject();//get obj from pref (at first time it will be restored from pref)
        UserSettings userSettings2 = userSettingsPref.getObject();//get obj from pref (at second time it will be restored from cachedObject from RAM)

        UserSettings newUserSettings = new UserSettings();
        newUserSettings.setName("AnyName");
        userSettingsPref.setObject(newUserSettings);//save to pref, change cachedObject in RAM

    }

    public static class UserSettingsSingleton{

        private static ObjectPreference<UserSettings> userSettingsPref;

        public static ObjectPreference<UserSettings> get(Context context) {
            if (userSettingsPref==null) {
                SharedPreferences preferences = context.getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                UserSettings defaultValue = new UserSettings();
                userSettingsPref = new ObjectPreference<>(UserSettings.class, getLowerCaseGson(), preferences, "prefs_user_settings", defaultValue);
            }
            return userSettingsPref;
        }

        public static Gson getLowerCaseGson() {
            return new GsonBuilder()
                    .setLenient()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
        }
    }
}
