package com.wa.last.mvc.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.wa.last.mvc.model.LocalDateTimeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author viv
 * @since 2021-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DownVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hisInpatientId;

    private String patientIndex;

    private String name;

    private String jcxmzw;

    private String ybjcjg;

    @ExcelProperty( converter = LocalDateTimeConverter.class)
    private LocalDateTime jgczsj;

    private Map<String,String> result;
    private Map<String,String> result1;
    private Map<String,String> result2;
}
