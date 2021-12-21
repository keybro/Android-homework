package com.example.individualview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.individualview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class helloChartsActivity extends AppCompatActivity {
    /**
     * 折线图
     */
    @BindView(R.id.lineChart)
    LineChartView lineChartView;

    /**
     * 饼图
     */
    @BindView(R.id.pieChart)
    PieChartView pieChartView;

    //自文本
    @BindView(R.id.name)
    TextView nameTextView;

    @BindView(R.id.value)
    TextView valueTextView;


    //x轴坐标数据
    private String [] xArray = {"1","2","3","4"};

    //y轴的数据
    private int [] score = {8,4,6,3};

    //轴坐标数据
    private List<AxisValue> mAxisXValues = new ArrayList<>();

    //点坐标数据
    private List<PointValue> mPointValues = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_charts);

        ButterKnife.bind(this);

        //给x轴添加数据
        getAxisXLables();
        //给折线图添加数据点
        getAxisPoint();
        //初始化图标
        initLineChart();

        //设置饼图数据
        setPieChartData();

    }

    //设置X轴显示
    private void getAxisXLables() {
        for (int i = 0; i < xArray.length; i++) {
            //将xArray中的值加入得到mAxisXValues中
                mAxisXValues.add(new AxisValue(i).setLabel(xArray[i]));
        }
    }

    //图标的数据点显示
    private void getAxisPoint(){
        for (int i = 0;i< score.length;i++){
            //将score中的值加入到mPointValues中
            mPointValues.add(new PointValue(i,score[i]));
        }
    }

    /**
     * 折线图初始化
     */
    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#6699CC"));  //折线的颜色
        List<Line> lines = new ArrayList<>();
        //数据点形状
        line.setShape(ValueShape.CIRCLE);
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(false);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis();//X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.RED);  //设置字体颜色
        //axisX.setName("");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        //axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setTextColor(Color.RED);
        data.setAxisYLeft(axisY);  //Y轴设置在左边

        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);
        lineChartView.setMaxZoom((float) 2);//最大方法比例
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChartView.setLineChartData(data);
        lineChartView.setVisibility(View.VISIBLE);

        //设置X轴显示个数
        Viewport v = new Viewport(lineChartView.getMaximumViewport());
        v.left = 0;
        v.right = 5;
        lineChartView.setCurrentViewport(v);
    }


    /**
     * 饼图初始化获取数据
     */
    private void setPieChartData() {

        List values = new ArrayList<>();
        //颜色list
        final List colorData = new ArrayList<>();
        //标签信息
        final List titleData = new ArrayList<>();
        //10种颜色
        colorData.add(Color.parseColor("#85B74F"));
        colorData.add(Color.parseColor("#009BDB"));
        colorData.add(Color.parseColor("#FF0000"));
        colorData.add(Color.parseColor("#9569F8"));
        colorData.add(Color.parseColor("#F87C67"));
        colorData.add(Color.parseColor("#F1DA3D"));
        colorData.add(Color.parseColor("#87EA39"));
        colorData.add(Color.parseColor("#48AEFA"));
        colorData.add(Color.parseColor("#4E5052"));
        colorData.add(Color.parseColor("#D36458"));
        //10中标签
        titleData.add("华为 Mate 10");
        titleData.add("荣耀6X");
        titleData.add("一加5T");
        titleData.add("华为Mate 10 Pro");
        titleData.add("魅族note6");
        titleData.add("360N6S");
        titleData.add("三星GALAXY Note 8");
        titleData.add("苹果iPhone X");
        titleData.add("vivo X20");
        titleData.add("OPPO R11s");

        //10种模块，数据100随机数
        for (int i = 0; i < colorData.size(); i++) {
            //注意数据list大一定要对应颜色值大小否者越界
            SliceValue sliceValue = new SliceValue((float) (100 * Math.random()), (int)colorData.get(i));
            values.add(sliceValue);
        }

        final PieChartData pieChardata = new PieChartData();
        //显示标签信息
        pieChardata.setHasLabels(true);
        //true：只有点击对应的模块才显示标签信息  false：全部展示出来
        pieChardata.setHasLabelsOnlyForSelected(false);
        //true：占的百分比否显示在饼图外面 false：显示在模块的中间
        pieChardata.setHasLabelsOutside(true);
        //true：环形显示 false：圆形显示
        pieChardata.setHasCenterCircle(true);
        //设置每个模板之间的间隙
        pieChardata.setSlicesSpacing(5);
        //只有设置样式为圆环才能有效设置文字 （在assets目录下新建fonts目录，把ttf字体文件放到这）
        //Typeface tf = Typeface.createFromAsset(getAssets(), "你的字体资源文件路径");
        //pieChardata.setCenterText1Typeface(tf);
        //填充数据 注意不能放在最后 否则就只显示条
        pieChardata.setValues(values);

        //设置中间环形的颜色 只有setHasCenterCircle(true)为环形时配合使用 这里我的模式为圆形故不用
        pieChardata.setCenterCircleColor(Color.WHITE);
        //设置环形的大小级别 也是配合setHasCenterCircle(true)使用
        pieChardata.setCenterCircleScale(0.3f);

        //将参数设置到控件上
        pieChartView.setPieChartData(pieChardata);
        //true：点击选中模块变大  false：只有手指按住模板时才变大，手指离开恢复原状
        pieChartView.setValueSelectionEnabled(true);
        pieChartView.setAlpha(0.9f);//设置透明度
        //设置饼图大小 值越大图越大 1是与父控件相等（如果设置便签信息在饼图外面建议设置0.9f 否则会出现展示不全）
        pieChartView.setCircleFillRatio(0.9f);

        //点击事件 (只有设置样式为圆环才能有效)
        pieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                /**
                 *字体大小(查看了源码 text1：默认为42 text2：默认16 无论怎么修改字体都不能改变原因未知_(:з」∠)_)
                 * 同时注释Text1参数设置text2也不能显示出来...
                 * 建议另写TextView 赋值上去
                 */
                //设置上面文字显示内容
                //pieChardata.setCenterText1(titleData.get(i));
                //设置颜色
                //pieChardata.setCenterText1Color(colorData.get(i));
                //字体大小（无用）
                //pieChardata.setCenterText1FontSize(42);
                //pieChardata.setCenterText2(String.valueOf(sliceValue.getValue()));
                //pieChardata.setCenterText2Color(colorData.get(i));
                //pieChardata.setCenterText2FontSize(16);

                //自建文本赋值上去
                nameTextView.setText(String.valueOf(titleData.get(i)));
                valueTextView.setText(sliceValue.getValue() + "");
            }

            @Override
            public void onValueDeselected() {
            }
        });
    }
}