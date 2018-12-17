package com.example.suvrajitkarmaker.cf;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class login extends AppCompatActivity {
   String globalstr;
    EditText edit;

    Button btn;
   public static String sr,single;
    Intent intent;
    private static login mcontest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mcontest=this;
        btn=findViewById(R.id.loginbtn);
        edit=findViewById(R.id.userid);


        new fetchcontest().execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sr=edit.getText().toString();

                new fetchData().execute("https://codeforces.com/api/user.info?handles="+sr);



            }
        });


    }
    public static String time(long x)
    {
        long dv = Long.valueOf(x)*1000;// its need to be in milisecond
        Date df = new java.util.Date(dv);
        String vv = new SimpleDateFormat("dd/MM/yyyy hh:mma").format(df);
        return vv;
    }
    public static login getContext(){
        return mcontest;
    }


}
class fetchData extends AsyncTask<String,String,String> {

    public static String fullname="", city="", titlePhoto="", avatar="", organization="", rank="", maxRank="";
    public static int maxRating, rating;
    public static String tmp;
    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection=null;
        BufferedReader reader=null;

        try {
            URL url=new URL(strings[0]);
            connection= (HttpURLConnection) url.openConnection();
            InputStream stream=connection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer=new StringBuffer();

            String line="";
            while ((line=reader.readLine())!=null){
                buffer.append(line);

            }
            String data=buffer.toString();
            //tmp=data;
            JSONObject json = (JSONObject) new JSONTokener(data).nextValue();
            data = (String) json.get("status");
            JSONArray JA = json.getJSONArray("result");
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);

                try {
                    fullname = (String) JO.get("firstName") + " " + JO.get("lastName");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    city = (String) JO.get("city") + ", " + (String) JO.get("country");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    titlePhoto = (String) JO.get("titlePhoto");
                    avatar = (String) JO.get("avatar");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    organization = (String) JO.get("organization");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                rating = (int) JO.get("rating");

                rank = (String) JO.get("rank");

                maxRating = (int) JO.get("maxRating");

                maxRank = (String) JO.get("maxRank");
            }

            return data;


        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            if (connection!=null) {
                connection.disconnect();
            }
            try {
                if (reader!=null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "NO";

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //(MainActivity.txt).setText("sdafsafdafsaf");
        //(login.txt).setText(result);

        if(result.equals("OK")){
            //(MainActivity.txt).setText(result);
            Intent intent=new Intent(login.getContext(),MainActivity.class);
            (login.getContext()).startActivity(intent);
        }
        else {

            Toast.makeText(login.getContext(), "Invalid Username", Toast.LENGTH_LONG).show();

        }



    }
}