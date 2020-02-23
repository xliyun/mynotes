package com.liyun.learcn.annotation;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;


@Inherited//可继承
@Repeatable(MyAnntations.class)//可重复
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE
,TYPE_PARAMETER
        ,TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @interface MyAnnotation {

    String[] value() default "hello";
}
