package com.henu.example.com.wechat.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henu.example.com.wechat.Constant;
import com.henu.example.com.wechat.Helper;
import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.domain.EmojiconExampleGroupData;
import com.henu.example.com.wechat.domain.RobotUser;
import com.henu.example.com.wechat.main.activity.ChatSettingGroupActivity;
import com.henu.example.com.wechat.main.activity.ChatSettingSingleActivity;
import com.henu.example.com.wechat.ui.PickAtUserActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.easeui.widget.emojicon.EaseEmojiconMenu;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.PathUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;


public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper{

    private static final int ITEM_VIDEO = 11;
    private static final int ITEM_FILE = 12;
    private static final int ITEM_VOICE_CALL = 13;
    private static final int ITEM_VIDEO_CALL = 14;
    private static final int REQUEST_CODE_SELECT_VIDEO = 11;
    private static final int REQUEST_CODE_SELECT_FILE = 12;
    private static final int REQUEST_CODE_GROUP_DETAIL = 13;
    private static final int REQUEST_CODE_CONTEXT_MENU = 14;
    private static final int REQUEST_CODE_SELECT_AT_USER = 15;

    private static final int REQUEST_CODE_SEND_MONEY = 16;

    private static final int MESSAGE_TYPE_SENT_VOICE_CALL = 1;
    private static final int MESSAGE_TYPE_RECV_VOICE_CALL = 2;
    private static final int MESSAGE_TYPE_SENT_VIDEO_CALL = 3;
    private static final int MESSAGE_TYPE_RECV_VIDEO_CALL = 4;

    //red packet code : 红包功能使用的常量
    private static final int MESSAGE_TYPE_RECV_RED_PACKET = 5;
    private static final int MESSAGE_TYPE_SEND_RED_PACKET = 6;
    private static final int MESSAGE_TYPE_SEND_RED_PACKET_ACK = 7;
    private static final int MESSAGE_TYPE_RECV_RED_PACKET_ACK = 8;
    private static final int REQUEST_CODE_SEND_RED_PACKET = 16;
    private static final int ITEM_RED_PACKET = 16;

    private boolean isRobot;
    private TextView tvName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    protected void setUpView() {
        setChatFragmentListener(this);
        tvName=(TextView)getView().findViewById(R.id.name);
        if(chatType== Constant.CHATTYPE_SINGLE){
            Map<String, RobotUser> robotMap = Helper.getInstance().getRobotList();
            if (robotMap != null && robotMap.containsKey(toChatUsername)) {
                isRobot = true;
            }
            getView().findViewById(R.id.iv_setting_single).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.iv_setting_single).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ChatSettingSingleActivity.class).putExtra("userId", toChatUsername));

                }
            });
            getView().findViewById(R.id.iv_setting_group).setVisibility(View.GONE);
            if (Helper.getInstance().getContactList().containsKey(toChatUsername)) {

                tvName.setText(Helper.getInstance().getContactList().get(toChatUsername).getNick());
            }
        }
        super.setUpView();
        hideTitleBar();

        ((EaseEmojiconMenu) inputMenu.getEmojiconMenu()).addEmojiconGroup(EmojiconExampleGroupData.getData());
        if (chatType == EaseConstant.CHATTYPE_GROUP) {
            getView().findViewById(R.id.iv_setting_group).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.iv_setting_group).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ChatSettingGroupActivity.class).putExtra("groupId", toChatUsername));
                }
            });
            getView().findViewById(R.id.iv_setting_single).setVisibility(View.GONE);
            inputMenu.getPrimaryMenu().getEditText().addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1 && "@".equals(String.valueOf(s.charAt(start)))) {
                        startActivityForResult(new Intent(getActivity(), PickAtUserActivity.class).
                                putExtra("groupId", toChatUsername), REQUEST_CODE_SELECT_AT_USER);
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            EMGroup group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
            if (group == null || group.getAffiliationsCount() <= 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().groupManager().getGroupFromServer(toChatUsername);
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                //获取群成员信息

            }

            tvName.setText("群聊");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CONTEXT_MENU) {
            switch (resultCode) {



                default:
                    break;
            }
        }
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_VIDEO: //send the video
                    if (data != null) {
                        int duration = data.getIntExtra("dur", 0);
                        String videoPath = data.getStringExtra("path");
                        File file = new File(PathUtil.getInstance().getImagePath(), "thvideo" + System.currentTimeMillis());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            Bitmap ThumbBitmap = ThumbnailUtils.createVideoThumbnail(videoPath, 3);
                            ThumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            sendVideoMessage(videoPath, file.getAbsolutePath(), duration);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_CODE_SELECT_FILE: //send the file
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
                            sendFileByUri(uri);
                        }
                    }
                    break;
                case REQUEST_CODE_SELECT_AT_USER:
                    if (data != null) {
                        String username = data.getStringExtra("username");
                        inputAtUsername(username, false);
                    }
                    break;

                case REQUEST_CODE_SEND_MONEY:


                default:
                    break;
            }
        }
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
