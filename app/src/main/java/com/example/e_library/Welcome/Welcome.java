package com.example.e_library.Welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_library.Adapter.SliderAdapter;
import com.example.e_library.Beranda.Beranda;
import com.example.e_library.BluePrint.TranslucentOptions;
import com.example.e_library.Login;
import com.example.e_library.Notifikasi.Notifikasi;
import com.example.e_library.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class Welcome extends AppCompatActivity {

    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    TextView skipButton;
    Animation animFadein, animFadeout;
    MaterialButton iya, tidak;
    MaterialCardView welcome_pop_up;

    TextView[] dots;
    SliderAdapter sliderAdapter;
    SharedPreferences SessionStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new TranslucentOptions().onlyTransparentStatusBar(this.getWindow(), Welcome.this);

        mSLideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.indicator_layout);
        skipButton = findViewById(R.id.skipButton);

        SessionStorage = getSharedPreferences("SESSION", MODE_PRIVATE);

        if (!SessionStorage.getString("Tokens", "").equals("")){
            if (SessionStorage.getString("Activity", "").equals("Notifikasi")){
                startActivity(new Intent(Welcome.this, Notifikasi.class));
            } else {
                startActivity(new Intent(Welcome.this, Beranda.class));
            }
        }

        animFadein = AnimationUtils.loadAnimation(Welcome.this,
                R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(Welcome.this,
                R.anim.fade_out);

        welcome_pop_up = findViewById(R.id.welcome_pop_up);
        iya = findViewById(R.id.iya);
        tidak = findViewById(R.id.tidak);

        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome_pop_up.startAnimation(animFadeout);
                welcome_pop_up.setVisibility(View.GONE);
            }
        });

        iya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome_pop_up.startAnimation(animFadeout);
                welcome_pop_up.setVisibility(View.GONE);

                Intent Intent = new Intent(Welcome.this, Login.class);
                startActivity(Intent);
                overridePendingTransition(R.anim.enter_bottom_to_top, R.anim.stay_position);
                finish();
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome_pop_up.setVisibility(View.VISIBLE);
                welcome_pop_up.startAnimation(animFadein);
            }
        });

        sliderAdapter = new SliderAdapter(this);

        mSLideViewPager.setAdapter(sliderAdapter);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpindicator(int position){

        dots = new TextView[2];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return mSLideViewPager.getCurrentItem() + i;
    }

}