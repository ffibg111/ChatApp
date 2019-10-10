package com.example.androidbasicappassignment10_07_19;
import android.net.Uri;

public class ImageConnection {
    public static String getURLForResource(int turkey) {
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+turkey).toString();
    }
}
