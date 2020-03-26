package com.wiwj.appinterface.Model;

import com.alibaba.fastjson.JSONObject;

public class ResponseObj<T> {
    private  Integer Code;
    private  String Message;
    private  String DetailMessage;
    private  T Data;

    public ResponseObj(StatusCode code, String detail,T data) {
        Code = code.Code;
        Message = code.Message;
        DetailMessage = detail;
        Data = data;
    }



    public JSONObject tojson() {
        JSONObject obj = new JSONObject();
        obj.put("Code",Code);
        obj.put("Message",Message);
        obj.put("DetailMessage",DetailMessage);
        obj.put("Data",Data);
        return  obj;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDetailMessage() {
        return DetailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        DetailMessage = detailMessage;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
