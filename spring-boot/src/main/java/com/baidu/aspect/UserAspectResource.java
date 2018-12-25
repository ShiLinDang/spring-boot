package com.baidu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Auther: dangshilin
 * @Date: 2018/12/25
 */
@Aspect// 定义切面类
@Component
public class UserAspectResource {
    /**
     * 定义一个切入点，可以是规则表达式，也可以是某个package下的所有函数，也可以是一个注解,其实就是执行条件，满足此条件的就切入
     * 这里是：com.baidu.controller包以及子包下的所有类的所有方法，返回类型任意，方法参数任意
     */
    @Pointcut("execution(* com.baidu.controller..*.*(..))")
    public void webLog(){}

    /**
     * 在切入点之前执行
     * @param joinPoint 连接点，实在应用执行过程中能够插入切面的一个点，这个点可以是调用方法时，抛出异常时，甚至是修改一个字段时，
     *                  切面代码可以利用这些连接点插入到应用的正常流程中，并添加新的行为，如日志、安全、事务、缓存等。
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //记录请求日志
        System.out.println(("==========================================>url；"+request.getRequestURL().toString()));
        System.out.println(("==========================================>httpMethod:"+request.getMethod()));
        System.out.println(("==========================================>ip:"+ request.getRemoteAddr()));
        System.out.println(("==========================================>classMethod : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()));
        System.out.println(("==========================================>args："+ Arrays.toString(joinPoint.getArgs())));

    }

    /**
     * 在切入点之后执行
     * @param response
     */
    @AfterReturning(returning = "response",pointcut = "webLog()")
    public void doAfterReturn(Object response){
        //记录返回值
        System.out.println(("==========================================>response:"+response));
    }
}
