#### 项目说明
原本此项目为新手引导的高亮区控件，后面有加入了一个宫格控件，用于方便的进行分页宫格显示。一下为此项目的模块说明：
1. 新手引导的高亮控件
2. pagerCard控件，一个简单好用的宫格控件


#### 安装教程
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}

dependencies {
	        implementation 'com.github.libowu03:guideview:v1.0.5'
}

  
```

#### guideView介绍
此依赖的目的是为大部分view添加新手引导。目前activity，fragment的任意view都可以使用此依赖来制作新手引导。新手引导的高亮区支持控件本身的形状，圆形，矩形，椭圆形。<br>
![](https://images.gitee.com/uploads/images/2019/0806/145419_c5ce617d_1951678.gif)
![](https://images.gitee.com/uploads/images/2019/0806/145222_ade564b4_1951678.gif)

#### guideView使用说明
使用代码如下：
```
 //将要说明的控件添加到集合中
guides = new ArrayList<>();
//GuideBean的构造方法比较多，后面会提到各个构造方法的作用
guides.add(new GuideBean(R.mipmap.guide,getActivity(),test_two));
guides.add( new GuideBean(R.mipmap.guide,getActivity(),test_go).setShape(GuideView.Config.RECT).setPadding(20));
guides.add(new GuideBean(R.mipmap.guide,getActivity(),new Rect(0,0,getResources().getDisplayMetrics().widthPixels,200)));
//设置高亮区弹窗,传入的view为activity或fragment的主界面的view，activity可以通过getWindow().getDecorView()的方式获取，fragment可以在onViewCreated中获取到，用于判断主view是否可以获取到控件的位置参数了。
        guideDialog = new GuideDialog(view);
        //设置高亮集合
        guideDialog.setGuideBeans(guides);
        //设置是否启用精确点击（true时只有点击高亮区时才执行下一步操作，默认为false）
        guideDialog.setExactClick(false);
        //是否启用一个屏幕内显示多个高亮区（true时一个屏幕内将显示出高亮集合中的所有内容，默认为false）
        guideDialog.setOpenMore(false);
        //设置高亮区点击事件的监听器
        guideDialog.setGuideListener(new GuideViewClickCallBack() {
            @Override
            public void guideClick(GuideBean guideBean, int guideIndex) {

            }

            @Override
            public void guideMoreClick(List<GuideBean> guideBeans) {

            }

            @Override
            public void guideEndCallback() {

            }

        });
//如果要显示字体，这个可以设置显示字体的颜色（目前只针对直接传入rect的有效，通过view获取的rect无效）
guideDialog.setGuideTextColor(Color.RED);
//设置字体大小（目前只针对直接传入rect的有效，通过view获取的rect无效）
guideDialog.setGuideTextSize(20);
//设置高亮区的padding（只针对简单几何图形的高亮区有效，如果高亮区为某个view的图形，此设置无效）
guideDialog.setHeightLightPadding(20);
//设置高亮区遮罩层的样色,默认值为#cc000000
guideDialog.setMarkColor(Color.parseColor("#DD000000"));
//执行显示高亮控件
guideDialog.show(getFragmentManager(),getClass().getName());
```
#### guideView构造方法说明
1. GuideBean(int img, Activity act, View view)<br>
img:高亮区说明图片<br>
act:当前activity<br>
View:要被说明的控件<br>

2. GuideBean(int img, final Activity act,final Rect simpleRect)
img:高亮区说明图片<br>
act:当前activity<br>
simpleRect:自由绘制的矩形<br>

#### pagerCard介绍
paerCard控件的目的是方便的编写一个可以分页的宫格控件（也可不进行分页显示）。宫格中的图片显示支持矩形，圆角矩形，矩形，圆形的显示方式


#### pagerCard使用说明
* xml属性介绍

|属性名称|属性作用|
|-------|:--------:|
|pagerCardTextColor|标题颜色|
|pagerCardTextSize|标题字体大小|
|seIndicatorColor|选中的指示器颜色|
|unSeIndicatorColor|未选中的指示器颜色|
|indicatorWidth|指示器宽度|
|indicatorHeight|指示器高度|
|pagerCardImgHeight|图片高度|
|pagerCardImgWidth|图片宽度|
|pagerCardRedPointTextSize|右上角文字大小|
|pagerCardRedPointTextColor|右上角文字颜色|
|pagerCardRedPointHeight|右上角红点高度|
|pagerCardRedPointWidth|右上角红点宽度|
|pagerCardRedPointColor|右上角红点颜色|
|needIndicator|是否显示指示器,默认需要|
|itemDecorationColor|分割线颜色|
|itemDecorationWeight|分割线高度|
|itemMarginLeft|左边距|
|itemMarginRight|右边距|
|itemMarginTop|上边距|
|itemMarginBottom|下边距|
|itemMargin|边距|
|itemPadding|内边距|
|itemPaddingTop|上边内边距|
|itemPaddingBottom|下边内边距|
|itemPaddingRight|右边内边距|
|itemPaddingLeft|左边内边距|
|itemBg|宫格背景|
|enableInfinite|是否支持无限循环|
|playDuration|自动播放时间|
|pagerCardImgType|图片显示类型(CIRCLE:圆形，ROUND_CIRCLE:圆角矩形，RECT:矩形)|
|canScrollVertically|是否支持内容滑动，默认不支持)|
|imgCorner|图片圆角弧度（仅对pagerCardImgType使用ROUND_CIRCLE的情况生效）|

* 示例代码如下：
```
 pagerCardView = findViewById(R.id.cardview);
 List<PagerCardBean> bean = new ArrayList<>();
 //PagerCardBean的构造方法中图片为必传参数，其他的set方法可以不设置。
 bean.add(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg").setName("dqq"));
 bean.add(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569647607838&di=daadb04b3c41ab14c6a4eb5ebdb4f5ce&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D432aef2ac35c1038247ecec28211931c%2Fd4628535e5dde7113a95acc6a2efce1b9d1661bf.jpg").setName("dqq"));
 bean.add(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg").setName("dqq"));
 bean.add(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569418221253&di=1770a74bb77874862ef83b5d0dd9deea&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201511%2F14%2F20151114095852_2KEvG.jpeg").setName("dqq"));
 bean.add(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg").setName("dqq"));
 bean.add(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569647607838&di=daadb04b3c41ab14c6a4eb5ebdb4f5ce&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D432aef2ac35c1038247ecec28211931c%2Fd4628535e5dde7113a95acc6a2efce1b9d1661bf.jpg").setName("dqq"));
 //this为监听器，2,3分别代表宫格的行数和列数，行数设置为-1时表示不进行分页显示，即有多少内容就显示多少。
 pagerCardView.setCardContent(bean,getSupportFragmentManager(),2,3,this);
```

#### 更新历史说明
* v1.0.5
  + 修正pagerCard在多个viewpager中使用时滑动可能导致闪退的问题
* v1.0.4
  + 修正在同一个fragment或activity中无法同时使用多个pagerCard的问题
  + 修正pagerCard中旋屏时出现的闪退问题
  + pagerCard添加链式调用的方法添加数据源，也可以使用以前的setCardContent（）方法设置数据源
* v1.0.3
  + pagerCard的xml添加自定义属性
  + pagerCard添加无限循环和自动滑动功能


