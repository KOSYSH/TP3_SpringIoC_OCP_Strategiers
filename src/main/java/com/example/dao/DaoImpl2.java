package com.example.dao;


import org.springframework.stereotype.Component;

@Component("dao2")
public class DaoImpl2 implements IDao{
    @Override
    public double getValue(){
        System.out.println("La lecture depuis une base de donne ");
        return 150.00;
    }
}
