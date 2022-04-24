package com.icepoint.framework.autoconfigure.web.security;

import com.icepoint.framework.autoconfigure.web.security.customizer.*;
import com.icepoint.framework.core.util.CaseUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Jiawei Zhao
 */
public class CustomersRegistrar implements ImportBeanDefinitionRegistrar {

    private static final Class<?>[] CUSTOMER_CLASSES = new Class[] {
            AuthorizeRequestCustomizer.class,
            CsrfCustomizer.class,
            ExceptionHandlingCustomizer.class,
            LoginConfigurer.class,
            LogoutCustomizer.class
    };

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        for (Class<?> clazz : CUSTOMER_CLASSES) {
            registerCustomer(registry, clazz);
        }
    }

    private void registerCustomer(BeanDefinitionRegistry registry, Class<?> clazz) {

        String beanName = CaseUtils.toLowerUnderScore(clazz.getSimpleName());
        RootBeanDefinition bd = new RootBeanDefinition(clazz);

        registry.registerBeanDefinition(beanName, bd);
    }
}
