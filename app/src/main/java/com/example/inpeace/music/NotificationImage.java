package com.example.inpeace.music;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NotificationImage extends AsyncTask<Void,Void, Bitmap> {

    private String url ;

    public NotificationImage(String url) {
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {

        Bitmap bitmap = null;

        try {
            Log.d("ABC", "notificationMusic: started ");

            URL urlImage = new URL(url);
            Log.d("ABC", urlImage.toString());

            HttpsURLConnection connection = (HttpsURLConnection) urlImage.openConnection();
            Log.d("ABC", "notificationMusic: connection open ");


//            OkHttpClient okHttpClient = new OkHttpClient();
            connection.setDoInput(true);
//            okHttpClient.d
//            Log.d("ABC", ""+  okHttpClient.connectTimeoutMillis());
            connection.connect();
            Log.d("ABC", "notificationMusic: connected ");

            InputStream inputStream = connection.getInputStream();

            Log.d("ABC", "" + inputStream);

            bitmap = BitmapFactory.decodeStream(inputStream);
            Log.d("ABC", "notificationMusic: completed ");
        } catch (Exception e) {
            Log.d("ABC","KK");
        }

        return null;
    }
}
