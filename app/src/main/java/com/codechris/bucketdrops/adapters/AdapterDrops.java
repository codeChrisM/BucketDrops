package com.codechris.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codechris.bucketdrops.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Christopher on 3/21/2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {
    private LayoutInflater mInflator;
    private ArrayList<String> mIteams = new ArrayList<>();
    public static  final String TAG = "Test Text";


    public AdapterDrops(Context context){
        mInflator= LayoutInflater.from(context);
        mIteams = generateValues();

    }
    public static ArrayList<String> generateValues(){
        ArrayList<String> dummyValues = new ArrayList<>();
        for (int i= 1; i < 101; i++){
            dummyValues.add("Item " + i);
        }
        return dummyValues;
    }

    @Override
    public DropHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.row_drop, parent, false);
        DropHolder holder = new DropHolder(view);
        Log.d(TAG, "onCreateViewHolder: ");
        return holder;
    }

    @Override
    public void onBindViewHolder(DropHolder holder, int position) {
        holder.mTextWhat.setText(mIteams.get(position));
        Log.d(TAG, "onBindViewHolder: " +  position);


    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class DropHolder extends RecyclerView.ViewHolder{

        TextView mTextWhat;
        public DropHolder(View itemView) {
            super(itemView);
            mTextWhat = (TextView) itemView.findViewById(R.id.tv_what);
        }
    }
}