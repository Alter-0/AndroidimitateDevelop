package demo.you.com.developerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class MyPagerAdapter extends FragmentPagerAdapter {

    public List<Fragment> list = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if (list == null) {
            Log.d("这个列表：", "list空的");
            return 0;
        } else {
            return list.size();
        }
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}