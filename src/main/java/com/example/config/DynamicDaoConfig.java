package com.example.config;

import com.example.dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class DynamicDaoConfig {

    @Value("${dao.target}")
    private String daoTarget;


    @Bean
    @Primary

    public IDao dynamicDao(){
        switch (daoTarget){
            case "dao": return new DaoImpl();
            case "dao2": return new DaoImpl2();
            case "daoApi": return new DaoApi();
            case "daoFile": return new DaoFile();
            default: throw new IllegalArgumentException("Cible Dao est inaccessible"+ daoTarget);
        }
    }
}
