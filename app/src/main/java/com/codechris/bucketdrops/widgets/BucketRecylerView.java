package com.codechris.bucketdrops.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;

import com.codechris.bucketdrops.extras.Util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Christopher on 3/31/2017.
 */

public class BucketRecylerView extends RecyclerView {

    private List<View> mNonEmptyViews = Collections.emptyList();
    private List<View> mEmptyViews = Collections.emptyList();

    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }
    };

    private void toggleViews() {
        if(getAdapter()!=null && !mEmptyViews.isEmpty() && !mNonEmptyViews.isEmpty()){
            if(getAdapter().getItemCount()==1){
                //show all the empty views
                Util.showViews(mEmptyViews);

                //hide recycler view
                setVisibility(View.GONE);

                //hide all the views which are meant to be hidden
                Util.hideViews(mNonEmptyViews);
            }else{
                //hide all the empty views
                Util.showViews(mNonEmptyViews);

                //show the recycler view
                setVisibility(View.VISIBLE);

                //hide all the views which are mean to be hidden
                Util.hideViews(mEmptyViews);
            }
        }
    }

    public BucketRecylerView(Context context) {
        super(context);
    }

    public BucketRecylerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BucketRecylerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(adapter != null){
            adapter.registerAdapterDataObserver(mObserver);
        }
        mObserver.onChanged();
    }

    public void hideIfEmpty(View ...views) {
        mNonEmptyViews = Arrays.asList(views);
    }

    public void showIfEmpty(View ...emptyViews) {
        mEmptyViews = Arrays.asList(emptyViews);
    }
}
