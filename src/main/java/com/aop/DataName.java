package com.aop;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DataName {
    /**
     * 字段名称
     * @return
     */
    String name() default "";
    boolean type() default false;

 }
