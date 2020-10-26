package demo.you.com.developerdemo.customadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;


public class RollPagerAdapter extends StaticPagerAdapter {
    private int[] images;

    public RollPagerAdapter(int[] images) {
        this.images = images;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view=new ImageView(container.getContext());
        //设置图片资源
        view.setImageResource(images[position]);
        //设置宽高
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        //设置拉伸的方式
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return view;
    }

    @Override
    public int getCount() {
        return images.length;
    }
}
