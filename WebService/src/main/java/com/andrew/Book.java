package com.andrew;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "�ѥ����")
public class Book {
    @ApiModelProperty(value = "�Ǹ�", required = true)
    private Integer bookid;
    @ApiModelProperty(value = "�ѦW", required = true)
    private String name;
    @ApiModelProperty(value = "�@��", required = true)
    private String author;
}