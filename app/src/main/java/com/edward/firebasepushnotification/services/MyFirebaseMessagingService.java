package com.edward.firebasepushnotification.services;

import android.util.Log;

import com.edward.firebasepushnotification.utils.PrefUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static String TAG = MyFirebaseMessagingService.class.getName();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "onMessageReceived() --> " + remoteMessage.getNotification().getTitle());

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e(TAG, "onNewToken() --> " + s);
        PrefUtils.saveInstaneIdToken(this, s);
    }

}
