package demo.you.com.developerdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.you.com.developerdemo.R;


public class MessageFragment extends Fragment {
    ListView message;
    SimpleAdapter adapter;
    List<Map<String,Object>> list;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.message_fragment,container,false);
        init();
        return view;
    }
    //初始化message界面
    private void init(){
        message= (ListView) view.findViewById(R.id.message_list);
        list=getData();
        adapter=new SimpleAdapter(getActivity(),
                list,
                R.layout.message_listview,
                new String[]{"messageImg","messageName"},
                new int[]{R.id.message_img,R.id.message_name});
        message.setAdapter(adapter);
    }
    //获取数据源
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> mapList=new ArrayList<>();
        Map<String,Object> data=new HashMap<>();
        data.put("messageImg",R.mipmap.chat_avatar_subscribe);
        data.put("messageName",getActivity().getResources().getString(R.string.message_read));
        mapList.add(data);
        data=new HashMap<>();
        data.put("messageImg",R.mipmap.chat_avatar_comment);
        data.put("messageName",getActivity().getResources().getString(R.string.message_comment));
        mapList.add(data);
        data=new HashMap<>();
        data.put("messageImg",R.mipmap.chat_avatar_follow);
        data.put("messageName",getActivity().getResources().getString(R.string.message_focu));
        mapList.add(data);
        data=new HashMap<>();
        data.put("messageImg",R.mipmap.chat_avatar_notice);
        data.put("messageName",getActivity().getResources().getString(R.string.message_system));
        mapList.add(data);
        return mapList;
    }
}
