package demo.you.com.developerdemo.customadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.you.com.developerdemo.Account;
import demo.you.com.developerdemo.R;


public class NiceAdapter extends BaseAdapter {

    Context context;
    List<Account> accountList;
    LayoutInflater inflater;

    public NiceAdapter(Context context, List<Account> accountList) {
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        final NiceViewHolder viewHolder;
        Account account= (Account) getItem(position);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.sub_tab1_nice_listview,null);
            viewHolder=new NiceViewHolder();
            viewHolder.essayTitle= (TextView) convertView.findViewById(R.id.nice_title);
            viewHolder.avatar= (ImageView) convertView.findViewById(R.id.nice_avatar);
            viewHolder.likeimg= (ImageView) convertView.findViewById(R.id.like_img);
            viewHolder.likeCount= (TextView) convertView.findViewById(R.id.like_count);
            viewHolder.commentCount= (TextView) convertView.findViewById(R.id.comment_count);
            viewHolder.accountName= (TextView) convertView.findViewById(R.id.account_name);
            viewHolder.commentImg= (ImageView) convertView.findViewById(R.id.comment_img);
            final NiceViewHolder finalViewHolder=viewHolder;
            viewHolder.likeimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Account myaccount= (Account) finalViewHolder.likeCount.getTag();
                    if(myaccount.isLike()){
                        viewHolder.likeimg.setImageResource(R.drawable.ic_item_like);
                        viewHolder.likeCount.setTextColor(parent.getResources().getColor(R.color.feature_sub_tab1_count));
                        int currentLikeCount=Integer.parseInt(viewHolder.likeCount.getText().toString());
                        int resultLikeCount=currentLikeCount-1;
                        viewHolder.likeCount.setText(resultLikeCount+"");
                        myaccount.setLike(false);
                    }else {
                        viewHolder.likeimg.setImageResource(R.drawable.ic_item_liked);
                        viewHolder.likeCount.setTextColor(parent.getResources().getColor(R.color.main_theme));
                        int currentLikeCount=Integer.parseInt(viewHolder.likeCount.getText().toString());
                        viewHolder.likeCount.setText((currentLikeCount+1)+"");
                        myaccount.setLike(true);
                    }
                }
            });
            viewHolder.commentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Account myaccount= (Account) finalViewHolder.accountName.getTag();
                    Toast.makeText(context,"留言给"+myaccount.getAccountName(),Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(viewHolder);
            viewHolder.likeCount.setTag(account);
            viewHolder.accountName.setTag(account);
        }else{
            viewHolder = (NiceViewHolder) convertView.getTag();
            viewHolder.likeCount.setTag(account);
            viewHolder.accountName.setTag(account);
        }
        viewHolder.essayTitle.setText(account.getEssayTitle());
        viewHolder.avatar.setImageResource(account.getAccountImg());
        if(account.isLike()){
            viewHolder.likeimg.setImageResource(R.drawable.ic_item_liked);
        }else {
            viewHolder.likeimg.setImageResource(R.drawable.ic_item_like);
        }
        viewHolder.likeCount.setText(account.getLikeCount()+"");
        viewHolder.commentCount.setText(account.getCommentCount()+"");
        viewHolder.accountName.setText(account.getAccountName());
        return convertView;
    }

    private class NiceViewHolder{
        private TextView essayTitle;
        private ImageView avatar;
        private ImageView likeimg;
        private TextView likeCount;
        private ImageView commentImg;
        private TextView commentCount;
        private TextView accountName;
    }
}
