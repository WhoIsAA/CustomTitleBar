# CustomTitleBar
一个简单的自定义标题栏

在布局文件中的应用：
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:titlebar="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#cce8cf"
    android:orientation="vertical">

    <aa.customtitlebar.ui.widget.CustomTitleBar
        android:id="@+id/id_ctb_main"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="#ff5e4c"
        android:padding="10dp"
        titlebar:amboTextSize="16sp"
        titlebar:leftImage="@drawable/icon_back"
        titlebar:rightText="点一点"
        titlebar:textColor="#ffffff"
        titlebar:titleText="自定义标题栏"
        titlebar:leftVisible="true"
        titlebar:rightVisible="true"
        titlebar:titleTextSize="19sp" />

</LinearLayout>
