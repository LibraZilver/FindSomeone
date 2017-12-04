package com.example.libra.findsomeone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.libra.findsomeone.AndroidVisionQRReader.QRActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    public static final int QR_REQUEST = 111;
    private int mCurrentTab;

    private List<ImageView> mTabBar;
    @BindView(R.id.home_tab)
    ImageView mHomeTab;
    @BindView(R.id.history_tab)
    ImageView mHistoryTab;
    @BindView(R.id.event_tab)
    ImageView mEventTab;
    @BindView(R.id.setting_tab)
    ImageView mSettingTab;

    @BindView(R.id.qr_scanner_button)
    ImageView mQRScannerButton;

    private List<Fragment> mPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeTabBar();
        initializeTabFragmentLayout();
        initializeQRScannerButton();
    }

    private void initializeTabBar() {
        mTabBar = new ArrayList<>();
        mTabBar.add(mHomeTab);
        mTabBar.add(mHistoryTab);
        mTabBar.add(mEventTab);
        mTabBar.add(mSettingTab);
    }


    private void initializeQRScannerButton() {
        final Animation animationEnlarge = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_grow);
        final Animation animationShrink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_shrink);

        mQRScannerButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        animationEnlarge.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (event.getAction() != MotionEvent.ACTION_DOWN && MotionEvent.ACTION_MASK != MotionEvent.ACTION_DOWN)
                                    mQRScannerButton.startAnimation(animationShrink);
                            }
                        });
                        animationShrink.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                                Intent qrScanIntent = new Intent(getApplicationContext(), QRActivity.class);
                                startActivityForResult(qrScanIntent, QR_REQUEST);
                            }
                        });

                        animationEnlarge.setFillAfter(true);
                        mQRScannerButton.startAnimation(animationEnlarge);

                        break;
                    case MotionEvent.ACTION_UP:
                        if (animationEnlarge.hasEnded())
                            mQRScannerButton.startAnimation(animationShrink);
                        break;
                }

                return false;
            }

        });
    }

    private void initializeTabFragmentLayout() {
        mPages = new ArrayList<>();
        mPages.add(HomeFragment.newInstance());
        mPages.add(HistoryFragment.newInstance());
        mPages.add(EventFragment.newInstance());
        mPages.add(SettingFragment.newInstance());

        setSelectedTab(mCurrentTab = 0);
    }

    private void setSelectedTab(int selectedTab) {
        mTabBar.get(mCurrentTab).setColorFilter(ContextCompat.getColor(this, R.color.colorUnSelectedTab));
        mTabBar.get(selectedTab).setColorFilter(ContextCompat.getColor(this, R.color.colorSelectedTab));
        displayView(mCurrentTab = selectedTab);
    }

    private void displayView(int selectedTab) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, mPages.get(selectedTab)).commit();
    }

    public void onClickTabBar(View v) {
        int selectedTab = Integer.parseInt(v.getTag().toString());
        if (mCurrentTab == selectedTab) return;
        setSelectedTab(selectedTab);
    }

//    private void setAnimationGrowShrink(final ImageView imgV) {
//
//        imgV.startAnimation(animationShrink);
//
//
//        animationShrink.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                imgV.startAnimation(animationEnlarge);
//            }
//        });
//
//    }

    public void onClickQRScanner(View v) {
//        setAnimationGrowShrink(mQRScannerButton);

//        mQRScannerButton.setColorFilter(ContextCompat.getColor(this, R.color.colorSelectedTab));
//        Intent qrScanIntent = new Intent(this, QRActivity.class);
//        startActivityForResult(qrScanIntent, QR_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QR_REQUEST) {
            String result;
            if (resultCode == RESULT_OK) {
                result = data.getStringExtra(QRActivity.EXTRA_QR_RESULT);
            } else {
                result = "Error";
            }
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }


}
