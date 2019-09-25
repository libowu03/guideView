# guideView

#### 介绍
此依赖的目的是为大部分view添加新手引导。目前activity，fragment的任意view都可以使用此依赖来制作新手引导。新手引导的高亮区支持控件本身的形状，圆形，矩形，椭圆形。<br>
![](https://images.gitee.com/uploads/images/2019/0806/145419_c5ce617d_1951678.gif)<br>
![](https://images.gitee.com/uploads/images/2019/0806/145222_ade564b4_1951678.gif)

#### 安装教程
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}

dependencies {
	        implementation 'com.github.libowu03:guideview:v1.0.1'
}
  
```

#### 使用说明
使用代码如下：
```
 //新手集合必须添加，其他可不需要可不设置
 List<GuideBean> guideBeans = new ArrayList<>();
 guideBeans.add(new GuideBean(R.drawable.guide/*新手引导图片*/,this/*activity*/,menuBtn/*要说明的view*/));
 GuideDialog guideDialog = new GuideDialog(getWindow().getDecorView()/*activity或fragment的主view*/);
 guideDialog.setGuideBeans(guideBeans);
 //设置高亮区弹窗,传入的view为activity或fragment的主界面的view，activity可以通过getWindow().getDecorView()的方式获取，fragment可以在  onViewCreated中获取到，用于判断主view是否可以获取到控件的位置参数了。
        guideDialog = new GuideDialog(view);
        //设置高亮集合
        guideDialog.setGuideBeans(guides);
        //设置是否启用精确点击（true时只有点击高亮区时才执行下一步操作，默认为false）
        guideDialog.setExactClick(false);
        //是否启用一个屏幕内显示多个高亮区（true时一个屏幕内将显示出高亮集合中的所有内容，默认为false）
        guideDialog.setOpenMore(true);
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
 guideDialog.show(getSupportFragmentManager(),getClass().getName());
```

#### 构造方法说明
1.GuideBean(int img, Activity act, View view)
img:高亮区说明图片
act:当前activity
View:要被说明的控件

2.GuideBean(int img, Activity act, View view ,int position)
img:高亮区说明图片
act:当前activity
View:要被说明的控件
position:控件说明图片相对于控件的位置

3.GuideBean(int img, Activity act, View view,boolean isSimpleShape,byte shape)
img:高亮区说明图片
act:当前activity
View:要被说明的控件
isSimpleShape:高亮区是否需要显示基本几何图形。默认不是基本几何图形。
shape：如果是基本几何图形的高亮区，则这里定义基本几何图形的形状

4.GuideBean(int img, Activity act, View view,boolean isSimpleShape,byte shape,int position)
img:高亮区说明图片
act:当前activity
View:要被说明的控件
isSimpleShape:高亮区是否需要显示基本几何图形。默认不是基本几何图形。
shape：如果是基本几何图形的高亮区，则这里定义基本几何图形的形状
position:控件说明图片相对于控件的位置

5.GuideBean(int img,Activity act,View view,boolean isSimpleShape,byte shape,int position,int marginLeft,int marginRight,int marginTop,int marginBottom)
img:高亮区说明图片
act:当前activity
View:要被说明的控件
isSimpleShape:高亮区是否需要显示基本几何图形。默认不是基本几何图形。
shape：如果是基本几何图形的高亮区，则这里定义基本几何图形的形状
position:控件说明图片相对于控件的位置
剩下的参数是设置上下左右的margin
