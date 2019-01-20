package com.escrow.smartexamination.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.escrow.smartexamination.Activity.Paper;
import com.escrow.smartexamination.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mqnum ;  //=new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mqnum) {
        this.mqnum = mqnum;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_questionlist,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.qnum.setText(mqnum.get(position));
        ImageView imageView= holder.parentLayout.findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Log.d(TAG, "Position" + mqnum.get(position));
                //Utils.RecycleViewClick=true;
                String str=mqnum.get(position);
                String sub=str.substring(9);
                Log.d(TAG, "sub "+sub+"\nstr"+str);
                Intent intent= new Intent(mContext,Paper.class);
                intent.putExtra("Question", sub);
                Log.d(TAG, "mContext"+intent);
                mContext.startActivity(intent);


                Toast.makeText(mContext,mqnum.get(position),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mqnum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView qnum;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            qnum=itemView.findViewById(R.id.q_number);
            parentLayout=itemView.findViewById(R.id.Parent_layout);
        }
    }
}

