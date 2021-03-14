package app.repository;

import app.entity.Tantargyak;
import app.entitymanager.CustomEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    public List<Object[]> selectMindenTantargy() {

        try {
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("SELECT t.nev, t.kreditszam, t.kod  FROM tantargyak t");
            List<Object[]> tantargyList = q.getResultList();
            entityManager.getTransaction().commit();
            return tantargyList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}