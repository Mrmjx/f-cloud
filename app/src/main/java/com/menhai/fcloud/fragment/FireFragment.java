package com.menhai.fcloud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menhai.fcloud.R;
import com.menhai.fcloud.base.BaseFragment;

/**
 * Created by mjx on 2018/3/27.
 */

public class FireFragment extends BaseFragment{



    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_fire,null);
        return view;
    }
}
