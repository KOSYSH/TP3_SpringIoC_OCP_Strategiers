package com.example.extensions.multiple;

import com.example.dao.IDao;
import com.example.metier.IMetier;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MetierMultiple implements IMetier {
    private final List<IDao> daos;
    private final Map<String, IDao> daoMap;

    public MetierMultiple(List<IDao> daos, Map<String, IDao> daoMap) {
        this.daos = daos;
        this.daoMap = daoMap;
    }

    public void afficherSources() {
        // La map permet de retrouver les DAO par leur nom.
        System.out.println("DAO disponibles : " + daoMap.keySet());
    }

    @Override
    public double calcul() {
        // On additionne les valeurs des quatre DAO.
        return daos.stream().mapToDouble(IDao::getValue).sum();
    }
}
