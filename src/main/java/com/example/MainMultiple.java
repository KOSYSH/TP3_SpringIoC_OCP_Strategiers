package com.example;

import com.example.config.MultipleDaoConfig;
import com.example.extensions.multiple.MetierMultiple;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainMultiple {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MultipleDaoConfig.class)) {
            MetierMultiple metier = context.getBean(MetierMultiple.class);
            metier.afficherSources();
            System.out.println("La somme est : " + metier.calcul());
        }
    }
}
