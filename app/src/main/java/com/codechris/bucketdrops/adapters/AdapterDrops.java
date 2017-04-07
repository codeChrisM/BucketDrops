package com.codechris.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codechris.bucketdrops.R;
import com.codechris.bucketdrops.beans.Drop;

import java.util.ArrayList;

import io.realm.RealmResults;

import static android.media.CamcorderProfile.get;

/**
 * Created by Christopher on 3/21/2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM =0;
    public static final int FOOTER=1;

    private LayoutInflater mInflater;
    private RealmResults<Drop> mResults;
    public static final String TAG = "codeChris Tag";



    public AdapterDrops(Context context, RealmResults<Drop> results){
        mInflater = LayoutInflater.from(context);
        update(results);

    }

    public void update( RealmResults<Drop> results){
        mResults = results;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOTER){
            View view = mInflater.inflate(R.layout.footer, parent, false);
            return new FooterHolder(view);
        }else{
            View view = mInflater.inflate(R.layout.row_drop, parent, false);
            return new DropHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DropHolder){
            DropHolder dropHolder= (DropHolder) holder;
            Drop drop = mResults.get(position);
            dropHolder.mTextWhat.setText(drop.getWhat());
        }
    }

    @Override
    public int getItemCount() {
        return mResults.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mResults == null || position < mResults.size()){
            return ITEM;
        }else{
            return FOOTER;
        }

    }

    public static class DropHolder extends RecyclerView.ViewHolder{

        TextView mTextWhat;
        public DropHolder(View itemView) {
            super(itemView);
            mTextWhat = (TextView) itemView.findViewById(R.id.tv_what);
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder{

        Button mBtnAdd;
        public FooterHolder(View itemView) {
            super(itemView);
            mBtnAdd = (Button) itemView.findViewById(R.id.btn_footer);
        }
    }
}
