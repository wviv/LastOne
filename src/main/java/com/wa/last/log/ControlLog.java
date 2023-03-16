package com.wa.last.log;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 
 * @date 2019/8/30 9:40
 */
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ControllerHandler.class)
public @interface ControlLog {
}
