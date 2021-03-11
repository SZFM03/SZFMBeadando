package app.repository;

import app.entity.Tantargyak;
import app.entitymanager.CustomEntityManager;
import javax.persistence.EntityManager;

public class TantargyakRepository {

    private static final EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }

    public void save (Tantargyak tantargyak){
        entityManager.getTransaction().begin();
        entityManager.persist(tantargyak);
        entityManager.getTransaction().commit();
    }

}