package com.example.api_call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import retrofit2.Call;

import android.os.PowerManager;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_call.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    LinearLayoutManager layoutManager;
    Adapter adapter;
    BetteryReceiver betteryReceiver;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //brodcast reciever
        betteryReceiver=new BetteryReceiver();
//        registerReceiver(betteryReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(betteryReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //  notification for oreo and that above version
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager1=getSystemService(NotificationManager.class);
            manager1.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg ="Failed";
                        }
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
// api calling
        String url = "https://reqres.in/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface api = retrofit.create(APIInterface.class);

        Call<model> call = api.getmodels("2");

        call.enqueue(new Callback<model>() {
            @Override
            public void onResponse(retrofit2.Call<model> call, Response<model> response) {
                binding.idPBLoading.setVisibility(View.GONE);

               model userlist = response.body();

                Log.e("respon",""+userlist);

                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                    binding.recyclerview.setLayoutManager(layoutManager);

                    ArrayList<data>mdata = userlist.getDataArrayList();

                        adapter = new Adapter(mdata);
                        binding.recyclerview.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(retrofit2.Call<model> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(betteryReceiver);
    }
}