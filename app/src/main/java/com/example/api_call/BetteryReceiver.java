package com.example.api_call;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.TextView;
import android.widget.Toast;

public class BetteryReceiver extends BroadcastReceiver {
//    TextView tv;
//    public BetteryReceiver(TextView tv) {
//        this.tv=tv;
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //int percentage = intent.getIntExtra("level", 0);
        boolean boolextra=intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
//        if (percentage != 0) {
//            tv.setText(percentage + "%");
//        }
       if(!boolextra) {
           Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
        }
       else {
           Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();

       }
    }
}
