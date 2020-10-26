package demo.you.com.developerdemo.customadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import demo.you.com.developerdemo.Account;
import demo.you.com.developerdemo.R;


public class AboutAdapter extends BaseAdapter {
    Context context;
    List<Account> list;
    LayoutInflater inflater;
    View partline;

    public AboutAdapter(List<Account> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null!=list.get(position)?list.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AboutViewHolder viewHolder;
        Account account= (Account) getItem(position);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.about_listview,null);
            viewHolder=new AboutViewHolder();
            viewHolder.about_root= (RelativeLayout) convertView.findViewById(R.id.about_root);
            viewHolder.about_img= (ImageView) convertView.findViewById(R.id.about_img);
            viewHolder.about_name= (TextView) convertView.findViewById(R.id.about_name);
            viewHolder.spc_text= (TextView) convertView.findViewById(R.id.spe_text);
            viewHolder.simple_text= (TextView) convertView.findViewById(R.id.simple_text);
            convertView.setTag(viewHolder);
//            viewHolder.spc_text.setTag(position);
        }else {
            viewHolder= (AboutViewHolder) convertView.getTag();
//            viewHolder.spc_text.setTag(position);
        }
        partline=convertView.findViewById(R.id.partline);
        viewHolder.about_img.setImageResource(account.getInfoImg());
        viewHolder.about_name.setText(account.getInfoName());
        viewHolder.spc_text.setText(account.getSpcText()+"");
        viewHolder.simple_text.setText(account.getSimpleText());
        if(position==1||position==4||position==6||position==8){
            partline.setBackgroundResource(R.color.part_line);
        }else if(position==9){
            partline.setBackgroundResource(R.color.part_line);
            //viewHolder.spc_text.setBackgroundResource(R.mipmap.ic_badge_new);
        }else if(position==0){
            viewHolder.spc_text.setBackgroundResource(R.color.press_item);
        }else {
            partline.setBackgroundResource(R.color.press_item);
        }
        return convertView;
    }

    private class AboutViewHolder{
        RelativeLayout about_root;
        ImageView about_img;
        TextView about_name;
        TextView spc_text;
        TextView simple_text;
    }
}
