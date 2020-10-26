package demo.you.com.developerdemo.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.you.com.developerdemo.Account;
import demo.you.com.developerdemo.R;


public class AccountAdapter extends BaseAdapter {
    Context context;
    List<Account> accountList;
    LayoutInflater inflater;

    public AccountAdapter(Context context, List<Account> accountList) {
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
        return null!=accountList?accountList.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Account account= (Account) getItem(position);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.placeholder_listview,null);
            viewHolder=new ViewHolder();
            viewHolder.imgPlaceHolder= (ImageView) convertView.findViewById(R.id.placeholder_img);
            viewHolder.accountName= (TextView) convertView.findViewById(R.id.my_account_name);
            viewHolder.shareCount= (TextView) convertView.findViewById(R.id.share_count);
            viewHolder.readmeCount= (TextView) convertView.findViewById(R.id.readme_count);
            viewHolder.btnShow= (Button) convertView.findViewById(R.id.show);

            viewHolder.btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了晒一晒", Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.imgPlaceHolder.setImageResource(account.getAccountImg());
        viewHolder.accountName.setText(account.getAccountName());
        viewHolder.shareCount.setText(account.getAccountShareCount()+"");
        viewHolder.readmeCount.setText(account.getReadMeCount()+"");
        return convertView;
    }

    private class ViewHolder{
        private ImageView imgPlaceHolder;
        private TextView accountName;
        private TextView shareCount;
        private TextView readmeCount;
        private Button btnShow;
    }
}
