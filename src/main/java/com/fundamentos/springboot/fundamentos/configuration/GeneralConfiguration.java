package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
//Para clases donde iran configuraciones, dependencias etc.
@Configuration
//Acceder al archivo de propiedades que creamos
@PropertySource("classpath:connection.properties")
//Permite definir la clase como la representante de las propiedades y ya podremos inyectarla como dependencia
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    //Esta notación permite llamar las Value que definimos en el archivo propeties
    //y poder mapear las propiedades a una variable, en este caso String name
    @Value("${value.name}")
    private String name;

    @Value("${value.lastname}")
    private String lastname;

    @Value("${value.random}")
    private String random;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${driver}")
    private String driver;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public MyBeanWithProperties function() {
        return new MyBeanWithPropertiesImplement(name, lastname);
    }

    //Con este Bean y su método se realizara toda la configuración sobre la base de datos, osea la configuración con clases
    @Bean
    public DataSource dataSource() {
        //Realizamos toda la configuración que se hizo en el properties con dataSourceBuilder
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();
    }
}
