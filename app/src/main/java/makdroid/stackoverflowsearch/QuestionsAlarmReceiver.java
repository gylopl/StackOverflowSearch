package makdroid.stackoverflowsearch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import makdroid.stackoverflowsearch.services.BackgroundQueryService;

/**
 * Created by Grzecho on 01.07.2016.
 */
public class QuestionsAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "requery";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, BackgroundQueryService.class);
        i.putExtra(ACTION, ACTION);
        context.startService(i);
    }
}