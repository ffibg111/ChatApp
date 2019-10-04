package com.example.datafireany_api;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {
    // remove null by initializing parsed string
    static String parsed = "";
    public static void refresh() {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String line;
                    try {
                        URL url = new URL("https://calm-quetzal.dev.with-datafire.io/reddit/");
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder result = new StringBuilder();
                        while ((line = bufferedReader.readLine()) != null) {
                            result.append(line);
                        }
                        return result.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                return null;
                }
            @Override
            protected void onPostExecute(String s) {
                if(s==null) {
                    MainActivity.showDataTxtView.setText("No host found!");
                } else {
                    try {
                        JSONObject joFeed = new JSONObject(s).getJSONObject("feed");
                        JSONArray jaEntries = joFeed.getJSONArray("entries");
                        for (int i = 0; i < jaEntries.length(); i++) {
                            JSONObject jx = (JSONObject) jaEntries.get(i);
                            // display all items
                            parsed = parsed + "Title: " + jx.get("title") + "\n" +
                                    "PubDate: " + jx.get("pubDate") + "\n" +
                                    "Author: " + jx.get("author") + "\n" +
                                    "Content: " + jx.get("content") + "\n\n";
                        }
                        MainActivity.showDataTxtView.setText(parsed);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.execute();
    }
}


