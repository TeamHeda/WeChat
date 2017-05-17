package com.henu.example.com.wechat.main.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.henu.example.com.wechat.Helper;
import com.henu.example.com.wechat.R;
import com.henu.example.com.wechat.main.activity.UserDetailsActivity;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.util.NetUtils;

import java.util.Map;

public class ContactListFragment extends EaseContactListFragment implements View.OnClickListener {

    private static final String TAG = ContactListFragment.class.getSimpleName();

    @Override
    protected void initView() {
        super.initView();

        View headerView=LayoutInflater.from(getActivity()).inflate(R.layout.wx_item_contact_list_header,null);
        headerView.findViewById(R.id.re_newfriends).setOnClickListener(this);
        headerView.findViewById(R.id.re_chatroom).setOnClickListener(this);
        headerView.findViewById(R.id.re_tag).setOnClickListener(this);
        headerView.findViewById(R.id.re_public).setOnClickListener(this);

        listView.addHeaderView(headerView);
        this.titleBar.setVisibility(View.GONE);
        getView().findViewById(R.id.search_bar_view).setVisibility(View.GONE);
        registerForContextMenu(listView);
    }


    @Override
    public void refresh() {
        Map<String,EaseUser> m= Helper.getInstance().getContactList();
        setContactsMap(m);
        super.refresh();
    }

    @Override
    protected void setUpView() {
        titleBar.setRightImageResource(R.drawable.wx_add);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NetUtils.hasDataConnection(getActivity());
            }
        });
        Map<String, EaseUser> m = Helper.getInstance().getContactList();
        setContactsMap(m);
        super.setUpView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EaseUser user=((EaseUser) listView.getItemAtPosition(position));
                if(user!=null&&user.getUserInfo()!=null){
                    startActivity(new Intent(getActivity(), UserDetailsActivity.class));
                }
                startActivity(new Intent(getActivity(), UserDetailsActivity.class));
            }
        });
    }

    @Override
    public void onDestroy() {
        //super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_newfriends:
                // 进入申请与通知页面
                //Toast.makeText(getContext(),"123",Toast.LENGTH_SHORT).show();
                break;
            case R.id.re_chatroom:
                // 进入群聊列表页面

                break;
            case R.id.re_tag:
                //进入直播间
//                startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("chatType", 3).
//                        putExtra("userId",FXConstant.FXLIVE_CHATROOM_ID));

                break;
            case R.id.re_public:
                //进入Robot列表页面

                break;

            default:
                break;
        }
    }
}
