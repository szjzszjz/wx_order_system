package com.szjz.sell.resultObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author szjz
 * @date 2019/5/8 11:47
 * 返回前端的结果对象
 */


@Data
public class ResultObject<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /** 返回的数据 */
    @JsonProperty("data")
    private T data;

}
