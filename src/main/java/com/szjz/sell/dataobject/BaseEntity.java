package com.szjz.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * @author szjz
 * @date 2019/5/15 10:27
 * <p>
 * 基础类
 */

@Data
@DynamicUpdate
public class BaseEntity {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
