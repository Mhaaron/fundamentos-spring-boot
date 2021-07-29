package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Con esto Springboot determina que tendremos una configuración adicional de nuestros beans
@Configuration
public class MyConfigurationBean {
    //Primer Bean creado
    @Bean
    public MyBean beanOperation() {
        return new MyBeanDosImplement();
    }

    @Bean
    public MyOperation beanOperationOp() {
        return new MyOperationImplement();
    }
    //Inyentamos una dependencia dentro de otra dependencia
    @Bean
    public MyBeanWithDependency beanOperationOpWithDependency(MyOperation myOperation) {
        return new MyBeanWithDependencyImplement(myOperation);
    }

    @Bean
    public MyChallenge beanOperationChallenge() {
        return new MyChallengeImplement();
    }
}
