package makdroid.stackoverflowsearch.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import makdroid.stackoverflowsearch.QuestionsAlarmReceiver;
import makdroid.stackoverflowsearch.R;
import makdroid.stackoverflowsearch.StackOverApplication;
import makdroid.stackoverflowsearch.adapters.RecyclerItemClickListener;
import makdroid.stackoverflowsearch.adapters.ResponseStackOverAdapter;
import makdroid.stackoverflowsearch.model.Item;
import makdroid.stackoverflowsearch.model.StackOverResponse;
import makdroid.stackoverflowsearch.prefs.UserPrefMaster;
import makdroid.stackoverflowsearch.services.StackOverflowService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    StackOverflowService stackOverflowService;
    @Inject
    UserPrefMaster userPrefMaster;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.pb_loading)
    ProgressBar mPbarLoading;

    private ResponseStackOverAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeDependencyInjector();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_questions_list, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                requestJSON(query);
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                MainActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeDependencyInjector() {
        StackOverApplication application = (StackOverApplication) getApplication();
        application.getNetComponent().inject(this);
    }


    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext()) {
            @Override
            public void onItemClicked(MotionEvent e) {
                View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                int position = mRecyclerView.getChildLayoutPosition(view);
                onCardItemClick(adapter.getItem(position).link, adapter.getItem(position).title);
            }
        });
        adapter = new ResponseStackOverAdapter(new ArrayList<Item>());
        mRecyclerView.setAdapter(adapter);
    }

    private void requestJSON(String query) {
        if (TextUtils.isEmpty(query)) {
            adapter.clearItems();
        } else {
            showProgressBar();
            userPrefMaster.setUserLastQuery(query);
            Call<StackOverResponse> getQuestions = stackOverflowService.getQuestionsByTitle(query);
            getQuestions.enqueue(new Callback<StackOverResponse>() {
                @Override
                public void onResponse(Call<StackOverResponse> call, Response<StackOverResponse> response) {
                    onResponseStackOver(response);
                    Log.v("success Repo", response.message());
                }

                @Override
                public void onFailure(Call<StackOverResponse> call, Throwable t) {
                    Log.v("Error", t.getMessage());
                    hideProgressBar();
                }
            });
        }
    }

    private void onResponseStackOver(Response<StackOverResponse> response) {
        StackOverResponse jsonResponse = response.body();
        if (jsonResponse != null && jsonResponse.items != null) {
            adapter.clearItems();
            adapter.addItems(jsonResponse.items);
        }
        hideProgressBar();
    }

    private void showProgressBar() {
        this.mPbarLoading.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        this.mPbarLoading.setVisibility(View.GONE);
    }

    @NonNull
    void onCardItemClick(String url, String title) {
        DetailsActivity.start(this, url, title);
    }



}
