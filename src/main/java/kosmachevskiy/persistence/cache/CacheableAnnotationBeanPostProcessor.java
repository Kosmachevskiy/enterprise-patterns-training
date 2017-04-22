package kosmachevskiy.persistence.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Konstantin Kosmachevskiy
 */
@Component
public class CacheableAnnotationBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheableAnnotationBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Method[] declaredMethods = bean.getClass().getDeclaredMethods();

        // Collect annotated methods //
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Cacheable.class)) {
                annotatedMethods.add(method);
            }
        }

        // Wrap Bean in Proxy in case of at lease one method is annotated //
        if (!annotatedMethods.isEmpty()) {
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new Handler(bean, annotatedMethods));
        } else
            return bean;
    }


    private class Handler implements InvocationHandler {

        /**
         * Storage of methods names as key and cache as value.
         * Map which consists of query as key and last invocation result as value.
         */
        private Map<String, Map<Object, Object>> cacheableMathods = new HashMap<>();
        private Object target;

        Handler(Object bean, List<Method> methods) {
            target = bean;
            for (Method method : methods) {
                cacheableMathods.put(method.getName(), new HashMap<Object, Object>());
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            String methodName = method.getName();

            // If method is not annotated just invoke original method //
            if (cacheableMathods.containsKey(methodName)) {
                Map<Object, Object> methodCache = cacheableMathods.get(methodName);

                // In methods with no args Method will be as a key //
                if (method.getParameterCount() == 0) {
                    if (!methodCache.containsKey(methodName)) {
                        methodCache.put(methodName, method.invoke(target, args));
                    }
                    return methodCache.get(methodName);
                }


                // Lookup annotated parameter or use first arg as key //
                int keyNumber = 0;

                Method declaredMethod = target.getClass()
                        .getDeclaredMethod(method.getName(), method.getParameterTypes());
                Annotation[][] parameterAnnotations = declaredMethod.getParameterAnnotations();

                int paramNum = 0;
                for (Annotation[] parameter : parameterAnnotations) {
                    for (Annotation annotation : parameter) {
                        if (annotation instanceof Key) {
                            keyNumber = paramNum;
                            break;
                        }
                    }
                    // Break look in case of we find key //
                    if(keyNumber>0) break;
                    paramNum++;
                }


                // Put value //
                if (!methodCache.containsKey(args[keyNumber])) {
                    methodCache.put(args[keyNumber], method.invoke(target, args));
                }
                // Return by key //
                return methodCache.get(args[keyNumber]);
            } else {
                return method.invoke(target, args);
            }
        }
    }
}
