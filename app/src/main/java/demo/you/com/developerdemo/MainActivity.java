package demo.you.com.developerdemo;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.you.com.developerdemo.fragment.AccountFragment;
import demo.you.com.developerdemo.fragment.FeatureFragment;
import demo.you.com.developerdemo.fragment.MessageFragment;
import demo.you.com.developerdemo.fragment.SearchFragment;


public class MainActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public RadioGroup tabs;
    public List<Fragment> list=new ArrayList<>();
    public MyPagerAdapter adapter;
    public RadioButton rb1,rb2,rb3,rb4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    //初始化操作
    public void init(){
        viewPager= (ViewPager) findViewById(R.id.tab_content);
        list=getList();
        adapter=new MyPagerAdapter(getSupportFragmentManager(),list);
        tabs= (RadioGroup) findViewById(R.id.rg_tabs);
        rb1= (RadioButton) findViewById(R.id.feature);
        rb2= (RadioButton) findViewById(R.id.message);
        rb3= (RadioButton) findViewById(R.id.search);
        rb4= (RadioButton) findViewById(R.id.account);
        tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.feature:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.message:
                        viewPager.setCurrentItem(1);

                        break;
                    case R.id.search:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.account:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
        tabs.check(R.id.feature);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new PageChange());
        viewPager.setOffscreenPageLimit(4);
    }
    //获取Fragment列表
    public List<Fragment> getList(){
        List<Fragment> fragmentList=new ArrayList<>();
        FeatureFragment featureFragment=new FeatureFragment();
        MessageFragment messageFragment=new MessageFragment();
        SearchFragment searchFragment=new SearchFragment();
        AccountFragment accountFragment=new AccountFragment();
        fragmentList.add(featureFragment);
        fragmentList.add(messageFragment);
        fragmentList.add(searchFragment);
        fragmentList.add(accountFragment);
        return fragmentList;
    }
    //监听页面滑动
    class PageChange implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    tabs.check(R.id.feature);
                    break;
                case 1:
                    tabs.check(R.id.message);
                    break;
                case 2:
                    tabs.check(R.id.search);
                    break;
                case 3:
                    tabs.check(R.id.account);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    //切换到发现界面当没有订阅独家号的时候点击按钮调用
    public void tranFragment(){
        viewPager.setCurrentItem(2);
        viewPager.getAdapter().notifyDataSetChanged();
    }
    //监听返回键
    boolean isExit=false;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(isExit){
                finish();
            }else {
                Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
                isExit=true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit=false;
                    }
                },2000);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
