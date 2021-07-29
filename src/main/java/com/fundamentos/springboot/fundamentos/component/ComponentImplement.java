package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;
//Primer dependencia creada apartir de una interfaz
@Component
public class ComponentImplement implements ComponentDependency {
    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente");
    }
}
