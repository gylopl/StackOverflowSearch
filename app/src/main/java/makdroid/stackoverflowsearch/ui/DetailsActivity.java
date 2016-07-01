package makdroid.stackoverflowsearch.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import makdroid.stackoverflowsearch.R;

public class DetailsActivity extends AppCompatActivity {

    private final static String EXTRA_URL = "EXTRA_URL";
    private final static String EXTRA_TITLE = "EXTRA_TITLE";

    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        loadWebPage();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadWebPage() {
        Bundle extras = getIntent().getExtras();
        this.setTitle(extras.getString(EXTRA_TITLE));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(extras.getString(EXTRA_URL));
    }


    @NonNull
    public static void start(Context context, String url, String title) {
        Intent generateMemIntent = new Intent(context, DetailsActivity.class);
        generateMemIntent.putExtra(EXTRA_URL, url);
        generateMemIntent.putExtra(EXTRA_TITLE, title);
        context.startActivity(generateMemIntent);
    }
}
