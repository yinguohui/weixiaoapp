package com.xihua.wx.weixiao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.xihua.wx.weixiao.achieve.discuss.DiscussFragment;
import com.xihua.wx.weixiao.achieve.main.MainFragment;
import com.xihua.wx.weixiao.achieve.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    FragmentTabHost fragmentTabHost;
    private List<TabItem> tabItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabData();
        initTabHost();
    }
    //初始化Tab数据
    private void initTabData(){
        tabItemList = new ArrayList<>();
        //添加首页Tab
        tabItemList.add(new TabItem(R.drawable.main_normal
                ,R.drawable.main_press,R.string.main_text, MainFragment.class));
        //添加推荐Tab
        tabItemList.add(new TabItem(R.drawable.discuss_normal
                ,R.drawable.discuss_press,R.string.discuss_text, DiscussFragment.class));
        //添加我的Tab
        tabItemList.add(new TabItem(R.drawable.mine_normal
                ,R.drawable.mine_press,R.string.mine_text, MineFragment.class));

    }

    //初始化主页选项卡视图
    private void initTabHost(){
        //获取FragmentTabHost
        fragmentTabHost = findViewById(android.R.id.tabhost);
        //绑定TabHost(绑定我们的body)
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for (int i= 0; i<tabItemList.size() ;i++){
            TabItem tabItem = tabItemList.get(i);
            //绑定Fragment(将Fragment添加到FragmentTabHost组件上面)
            //newTabSpec:代表Tab名字
            //setIndicator:图片(今天我们采用布局文件--Tab到样式我们自己做)
            TabHost.TabSpec tabSpec = fragmentTabHost.
                    newTabSpec(tabItem.getTitleString())
                    .setIndicator(tabItem.getView());
            //添加Fragment
            //tabSpec:选项卡
            //tabItem.getFragmentClass():具体的Fragment
            //tabItem.getBundle():给我们的具体的Fragment传参数
            fragmentTabHost.addTab(tabSpec,tabItem.getFragmentClass(),tabItem.getBundle());
            //给我们的Tab按钮设置背景
            fragmentTabHost.getTabWidget()
                    .getChildAt(i)
                    .setBackgroundColor(getResources().getColor(R.color.main_bottom_bg));
            //监听点击Tab
            fragmentTabHost.setOnTabChangedListener(this);
            //默认选中第一个Tab
            if (i == 0){
                tabItem.setChecked(true);
            }
        }
    }

    public void onTabChanged(String tabId) {
        //ToastUtil.showToast(this,tabId);
        //重置Tab样式
        for (int i = 0;i < tabItemList.size();i++){
            TabItem tabItem = tabItemList.get(i);
            if (tabId.equals(tabItem.getTitleString())){
                //选中设置为选中壮体啊
                tabItem.setChecked(true);
            }else {
                //没有选择Tab样式设置为正常
                tabItem.setChecked(false);
            }
        }
    }

    //代表每一个Tab
    class TabItem{
        //正常情况下显示的图片
        private int imageNormal;
        //选中情况下显示的图片
        private int imagePress;
        //tab的名字
        private int title;
        private String titleString;
        private Class<? extends Fragment> fragmentClass;

        private View view;
        private ImageView imageView;
        private TextView textView;
        private Bundle bundle;

        public TabItem(int imageNormal,int imagePress,int title,Class<? extends Fragment> fragmentClass){
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.title = title;
            this.fragmentClass = fragmentClass;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTitle() {
            return title;
        }

        public String getTitleString() {
            if (title == 0){
                return "";
            }
            if (TextUtils.isEmpty(titleString)){
                titleString = getString(title);
            }
            return titleString;
        }

        public Bundle getBundle(){
            if (bundle == null){
                bundle = new Bundle();
            }
            bundle.putString("title",getTitleString());
            return bundle;
        }

        //还需要提供一个切换Tab方法---改变Tab样式
        public void setChecked(boolean isChecked){
            if(imageView != null){
                if (isChecked){
                    imageView.setImageResource(imagePress);
                }else {
                    imageView.setImageResource(imageNormal);
                }
            }
            if (textView != null && title != 0){
                if (isChecked){
                    textView.setTextColor(getResources().getColor(R.color.main_bottom_text_select));
                }else {
                    textView.setTextColor(getResources().getColor(R.color.main_bottom_text_normal));
                }
            }
        }

        public View getView(){
            if (this.view == null){
                this.view = getLayoutInflater().inflate(R.layout.view_tab_indicator,null);
                this.imageView = this.view.findViewById(R.id.iv_tab);
                this.textView =  this.view.findViewById(R.id.tv_tab);
                //判断资源是否存在,不再我就因此
                if (this.title == 0){
                    this.textView.setVisibility(View.GONE);
                }else {
                    this.textView.setVisibility(View.VISIBLE);
                    this.textView.setText(getTitleString());
                }
                //绑定图片默认资源
                this.imageView.setImageResource(imageNormal);
            }
            return this.view;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        int id = getIntent().getIntExtra("userloginflag", 0);
        if (id == 1) {
            fragmentTabHost.setCurrentTab(1);
            onTabChanged("影片");
        }
        getIntent().putExtra("userloginflag",0);
    }
}
