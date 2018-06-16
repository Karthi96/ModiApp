package com.liteart.apps.modivsits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public ArrayList<PMVisit> visitslist,summitlist;//list arrays
    boolean connected = false;
    TextView b1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        b1=(TextView)findViewById(R.id.refreshbtn);
        b1.setOnClickListener(this);
        pref = getApplicationContext().getSharedPreferences("dataPref", MODE_PRIVATE);
        editor = pref.edit();
        String Visitlisttemp = pref.getString("VisitList", null);
        String Summitlisttemp = pref.getString("SummitList", null);

        if (checkinternet()) {

            b1.setText("Loading....");
            new HttpAsyncTask().execute("https://liteartapps.com/android_production/others/PMvisit/news_updates/pmvisit.json");

        }
        else
        {
            b1.setText(Html.fromHtml("Click here to <u>"+"Refresh Page"+"</u>"));
            Toast.makeText(getApplicationContext(),"No internet connection.Turn on your mobile data",Toast.LENGTH_LONG).show();
        }

    }

    public boolean checkinternet()
    {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        return connected;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();


            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    @Override
    public void onClick(View view) {

        finish();
        startActivity(getIntent());
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            try {

                JSONObject obj = new JSONObject(result);
                JSONArray array = obj.getJSONArray("pmvisit");
                Log.e("arrray= ", array.toString());
                visitslist = new ArrayList<>();
                summitlist = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {


                    JSONObject jsn = array.getJSONObject(i);
                    if(jsn.get("type").toString().equalsIgnoreCase("Visits"))
                    {
                        PMVisit v1=new PMVisit(jsn.getString("place_of_visit_or_summit").trim(),jsn.getString("year ").trim(),jsn.getString("period_of_visit").trim(),jsn.getString("visit_details ").trim(),jsn.getString("expenses").trim(),jsn.getString("more_updates ").trim());
                        visitslist.add(v1);

                    }
                    else
                    {
                        PMVisit v2=new PMVisit(jsn.getString("place_of_visit_or_summit").trim(),jsn.getString("year ").trim(),jsn.getString("period_of_visit").trim(),jsn.getString("visit_details ").trim(),jsn.getString("expenses").trim(),jsn.getString("more_updates ").trim());
                        summitlist.add(v2);
                    }

                    /*Log.e("Visitlist loop= ", visitslist.toString());
                    Log.e("Summitlist loop = ", summitlist.toString());*/
                }

                Gson gson = new Gson();
                editor.putString("VisitList", gson.toJson(visitslist));
                editor.putString("SummitList", gson.toJson(summitlist));

                editor.commit();
                Log.e("Visitlist after commit=",pref.getString("VisitList",null));
                Log.e("Summit after commit=",pref.getString("SummitList",null));

                startActivity(new Intent(getApplicationContext(),MainActivityTab.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
        }
    }
}
