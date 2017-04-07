package com.codechris.bucketdrops.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.codechris.bucketdrops.R;

/**
 * Created by Christopher on 4/7/2017.
 */

public class Divider extends RecyclerView.ItemDecoration {
    public static final String TAG= "codeChris";
    public Drawable mDivider;
    public int mOrientation;

    public Divider(Context context, int orientation){
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider);
        if(orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("This Iteam Decoration can be used only with a RecyclerView that uses a LinearLayoutManager with verticle orientation");
        }
        mOrientation=orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawHorizontalDivider(c,parent,state);
        }
    }

    private void drawHorizontalDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left, right, top, bottom;
        left=parent.getPaddingLeft();
        right=parent.getWidth() - parent.getPaddingRight();
        int count = parent.getChildCount();
        for(int i=0;i <count; i++){
            if(AdapterDrops.FOOTER != parent.getAdapter().getItemViewType(i)){
                View current = parent.getChildAt(i);
                RecyclerView.LayoutParams params =(RecyclerView.LayoutParams) current.getLayoutParams();
                top= current.getTop()- params.topMargin;
                bottom = top+ mDivider.getIntrinsicHeight();
                mDivider.setBounds(left,top,right,bottom);
                mDivider.draw(c);
                Log.d(TAG,"drawHorizontaleDiver: " +" l:"+left +" r:"+ right +" t:"+ top +" b:"+ bottom );
            }

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0,0,0, mDivider.getIntrinsicHeight());
        }
    }
}
