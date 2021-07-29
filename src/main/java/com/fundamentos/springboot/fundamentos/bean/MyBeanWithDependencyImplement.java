package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {
    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    //En esta implementación se llama otra dependencia
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        //Implementación de log a nivel informativo
        LOGGER.info("Hemos ingresado al metodo printWithDependency");
        int num = 1;
        LOGGER.debug("El número enviado com parámetro a la dependencia operación es: " + num);
        System.out.println(myOperation.sum(num));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
