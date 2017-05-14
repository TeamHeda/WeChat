package com.henu.example.com.wechat.main.fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.main.activity.AddFriendsNextActivity;
import com.henu.example.com.wechat.main.activity.AddFriendsPreActivity;
import com.henu.example.com.wechat.main.activity.FeedBackActivity;
import com.henu.example.com.wechat.main.activity.GroupAddMembersActivity;
import com.henu.example.com.wechat.main.activity.ScanCaptureActivity;
import com.henu.example.com.wechat.main.widget.WCPopWindow;

public class MainFragmentActivity extends AppCompatActivity {

    private TextView unreadLable;
    private TextView unreadAddressLable;

    private Button[] mTabs;
    private ContactListFragment contactListFragment;
    private FragmentFind fragmentFind;
    private FragmentProfile fragmentProfile;
    private ConversationListFragment conversationListFragment;
    private Fragment[] fragments;

    private int index;
    private  int currentTabIndex;


    private static final String TAG_COVERSATION = "TAG_COVERSATION";
    private static final String TAG_CONTACTS = "TAG_CONTACTS";
    private static final String TAG_FIND = "TAG_FIND";
    private static final String TAG_PROFILE = "TAG_PROFILE";

    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        initView();


        contactListFragment =new ContactListFragment();
        fragmentFind=new FragmentFind();
        fragmentProfile=new FragmentProfile();
        conversationListFragment=new ConversationListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,conversationListFragment)
                .add(R.id.fragment_container,contactListFragment)
                .hide(contactListFragment)
                .show(conversationListFragment)
                .commit();
        fragments=new Fragment[]{conversationListFragment,contactListFragment,fragmentFind,fragmentProfile};
    }

    private void initView() {
        unreadLable=(TextView)findViewById(R.id.unread_msg_number);
        unreadAddressLable=(TextView)findViewById(R.id.unread_address_number);
        mTabs=new Button[4];
        mTabs[0]=(Button)findViewById(R.id.btn_conversation);
        mTabs[1]=(Button)findViewById(R.id.btn_address_list);
        mTabs[2]=(Button)findViewById(R.id.btn_find);
        mTabs[3]=(Button) findViewById(R.id.btn_profile);
        mTabs[0].setSelected(true);
        final ImageView ivAdd=(ImageView)findViewById(R.id.iv_add);
        final ImageView iv_search=(ImageView)findViewById(R.id.iv_search);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainFragmentActivity.this, AddFriendsNextActivity.class));
            }
        });
        final WCPopWindow fxPopWindow=new WCPopWindow(this,R.layout.wx_popupwindow_add,new WCPopWindow.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                switch (position){
                    //发起群聊
                    case 0:
                        startActivity(new Intent(MainFragmentActivity.this,GroupAddMembersActivity.class));
                        break;
                    //添加新的好友
                    case 1:
                        startActivity(new Intent(MainFragmentActivity.this,AddFriendsPreActivity.class));
                        break;
                    //扫一扫
                    case 2:
                        startActivity(new Intent(MainFragmentActivity.this, ScanCaptureActivity.class));
                        break;
                    //帮助及反馈
                    case 3:
                        startActivity(new Intent(MainFragmentActivity.this, FeedBackActivity.class));
                        break;
                }
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fxPopWindow.showPopupWindow(ivAdd);
            }
        });

    }
    public void onTabClicked(View view){
        switch (view.getId()){
            case R.id.btn_conversation:
                index=0;
                break;
            case R.id.btn_address_list:
                index=1;
                break;
            case R.id.btn_find:
                index=2;
                break;
            case R.id.btn_profile:
                index=3;
                break;
        }
        if (currentTabIndex!=index){
            FragmentTransaction trx=getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if(!fragments[index].isAdded()){
                trx.add(R.id.fragment_container,fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex=index;
    }
}
