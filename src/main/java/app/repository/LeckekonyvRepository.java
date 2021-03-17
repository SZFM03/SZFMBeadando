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

    public Leckekonyv selectHallgatoIDTantargyID(String hallgato_id, String tantargy_id){
     try {
         return (Leckekonyv) entityManager.createQuery("SELECT l FROM Leckekonyv l WHERE l.hallgato_id = :hallgato_id AND l.tantargy_id = :tantargy_id")
                 .setParameter("hallgato_id", hallgato_id)
                 .setParameter("tantargy_id", tantargy_id)
                 .getSingleResult();
     }catch (Exception e){
         throw new RuntimeException(e.getMessage());
     }
    }

   /* public boolean updateJegy(String jegy){
        try{
            Leckekonyv leckekonyv = selectHallgatoIDTantargyID();
        }
    }*/
}