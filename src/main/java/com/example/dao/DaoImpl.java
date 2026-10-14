package com.example.dao;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//@Primary
@Component("dao")
public class DaoImpl implements IDao{
    @Override
    public double getValue(){
        System.out.println("La lecture depuis une base de donne ");
        return 100.00;
    }
}
