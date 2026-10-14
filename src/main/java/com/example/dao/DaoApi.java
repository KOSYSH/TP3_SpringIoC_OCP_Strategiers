package com.example.dao;


import org.springframework.stereotype.Component;

@Component("daoApi")
public class DaoApi implements IDao{
    @Override
    public double getValue(){
        System.out.println("Lecture depuis un Api externe. ");
        return  220.0;
    }
}
