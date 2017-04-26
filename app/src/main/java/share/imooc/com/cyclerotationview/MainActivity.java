package share.imooc.com.cyclerotationview;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private ViewPager mViewPager;
    private LinearLayout mPointGroup;
    private List<ImageView>mList;
    private int[]images={R.mipmap.dog2,R.mipmap.dog3,R.mipmap.dog4,R.mipmap.dog5,R.mipmap.dog7,R.mipmap.dog10};
    private int lastPosition;
    private Handler mHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        mPointGroup= (LinearLayout) findViewById(R.id.pointGroup);
        mList=new ArrayList<ImageView>();
        initDatas();
        initPoints();
        mViewPager.setAdapter(new CycleAdapter(mList));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position=position%mList.size();
                mPointGroup.getChildAt(position).setSelected(true);
                mPointGroup.getChildAt(lastPosition).setSelected(false);
                lastPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem=mViewPager.getCurrentItem();
                if (currentItem== mViewPager.getAdapter().getCount() - 1){
                    mViewPager.setCurrentItem(0);
                }else {
                    mViewPager.setCurrentItem(currentItem + 1);
                }
                mHandler.postDelayed(this, 3000);
            }
        }, 3000);
    }

    private void initPoints() {
        for (int i = 0; i <images.length ; i++) {
            ImageView point = new ImageView(MainActivity.this);
            point.setImageResource(R.drawable.shape_point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i > 0) {
                params.leftMargin = 10;
                point.setSelected(false); // 默认选中第1个
            } else {
                point.setSelected(true); // 默认选中第1个
            }
            point.setLayoutParams(params); // 给指示器设置参数
            mPointGroup.addView(point);
        }
    }

    private void initDatas() {
        for (int i = 0; i <images.length ; i++) {
            ImageView imageView=new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(images[i]);
            mList.add(imageView);
        }
    }
}
