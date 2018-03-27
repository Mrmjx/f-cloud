package com.menhai.fcloud;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.menhai.fcloud.base.BaseActivity;
import com.menhai.fcloud.bean.MainModel;
import com.menhai.fcloud.retrofit.ApiCallback;
import com.menhai.fcloud.retrofit.RetrofitCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by mjx on 2016/3/24.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBarAsHome("MVP+Retrofit+Rxjava");

    }




    @OnClick({R.id.button0, R.id.button1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                loadDataByRetrofit();
                break;
            case R.id.button1:
                loadDataByRetrofitRxjava();
                break;

        }
    }

    private void loadDataByRetrofit() {
        showProgressDialog();
        Call<MainModel> call = apiStores().loadDataByRetrofit("101190201");
        call.enqueue(new RetrofitCallback<MainModel>() {
            @Override
            public void onSuccess(MainModel model) {
                dataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                toastShow(msg);
            }

            @Override
            public void onThrowable(Throwable t) {
                toastShow(t.getMessage());
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
        addCalls(call);
    }

    //全国+国外主要城市代码http://mobile.weather.com.cn/js/citylist.xml
    private void loadDataByRetrofitRxjava() {
        showProgressDialog();
        addSubscription(apiStores().loadDataByRetrofitRxjava("101220602"),
                new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        dataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        toastShow(msg);
                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
    }

    private void dataSuccess(MainModel model) {
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }
}
