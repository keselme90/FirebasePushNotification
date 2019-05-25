package com.edward.firebasepushnotification.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    public static void saveInstaneIdToken(Context context, String token){

        SharedPreferences.Editor prefs = context.getSharedPreferences("FIREBASE_MESSAGING", Context.MODE_PRIVATE).edit();
        prefs.putString("token",token);
        prefs.apply();
    }

    public static String getInstanceIdToken(Context context){

        SharedPreferences prefs = context.getSharedPreferences("FIREBASE_MESSAGING", Context.MODE_PRIVATE);
        return prefs.getString("token","");
    }
}
