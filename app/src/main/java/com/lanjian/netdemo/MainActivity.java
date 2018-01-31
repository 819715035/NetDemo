package com.lanjian.netdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lanjian.netdemo.net.Banner;
import com.lanjian.netdemo.net.NetClient;
import com.lanjian.netdemo.net.SubscriberCallBack;

import java.util.List;

import rx.Scheduler;
import rx.internal.schedulers.ScheduledAction;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testNetBtn = findViewById(R.id.testNet_btn);
        testNetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetClient.getRetrofit()
                        .getBanner(1)
                        .subscribeOn(Schedulers.io())
                        .subscribe(new SubscriberCallBack<List<Banner>>(MainActivity.this) {
                            @Override
                            public void onSuccess(List<Banner> result) {
                                Log.e("tag",result.toString());
                            }
                        });
            }
        });
    }
}
