package com.example.suvrajitkarmaker.cf;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fetchcontest extends AsyncTask<Void, Void, Void> {
    String data = "";
    public  static String dataparsed = "";



    @Override
    protected Void doInBackground(Void... voids) {
        try {

            String urlString = "https://codeforces.com/api/contest.list";

            URL url = new URL(urlString);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            int ct=0;
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
                ct++;
            }

            JSONObject json= (JSONObject) new JSONTokener(data).nextValue();


            JSONArray JA=json.getJSONArray("result");

           for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                    String s= (String) JO.get("phase");
                    if(s.equals("BEFORE")){
                        String single= (String) JO.get("name")+"\n";
                       String dd=login.time((Integer) JO.get("startTimeSeconds"));
                        dataparsed = dataparsed+(Integer.toString(i+1)+".")+single+"Contest Start: "+dd+"\n\n";
                    }



          }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //login.txt.setText(single);
    }
   // public String getData() {
     //   return single;
   // }




}
