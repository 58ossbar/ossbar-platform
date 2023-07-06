package com.ossbar.modules.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

	/**
     * 时间，默认为10秒  => 1000*10
     * @return
     */
    long value() default 1000*10;
    
    String msg() default "重复请求，请稍后再试";

}
