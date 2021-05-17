package app.repository;

import app.entity.Tantargy;
import app.entitymanager.CustomEntityManager;
import javax.persistence.EntityManager;
import java.util.List;

public class TantargyakRepository {

    private static final EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }

    public void save (Tantargy tantargy){
        entityManager.getTransaction().begin();
        entityManager.persist(tantargy);
        entityManager.getTransaction().commit();
    }

    public List<Tantargy> selectMindenTantargy() {

        try {
            return entityManager.createQuery("SELECT t FROM Tantargy t")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}