package com.ttl.ritz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    FirebaseAuth mAuth;

    ImageButton btnTask, btnProject, btnCli,btnProfile;
    private String[] urls = new String[]{
            "https://www.dumblittleman.com/wp-content/uploads/2016/11/hiring-the-right-people-696x409.jpg","https://hiring.workopolis.com/wp-content/uploads/sites/3/2017/02/iStock-490396144.jpg",
            "https://betanews.com/wp-content/uploads/2015/08/Startup-people-talking-e1438595959281.jpg","https://www.smartcompany.com.au/wp-content/uploads/2016/11/startup-team.jpg"
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnTask = findViewById(R.id.btntask);
        btnProject = findViewById(R.id.btnproject);
        btnCli = findViewById(R.id.btnClient);
        btnProfile = findViewById(R.id.btnprofile);
        btnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,taskActivity.class));
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,profileActivity.class));
            }
        });
        btnProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,projectActivity.class));
            }
        });
        btnCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,clientActivity.class));
            }
        });
//        if(FirebaseAuth.getInstance().getCurrentUser() == null){
//            startActivity(new Intent(MainActivity.this,loginActivity.class));
//            finish();
//        }

    }
    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this, urls));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = urls.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}