package demo.you.com.developerdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;

import java.util.ArrayList;
import java.util.List;

import demo.you.com.developerdemo.Account;
import demo.you.com.developerdemo.MainActivity;
import demo.you.com.developerdemo.customadapter.NiceAdapter;
import demo.you.com.developerdemo.R;
import demo.you.com.developerdemo.customadapter.RollPagerAdapter;
import demo.you.com.developerdemo.customadapter.TabPagerAdapter;


public class FeatureFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    TabLayout tab;
    ViewPager viewPager;
    View nice,read,view;
    List<String> titles;
    List<View> views;
    LayoutInflater inflater;
    TabPagerAdapter adapter;
    RollPagerView rollPagerView;
    RollPagerAdapter pagerAdapter;
    SwipeRefreshLayout layoutForNice,layoutForRead;
    private static final int REFRESH_COMPLETE_FOR_NICE=0x110;
    private static final int REFRESH_COMPLETE_FOR_READ=0x111;
    private int images[]={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.feature_fragment,container,false);
        views=getViews();
        titles=getTitles();
        init();
        return view;
    }
    //初始化操作
    private void init(){
        tab= (TabLayout) view.findViewById(R.id.tabs);
        viewPager= (ViewPager) view.findViewById(R.id.tab_content);
        //设计选项模式
       tab.setTabMode(TabLayout.MODE_FIXED);

        //添加选项卡
        tab.addTab(tab.newTab().setText(titles.get(0)));
        tab.addTab(tab.newTab().setText(titles.get(1)));

        //添加适配器
        adapter=new TabPagerAdapter(views,titles);
        viewPager.setAdapter(adapter);
        //将tabLayout与viewPager关联起来

        tab.setupWithViewPager(viewPager);
        //设置tablayout的监听事件



        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            ImageView img= (ImageView) view.findViewById(R.id.tab_img);
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                   // img.setVisibility(View.VISIBLE);
                }else {
                  //  img.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //给tab添加适配器
        tab.setTabsFromPagerAdapter(adapter);

    }
    //动态加载布局
    private List<View> getViews(){
        List<View> viewList=new ArrayList<>();
        inflater=LayoutInflater.from(getActivity());
        nice=inflater.inflate(R.layout.sub_tab1,null);
        setNice(nice);
        read=inflater.inflate(R.layout.sub_tab2,null);
        setRead(read);
        viewList.add(nice);
        viewList.add(read);
        return viewList;
    }
    //获取标题
    private List<String> getTitles(){
        List<String> titleList=new ArrayList<>();
        String subtab1=getActivity().getResources().getString(R.string.tab_sub_nice);
        String subtab2=getActivity().getResources().getString(R.string.tab_sub_read);
        titleList.add(subtab1);
        titleList.add(subtab2);
        return titleList;
    }
    //精选界面
    private void setNice(View view){
        pagerAdapter=new RollPagerAdapter(images);
        rollPagerView= (RollPagerView) view.findViewById(R.id.roll_pager_view);
        //设置每个图片的切换时间
        rollPagerView.setPlayDelay(5000);
        //设置动画间隔时间
        rollPagerView.setAnimationDurtion(500);
        //设置自定义指示器
        rollPagerView.setHintView(new IconHintView(getActivity(),R.drawable.feature_sub_tab_selected,R.drawable.feature_sub_tab_unselected,0));
        rollPagerView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //设置指示器颜色
//        rollPagerView.setHintView(new ColorPointHintView(getActivity(),getResources().getColor(R.color.colorPrimary), Color.WHITE));
        rollPagerView.setAdapter(pagerAdapter);
        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(),"第"+(position+1)+"幅图片",Toast.LENGTH_SHORT).show();
            }
        });
        ListView niceList= (ListView) view.findViewById(R.id.nice_listview);
        niceList.setFocusable(false);
        List<Account> list=getNiceList();
        NiceAdapter niceAdapter=new NiceAdapter(getActivity(),list);
        niceList.setAdapter(niceAdapter);
        niceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView= (TextView) view.findViewById(R.id.nice_title);
                Toast.makeText(getContext(),textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        layoutForNice= (SwipeRefreshLayout) view.findViewById(R.id.tab1_refresh);
        layoutForNice.setOnRefreshListener(this);
        layoutForNice.setColorSchemeResources(
                R.color.main_theme
        );
    }

    //精选界面的列表
    private List<Account> getNiceList(){
        List<Account> niceList=new ArrayList<>();
        Account account1=new Account(R.mipmap.default_avatar,"用户用户用户",0,6,"移动端开发所需要的小技巧",false);
        Account account2=new Account(R.mipmap.default_avatar,"我是1号作者",2,8,"如何学习JAVA",false);
        Account account3=new Account(R.mipmap.default_avatar,"作者22作者22",4,22,"带你快速入门Python",false);
        Account account4=new Account(R.mipmap.default_avatar,"有一个独家号",3,1,"分享一个开发中遇到的问题",false);
        Account account5=new Account(R.mipmap.default_avatar,"我是独家号",1,31,"计算机网络基础知识概述",false);
        Account account6=new Account(R.mipmap.default_avatar,"Java技术教学",5,14,"程序员必备知识(1)",false);
        niceList.add(account1);
        niceList.add(account2);
        niceList.add(account3);
        niceList.add(account4);
        niceList.add(account5);
        niceList.add(account6);
        return niceList;
    }

    //订阅界面
    private void setRead(View view){
        Button toRead= (Button) view.findViewById(R.id.toread);
        toRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity= (MainActivity) getActivity();
                activity.tranFragment();
            }
        });
        layoutForRead= (SwipeRefreshLayout) view.findViewById(R.id.tab2_refresh);
        layoutForRead.setOnRefreshListener(this);
        layoutForRead.setColorSchemeResources(
                R.color.main_theme
        );
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_SHORT).show();
        handler.sendEmptyMessageAtTime(REFRESH_COMPLETE_FOR_NICE,3000);
        handler.sendEmptyMessageAtTime(REFRESH_COMPLETE_FOR_READ,3000);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_COMPLETE_FOR_NICE:
                    //隐藏或显示刷新进度
                    layoutForNice.setRefreshing(false);
                    break;
                case REFRESH_COMPLETE_FOR_READ:
                    layoutForRead.setRefreshing(false);
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
