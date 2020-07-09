package com.kunyiduan.annotation.phone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
//声明该注解只允许用在类的字段上
@Target({ ElementType.FIELD})
//声明注解保留在程序运行期间，可以通过反射获取定义在某个类上的所有注解
@Retention(RetentionPolicy.RUNTIME)
//指定注解的实现类
@Constraint(validatedBy = PhoneValidated.class)
public @interface Phone {

    //校验失败时返回信息
    String message() default "手机号码错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Phone[] value();
    }

}

//    ElementType.TYPE：说明该注解只能被声明在一个类前。
//    ElementType.FIELD：说明该注解只能被声明在一个类的字段前。
//    ElementType.METHOD：说明该注解只能被声明在一个类的方法前。
//    ElementType.PARAMETER：说明该注解只能被声明在一个方法参数前。
//    ElementType.CONSTRUCTOR：说明该注解只能声明在一个类的构造方法前。
//    ElementType.LOCAL_VARIABLE：说明该注解只能声明在一个局部变量前。
//    ElementType.ANNOTATION_TYPE：说明该注解只能声明在一个注解类型前。
//    ElementType.PACKAGE：说明该注解只能声明在一个包名前

//    RetentionPolicy.SOURCE : 注解只保留在源文件中
//    RetentionPolicy.CLASS: 注解保留在class文件中，在加载到JVM虚拟机时丢弃
//    RetentionPolicy.RUNTIME : 注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解
