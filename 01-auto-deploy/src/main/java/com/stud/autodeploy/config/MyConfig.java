package com.stud.autodeploy.config;

import ch.qos.logback.core.db.DBHelper;
import com.stud.autodeploy.bean.Person;
import com.stud.autodeploy.bean.Pet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 从容器中获取组件时候：
 * Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
 *
 * Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式
 */
@Import({Person.class, DBHelper.class})
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(name = "autoDeployApplication")
@ImportResource("classpath:beans.xml")
public class MyConfig {

    @Bean
    public Person user01() {
        return new Person("jack", 12);
    }

    @Bean
    public Pet pet01() {
        return new Pet("小狗旺财");
    }
}
