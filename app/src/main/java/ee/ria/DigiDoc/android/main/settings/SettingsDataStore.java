package ee.ria.DigiDoc.android.main.settings;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceManager;

import java.util.Locale;

import javax.inject.Inject;

import ee.ria.DigiDoc.R;

public final class SettingsDataStore {

    private static final String KEY_LOCALE = "locale";

    private final SharedPreferences preferences;
    private final Resources resources;

    @Inject SettingsDataStore(Application application) {
        preferences = PreferenceManager.getDefaultSharedPreferences(application);
        this.resources = application.getResources();
    }

    public String getPhoneNo() {
        return preferences.getString(resources.getString(R.string.main_settings_phone_no_key), "");
    }

    public void setPhoneNo(String phoneNo) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(resources.getString(R.string.main_settings_phone_no_key), phoneNo);
        editor.apply();
    }

    public String getPersonalCode() {
        return preferences.getString(resources.getString(R.string.main_settings_personal_code_key),
                "");
    }

    public void setPersonalCode(String personalCode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(resources.getString(R.string.main_settings_personal_code_key),
                personalCode);
        editor.apply();
    }

    @Nullable public Locale getLocale() {
        String locale = preferences.getString(KEY_LOCALE, null);
        if (locale != null) {
            return new Locale(locale);
        }
        return null;
    }

    public void setLocale(@Nullable Locale locale) {
        if (locale == null) {
            preferences.edit().remove(KEY_LOCALE).apply();
        } else {
            preferences.edit().putString(KEY_LOCALE, locale.getLanguage()).apply();
        }
    }
}
