package app.repository;

import app.entity.Jegy;
import app.entitymanager.CustomEntityManager;

import javax.persistence.EntityManager;

public class JegyRepository {

    private static final EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }

    public void save (Jegy jegy){
        entityManager.getTransaction().begin();
        entityManager.persist(jegy);
        entityManager.getTransaction().commit();
    }

    public Jegy selectHallgatoIDTantargyID(long hallgato_id, long tantargy_id){
        try {
            return (Jegy) entityManager.createQuery("SELECT j FROM Jegy j WHERE j.hallgato_id = :hallgato_id AND j.tantargy_id = :tantargy_id")
                    .setParameter("hallgato_id", hallgato_id)
                    .setParameter("tantargy_id", tantargy_id)
                    .getSingleResult();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean removeJegy(long hallgato_id, long tantargy_id) {
        try {
            Jegy jegy = selectHallgatoIDTantargyID(hallgato_id, tantargy_id);
            entityManager.getTransaction().begin();
            entityManager.remove(jegy);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public boolean updateJegy(long hallgato_id,long tantargy_id, Integer osztalyzat){
        try{
            Jegy jegy = selectHallgatoIDTantargyID(hallgato_id, tantargy_id);
            jegy.setJegy(osztalyzat);

            entityManager.getTransaction().begin();
            entityManager.merge(jegy);
            entityManager.getTransaction().commit();
            System.out.println(jegy);
            return true;
        } catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

}
