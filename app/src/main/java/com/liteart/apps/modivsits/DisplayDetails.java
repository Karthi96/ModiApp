
package com.liteart.apps.modivsits;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class DisplayDetails extends AppCompatActivity {

    String str;
    InterstitialAd mInterstitialAd;

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
    public void displayInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mInterstitialAd = new InterstitialAd(DisplayDetails.this);
                mInterstitialAd.setAdUnitId(getString(R.string.interid1));
                AdRequest adRequest = new AdRequest.Builder()
                        .build();
                mInterstitialAd.loadAd(adRequest);
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override

                    public void onAdLoaded() {
                        displayInterstitial();
                    }
                });

            }
        } , 8000);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1439926476174790~8401272429");
        AdView mAdView = (AdView)findViewById(R.id.adView_displaypage);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        str=getIntent().getStringExtra("year");
        String temptype=getIntent().getStringExtra("type");
        setTitle(Html.fromHtml("<font color='#FFFFFF'>"+temptype.substring(0,1).toUpperCase()+temptype.substring(1).toLowerCase()+" [ "+str+" ] "+" </font>"));

        try {
            SharedPreferences pref = getSharedPreferences("dataPref", Context.MODE_PRIVATE);
            Log.e("Visitlist fragment=",pref.getString("VisitList",null));
            Log.e("Summit Fragment=",pref.getString("SummitList",null));
            Gson gson = new Gson();
            String visit = pref.getString("VisitList",null);
            String summit = pref.getString("SummitList",null);



            Type type = new TypeToken<ArrayList<PMVisit>>() {
            }.getType();
            ArrayList<PMVisit> visitList;
            if(temptype.equalsIgnoreCase("visit"))
            {
                visitList = gson.fromJson(visit, type);
            }
            else
            {
                visitList = gson.fromJson(summit, type);
            }


            ArrayList<PMVisit> result=new ArrayList<PMVisit>();

            for (PMVisit item : visitList) {

                // If String is not in set, add it to the list and the set.
                if ((item.getYear().toString()).equalsIgnoreCase(str)) {


                    PMVisit v2=new PMVisit(item.getPlaceOfVisitOrSummit(),item.getYear(),item.getPeriodOfVisit(),item.getVisitDetails(),item.getExpenses(),item.getMoreUpdates());
                    result.add(v2);

                }

                //  result.add(item.getYear().toString());
            }

            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view2);
            recyclerView.setHasFixedSize(true);


            AdapterDisplayDetail adapter = new AdapterDisplayDetail(getApplication(),result,str);
            LinearLayoutManager llm = new LinearLayoutManager(getApplication());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.e("excp",e.toString());
        }


    }
}
