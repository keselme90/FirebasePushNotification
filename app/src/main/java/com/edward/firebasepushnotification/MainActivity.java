package com.edward.firebasepushnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.edward.firebasepushnotification.utils.PrefUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("test_topic");

        ((SwitchCompat)findViewById(R.id.notification_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    Toast.makeText(MainActivity.this, "Switch activated ", Toast.LENGTH_LONG).show();
                    FirebaseMessaging.getInstance().subscribeToTopic("test_topic");
                }else {
                    Toast.makeText(MainActivity.this, "Switch deactivated ", Toast.LENGTH_LONG).show();
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("test_topic");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
//                                    FirebaseInstanceId.getInstance().deleteInstanceId();
                                    String token = PrefUtils.getInstanceIdToken(MainActivity.this);
                                    if(!token.isEmpty())
                                        FirebaseInstanceId.getInstance().deleteToken(token, "FCM");
//                                    FirebaseInstanceId.getInstance().deleteToken();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                }
            }
        });
    }
}
