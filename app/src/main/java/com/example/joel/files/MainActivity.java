package com.example.joel.files;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
   // Context context;
 //  String url = "https://podcast.pastoroti.org/podcast-download/592/6th-november-2017-run-to-obtain-5-mins-with-pastor-oti-love-economy.mp3?ref=download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button create =(Button) findViewById(R.id.button1);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "orango");

                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        Log.d("App", "failed to create directory");

                    }
                }


               /* DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }

                request.setDestinationInExternalPublicDir(mediaStorageDir.getAbsolutePath(),"/sdcard/orango");


                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
                */


              /*  DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

                Uri downloadUri = Uri.parse(url);
                DownloadManager.Request request = new DownloadManager.Request(
                        downloadUri);

                request.setAllowedNetworkTypes(
                        DownloadManager.Request.NETWORK_WIFI
                                | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false).setTitle("Demo")
                        .setDescription("Something useful. No, really.")
                        .setDestinationInExternalPublicDir("/orango", url);

                mgr.enqueue(request);*/



            }
        });

    }
    public  class DownloadFile extends AsyncTask<String, Integer, String> {
        @Override
        public String doInBackground(String... url) {


            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "awesome");

            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("App", "failed to create directory");

                }
            }

            // Toast.makeText(getApplicationContext(),"clicks",Toast.LENGTH_SHORT).show();

            int count;

            try {


                URL url2 = new URL("https://podcast.pastoroti.org/podcast-download/592/6th-november-2017-run-to-obtain-5-mins-with-pastor-oti-love-economy.mp3?ref=download");
                URLConnection conexion = url2.openConnection();
                conexion.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conexion.getContentLength();


                //create and save in in file


                // downlod the file
                InputStream input = new BufferedInputStream(url2.openStream());
                OutputStream output = new FileOutputStream("awesome");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    publishProgress((int) (total * 100 / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;
        }
    }
}
