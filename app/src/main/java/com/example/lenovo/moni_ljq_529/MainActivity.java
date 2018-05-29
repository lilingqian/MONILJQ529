package com.example.lenovo.moni_ljq_529;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.moni_ljq_529.app.App;
import com.example.lenovo.moni_ljq_529.bean.HistoricalBean;
import com.fynn.fluidlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_title)
    TitleItem mainTitle;
    @BindView(R.id.fluid_layout)
    FluidLayout fluidLayout;
    @BindView(R.id.but_clear)
    Button butClear;


    private List<HistoricalBean> list;
    private HistoricalBeanDao historicalBeanDao;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = new ArrayList<>();

        //创建数据库
        historicalBeanDao = App.getInstance().getDao().getHistoricalBeanDao();

        //得到自定义控件上的值
        mainTitle.setOnTitleClickListener(new TitleItem.OnTitleClickListener() {
            @Override
            public void onClick(String s) {
                //当前页面中的控件清空
                fluidLayout.removeAllViews();
                //创建自定义的HistoricalBean
                HistoricalBean historicalBean = new HistoricalBean();
                //把从自定义控件上得到的s值放到bean对象中
                historicalBean.setName(s);
                //把对象存到数据库中,不添加重复的字段名
                MainActivity.this.historicalBeanDao.insertOrReplace(historicalBean);
                //清空list中的数据
                list.clear();
                //添加完数据后，再次查询数据库，把刚才添加的搜索内容，展示出来
                list.addAll(MainActivity.this.historicalBeanDao.loadAll());
                genTag();
            }
        });
        //查询数据库
        list.addAll(this.historicalBeanDao.loadAll());
        genTag();

    }

    private void genTag() {
        for (int x = 0; x < list.size(); x++) {
            tv = new TextView(MainActivity.this);
            tv.setText(list.get(x).getName());
            tv.setTextSize(16);
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 12, 12, 12);
            fluidLayout.addView(tv, params);
        }
    }

    @OnClick(R.id.but_clear)
    public void onViewClicked() {
        fluidLayout.removeAllViews();
        historicalBeanDao.deleteAll();
    }
}

