package com.chatuiproject.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //页面控件
    private Button leftSend;
    private Button rightSend;
    private EditText inputText;
    private RecyclerView msgRecyclerView;

    private List<Msg> msgList=new ArrayList<>();    //消息列表
    private MsgAdapter msgAdapter;  //RecyclerView适配器
    private String content; //所要发送的消息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();

        initUI();
    }

    /**
     * 初始化界面
     */
    private void initUI(){
        msgRecyclerView=findViewById(R.id.msg_recycler_view);
        leftSend=findViewById(R.id.left_send);
        rightSend=findViewById(R.id.right_send);
        inputText=findViewById(R.id.input_text);

        //初始化适配器
        msgAdapter=new MsgAdapter(msgList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);    //RecyclerView设置布局方式
        msgRecyclerView.setAdapter(msgAdapter);     //RecyclerView设置适配器
        //设置监听
        leftSend.setOnClickListener(this);
        rightSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_send:
                content=inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_RECEIVED);
                    msgList.add(msg);
                    //当有新消息时刷新RecyclerView中的显示
                    msgAdapter.notifyItemInserted(msgList.size()-1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
                break;
            case R.id.right_send:
                content=inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    //当有新消息时刷新RecyclerView中的显示
                    msgAdapter.notifyItemInserted(msgList.size()-1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
                break;
        }
    }

    public void initMsg(){
        Msg msg1=new Msg("1111",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("2222",Msg.TYPE_SEND);
        msgList.add(msg2);
    }
}
