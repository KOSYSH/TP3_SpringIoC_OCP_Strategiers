package com.example.config;

import com.example.dao.DaoApi;
import com.example.dao.DaoFile;
import com.example.dao.DaoImpl;
import com.example.dao.DaoImpl2;
import com.example.extensions.multiple.MetierMultiple;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// On charge les quatre DAO et le metier qui calcule leur somme.
@Import({DaoImpl.class, DaoImpl2.class, DaoFile.class, DaoApi.class, MetierMultiple.class})
public class MultipleDaoConfig {
}
