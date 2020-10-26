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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.you.com.developerdemo.Account;
import demo.you.com.developerdemo.R;
import demo.you.com.developerdemo.customadapter.SearchAdapter;


public class SearchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    ListView activeTeam,activeAccount,hotAccount;
    SearchAdapter adapter;
    List<Account> teams,accounts,hots;
    View view;
    SwipeRefreshLayout layout;
    private static final int REFRESH_COMPLETE=0x110;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.search_fragment,container,false);
        initRefresh();
        initActiveTeam();
        initActiveAccount();
        initHotAccount();
        return view;
    }
    //活跃团队号
    private void initActiveTeam(){
        activeTeam= (ListView) view.findViewById(R.id.hot_team_list);
        activeTeam.setFocusable(false);
        teams=getTeams();
        adapter=new SearchAdapter(getActivity(),teams);
        activeTeam.setAdapter(adapter);
    }
    //获取活跃团队号
    private List<Account> getTeams(){
        List<Account> teamlist=new ArrayList<>();
        Account member1=new Account(R.mipmap.default_avatar,"这里是团队号名称",false,"这里是我的签名",3,true,12,21);
        Account member2=new Account(R.mipmap.default_avatar,"JAVA学习之路",false,"我的签名是不是很苦",23,true,2,52);
        Account member3=new Account(R.mipmap.default_avatar,"Python学习之路",false,"签名什么的随便了",13,true,11,24);
        Account member4=new Account(R.mipmap.default_avatar,"这里有个团队",false,"写什么签名那么麻烦",21,true,14,32);
        Account member5=new Account(R.mipmap.default_avatar,"这里有个团队",false,"放弃签名中",13,true,15,12);
        teamlist.add(member1);
        teamlist.add(member2);
        teamlist.add(member3);
        teamlist.add(member4);
        teamlist.add(member5);
        return teamlist;
    }

    //活跃独家号
    private void initActiveAccount(){
        activeAccount= (ListView) view.findViewById(R.id.active_account_list);
        accounts=getAccounts();
        adapter=new SearchAdapter(getActivity(),accounts);
        activeAccount.setAdapter(adapter);

    }
    //获取活跃独家号
    private List<Account> getAccounts(){
        List<Account> accountList=new ArrayList<>();
        Account member1=new Account(R.mipmap.default_avatar,"这里是团队号名称",false,"这里是我的签名",3,false,12,21);
        Account member2=new Account(R.mipmap.default_avatar,"JAVA学习之路",false,"我的签名是不是很苦",23,false,2,52);
        Account member3=new Account(R.mipmap.default_avatar,"Python学习之路",false,"签名什么的随便了",13,false,11,24);
        Account member4=new Account(R.mipmap.default_avatar,"这里有个团队",false,"写什么签名那么麻烦",21,false,14,32);
        Account member5=new Account(R.mipmap.default_avatar,"这里有个团队",true,"放弃签名中",13,false,15,12);
        accountList.add(member1);
        accountList.add(member2);
        accountList.add(member3);
        accountList.add(member4);
        accountList.add(member5);
        return accountList;
    }

    //热门独家号
    private void initHotAccount(){
        hotAccount= (ListView) view.findViewById(R.id.hot_account_list);
        hots=getHots();
        adapter=new SearchAdapter(getActivity(),hots);
        hotAccount.setAdapter(adapter);
    }
    //获取热门独家号
    private List<Account> getHots(){
        List<Account> accountList=new ArrayList<>();
        Account member1=new Account(R.mipmap.default_avatar,"这里是团队号名称",false,"这里是我的签名",3,false,12,21);
        Account member2=new Account(R.mipmap.default_avatar,"JAVA学习之路",true,"我的签名是不是很苦",23,false,2,52);
        Account member3=new Account(R.mipmap.default_avatar,"Python学习之路",false,"签名什么的随便了",13,true,11,24);
        Account member4=new Account(R.mipmap.default_avatar,"这里有个团队",true,"写什么签名那么麻烦",21,false,14,32);
        Account member5=new Account(R.mipmap.default_avatar,"这里有个团队",false,"放弃签名中",13,false,15,12);
        Account member6=new Account(R.mipmap.default_avatar,"这里有个团队",false,"放弃签名中",13,false,15,12);
        Account member7=new Account(R.mipmap.default_avatar,"这里有个团队",false,"放弃签名中",13,true,15,12);
        Account member8=new Account(R.mipmap.default_avatar,"这里有个团队",false,"放弃签名中",13,false,15,12);
        Account member9=new Account(R.mipmap.default_avatar,"这里有个团队",false,"放弃签名中",13,false,15,12);
        accountList.add(member1);
        accountList.add(member2);
        accountList.add(member3);
        accountList.add(member4);
        accountList.add(member5);
        accountList.add(member6);
        accountList.add(member7);
        accountList.add(member8);
        accountList.add(member9);
        return accountList;
    }

    //初始化下拉刷新
    private void initRefresh(){
        layout= (SwipeRefreshLayout) view.findViewById(R.id.search_refresh);
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
                    adapter.notifyDataSetChanged();
                    //隐藏或显示刷新进度
                    layout.setRefreshing(false);
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
