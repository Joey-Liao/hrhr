package com.uestc.appinterface.Exception;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uestc.appinterface.Model.StatusCode;

public class MyException extends Exception{
    /*无参构造函数*/
    public MyException(Exception ex){
        super(ex.getMessage());
        if(ex.getClass() == this.getClass())
        {
            statuscode = ((MyException)ex).getStatuscode();
            data = ((MyException)ex).getData();
        }

    }

    JSONObject data =new JSONObject();
    StatusCode statuscode = StatusCode.error_unkown;

    //用详细信息指定一个异常
    public MyException(StatusCode statuscode,String message,JSONObject data){

        super(message);
        this.statuscode = statuscode;
        this.data = data;
    }



    public JSONObject getData() {
        return data;
    }

    public StatusCode getStatuscode() {
        return statuscode;
    }
}