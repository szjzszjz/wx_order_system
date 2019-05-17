package com.szjz.sell.resultObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author szjz
 * @date 2019/5/8 12:13
 */

@Data
public class CategoryObject implements Serializable {

    private static final long serialVersionUID = -4626345709861920609L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductObject> productList;
}
