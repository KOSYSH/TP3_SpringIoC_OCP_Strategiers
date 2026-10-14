package com.example;

import com.example.metier.IMetier;
import com.example.config.AppConfig;
import com.example.config.DynamicDaoConfig;
import com.example.config.MultipleDaoConfig;
import com.example.config.ProfileConfig;
import com.example.dao.IDao;
import com.example.extensions.multiple.MetierMultiple;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OcpSelectionTest {

    @Test
    public void testDaoImplParDefaut() {
        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class)) {
            // Sans configuration dynamique, le nom dao choisit DaoImpl.
            IMetier metier = context.getBean(IMetier.class);
            assertEquals(200.0, metier.calcul(), 0.001);
        }
    }

    @Test
    public void testSelectionDao() {
        verifierSelection("dao", 200.0);
    }

    @Test
    public void testSelectionDao2() {
        verifierSelection("dao2", 300.0);
    }

    @Test
    public void testSelectionFichier() {
        verifierSelection("daoFile", 360.0);
    }

    @Test
    public void testSelectionApi() {
        verifierSelection("daoApi", 440.0);
    }

    private void verifierSelection(String cible, double resultat) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            // On change la propriete seulement pour ce test.
            context.getEnvironment().getPropertySources().addFirst(
                    new MapPropertySource("test", Collections.singletonMap("dao.target", cible)));
            context.register(AppConfig.class, DynamicDaoConfig.class);
            context.refresh();

            assertEquals(resultat, context.getBean(IMetier.class).calcul(), 0.001);
        }
    }

    @Test
    public void testCibleInconnue() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().getPropertySources().addFirst(
                    new MapPropertySource("test", Collections.singletonMap("dao.target", "inconnue")));
            context.register(AppConfig.class, DynamicDaoConfig.class);

            try {
                context.refresh();
                fail("Une cible inconnue doit provoquer une erreur");
            } catch (BeanCreationException exception) {
                Throwable cause = exception.getMostSpecificCause();
                assertTrue(cause instanceof IllegalArgumentException);
                assertTrue(cause.getMessage().contains("inconnue"));
            }
        }
    }

    @Test
    public void testProfilProd() {
        verifierProfil("prod", "daoProd", 200.0);
    }

    @Test
    public void testProfilDev() {
        verifierProfil("dev", "daoDev", 300.0);
    }

    private void verifierProfil(String profil, String nomDao, double resultat) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles(profil);
            context.register(ProfileConfig.class);
            context.refresh();

            // Un seul DAO doit etre actif pour ce profil.
            assertEquals(Collections.singleton(nomDao), context.getBeansOfType(IDao.class).keySet());
            assertEquals(resultat, context.getBean(IMetier.class).calcul(), 0.001);
        }
    }

    @Test
    public void testSommeDesQuatreDao() {
        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MultipleDaoConfig.class)) {
            assertEquals(new TreeSet<>(Arrays.asList("dao", "dao2", "daoFile", "daoApi")),
                    new TreeSet<>(context.getBeansOfType(IDao.class).keySet()));
            assertEquals(1, context.getBeansOfType(IMetier.class).size());
            assertTrue(context.getBean(IMetier.class) instanceof MetierMultiple);
            assertEquals(650.0, context.getBean(IMetier.class).calcul(), 0.001);
        }
    }
}
