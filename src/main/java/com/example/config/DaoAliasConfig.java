package com.example.config;

import com.example.dao.DaoFile;
import com.example.dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DaoAliasConfig {
    @Primary
    @Bean
    public IDao daoAlias(){
        // On choisit la source fichier.
        return new DaoFile();
    }
}
