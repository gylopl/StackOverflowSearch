package makdroid.stackoverflowsearch;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import makdroid.stackoverflowsearch.dagger.AppModule;
import makdroid.stackoverflowsearch.dagger.DaggerNetComponent;
import makdroid.stackoverflowsearch.dagger.NetComponent;
import makdroid.stackoverflowsearch.dagger.NetModule;
import makdroid.stackoverflowsearch.dagger.PrefsModule;

/**
 * Created by Grzecho on 01.07.2016.
 */
public class StackOverApplication extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.stackexchange.com/"))
                .prefsModule(new PrefsModule())
                .build();
        scheduleAlarm();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public void scheduleAlarm() {
        Intent intent = new Intent(this, QuestionsAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, QuestionsAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis();
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pIntent);
    }
}