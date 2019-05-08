package com.szjz.sell.utils;

import com.szjz.sell.resultObject.ResultObject;

/**
 * @author szjz
 * @date 2019/5/8 15:09
 * 调用该类获取最终的返回对象
 */
public class ResultObjectUtil {

    /** 成功获取数据返回 */
    public static ResultObject success(Object data){
        ResultObject<Object> ro = new ResultObject<>();
        ro.setMsg("成功");
        ro.setCode(200);
        ro.setData(data);
        return  ro;
    }

    /** 返回无数据的结果 */
    public static ResultObject success(){
        ResultObject<Object> ro = new ResultObject<>();
        ro.setCode(200);
        ro.setMsg("成功");
        return ro;
    }

    /** 返回错误 */
    public static ResultObject error(Integer code, String msg){
        ResultObject<Object> ro = new ResultObject<>();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }
}
