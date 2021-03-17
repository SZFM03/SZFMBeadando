package app.repository;

import app.entity.Hallgato;
import app.entity.Leckekonyv;
import app.entitymanager.CustomEntityManager;

import javax.persistence.EntityManager;

public class LeckekonyvRepository {

    private static final EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }

    public static void save(Leckekonyv leckekonyv) {
        entityManager.getTransaction().begin();
        entityManager.persist(leckekonyv);
        entityManager.getTransaction().commit();
    }


}