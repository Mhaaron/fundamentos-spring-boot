package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {
    //Los parámetros que recibe se representan con el constructor de la clase
    private String name;
    private String lastname;

    public MyBeanWithPropertiesImplement(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String function() {
        return name + "-" + lastname;
    }
}
