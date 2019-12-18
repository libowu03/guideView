package com.kit.ActivityUiFrame;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kit.guide.R;
import com.kit.utils.GetActionBarHeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主界面框架view
 *
 * @author libowu
 * @date 2019/12/08
 */
public class MainUIFrameView extends LinearLayout implements View.OnClickListener {
    private Activity activity;
    private DrawerLayout mainUiBox;
    private LinearLayout toolbar;
    private ImageView mainuiOpenMenu;
    private ImageView mainuiRightBtn;
    private LinearLayout defaultTab;
    private Fragment oldFragment;
    private List<TabViewInfo> tabViewInfos;
    private LinearLayout mainuiLeftMenuFoot;
    private NavigationView mainuiLeftMenuHeadAndBody;
    private int headLayout, menuLayout, footLayout, customMenuLayout, toolBarLayout;
    private MainUiMenuItemClickListener listener;
    private View userCustomView;
    private TabClickListener tabClickListener;


    public MainUIFrameView(Context context) {
        this(context, null);
    }

    public MainUIFrameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainUIFrameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getObject(context);

        LayoutInflater.from(getContext()).inflate(R.layout.mainui_view_main, this, true);

        initView(attrs, defStyleAttr);

        initListener();
    }


    /**
     * 获取非的实例
     *
     * @param context
     */
    private void getObject(Context context) {
        activity = (Activity) context;
        tabViewInfos = new ArrayList<>();
    }


    /**
     * 初始化监听器
     */
    private void initListener() {
        //用户自定义状态了后这两个控件可能为空值
        if (mainuiOpenMenu != null) {
            mainuiOpenMenu.setOnClickListener(this);
        }
        if (mainuiRightBtn != null) {
            mainuiRightBtn.setOnClickListener(this);
        }

        if (mainuiLeftMenuHeadAndBody != null) {
            mainuiLeftMenuHeadAndBody.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    if (listener != null) {
                        return listener.onClick(menuItem);
                    } else {
                        return false;
                    }
                }
            });
        }
    }


    /**
     * 设置侧边栏菜单点击
     *
     * @param listener
     */
    public void setMainUiMenuItemClickListener(MainUiMenuItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 获取侧边栏的头部
     *
     * @return
     */
    public View getLeftMenuHeadLayout() {
        if (mainuiLeftMenuHeadAndBody == null) {
            return null;
        }
        return mainuiLeftMenuHeadAndBody.getHeaderView(0);
    }


    /**
     * 获取侧边栏的中间菜单
     *
     * @return
     */
    public Menu getLeftMenuBodyLayout() {
        if (mainuiLeftMenuHeadAndBody == null) {
            return null;
        }
        return mainuiLeftMenuHeadAndBody.getMenu();
    }

    public View getLeftMenuBottomLayout() {
        if (mainuiLeftMenuFoot != null) {
            if (mainuiLeftMenuFoot.getChildCount() == 1) {
                Log.e("日志", "获取的侧边栏底部菜单的孩子数量为：" + mainuiLeftMenuFoot.getChildCount());
                return mainuiLeftMenuFoot.getChildAt(0);
            } else {
                return mainuiLeftMenuFoot;
            }
        } else {
            return null;
        }
    }



    /**
     * 设置fragment
     *
     * @param tabContents
     */
    public void setFragmentsList(final List<TabContent> tabContents) {
        defaultTab.removeAllViews();
        for (final TabContent tab : tabContents) {
            final View tabView = LayoutInflater.from(getContext()).inflate(R.layout.mainui_view_tab, null);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            llp.height = LayoutParams.WRAP_CONTENT;
            llp.weight = 1;
            tabView.setLayoutParams(llp);
            TextView name = tabView.findViewById(R.id.mainuiTabName);
            ImageView tabIcon = tabView.findViewById(R.id.mainuiTabIcon);
            if (tab.getTabName() != null && !tab.getTabName().isEmpty()) {
                name.setTag(tab.getTabName());
                name.setText(tab.getTabName());
            } else {
                name.setVisibility(GONE);
            }
            tabViewInfos.add(new TabViewInfo(tabIcon, name, tab));

            //设置tab点击事件
            tabView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //设置默认fragment
                    FragmentManager fragmentTransaction = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    FragmentTransaction transation = fragmentTransaction.beginTransaction();
                    transation.hide(oldFragment);
                    if (!tab.getFragment().isAdded()) {
                        transation.add(R.id.mainContent, tab.getFragment(), tab.getTabName());
                    } else {
                        transation.show(tab.getFragment());
                    }
                    transation.commit();
                    oldFragment = tab.getFragment();
                    setCheckAndUncheck(tab);

                    if (tabClickListener != null){
                        tabClickListener.onTabClickListener(tab,tabContents.indexOf(tab),tabView);
                    }
                }
            });
            defaultTab.addView(tabView);
        }

        //设置默认fragment
        FragmentManager fragmentTransaction = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction transation = fragmentTransaction.beginTransaction();
        transation.add(R.id.mainContent, tabContents.get(0).getFragment(), tabContents.get(0).getTabName());
        transation.commit();
        oldFragment = tabContents.get(0).getFragment();
        setCheckAndUncheck(tabContents.get(0));
    }


    /**
     * 设置fragment
     *
     * @param tabContents
     */
    public void setFragmentsList(final List<TabContent> tabContents, final int viewLayout) {
        defaultTab.removeAllViews();
        for (final TabContent tab : tabContents) {
            final View tabView = LayoutInflater.from(getContext()).inflate(viewLayout, null);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            llp.height = LayoutParams.WRAP_CONTENT;
            llp.weight = 1;
            tabView.setLayoutParams(llp);
            tabViewInfos.add(new TabViewInfo(null, null, tab));

            //设置tab点击事件
            tabView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //设置默认fragment
                    FragmentManager fragmentTransaction = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    FragmentTransaction transation = fragmentTransaction.beginTransaction();
                    transation.hide(oldFragment);
                    if (!tab.getFragment().isAdded()) {
                        transation.add(R.id.mainContent, tab.getFragment(), tab.getTabName());
                    } else {
                        transation.show(tab.getFragment());
                    }
                    transation.commit();
                    oldFragment = tab.getFragment();

                    if (tabClickListener != null){
                        tabClickListener.onTabClickListener(tab,tabContents.indexOf(tab),tabView);
                    }
                }
            });
            defaultTab.addView(tabView);
        }

        //设置默认fragment
        FragmentManager fragmentTransaction = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction transation = fragmentTransaction.beginTransaction();
        transation.add(R.id.mainContent, tabContents.get(0).getFragment(), tabContents.get(0).getTabName());
        transation.commit();
        oldFragment = tabContents.get(0).getFragment();
    }


    /**
     * 设置tab的监听器
     * @param tabClickListener 监听器
     */
    public void setTabClickListener(TabClickListener tabClickListener){
        this.tabClickListener = tabClickListener;
    }


    /**
     * 设置选中和未选中的字体颜色及图片状态
     *
     * @param tabContent
     */
    public void setCheckAndUncheck(TabContent tabContent) {
        for (TabViewInfo info : tabViewInfos) {
            if (info.getTabContent().getTabName().equals(tabContent.getTabName())) {
                info.getTabName().setTextColor(Color.RED);
                setCheckAndUncheckImg(true, tabContent, info);
            } else {
                setCheckAndUncheckImg(false, tabContent, info);
                info.getTabName().setTextColor(Color.GRAY);
            }
        }
    }

    /**
     * 设置图片状态
     *
     * @param isChoose
     * @param tab
     * @param info
     */
    public void setCheckAndUncheckImg(boolean isChoose, TabContent tab, TabViewInfo info) {
        ImageView tabIcon = info.getTabIcon();
        //设置tab的icon
        if (!isChoose) {
            if (tab.getTabIcon() == null) {
                tabIcon.setVisibility(GONE);
            } else if (tab.getTabIcon() instanceof String) {
                //是网络图片才进行加载显示，否则直接隐藏掉icon
                String url = (String) tab.getTabIcon();
                if (url.contains("https://") || url.contains("http://")) {
                    Glide.with(getContext()).load(url).into(tabIcon);
                } else {
                    tabIcon.setVisibility(GONE);
                }
            } else if (tab.getTabIcon() instanceof Integer) {
                tabIcon.setImageResource((Integer) tab.getTabIcon());
            } else if (tab.getTabIcon() instanceof Bitmap) {
                tabIcon.setImageBitmap((Bitmap) tab.getTabIcon());
            }
        } else {
            if (tab.getTabSelectIcon() == null) {
                tabIcon.setVisibility(GONE);
            } else if (tab.getTabIcon() instanceof String) {
                //是网络图片才进行加载显示，否则直接隐藏掉icon
                String url = (String) tab.getTabSelectIcon();
                if (url.contains("https://") || url.contains("http://")) {
                    Glide.with(getContext()).load(url).into(tabIcon);
                } else {
                    tabIcon.setVisibility(GONE);
                }
            } else if (tab.getTabIcon() instanceof Integer) {
                tabIcon.setImageResource((Integer) tab.getTabSelectIcon());
            } else if (tab.getTabIcon() instanceof Bitmap) {
                tabIcon.setImageBitmap((Bitmap) tab.getTabSelectIcon());
            }
        }
    }


    /*    *//**
     * 添加fragment
     * @param tabContent
     * @return
     *//*
    public List<TabContent> addFragment(TabContent tabContent){
        tabContentList.add(tabContent);
        return tabContentList;
    }*/


    /**
     * 初始化界面
     */
    private void initView(AttributeSet attributeSet, int def) {
        getAttr(attributeSet, def);

        mainUiBox = findViewById(R.id.mainUiBox);
        toolbar = findViewById(R.id.defaultToolbar);
        defaultTab = findViewById(R.id.defaultTab);

        //添加默认工具栏
        if (toolBarLayout != 0){
            View defaultToolbar = LayoutInflater.from(getContext()).inflate(toolBarLayout, this,false);
            toolbar.addView(defaultToolbar);
        }else {
            View defaultToolbar = LayoutInflater.from(getContext()).inflate(R.layout.mainui_default_main_toolbar, this,false);
            toolbar.addView(defaultToolbar);
        }

        //隐藏app默认状态栏
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            if (appCompatActivity.getSupportActionBar() != null) {
                appCompatActivity.getSupportActionBar().hide();
            }
            transparentStatusBar();
        }

        //默认顶部工具栏内部控件
        mainuiOpenMenu = findViewById(R.id.mainuiOpenMenu);
        mainuiRightBtn = findViewById(R.id.mainuiRightBtn);

        mainuiLeftMenuHeadAndBody = findViewById(R.id.mainuiLeftMenuHeadAndBody);
        mainuiLeftMenuHeadAndBody.setItemIconTintList(null);

        setLeftMenu();
    }

    /**
     * 设置侧边栏内容
     */
    private void setLeftMenu() {
        try {
            if (mainuiLeftMenuHeadAndBody == null) {
                return;
            }
            //设置中间菜单
            if (menuLayout != 0) {
                mainuiLeftMenuHeadAndBody.inflateMenu(menuLayout);
            }
            //设置头部
            if (headLayout != 0) {
                mainuiLeftMenuHeadAndBody.inflateHeaderView(headLayout);
            }

            //设置底部
            if (footLayout != 0) {
                mainuiLeftMenuFoot = findViewById(R.id.mainuiLeftMenuFoot);
                View leftmenuFoot = LayoutInflater.from(getContext()).inflate(footLayout, null);
                mainuiLeftMenuFoot.addView(leftmenuFoot);
            }
        } catch (Exception e) {
            Log.e("kitView", "建议：menuLayout传入的时，menu而不是layout或其他的值，headLayoutc传入的时layout而不是其他的值。具体原因如下：" + e.getLocalizedMessage());
        }
    }


    /**
     * 获取传入的属性
     */
    private void getAttr(AttributeSet attributeSet, int def) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.MainUIFrameView, def, 0);
        headLayout = typedArray.getResourceId(R.styleable.MainUIFrameView_headLayout, 0);
        menuLayout = typedArray.getResourceId(R.styleable.MainUIFrameView_menuLayout, 0);
        footLayout = typedArray.getResourceId(R.styleable.MainUIFrameView_footLayout, 0);
        customMenuLayout = typedArray.getResourceId(R.styleable.MainUIFrameView_customMenuLayout, 0);
        toolBarLayout = typedArray.getResourceId(R.styleable.MainUIFrameView_toolBarLayout, 0);
    }


    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏为透明，否则在部分手机上会呈现系统默认的浅灰色
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以考虑设置为透明色
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                window.setAttributes(attributes);
            }
        }
        //设置工具栏距离状态栏的margin及设置根布局的背景色
        if (toolbar.getChildCount() > 0) {
            View toolbarChild = toolbar.getChildAt(0);
            GetActionBarHeight.setMarginsNoneTop(toolbar);
            Drawable toolbarBackground = toolbarChild.getBackground();
            if (toolbarBackground instanceof ColorDrawable) {
                mainUiBox.setBackgroundColor(((ColorDrawable) toolbarBackground).getColor());
            }
        }

    }


    /**
     * 设置顶部工具栏，这个方法的属性都是自适应的，要自定义属性，可以使用{@link MainUIFrameView#setToolbar(View, LayoutParams)}
     *
     * @param view 工具栏
     */
    public void setToolbar(View view) {
        if (view == null) {
            return;
        }
        toolbar.removeAllViews();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.width = LayoutParams.MATCH_PARENT;
        lp.height = LayoutParams.WRAP_CONTENT;
        toolbar.addView(view, lp);
    }


    /**
     * 设置顶部工具栏，这个方法需要自定义工具栏属性，自适应属性可以使用{@link MainUIFrameView#setToolbar(View)}
     *
     * @param view         工具栏
     * @param layoutParams 工具栏属性
     */
    public void setToolbar(View view, LayoutParams layoutParams) {
        if (view == null) {
            return;
        }
        toolbar.removeAllViews();
        if (layoutParams == null) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lp.width = LayoutParams.MATCH_PARENT;
            lp.height = LayoutParams.WRAP_CONTENT;
            layoutParams = lp;
        }
        toolbar.addView(view, layoutParams);
    }


    /**
     * 获取工具栏
     * @return
     */
    public View getToolbar(){
        if (toolbar != null){
            if (toolbar.getChildCount() == 1){
                return toolbar.getChildAt(0);
            }else {
                return toolbar;
            }
        }else {
            return null;
        }
    }



    /**
     * 是否显示顶部工具栏
     *
     * @param isShow
     */
    public void showToolbar(boolean isShow) {
        if (isShow) {
            toolbar.setVisibility(VISIBLE);
        } else {
            toolbar.setVisibility(GONE);
        }
    }


    /**
     * 获取侧滑动
     *
     * @return
     */
    public DrawerLayout getDrawerLayout() {
        return mainUiBox;
    }


    @Override
    public void onClick(View view) {
        if (view == mainuiOpenMenu) {
            mainUiBox.openDrawer(Gravity.START);
        } else if (view == mainuiRightBtn) {
            Toast.makeText(getContext(), "点击右边按钮", Toast.LENGTH_SHORT).show();
        }
    }
}
