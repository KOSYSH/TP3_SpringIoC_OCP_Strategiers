package com.example.dao;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//@Primary
@Component("daoFile")
public class DaoFile implements IDao{
    @Override
    public double getValue(){
        System.out.println("Lecture depuis un fichier ");
        return  180.00;
    }
}
