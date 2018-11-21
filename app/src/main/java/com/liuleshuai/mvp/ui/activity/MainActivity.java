package com.liuleshuai.mvp.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liuleshuai.common.base.BaseActivity;
import com.liuleshuai.common.base.BaseFragment;
import com.liuleshuai.common.tools.BottomNavigationViewHelper;
import com.liuleshuai.mvp.R;
import com.liuleshuai.mvp.app.Constants;
import com.liuleshuai.mvp.model.MainContract;
import com.liuleshuai.mvp.presenter.MainPresenter;
import com.liuleshuai.mvp.ui.fragment.FindFragment;
import com.liuleshuai.mvp.ui.fragment.FriendFragment;
import com.liuleshuai.mvp.ui.fragment.SearchDialogFragment;
import com.liuleshuai.mvp.ui.fragment.WeChatFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author liukuo
 */
@Route(path = "/activity/main")
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.navigation_view)
    BottomNavigationView bottomNavigationView;

    Boolean isStop = false;

    private List<BaseFragment> fragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.extra_title_bar_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_item) {
            SearchDialogFragment searchDialogFragment = new SearchDialogFragment();
            searchDialogFragment.show(getSupportFragmentManager(), "SearchDialogFragment");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initEventAndData() {
        initData();
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mViewPager.addOnPageChangeListener(onPageListener);
        bottomNavigationView.setOnNavigationItemSelectedListener(onItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        final Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (!isStop) {
                    Log.d("liukuo", "hahahaha");
                    handler.postDelayed(this, 5000);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        };
        handler.post(r);

    }

    @Override
    protected void onStop() {
        super.onStop();
        isStop = true;
    }

    private ViewPager.OnPageChangeListener onPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener onItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetToDefaultIcon();//重置到默认不选中图片
            switch (item.getItemId()) {
                case R.id.wechat:
                    mViewPager.setCurrentItem(Constants.FIRST);
                    item.setIcon(R.mipmap.icon_project_not_selected);
                    break;
                case R.id.communcation:
                    mViewPager.setCurrentItem(Constants.SECOND);
                    break;
                case R.id.find:
                    mViewPager.setCurrentItem(Constants.THIRD);
                    break;
                case R.id.mine:
                    mViewPager.setCurrentItem(Constants.FOURTH);
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private void resetToDefaultIcon() {
        MenuItem weChat = bottomNavigationView.getMenu().findItem(R.id.wechat);
        weChat.setIcon(R.mipmap.icon_talk);
        MenuItem communcation = bottomNavigationView.getMenu().findItem(R.id.communcation);
        communcation.setIcon(R.mipmap.icon_knowledge_hierarchy_not_selected);
        MenuItem find = bottomNavigationView.getMenu().findItem(R.id.find);
        find.setIcon(R.mipmap.icon_navigation_not_selected);
        MenuItem mine = bottomNavigationView.getMenu().findItem(R.id.mine);
        mine.setIcon(R.mipmap.icon_home_pager_not_selected);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        WeChatFragment fragment1 = WeChatFragment.getInstance(null, null);
        fragmentList.add(fragment1);
        FriendFragment fragment2 = FriendFragment.getInstance(null, null);
        fragmentList.add(fragment2);
        FindFragment fragment3 = FindFragment.getInstance(null, null);
        fragmentList.add(fragment3);
        WeChatFragment fragment4 = WeChatFragment.getInstance(null, null);
        fragmentList.add(fragment4);

    }
}
