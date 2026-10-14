package com.example;
import com.example.config.AppConfig;
import com.example.config.DynamicDaoConfig;
import com.example.metier.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main( String[] args ) {
        // On charge la configuration et le choix du DAO par propriete.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, DynamicDaoConfig.class);


        //Recuperation du bean metier
        IMetier  metier = context.getBean(IMetier.class);


        //execution d'operation
        double resultat = metier.calcul();
        System.out.println("Le resulats est : "+ resultat);

        //Fermuture du conteneur
        context.close();

    }

}
