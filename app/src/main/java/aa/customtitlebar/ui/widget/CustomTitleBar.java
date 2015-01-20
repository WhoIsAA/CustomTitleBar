package aa.customtitlebar.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import aa.customtitlebar.R;
import aa.customtitlebar.utils.DisplayUtils;
import aa.customtitlebar.utils.StringUtils;

/**
 * 自定义标题栏
 * @author AA
 * @Date 2015-01-19
 */
public class CustomTitleBar extends RelativeLayout {

    /** 文字 */
    private String mLeftText, mTitleText, mRightText;
    /** 文字大小 */
    private float mAmboTextSize, mTitleTextSize;
    /** 文字颜色 */
    private int mTextColor;
    /** 图片 */
    private int mLeftImage, mRightImage;
    /** 是否可见 */
    private boolean mLeftVisible, mRightVisible;

    /** 左边、标题、右边 */
    private TextView mLeftTextView, mTitleTextView, mRightTextView;
    private ImageView mLeftImageView, mRightImageView;
    /** 标题栏视图参数 */
    private LayoutParams mLeftParams, mTitleParams, mRightParams;
    /** 自定义点击监听器 */
    private TitleBarClickListener mTitleBarClickListener;


    public CustomTitleBar(Context context) {
        this(context, null);
    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //获得自定义属性值
        getTypedArray(context, attrs);

        //创建LayoutParams，并且设置对齐方式
        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        mRightParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);

