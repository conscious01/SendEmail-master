package list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by lvjie on 2018/6/12.
 */

public class MyScrollView extends ScrollView {
    //定义一个接口的对象
    private ScrollViewListener scrollViewListener = null;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //对外提供接口回调的方法
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //scrollview的起始点+总高度==crollView的computeVerticalScrollRange
        if (getScrollY() + getHeight() == computeVerticalScrollRange()) {
            if (scrollViewListener != null) {
                scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
            }
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(MyScrollView context, int x, int y, int oldx, int oldy);
    }
}
