package com.innoli.dinnercoming;

import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by yli on 3/10/16.
 */
public class GcmSender {

    private static final String API_KEY = "AIzaSyB9fD86cbYgQ1euBzLaeGN_luupUdjI_5E";
    public static final String TAG = "dinnerComing_gcmSender";

    /**
     * Send notification.
     */
    public static void send() {

        try {
            JSONObject jGcmData = new JSONObject();
            JSONObject jData = new JSONObject();

            jData.put("message", "Dinner is arrived! Come quickly!");

            jGcmData.put("to", "/topics/global");
            jGcmData.put("data", jData);

            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);

            Log.i(TAG, "Received the response from GCM server");
            Log.i(TAG, resp);

        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        } catch (MalformedURLException e) {
            Log.d("dinnerComing_gcmSender", e.getMessage());
        } catch (ProtocolException e) {
            Log.d("dinnerComing_gcmSender", e.getMessage());
        } catch (IOException e) {
            Log.d("dinnerComing_gcmSender", e.getMessage());
        }


    }

}