        //左边文字
        if(StringUtils.isNotEmpty(mLeftText) && mLeftVisible) {
            mLeftTextView = new TextView(context);
            mLeftTextView.setText(mLeftText);
            mLeftTextView.setTextSize(mAmboTextSize);
            mLeftTextView.setTextColor(mTextColor);
            addView(mLeftTextView, mLeftParams);
            mLeftTextView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mTitleBarClickListener != null) {
                        //调用接口中方法
                        mTitleBarClickListener.onLeftClickListener();
                    }
                }
            });
        }
        //右边文字
        if(StringUtils.isNotEmpty(mRightText) && mRightVisible){
            mRightTextView = new TextView(context);
            mRightTextView.setText(mRightText);
            mRightTextView.setTextSize(mAmboTextSize);
            mRightTextView.setTextColor(mTextColor);
            addView(mRightTextView, mRightParams);
            mRightTextView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mTitleBarClickListener != null) {
                        mTitleBarClickListener.onRightClickListener();
                    }
                }
            });
        }
        //标题文字
        if(StringUtils.isNotEmpty(mTitleText)) {
            mTitleTextView = new TextView(context);
            mTitleTextView.setText(mTitleText);
            mTitleTextView.setTextSize(mTitleTextSize);
            mTitleTextView.setGravity(Gravity.CENTER);
            mTitleTextView.setTextColor(mTextColor);
            addView(mTitleTextView, mTitleParams);
        }
        //左边图片按钮
        if(mLeftImage > 0 && mLeftVisible) {
            mLeftImageView = new ImageView(context);
            mLeftImageView.setImageResource(mLeftImage);
            addView(mLeftImageView, mLeftParams);
            mLeftImageView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mTitleBarClickListener != null) {
                        mTitleBarClickListener.onLeftClickListener();
                    }
                }
            });
        }
        //右边图片按钮
        if(mRightImage > 0 && mRightVisible) {
            mRightImageView = new ImageView(context);
            mRightImageView.setImageResource(mRightImage);
            addView(mRightImageView, mRightParams);
            mRightImageView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mTitleBarClickListener != null) {
                        mTitleBarClickListener.onRightClickListener();
                    }
                }
            });
        }
    }

    /**
     * 获得自定义属性值
     * @param context
     * @param attrs
     */
    private void getTypedArray(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        int count = ta.getIndexCount();
        for(int i=0; i<count; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleBar_leftText:
                    mLeftText = ta.getString(attr);
                    break;

                case R.styleable.CustomTitleBar_titleText:
                    mTitleText = ta.getString(attr);
                    break;

                case R.styleable.CustomTitleBar_rightText:
                    mRightText = ta.getString(attr);
                    break;

                case R.styleable.CustomTitleBar_amboTextSize:
                    mAmboTextSize = DisplayUtils.px2sp(context, ta.getDimensionPixelSize(attr, 12));
                    break;

                case R.styleable.CustomTitleBar_titleTextSize:
                    mTitleTextSize = DisplayUtils.px2sp(context, ta.getDimensionPixelSize(attr, 16));
                    break;

                case R.styleable.CustomTitleBar_textColor:
                    mTextColor = ta.getColor(attr, Color.BLACK);
                    break;

                case R.styleable.CustomTitleBar_leftVisible:
                    mLeftVisible = ta.getBoolean(attr, false);
                    break;

                case R.styleable.CustomTitleBar_rightVisible:
                    mRightVisible = ta.getBoolean(attr, false);
                    break;

                case R.styleable.CustomTitleBar_leftImage:
                    mLeftImage = ta.getResourceId(attr, 0);
                    break;

                case R.styleable.CustomTitleBar_rightImage:
                    mRightImage = ta.getResourceId(attr, 0);
                    break;
            }
        }
        ta.recycle();
    }

    /**
     * 设置点击监听事件
     * @param listener
     */
    public void setOnTitleBarClickListener(TitleBarClickListener listener) {
        this.mTitleBarClickListener = listener;
    }

    /**
     * 设置左侧文字是否可见
     * @param visible
     */
    public void setLeftTextVisible(boolean visible) {
        this.mLeftTextView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置右侧文字是否可见
     * @param visible
     */
    public void setRightTextVisible(boolean visible) {
        this.mRightTextView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置左侧图片是否可见
     * @param visible
     */
    public void setLeftImageVisible(boolean visible) {
        this.mLeftImageView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置右侧图片是否可见
     * @param visible
     */
    public void setRightImageVisible(boolean visible) {
        this.mRightImageView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置左侧文字
     * @param text
     */
    public void setLeftText(String text) {
        this.mLeftTextView.setText(text);
    }

    /**
     * 设置标题文字
     * @param text
     */
    public void setTitleText(String text) {
        this.mTitleTextView.setText(text);
    }

    /**
     * 设置右侧文字
     * @param text
     */
    public void setRightText(String text) {
        this.mRightTextView.setText(text);
    }

    /**
     * 设置左侧文字大小
     * @param textSize
     */
    public void setLeftTextSize(float textSize) {
        this.mLeftTextView.setTextSize(textSize);
    }

    /**
     * 设置标题文字大小
     * @param textSize
     */
    public void setTitleTextSize(float textSize) {
        this.mTitleTextView.setTextSize(textSize);
    }

    /**
     * 设置右侧文字大小
     * @param textSize
     */
    public void setRightTextSize(float textSize) {
        this.mRightTextView.setTextSize(textSize);
    }

    /**
     * 设置左侧文字颜色
     * @param color
     */
    public void setLeftTextColor(int color) {
        this.mLeftTextView.setTextColor(color);
    }

    /**
     * 设置标题文字颜色
     * @param color
     */
    public void setTitleTextColor(int color) {
        this.mTitleTextView.setTextColor(color);
    }

    /**
     * 设置右侧文字颜色
     * @param color
     */
    public void setRightTextColor(int color) {
        this.mRightTextView.setTextColor(color);
    }

    /**
     * 设置左侧图片
     * @param resId
     */
    public void setLeftImageResource(int resId) {
        this.mLeftImageView.setImageResource(resId);
    }

    /**
     * 设置右侧图片
     * @param resId
     */
    public void setRightImageResource(int resId) {
        this.mRightImageView.setImageResource(resId);
    }

    /**
     * 自定义标题栏接口
     */
    public interface TitleBarClickListener {
        public void onLeftClickListener();
        public void onRightClickListener();
    }
}
