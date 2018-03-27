package com.menhai.fcloud;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.menhai.fcloud.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mjx on 2018/3/27.
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.home_fragment_fire)
    LinearLayout fireFragmentLay;
    @Bind(R.id.home_fragment_incident)
    LinearLayout incidentFragmentLay;
    @Bind(R.id.home_fragment_manager)
    LinearLayout managerFragmentLay;
    @Bind(R.id.home_fragment_mine)
    LinearLayout mineFragmentLay;

    FragmentTransaction fragmentTransaction;
    private final static String packageName = "com.menhai.fcloud.fragment.";
    String[] fragmentName = {"FireFragment", "IncidentFragment", "ManagerFragment", "MineFragment"};
    Fragment[] fragments = new Fragment[4];  //保存碎片
    int currIndex = -1;   //当前显示的碎片下标
    LinearLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tab = (LinearLayout)findViewById(R.id.tab);
        tab.getChildAt(0).setSelected(true);
        showFragment(0);
    }

    @OnClick({R.id.home_fragment_fire, R.id.home_fragment_incident, R.id.home_fragment_manager, R.id.home_fragment_mine})
    public void onClick(View view) {
            String tag = (String) view.getTag();//拿到选中的imageview的tag
            showFragment(Integer.parseInt(tag)); //直接设置显示碎片
            tab.getChildAt(Integer.parseInt(tag)).setSelected(true);       //底部菜单图片转换
    }


    public void showFragment(int index) {
        if (index == currIndex) return;
        fragmentTransaction = getFragmentManager().beginTransaction();  //获取到碎片事务管理
        if (currIndex != -1) {
            //将上一个碎片隐藏
            fragmentTransaction.hide(fragments[currIndex]);
            tab.getChildAt(currIndex).setSelected(false);
        }
        if (fragments[index] == null) {  // 还没创建该碎片实例
            CreadeFragment(index); //创建碎片
            /**显示当前碎片视图
             * 参数int：显示的FrameLayout的id； fragment：在该视图中显示的fragement; string:tag视图标志，可以自定义
             * */
            fragmentTransaction.add(R.id.fragments, fragments[index], index + "");
        } else { //已存在碎片实例
            fragmentTransaction.show(fragments[index]);//直接显示
        }
        tab.getChildAt(index).setSelected(true);
        currIndex = index;   // 更新当前显示的碎片
        fragmentTransaction.commit();//提交事务
    }

    public void CreadeFragment(int index) {
        try {
            //利用反射 创建当前碎片的实例
            fragments[index] = (Fragment) Class.forName(packageName + fragmentName[index]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
