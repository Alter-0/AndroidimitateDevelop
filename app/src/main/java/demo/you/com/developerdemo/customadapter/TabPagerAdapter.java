package demo.you.com.developerdemo.customadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class TabPagerAdapter extends PagerAdapter {
    List<View> viewList;
    List<String> titlelist;

    public TabPagerAdapter(List<View> viewList,List<String> titlelist) {
        this.viewList = viewList;
        this.titlelist=titlelist;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //添加选项卡
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //删除选项卡
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);
    }
}
