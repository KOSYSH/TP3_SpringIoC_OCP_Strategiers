package com.example.extensions.profiles;

import com.example.dao.IDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoDev")
@Profile("dev")
public class DaoImplDev implements IDao {
    @Override
    public double getValue() {
        System.out.println("Lecture depuis la base de developpement");
        return 150.0;
    }
}
