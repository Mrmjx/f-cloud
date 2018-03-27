package com.menhai.fcloud.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import butterknife.Bind
import butterknife.OnClick
import com.menhai.fcloud.R
import com.menhai.fcloud.base.BaseActivity

/**
 * Created by mjx on 2018/3/27.
 */
class PersonSettingActivity:BaseActivity(),View.OnClickListener{


    @Bind(R.id.mine_setting_modify_head)
    var modifyHeadLF:FrameLayout ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_setting)
    }

    @OnClick(R.id.mine_setting_modify_head)
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.mine_person_setting_lay -> {
                val intent = Intent(this, ModifyHeadIconActivity::class.java)
                startActivity(intent)
            }
        }

    }
}