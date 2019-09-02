package com.szjz.sell.resultObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author szjz
 * @date 2019/5/8 11:47
 * 返回前端的结果对象
 */


@Data
public class ResultObject<T> implements Serializable {

    private static final long serialVersionUID = 1578762596998649599L;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回的数据
     */
    @JsonProperty("data")
    private T data;

}
