package com.chatuiproject.android;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 *  消息列表适配器
 *  @version 1.0
 *  @author chenlei
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{
    private List<Msg> mMsgList; //消息列表
    private Msg msg;    //消息对象
    /**
     * 构造函数，得到传入消息列表
     * @param msgList 消息列表
     */
    public MsgAdapter(List<Msg> msgList){
        this.mMsgList=msgList;
    }

    /**
     * 用于创建ViewHolder实例
     * @param parent MainActivity的布局,RecyclerView的父布局
     * @param viewType 布局类型
     * @return ViewHolder实例
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewHolder对象
        ViewHolder viewHolder;
        //通过LayoutInflater创建布局，并初始化viewHolder
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        viewHolder=new ViewHolder(view);
        //为两个对话框添加点击事件
        viewHolder.leftMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),msg.getContent(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.rightMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),msg.getContent(),Toast.LENGTH_SHORT).show();
            }
        });

        //返回viewHolder实例
        return viewHolder;
    }

    /**
     * 通过ViewHolder对Recycler子项数据进行赋值
     * @param holder ViewHolder实例
     * @param position 当前RecyclerView子项位置
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        msg=mMsgList.get(position); //得到当前子项的消息
        //如果是收到的消息
        if(msg.getType()==Msg.TYPE_RECEIVED){
            //显示左边消息布局，将右边消息布局隐蔽
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        }
        //如果是发送的消息
        if(msg.getType()==Msg.TYPE_SEND){
            //显示右边消息布局，将左边消息布局隐蔽
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    /**
     * 得到RecyclerView子项总数
     * @return RecyclerView子项个数
     */
    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    /**
     * 内部类ViewHolder继承自RecyclerView.ViewHolder
     * 初始化RecyclerView子项布局，缓存控件实例
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayoutCompat leftLayout;     //左布局
        LinearLayoutCompat rightLayout;     //右布局

        TextView leftMsg;   //左侧收到的消息
        TextView rightMsg;  //右侧发送的消息

        /**
         * 构造函数，传入RecyclerView子项的最外层布局
         * @param itemView RecyclerViwe子项的最外层布局
         */
        public ViewHolder(View itemView) {
            super(itemView);
            //得到布局控件对象
            leftLayout=itemView.findViewById(R.id.left_layout);
            rightLayout=itemView.findViewById(R.id.right_layout);
            //得到文本控件对象
            leftMsg=itemView.findViewById(R.id.left_msg);
            rightMsg=itemView.findViewById(R.id.right_msg);
        }
    }
}
