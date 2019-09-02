package com.szjz.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author szjz
 * @date 2019/5/15 19:17
 * 卖家信息
 */
@Data
@Entity
public class SellerInfo implements Serializable {


    private static final long serialVersionUID = -4479586993810851715L;
    /**
     * 卖家id
     */
    @Id
    private String sellerId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 微信openID
     */
    private String openid;
}
