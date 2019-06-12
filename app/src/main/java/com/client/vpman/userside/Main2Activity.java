package com.client.vpman.userside;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alespero.expandablecardview.Utils;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Main2Activity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener
{

    private ImageView imageView;
    private TextView tname, money, date, time, desc;
    private boolean isHideToolbarView = false;
    private FrameLayout date_behavior;
    private LinearLayout titleAppbar;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String NameHolder1, DescHold1,DateHold1,TimeHold1,MoneyHold1,mimg;
    private Button participate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((AppBarLayout.BaseOnOffsetChangedListener) this);

        date_behavior = findViewById(R.id.date_behavior);
        titleAppbar = findViewById(R.id.title_appbar);
        imageView = findViewById(R.id.backdrop);
        tname=findViewById(R.id.t_name);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        desc=findViewById(R.id.description);
        money=findViewById(R.id.money1);
        participate=findViewById(R.id.participate);

        Intent intent=getIntent();
        NameHolder1=intent.getStringExtra("nameHolder1");
        DescHold1=intent.getStringExtra("descHold1");
        DateHold1=intent.getStringExtra("dateHold1");
        TimeHold1=intent.getStringExtra("timeHold1");
        MoneyHold1=intent.getStringExtra("moneyHold1");
        mimg=intent.getStringExtra("image");

        Glide.with(this).load(mimg).into(imageView);

        tname.setText(NameHolder1);
        date.setText(DateHold1);
        time.setText(TimeHold1);
        desc.setText(DescHold1);
        money.setText(MoneyHold1);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(i) / (float) maxScroll;
        if (percentage==1f && isHideToolbarView)
        {
            date_behavior.setVisibility(View.GONE);
            titleAppbar.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;
        }

        else if (percentage < 1f && !isHideToolbarView)
        {
            date_behavior.setVisibility(View.VISIBLE);
            titleAppbar.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }
}
