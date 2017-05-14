package com.henu.example.com.wechat.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.main.activity.NearPeopleActivity;
import com.henu.example.com.wechat.main.activity.ScanCaptureActivity;
import com.henu.example.com.wechat.main.activity.ShakeActivity;
import com.henu.example.com.wechat.main.activity.SocialMainActivity;

public class FragmentFind extends Fragment implements View.OnClickListener {


    private RelativeLayout re_friends,re_qrcode,re_fujin,re_piaoliuping,re_gouwu,re_youxi,re_yaoyiyao;
    private String userID="123";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_find, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setOnClick();
    }
    private void initView() {
        re_friends = (RelativeLayout) getView().findViewById(R.id.re_friends);
        re_qrcode = (RelativeLayout) getView().findViewById(R.id.re_qrcode);
        re_fujin = (RelativeLayout) getView().findViewById(R.id.re_fujin);
        re_piaoliuping = (RelativeLayout) getView().findViewById(R.id.re_piaoliuping);
        re_gouwu = (RelativeLayout) getView().findViewById(R.id.re_gouwu);
        re_youxi = (RelativeLayout) getView().findViewById(R.id.re_youxi);
        re_yaoyiyao = (RelativeLayout) getView().findViewById(R.id.re_yaoyiyao);
    }
    private void setOnClick() {
        re_friends.setOnClickListener(this);
        re_qrcode.setOnClickListener(this);
        re_fujin.setOnClickListener(this);
        re_piaoliuping.setOnClickListener(this);
        re_gouwu.setOnClickListener(this);
        re_youxi.setOnClickListener(this);
        re_yaoyiyao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.re_friends:
                if (!TextUtils.isEmpty(userID)) {
                    startActivity(new Intent(getActivity(), SocialMainActivity.class).putExtra("userID", userID));
                }
                break;
            case R.id.re_qrcode:
                startActivity(new Intent(getActivity(), ScanCaptureActivity.class));
                break;
            case R.id.re_yaoyiyao:
                if (!TextUtils.isEmpty(userID)) {
                    startActivity(new Intent(getActivity(), ShakeActivity.class).putExtra("userID", userID));
                }
                break;
            case R.id.re_fujin:
                startActivity(new Intent(getActivity(), NearPeopleActivity.class));
                break;
            case R.id.re_piaoliuping:
                break;
            case R.id.re_gouwu:
                break;
            case R.id.re_youxi:
                break;

        }
    }
}
