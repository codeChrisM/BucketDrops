package com.codechris.bucketdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codechris.bucketdrops.adapters.AdapterDrops;
import com.codechris.bucketdrops.beans.Drop;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class ActivityMain extends AppCompatActivity {

    Toolbar mtoolbar;
    Button mBtnAdd;
    RecyclerView mRecyler;
    Realm mRealm;
    RealmResults<Drop> mResults;
    AdapterDrops mAdaptor;
    private View.OnClickListener mBtnAddListener= new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            showDialogAdd();
        }
    };

    private RealmChangeListener mChangeListener = new RealmChangeListener() {

        @Override
        public void onChange(Object element) {
            String TAG = "MainActivity";
            Log.d(TAG, "on change: Called ");
            mAdaptor.update(mResults);
            Log.d(TAG, "on change: completed ");

        }
    };

    private void showDialogAdd() {
        DialogAdd dialog= new DialogAdd();
        dialog.show(getSupportFragmentManager(),"Add");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm=Realm.getDefaultInstance();
        mResults = mRealm.where(Drop.class).findAllAsync();
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        //---following video
        //mRealm=Realm.getDefaultInstance();
        //RealmResults<Drop> results = mRealm.where(Drop.class).findAll();
        //----end--
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mRecyler=(RecyclerView) findViewById(R.id.rv_drops);
        //this was changed
        mAdaptor = new AdapterDrops(this, mResults);
        mRecyler.setAdapter(mAdaptor);
        //----
        mBtnAdd.setOnClickListener(mBtnAddListener);
        setSupportActionBar(mtoolbar);
        initBackgroundImage();
    }

    private void initBackgroundImage() {
        ImageView background = (ImageView) findViewById(R.id.iv_background);
        Glide.with(this)
                .load(R.drawable.background)
                .centerCrop()
                .into(background);


    }

    @Override
    protected void onStart() {
        super.onStart();
        String TAG = "MainActivity";
        Log.d(TAG, " onStart: value of mResults: " + String.valueOf(mResults));
        mResults.addChangeListener(mChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //String TAG = "MainActivity";
        //Log.d(TAG, "onstop: ");
        mResults.removeChangeListener(mChangeListener);
    }
}
