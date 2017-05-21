package com.henu.example.com.wechat.main.utils;

import android.util.Log;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.henu.example.com.wechat.main.WXServer;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;

/**
 * Created by huangfangyi on 2016/7/5.\
 * QQ:84543217
 */
public class JSONUtil {

    public static EaseUser Json2User(JSONObject userJson) {
        EaseUser easeUser = new EaseUser(userJson.getString(WXServer.JSON_KEY_HXID));
        easeUser.setNick(userJson.getString(WXServer.JSON_KEY_NICK));
        easeUser.setAvatar(userJson.getString(WXServer.JSON_KEY_AVATAR));
        easeUser.setUserInfo(userJson.toJSONString());
        EaseCommonUtils.setUserInitialLetter(easeUser);
        return easeUser;
    }

    public static JSONObject User2Json(EaseUser user) {
        JSONObject jsonObject = new JSONObject();
        String userInfo = user.getUserInfo();
        try {
            if (userInfo != null) {

                jsonObject = JSONObject.parseObject(userInfo);
            }
        } catch (JSONException e) {

              Log.d("JSONUtil----->>","User2Json error");
        }

        return jsonObject;

    }


}

