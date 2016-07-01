package makdroid.stackoverflowsearch.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import java.io.IOException;

import javax.inject.Inject;

import makdroid.stackoverflowsearch.QuestionsAlarmReceiver;
import makdroid.stackoverflowsearch.R;
import makdroid.stackoverflowsearch.StackOverApplication;
import makdroid.stackoverflowsearch.model.StackOverResponse;
import makdroid.stackoverflowsearch.prefs.UserPrefMaster;
import retrofit2.Call;


public class BackgroundQueryService extends IntentService {

    @Inject
    StackOverflowService stackOverflowService;
    @Inject
    UserPrefMaster userPrefMaster;

    public BackgroundQueryService() {
        super("BackgroundQueryService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StackOverApplication application = (StackOverApplication) getApplication();
        application.getNetComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra(QuestionsAlarmReceiver.ACTION))
                && !TextUtils.isEmpty(userPrefMaster.getUserLastQuery())) {
            try {
                Call<StackOverResponse> callStackOverResponse = stackOverflowService.getQuestionsByTitle(userPrefMaster.getUserLastQuery());
                StackOverResponse stackOverResponse = callStackOverResponse.execute().body();
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_stat_not)
                                .setContentTitle("Query stackOverflow: " + userPrefMaster.getUserLastQuery())
                                .setContentText("Result: " + stackOverResponse.items.size());

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
