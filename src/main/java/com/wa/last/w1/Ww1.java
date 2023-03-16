package com.wa.last.w1;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author lvzhili
 * @Create 2021/3/9 16:30
 * @Version 1.0.0
 */
@Data
public class Ww1 implements Serializable {

    @ApiModelProperty("排序字段")
    private String sort;
    @ApiModelProperty("排序方式 desc、 asc")
    private String order;
    @ApiModelProperty("页码")
    private Integer start;
    @ApiModelProperty("每页行数")
    private Integer size;

    @ApiModelProperty("根据名字和编码模糊搜索")
    private String searchName;

    @ApiModelProperty("根据项目编码搜索")
    private String itemCode;

    @ApiModelProperty("知识属性")
    private String attribute;

    @ApiModelProperty("状态: 0 停用，1 正常")
    private Integer status;

    @ApiModelProperty("发布情况: 0 草稿，1 已发布")
    private Integer releaseCondition;

    @ApiModelProperty("租户id")
    private String tenantId;

    @ApiModelProperty("静态知识分组id")
    private String dictionaryGroupsId;

    @ApiModelProperty("是否分页 默认是")
    private boolean pageFlag = true;

    @ApiModelProperty("静态知识分类code")
    private String dictionaryGroupsTypeCode;


    private List<String> staticKnowledgeType;
}
