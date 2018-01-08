package com.chatuiproject.android;

/**
 * Created by chenlei on 2018/1/8.
 *
 */

public class Msg {
    public static final int TYPE_RECEIVED=0;    //收到消息的标志
    public static final int TYPE_SEND=1;    //发出消息的标志

    private String content;     //消息内容
    private int type;   //消息类型

    public Msg(String content,int type){
        this.content=content;
        this.type=type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
