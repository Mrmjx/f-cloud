package com.menhai.fcloud.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.menhai.fcloud.R;
import com.menhai.fcloud.activity.PersonSettingActivity;
import com.menhai.fcloud.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by mjx on 2018/3/27.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.mine_person_setting_lay)
    FrameLayout personSetting;

    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_mine,null);
        return view;
    }

    @OnClick({R.id.mine_person_setting_lay})
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.mine_person_setting_lay:
 Intent intent = new Intent(getActivity(), PersonSettingActivity.class);
 startActivity(intent);
                break;
        }
    }
}
