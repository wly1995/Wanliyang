package com.wanliyang.wanliyang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wanliyang.wanliyang.base.BaseFragment;
import com.wanliyang.wanliyang.fragment.CartFragment;
import com.wanliyang.wanliyang.fragment.CommunityFragment;
import com.wanliyang.wanliyang.fragment.HomeFragment;
import com.wanliyang.wanliyang.fragment.TypeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    /**
     * 记录位置
     */
    private int position;
    /**
     * 缓存的fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //初始化fragment
        initFragment();
        //初始化监听
        initListener();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new CartFragment());
    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                }
                Fragment currentFragment =  fragments.get(position);
                switchFragment(currentFragment);
            }
        });
//        rgMain.check(R.id.rb_home);

        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(Fragment currentFragment) {
        if (tempFragment!=currentFragment){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!currentFragment.isAdded()){
                //先隐藏缓存的fragment
                if (tempFragment!=null){//校验
                    ft.hide(tempFragment);
                }
                //在添加当前的
                ft.add(R.id.fl_main,currentFragment,null);
            } else{
                if (tempFragment!=null){//校验
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
            //事务进行提交
            ft.commit();
            //在这进行缓存（把当前的fragment赋值给缓存）
            tempFragment = currentFragment;
        }
    }
    private long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
