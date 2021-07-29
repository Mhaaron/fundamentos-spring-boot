package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanDosImplement implements MyBean {
    @Override
    public void print() {
        System.out.println("Hola desde mi implementación dos propia del bean dos");
    }
}
