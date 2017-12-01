package com.example.libra.findsomeone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private int mCurrentTab;

    private List<ImageView> mTabBar;
    @BindView(R.id.home_tab)
    ImageView mHomeTab;
    @BindView(R.id.history_tab)
    ImageView mHistoryTab;
    @BindView(R.id.qr_scanner_tab)
    ImageView mQRScannerTab;
    @BindView(R.id.event_tab)
    ImageView mEventTab;
    @BindView(R.id.setting_tab)
    ImageView mSettingTab;

    private List<Fragment> mPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeTabBar();
        initializeTabFragmentLayout();
    }

    private void initializeTabBar() {
        mTabBar = new ArrayList<>();
        mTabBar.add(mHomeTab);
        mTabBar.add(mHistoryTab);
        mTabBar.add(mQRScannerTab);
        mTabBar.add(mEventTab);
        mTabBar.add(mSettingTab);
    }

    private void initializeTabFragmentLayout() {
        mPages = new ArrayList<>();
        mPages.add(HomeFragment.newInstance());
        mPages.add(HistoryFragment.newInstance());
        mPages.add(QRScannerFragment.newInstance());
        mPages.add(EventFragment.newInstance());
        mPages.add(SettingFragment.newInstance());

        setSelectedTab(mCurrentTab = 0);
    }

    private void setSelectedTab(int selectedTab) {
        mTabBar.get(mCurrentTab).setColorFilter(ContextCompat.getColor(this, R.color.colorUnSelectedTab));
        mTabBar.get(selectedTab).setColorFilter(ContextCompat.getColor(this, R.color.colorSelectedTab));
        displayView(mCurrentTab = selectedTab);
    }

    private void displayView(int indexSelectedTab) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, mPages.get(indexSelectedTab)).commit();
    }

    public void onClickTabBar(View v) {
        int indexSelectedTab = Integer.parseInt(v.getTag().toString());
        if (mCurrentTab == indexSelectedTab) return;
        setSelectedTab(indexSelectedTab);
    }


}
