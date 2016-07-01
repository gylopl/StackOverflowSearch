package makdroid.stackoverflowsearch.dagger;

import javax.inject.Singleton;

import dagger.Component;
import makdroid.stackoverflowsearch.prefs.UserPrefMaster;
import makdroid.stackoverflowsearch.services.BackgroundQueryService;
import makdroid.stackoverflowsearch.ui.MainActivity;

/**
 * Created by Grzecho on 01.07.2016.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class, PrefsModule.class})
public interface NetComponent {
    void inject(MainActivity activity);

    void inject(BackgroundQueryService service);

}
