package makdroid.stackoverflowsearch.prefs;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Grzecho on 01.07.2016.
 */
@Singleton
public class UserPrefMaster {
    private final StringPreference userLastQuery;

    @Inject
    public UserPrefMaster(StringPreference userLastQuery) {
        this.userLastQuery = userLastQuery;
    }

    public void setUserLastQuery(String query) {
        this.userLastQuery.set(query);
    }

    public String getUserLastQuery() {
        return userLastQuery.get();
    }

}