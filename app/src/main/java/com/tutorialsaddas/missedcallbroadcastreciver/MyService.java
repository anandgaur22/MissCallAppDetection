package com.tutorialsaddas.missedcallbroadcastreciver;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anand
 */

public class MyService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String save_number = intent.getStringExtra("save_number");
        NetworkCall(save_number);


        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /* network call*/
    public void NetworkCall(final String mobile_number) {


        StringRequest postRequest = new StringRequest(Request.Method.POST, "https://androidhub22.000webhostapp.com/missed/missed_call.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        VolleyLog.e("Error: ", "" + error.getMessage());
                        Toast.makeText(getApplicationContext(), "no internet connection", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> postparams = new HashMap<String, String>();
                postparams.put("mobile_number", mobile_number);


                return postparams;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);

    }
}