package com.liteart.apps.modivsits;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterButtonSummit extends RecyclerView.Adapter<AdapterButtonSummit.MyViewHolder> {



    @Override
public AdapterButtonSummit.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View photoView = inflater.inflate(R.layout.activity_cardview_button, parent, false);
   AdapterButtonSummit.MyViewHolder viewHolder = new AdapterButtonSummit.MyViewHolder(photoView);
    return viewHolder;
}

    @Override
    public void onBindViewHolder(AdapterButtonSummit.MyViewHolder holder, int position) {

        holder.b1.setText(dataString.get(position));
    }

    @Override
    public int getItemCount() {
        return dataString.size();
    }
    private  ArrayList<String> dataString=new ArrayList<String>();
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button b1;

        public MyViewHolder(View itemView) {

            super(itemView);
            b1 = (Button)itemView.findViewById(R.id.yearbtn);
            b1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                String str=dataString.get(position);
               // Toast.makeText(mContext, "year"+str,Toast.LENGTH_LONG).show();
                Log.e("excp","hi"+str);
                Intent i=new Intent(mContext,DisplayDetails.class);
                i.putExtra("year",str);
                i.putExtra("type","summit");
                mContext.startActivity(i);
            }
        }
    }



    public AdapterButtonSummit(Context context, ArrayList<String> dataString) {
        mContext = context;
       this.dataString=dataString;
    }
}


