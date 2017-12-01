package com.example.libra.findsomeone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    private enum Tab {Home, History, QRScanner, Event, Setting}


    private Tab mCurrentTab;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

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
    }

    private void initializeTabBar() {
        mTabBar = new ArrayList<>();
        mTabBar.add(mHomeTab);
        mTabBar.add(mHistoryTab);
        mTabBar.add(mQRScannerTab);
        mTabBar.add(mEventTab);
        mTabBar.add(mSettingTab);

        setSelectedTab(mCurrentTab = Tab.Home);
    }

    private void initializeTabFragmentLayout() {
        mPages = new ArrayList<>();


    }

    public void onClickTabBar(View v) {
        switch (v.getId()) {
            case R.id.home_tab: {
                if (mCurrentTab == Tab.Home) return;
                setSelectedTab(Tab.Home);
            }
            break;
            case R.id.history_tab: {
                if (mCurrentTab == Tab.History) return;
                setSelectedTab(Tab.History);
            }
            break;
            case R.id.qr_scanner_tab: {
                if (mCurrentTab == Tab.QRScanner) return;
                setSelectedTab(Tab.QRScanner);
            }
            break;
            case R.id.event_tab: {
                if (mCurrentTab == Tab.Event) return;
                setSelectedTab(Tab.Event);
            }
            break;
            case R.id.setting_tab: {
                if (mCurrentTab == Tab.Setting) return;
                setSelectedTab(Tab.Setting);
            }
            break;
        }
    }

    private void setSelectedTab(Tab selectedTab) {
        mTabBar.get(mCurrentTab.ordinal()).setColorFilter(ContextCompat.getColor(this, R.color.colorUnSelectedTab));
        mTabBar.get(selectedTab.ordinal()).setColorFilter(ContextCompat.getColor(this, R.color.colorSelectedTab));
        mCurrentTab = selectedTab;
    }
}
