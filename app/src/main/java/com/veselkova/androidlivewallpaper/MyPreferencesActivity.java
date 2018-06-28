package com.veselkova.androidlivewallpaper;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;

public class MyPreferencesActivity
        extends PreferenceActivity
{
    Preference.OnPreferenceChangeListener numberCheckListener = new Preference.OnPreferenceChangeListener()
    {
        public boolean onPreferenceChange(Preference paramAnonymousPreference, Object paramAnonymousObject)
        {
            if ((paramAnonymousObject != null) && (paramAnonymousObject.toString().length() > 0) && (paramAnonymousObject.toString().matches("\\d*"))) {
                return true;
            }
            Toast.makeText(MyPreferencesActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.prefs);
        getPreferenceScreen().findPreference("numberOfCircles").setOnPreferenceChangeListener(this.numberCheckListener);
    }
}
