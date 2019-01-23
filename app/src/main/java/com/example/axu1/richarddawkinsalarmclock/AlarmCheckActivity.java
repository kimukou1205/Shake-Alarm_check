package com.example.axu1.richarddawkinsalarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmCheckActivity extends BroadcastReceiver {
    private static final String TAG = AlarmCheckActivity.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startActivityIntent = new Intent(context, PlaySoundActivity.class);
        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivityIntent);

    }

}
