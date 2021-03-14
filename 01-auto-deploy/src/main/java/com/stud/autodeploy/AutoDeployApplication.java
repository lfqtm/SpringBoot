package com.stud.autodeploy;

import ch.qos.logback.core.db.DBHelper;
import com.stud.autodeploy.bean.Person;
import com.stud.autodeploy.bean.Pet;
import com.stud.autodeploy.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AutoDeployApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AutoDeployApplication.class, args);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        MyConfig myConfig = context.getBean(MyConfig.class);
        Pet pet1 = myConfig.pet01();
        Pet pet2 = myConfig.pet01();
        // 如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件是否在容器中有。
        System.out.println("pet2 = pet1 ? " + (pet2 == pet1));

        System.out.println("======");
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for (String beanDefinitionName : beanNamesForType) {
            System.out.println(beanDefinitionName);
        }

        DBHelper bean = context.getBean(DBHelper.class);
        System.out.println("bean = " + bean);

    }

}
