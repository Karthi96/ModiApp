package com.liteart.apps.modivsits;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class SummitFragment extends Fragment{




    public SummitFragment() {


        // Required empty public constructor
    }


   /* @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summit, container, false);


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        MobileAds.initialize(getActivity(), "ca-app-pub-1439926476174790~8401272429");
        AdView mAdView = (AdView)getActivity().findViewById(R.id.adView_summit);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        try {
            SharedPreferences pref = getActivity().getSharedPreferences("dataPref", Context.MODE_PRIVATE);
            Log.e("Visitlist fragment=",pref.getString("VisitList",null));
            Log.e("Summit Fragment=",pref.getString("SummitList",null));
            Gson gson = new Gson();
            String summit = pref.getString("SummitList",null);

            Type type = new TypeToken<ArrayList<PMVisit>>() {
            }.getType();
            ArrayList<PMVisit> summitList = gson.fromJson(summit, type);


            // Store unique items in result.
            ArrayList<String> result = new ArrayList<>();

            // Record encountered Strings in HashSet.
            HashSet<String> set = new HashSet<>();

            // Loop over argument list.
            for (PMVisit item : summitList) {

                // If String is not in set, add it to the list and the set.
                if (!set.contains(item.getYear().toString())) {
                    result.add(item.getYear().toString());
                    set.add(item.getYear().toString());
                    Log.e("SummitList year= ",item.getYear().toString());

                }

                //  result.add(item.getYear().toString());
            }

            Collections.sort(result);
            Collections.reverse(result);

            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view1);
            recyclerView.setHasFixedSize(true);


            AdapterButtonSummit adapter = new AdapterButtonSummit(getActivity(),result);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
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