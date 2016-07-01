package makdroid.stackoverflowsearch.dagger;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import makdroid.stackoverflowsearch.prefs.PrefUserLastQuery;
import makdroid.stackoverflowsearch.prefs.StringPreference;
import makdroid.stackoverflowsearch.prefs.UserPrefMaster;

/**
 * Created by Grzecho on 01.07.2016.
 */
@Module
public class PrefsModule {
    private static String USER_LAST_QUERY = "USER_LAST_QUERY";

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application mApplication) {
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }

    @Provides
    @Singleton
    @PrefUserLastQuery
    StringPreference providePrefUserLastQuery(SharedPreferences preferences) {
        return new StringPreference(preferences, USER_LAST_QUERY);
    }

    @Provides
    @Singleton
    UserPrefMaster provideUserPrefMaster(@PrefUserLastQuery StringPreference userLastQuery) {
        return new UserPrefMaster(userLastQuery);
    }

}