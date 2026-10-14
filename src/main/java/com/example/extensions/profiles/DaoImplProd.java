package com.example.extensions.profiles;

import com.example.dao.IDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoProd")
@Profile("prod")
public class DaoImplProd implements IDao {
    @Override
    public double getValue() {
        System.out.println("Lecture depuis la base de production");
        return 100.0;
    }
}
