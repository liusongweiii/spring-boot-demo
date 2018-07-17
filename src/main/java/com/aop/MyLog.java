package com.aop;

import java.lang.annotation.*;

/**
 * 日志切面注解
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    String remark();
    Class serviceClazz();
    String idKey() default "id";
    String[] attrName();
    String[] attrKey();

}