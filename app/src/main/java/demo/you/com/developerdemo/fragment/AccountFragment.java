package demo.you.com.developerdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.you.com.developerdemo.customadapter.AboutAdapter;
import demo.you.com.developerdemo.Account;
import demo.you.com.developerdemo.customadapter.AccountAdapter;
import demo.you.com.developerdemo.customview.ListViewForScrollView;
import demo.you.com.developerdemo.R;




public class AccountFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    View view;
    ListView apply,myAccount;
    ListViewForScrollView about;
    ArrayAdapter applyAdapter;
    AccountAdapter accountAdapter;
    AboutAdapter aboutAdapter;
    List<String> list;
    List<Account> accountList,infoList;
    SwipeRefreshLayout layout;
    private static final int REFRESH_COMPLETE=0x110;
    @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view= inflater.inflate(R.layout.account_fragment,container,false);
            initRefresh();
            initApply();
            initAccount();
            initInfo();
        return view;
    }

    //初始化申请独家号listView
    private void initApply(){
        list=getList();
        apply= (ListView) view.findViewById(R.id.apply_new_account);
        apply.setFocusable(false);
        applyAdapter=new ArrayAdapter(getActivity(),R.layout.apply_new_account_listview,list);
        apply.setAdapter(applyAdapter);
    }
    // 申请独家号数据源
    private List<String> getList(){
        List<String> data=new ArrayList<>();
        data.add(getResources().getString(R.string.apply_new_account));
        return data;
    }
    //独家号信息初始化
    private void initAccount(){
        myAccount= (ListView) view.findViewById(R.id.my_account);
        accountList=getMyAccountList();
        accountAdapter=new AccountAdapter(getActivity(),accountList);
        myAccount.setAdapter(accountAdapter);
        myAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "有个独家号", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //独家号信息数据源
    private List<Account> getMyAccountList(){
        List<Account> myAccount=new ArrayList<>();
        Account account=new Account(R.mipmap.placeholder_no_cover,"有个601小白在此",2,3);
        myAccount.add(account);
        return myAccount;
    }
    //我的功能信息初始化
    private void initInfo(){
        infoList=getMyInfoList();
        about= (ListViewForScrollView) view.findViewById(R.id.about_listview);
        aboutAdapter=new AboutAdapter(infoList,getActivity());
        about.setAdapter(aboutAdapter);
        about.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    //我的功能信息数据源
    private List<Account> getMyInfoList(){
        List<Account> infoList=new ArrayList<>();
        Account myRead=new Account(R.mipmap.nav_icon_subscribed_subjects,"我订阅的独家号","","0");
        Account myIOcoin=new Account(R.mipmap.nav_icon_total_coin,"601通行币","0.0","账户总额");
        Account ydGain=new Account(R.mipmap.nav_icon_income,"昨日收益","","暂无收益");
        Account cvGift=new Account(R.mipmap.nav_icon_gift,"礼物兑换","","601通行币兑换");
        Account mycollect=new Account(R.mipmap.nav_icon_favorite,"我的收藏","","19");
        Account lastRead=new Account(R.mipmap.nav_icon_history,"最近浏览","","");
        Account feedback=new Account(R.mipmap.nav_icon_feedback,"意见反馈","","");
        Account coopration=new Account(R.mipmap.nav_icon_cooperation,"合作申请","","");
        Account share=new Account(R.mipmap.nav_icon_share,"推荐App给好友","","");
        Account setting=new Account(R.mipmap.nav_icon_settings,"设置","","");
        infoList.add(myRead);
        infoList.add(myIOcoin);
        infoList.add(ydGain);
        infoList.add(cvGift);
        infoList.add(mycollect);
        infoList.add(lastRead);
        infoList.add(feedback);
        infoList.add(coopration);
        infoList.add(share);
        infoList.add(setting);
        return infoList;
    }

    //初始化下拉刷新
    private void initRefresh(){
        layout= (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        layout.setOnRefreshListener(this);
        layout.setColorSchemeResources(
                R.color.main_theme
        );
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_SHORT).show();
        handler.sendEmptyMessageAtTime(REFRESH_COMPLETE,3000);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_COMPLETE:
                    applyAdapter.notifyDataSetChanged();
                    accountAdapter.notifyDataSetChanged();
                    aboutAdapter.notifyDataSetChanged();
                    //隐藏或显示刷新进度
                    layout.setRefreshing(false);
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
