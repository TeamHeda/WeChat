package com.henu.example.com.wechat.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.main.activity.ProfileActivity;
import com.henu.example.com.wechat.main.activity.SettingsActivity;

public class FragmentProfile extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }
    private void initView(){
        ImageView ivAvatar= (ImageView) getView().findViewById(R.id.iv_avatar);
        TextView tvNick= (TextView) getView().findViewById(R.id.tv_name);
        TextView tvFxid= (TextView) getView().findViewById(R.id.tv_fxid);


    }
    private void setListener(){
        getView().findViewById(R.id.re_myinfo).setOnClickListener(this);
        getView().findViewById(R.id.re_setting).setOnClickListener(this);
        getView().findViewById(R.id.re_wallet).setOnClickListener(this);
        getView().findViewById(R.id.re_xiangce).setOnClickListener(this);
        getView().findViewById(R.id.re_xiangce).setOnClickListener(this);
        getView().findViewById(R.id.re_smile).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re_myinfo:
                startActivityForResult(new Intent(getActivity(), ProfileActivity.class),0);
                break;

            case R.id.re_setting:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.re_wallet:
                //RedPacketUtil.startChangeActivity(getActivity());
                break;
            case R.id.re_xiangce:
                //startActivity(new Intent(getActivity(), SocialFriendActivity.class).putExtra("friendID", DemoHelper.getInstance().getCurrentUsernName()));
                break;
            case R.id.re_smile:
                //startActivity(new Intent(getActivity(), PasswordResetActivity.class).putExtra("isReset",true));
                break;
        }
    }
}
