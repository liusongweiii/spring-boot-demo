package com.aop;

import com.bean.SysLog;
import com.common.Service;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面实现
 */

@Component
@Aspect
public class LogService {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.aop.MyLog)")
    public void methodCachePointcut() {
    }


    /**
     * 切面
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("methodCachePointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        MyLog myLog = getMthodRemark(point);
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < myLog.attrName().length; i++) {
            map.put(myLog.attrName()[i], myLog.attrKey()[i]);
        }

        String id = request.getParameter(myLog.idKey());
        Object o = Service.queryById(id, myLog.serviceClazz());

        StringBuffer sb = new StringBuffer();
        sb.append(myLog.remark() + " ");

        if (o != null) {
            Field[] fields = o.getClass().getDeclaredFields();

            String val, newVal, ann;
            DataName dataName;

            for (Field f : fields) {
                f.setAccessible(true);
                val = String.valueOf(f.get(o));
                newVal = request.getParameter(map.get(f.getName()));
                dataName = f.getAnnotation(DataName.class);

                if (dataName != null && dataName.type()) {
                    if (!val.equals(newVal)) {
                        sb.append(dataName.name() + ": 原值=" + val + " 新值=" + newVal + " ");
                    }
                }

            }
        }
        SysLog log = new SysLog();
        log.setContent(sb.toString());
        Service.getInstance().sysLogService.save(log);

        return point.proceed();

    }


    public static boolean getFieldDataNameAnn(Object obj) {
        DataName dataName;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            dataName = f.getAnnotation(DataName.class);
            if (dataName != null) {
                System.out.println("属性:" + f.getName() + "  注解内容为:" + dataName.name());
            }
        }
        return true;
    }

    /**
     * 获取方法中的中文备注
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static MyLog getMthodRemark(ProceedingJoinPoint joinPoint) throws Exception {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        MyLog methodCache = null;
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                methodCache = m.getAnnotation(MyLog.class);
            }
        }
        return methodCache;
    }


}