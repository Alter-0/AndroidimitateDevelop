package demo.you.com.developerdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import demo.you.com.developerdemo.R;

/*  圆形ImageView，可设置最多两个宽度不同且颜色不同的圆形边框。设置颜色在xml布局文件中由自定义属性配置参数指定
 */
public class CircleImageView extends android.support.v7.widget.AppCompatImageView {

    private int borderThickness=0;
    private Context context;
    private int defaultColor=0xFFFFFFFF;
    //如果只有其中一个有值，则只画一个圆形边框
    private int borderOutsideColor=0;
    private int borderInsideColor=0;
    //控件默认长、宽
    private int defaultWidth=0;
    private int defaultHeight=0;
    public CircleImageView(Context context) {
        super(context);
        this.context=context;
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        setCustomAttributes(attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        setCustomAttributes(attrs);
    }

    private void setCustomAttributes(AttributeSet attrs){
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        borderThickness=a.getDimensionPixelSize(R.styleable.CircleImageView_border_thickness,0);
        borderOutsideColor=a.getColor(R.styleable.CircleImageView_border_outsize_color,defaultColor);
        borderInsideColor=a.getColor(R.styleable.CircleImageView_border_inside_color,defaultColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable=getDrawable();
        if (drawable==null){
            return;
        }
        if (getWidth()==0||getHeight()==0){
            return;
        }
        this.measure(0,0);
        if(drawable.getClass()== NinePatchDrawable.class)
            return;
        Bitmap b=((BitmapDrawable)drawable).getBitmap();
        Bitmap bitmap=b.copy(Bitmap.Config.ARGB_8888,true);
        if(defaultWidth==0)
            defaultWidth=getWidth();
        if(defaultHeight==0)
            defaultHeight=getHeight();
        int radius=0;//半径
        if(borderInsideColor!=defaultColor&&borderOutsideColor!=defaultColor){//定义画两个边框
            radius=(defaultWidth<defaultHeight?defaultWidth:defaultHeight)/2-2*borderThickness;
            drawCircleBorder(canvas,radius+borderThickness/2,borderInsideColor);
            drawCircleBorder(canvas,radius+borderThickness+borderThickness/2,borderOutsideColor);
        }else if(borderInsideColor!=defaultColor&&borderOutsideColor==defaultColor) {//画一个边框
            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2 - borderThickness;
            drawCircleBorder(canvas, radius + borderThickness / 2, borderInsideColor);
        }else if(borderInsideColor==defaultColor&&borderOutsideColor!=defaultColor){//画一个边框
            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2-borderThickness;
            drawCircleBorder(canvas,radius+borderThickness/2,borderOutsideColor);
        }else{//没有边框
            radius=(defaultWidth<defaultHeight?defaultWidth:defaultHeight)/2;
        }
        Bitmap circleBitmap=getCroppedRoundBitmap(bitmap,radius);
        canvas.drawBitmap(circleBitmap,defaultWidth/2-radius,defaultHeight/2-radius,null);
//        super.onDraw(canvas);
    }
    /**
     * 获取裁剪后的圆形图片
     * @param radius 半径
     */
    public  Bitmap getCroppedRoundBitmap(Bitmap bitmap,int radius){
        Bitmap scaledSrcBmp;
        int diameter=radius*2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int bmpWidth= bitmap.getWidth();
        int bmpHeight=bitmap.getHeight();
        int squareWidth,squareHeight;
        int x,y;
        Bitmap squareBitmap;
        if(bmpHeight>bmpWidth){
            squareWidth=squareHeight=bmpWidth;
            x=0;
            y=(bmpHeight-bmpWidth)/2;
            //截取正方形图片
            squareBitmap=Bitmap.createBitmap(bitmap,x,y,squareWidth,squareHeight);
        }else if(bmpHeight<bmpWidth){
            squareWidth=squareHeight=bmpHeight;
            x=(bmpWidth-bmpHeight)/2;
            y=0;
            squareBitmap=Bitmap.createBitmap(bitmap,x,y,squareWidth,squareHeight);
        }else {
            squareBitmap=bitmap;
        }
        //
        if(squareBitmap.getWidth()!=diameter||squareBitmap.getHeight()!=diameter){
            scaledSrcBmp=Bitmap.createScaledBitmap(squareBitmap,diameter,diameter,true);
        }else {
            scaledSrcBmp=squareBitmap;
        }
        Bitmap output=Bitmap.createBitmap(scaledSrcBmp.getWidth(),scaledSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(output);

        Paint paint=new Paint();
        Rect rect=new Rect(0,0,scaledSrcBmp.getWidth(),scaledSrcBmp.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0,0,0,0);
        canvas.drawCircle(scaledSrcBmp.getWidth()/2,scaledSrcBmp.getHeight()/2,scaledSrcBmp.getWidth()/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp,rect,rect,paint);
        bitmap=null;
        squareBitmap=null;
        scaledSrcBmp=null;
        return output;
    }

    //边缘画圆
    private void drawCircleBorder(Canvas canvas,int radius,int color){
        Paint paint=new Paint();
        /* 去锯齿 */
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
        /* 设置paint的　style　为STROKE：空心 */
        paint.setStyle(Paint.Style.STROKE);
        /* 设置paint的外框宽度 */
        paint.setStrokeWidth(borderThickness);
        canvas.drawCircle(defaultWidth/2,defaultHeight/2,radius,paint);
    }
}
