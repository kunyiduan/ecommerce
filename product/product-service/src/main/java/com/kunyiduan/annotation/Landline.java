package com.kunyiduan.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/10/09 09:35:00
 */
@Documented
//声明该注解只允许用在类的字段上
@Target({ElementType.FIELD})
//声明注解保留在程序运行期间，可以通过反射获取定义在某个类上的所有注解
@Retention(RetentionPolicy.RUNTIME)
//指定注解的实现类
@Constraint(validatedBy = LandlineValidated.class)
public @interface Landline {

    //校验失败时返回信息
    String message() default "固定电话错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        Landline[] value();
    }

}
