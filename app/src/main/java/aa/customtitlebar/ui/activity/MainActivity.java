package aa.customtitlebar.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import aa.customtitlebar.R;
import aa.customtitlebar.ui.widget.CustomTitleBar;
import aa.customtitlebar.utils.ToastUtils;


public class MainActivity extends Activity {

    private CustomTitleBar mTitleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleBar = (CustomTitleBar) findViewById(R.id.id_ctb_main);
        mTitleBar.setOnTitleBarClickListener(new CustomTitleBar.TitleBarClickListener() {
            @Override
            public void onLeftClickListener() {
                ToastUtils.show(MainActivity.this, "抱歉，没办法再返回了呢！");
            }

            @Override
            public void onRightClickListener() {
                ToastUtils.show(MainActivity.this, "哈哈，你点击了标题栏右按钮！");
            }
        });

//        mTitleBar.setTitleTextColor(Color.YELLOW);
//        mTitleBar.setTitleText("标题被修改了");
    }
}
