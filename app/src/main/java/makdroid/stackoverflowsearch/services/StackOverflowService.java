package makdroid.stackoverflowsearch.services;

import makdroid.stackoverflowsearch.model.StackOverResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grzecho on 01.07.2016.
 */
public interface StackOverflowService {

    @GET("2.2/search/advanced?order=desc&sort=activity&site=stackoverflow")
    Call<StackOverResponse> getQuestionsByTitle(@Query("q") String q);
}
