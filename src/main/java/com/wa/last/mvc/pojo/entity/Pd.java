package com.wa.last.mvc.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Pd implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hisInpatientId;

    private String patientIndex;

    private String name;
}
