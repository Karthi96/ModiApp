package com.liteart.apps.modivsits;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterDisplayDetail extends RecyclerView.Adapter<AdapterDisplayDetail.MyViewHolder> {

    private  ArrayList<PMVisit> dataString=new ArrayList<PMVisit>();
    private Context mContext;
    private  String str;

    @Override
    public AdapterDisplayDetail.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.activity_cardview_displaydetail, parent, false);
       AdapterDisplayDetail.MyViewHolder viewHolder = new AdapterDisplayDetail.MyViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterDisplayDetail.MyViewHolder holder, int position) {


            String temp=dataString.get(position).getPlaceOfVisitOrSummit().toLowerCase();
            Log.e("string value of =",temp);

            if(dataString.get(position).getPeriodOfVisit().toString().equalsIgnoreCase("null"))
            {
                holder.b1.setText(temp.substring(0,1).toUpperCase()+""+temp.substring(1).toLowerCase());
            }
            else
            {
                holder.b1.setText(temp.substring(0,1).toUpperCase()+temp.substring(1).toLowerCase()+" [ "+ dataString.get(position).getPeriodOfVisit() +" ]");

            }
            if(dataString.get(position).getVisitDetails().toString().equalsIgnoreCase("null"))
            {
                holder.t1.setText("Visit Details: There is no updated details available for this visit.We will update you soon... ");
            }
            else {

                holder.t1.setText("Visit Details: "+ dataString.get(position).getVisitDetails());

            }
        holder.t2.setText("More Updates:  "+ dataString.get(position).getMoreUpdates());
        holder.t3.setText("Expenses: Rs "+ dataString.get(position).getExpenses());

    }

    @Override
    public int getItemCount() {
        Log.e("count=",""+dataString.size());
        return dataString.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView b1;
        private TextView t1,t2,t3,t4,t5;


        public MyViewHolder(View itemView) {

            super(itemView);
            b1 = (TextView) itemView.findViewById(R.id.displaybtn);



            b1.setOnClickListener(this);
            t1=(TextView)itemView.findViewById(R.id.tn_desc);
            t2=(TextView)itemView.findViewById(R.id.tx_readmore_onclcik);
            t3=(TextView)itemView.findViewById(R.id.tx_expense_onclcik);
            t4=(TextView)itemView.findViewById(R.id.tn_readmore);
            t5=(TextView)itemView.findViewById(R.id.tn_expense);
           t4.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    if(t2.getVisibility()==view.VISIBLE)
                    {
                        t2.setVisibility(view.GONE);
                    }
                    else{
                        t2.setVisibility(view.VISIBLE);
                    }

                }
            });
            t5.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    if(t3.getVisibility()==view.VISIBLE)
                    {
                        t3.setVisibility(view.GONE);
                    }
                    else{
                        t3.setVisibility(view.VISIBLE);
                    }
                }
            });
            t1.setVisibility(itemView.GONE);
            t2.setVisibility(itemView.GONE);
            t3.setVisibility(itemView.GONE);
            t4.setVisibility(itemView.GONE);
            t5.setVisibility(itemView.GONE);

        }

        @Override
        public void onClick(View view) {


            if(t1.getVisibility()==view.VISIBLE)
            {
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                t5.setVisibility(View.GONE);
            }
            else
                {
                t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.GONE);
                    t3.setVisibility(View.GONE);
                t4.setVisibility(View.VISIBLE);
                t5.setVisibility(View.VISIBLE);
            }


        }
        public void readmore(View v)
        {
            t2.setVisibility(v.VISIBLE);
        }
        public void expense(View v)
        {
            t3.setVisibility(v.VISIBLE);
        }


       /* @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                String str=dataString.get(position);
                Toast.makeText(mContext, "year"+str,Toast.LENGTH_LONG).show();
                Log.e("excp","hi"+str);
                Intent i=new Intent(mContext,DisplayDetails.class);
                i.putExtra("year",str);
                mContext.startActivity(i);
            }
        }*/
    }



    public AdapterDisplayDetail(Context context, ArrayList<PMVisit> dataString,String str) {
        mContext = context;
        this.dataString=dataString;
        this.str=str;
    }
}
