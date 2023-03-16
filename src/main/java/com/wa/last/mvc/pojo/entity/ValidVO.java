package com.wa.last.mvc.pojo.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ValidVO {

    @NotBlank(message = "String 不能为空")
    private String string;
    @NotBlank
    private int integer;
}
