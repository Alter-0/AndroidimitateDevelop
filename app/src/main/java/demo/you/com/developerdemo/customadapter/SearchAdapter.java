package demo.you.com.developerdemo.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.FloatBuffer;
import java.util.List;

import demo.you.com.developerdemo.Account;
import demo.you.com.developerdemo.R;


public class SearchAdapter extends BaseAdapter {
    Context context;
    List<Account> accountList;
    LayoutInflater inflater;

    public SearchAdapter(Context context, List<Account> accountList) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return null!=accountList.get(position)?accountList.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchViewHolder viewHolder;
        Account account= (Account) getItem(position);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.search_listview,null);
            viewHolder=new SearchViewHolder();
            viewHolder.avatar= (ImageView) convertView.findViewById(R.id.team_avatar);
            viewHolder.teamTag= (TextView) convertView.findViewById(R.id.team_tag);
            viewHolder.accountName= (TextView) convertView.findViewById(R.id.account_name);
            viewHolder.massTag= (TextView) convertView.findViewById(R.id.mass_tag);
            viewHolder.signature= (TextView) convertView.findViewById(R.id.signature);
            viewHolder.memberCount= (TextView) convertView.findViewById(R.id.member_count);
            viewHolder.member= (TextView) convertView.findViewById(R.id.member);
            viewHolder.shareCount= (TextView) convertView.findViewById(R.id.share_count);
            viewHolder.readmeCount= (TextView) convertView.findViewById(R.id.readme_count);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (SearchViewHolder) convertView.getTag();
        }
        viewHolder.avatar.setImageResource(account.getAccountImg());
        if(account.isTeam()){
            viewHolder.teamTag.setText(R.string.team);
            viewHolder.teamTag.setBackgroundResource(R.drawable.search_team_tag_bg);
            viewHolder.memberCount.setText(account.getMemberCount()+"");
            viewHolder.member.setVisibility(View.VISIBLE);
            viewHolder.memberCount.setVisibility(View.VISIBLE);
        }else {
            viewHolder.teamTag.setText("");
            viewHolder.teamTag.setBackgroundResource(R.color.press_item);
            viewHolder.member.setVisibility(View.GONE);
            viewHolder.memberCount.setVisibility(View.GONE);
        }
        viewHolder.accountName.setText(account.getAccountName());
        if(account.isSupportMass()){
            viewHolder.massTag.setText(R.string.mass);
            viewHolder.massTag.setBackgroundResource(R.drawable.search_mass_tag_bg);
        }else {
            viewHolder.massTag.setText("");
            viewHolder.massTag.setBackgroundResource(R.color.press_item);
        }
        viewHolder.signature.setText(account.getSignature());
        viewHolder.shareCount.setText(account.getAccountShareCount()+"");
        viewHolder.readmeCount.setText(account.getReadMeCount()+"");
        return convertView;
    }

    private class SearchViewHolder{
        private ImageView avatar;
        private TextView teamTag;
        private TextView accountName;
        private TextView massTag;
        private TextView signature;
        private TextView memberCount;
        private TextView member;
        private TextView shareCount;
        private TextView readmeCount;
    }
}
