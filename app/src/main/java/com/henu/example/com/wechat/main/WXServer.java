package com.henu.example.com.wechat.main;

/**
 * Created by node4 on 2017/5/19.
 */

public class WXServer {
    public static String HOST="http://192.168.56.1/WeChat/index.php/";
    public static String URL_REGISTER=HOST+"User/register";
    public static final String URL_Get_UserInfo = HOST + "Inquery/getUserInfo";

    public static final String URL_AVATAR= HOST + "upload/";


    public static String KEY_USER_INFO="usrinfo";

    public static final String JSON_KEY_NICK ="nick";
    public static final String JSON_KEY_FXID ="fxid";
    public static final String JSON_KEY_HXID ="hxid";
    public static final String JSON_KEY_SEX ="sex";
    public static final String JSON_KEY_AVATAR ="avatar";
    public static final String JSON_KEY_CITY ="city";
    public static final String JSON_KEY_PROVINCE ="province";
    public static final String JSON_KEY_TEL ="tel";
    public static final String JSON_KEY_SIGN ="sign";

    public static final String EXTRA_USER_ID = "userId";
}
