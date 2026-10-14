package com.example.config;

import com.example.extensions.profiles.DaoImplDev;
import com.example.extensions.profiles.DaoImplProd;
import com.example.metier.MetierImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// On charge seulement les DAO des profils et le metier.
@Import({DaoImplProd.class, DaoImplDev.class, MetierImpl.class})
public class ProfileConfig {
}
