package com.example;

import com.example.config.ProfileConfig;
import com.example.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainProfils {
    public static void main(String[] args) {
        String profil = args.length == 0 ? "prod" : args[0];
        if (args.length > 1 || (!profil.equals("prod") && !profil.equals("dev"))) {
            throw new IllegalArgumentException("Choisir un seul profil : prod ou dev");
        }

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            // On choisit le profil avant de creer les beans.
            context.getEnvironment().setActiveProfiles(profil);
            context.register(ProfileConfig.class);
            context.refresh();

            IMetier metier = context.getBean(IMetier.class);
            System.out.println("Profil actif : " + profil);
            System.out.println("Le resultat est : " + metier.calcul());
        }
    }
}
