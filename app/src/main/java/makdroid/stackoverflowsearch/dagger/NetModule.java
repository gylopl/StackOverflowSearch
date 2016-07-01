package makdroid.stackoverflowsearch.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import makdroid.stackoverflowsearch.services.StackOverflowService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grzecho on 01.07.2016.
 */
@Module
public class NetModule {
    private String mBaseUrl;

    public NetModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    StackOverflowService provideStackOverflowService(Retrofit retrofit) {
        return retrofit.create(StackOverflowService.class);
    }
}