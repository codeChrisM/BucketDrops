package com.codechris.bucketdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codechris.bucketdrops.adapters.AdapterDrops;

import io.realm.Realm;

public class ActivityMain extends AppCompatActivity {

    Toolbar mtoolbar;
    Button mBtnAdd;
    RecyclerView mRecyler;
    Realm mRealm;
    private View.OnClickListener mBtnAddListener= new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            showDialogAdd();
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
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mRecyler=(RecyclerView) findViewById(R.id.rv_drops);

        mRecyler.setAdapter(new AdapterDrops(this));
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


}
