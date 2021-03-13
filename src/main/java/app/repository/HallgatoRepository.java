package app.repository;

import app.entity.Hallgato;
import app.entitymanager.CustomEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class HallgatoRepository {
    private static final EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }

    public void save (Hallgato hallgato){
        entityManager.getTransaction().begin();
        entityManager.persist(hallgato);
        entityManager.getTransaction().commit();
    }

    public Hallgato selectHallgato(String neptunKod) {
        try {
            return (Hallgato) entityManager.createQuery("SELECT h FROM Hallgato h WHERE h.neptun_kod = :neptun_kod")
                    .setParameter("neptun_kod", neptunKod)
                    .getSingleResult();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean updateHallgato(String nev, String ev, String neptunKod) {
        try {

            Hallgato hallgato = selectHallgato(neptunKod);

            hallgato.setNev(nev);
            hallgato.setSzuletesi_ev(ev);

            entityManager.getTransaction().begin();
            entityManager.merge(hallgato);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean hallgatoTorles(String neptunKod){
        try{
            Hallgato hallgato = selectHallgato(neptunKod);
            entityManager.getTransaction().begin();
            entityManager.remove(hallgato);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }

    }
    public List<Object[]> selectMindenHallgato() {

        try {
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("SELECT h.nev, h.szuletesi_ev, h.neptun_kod  FROM hallgatok h");
            List<Object[]> hallgatokList = q.getResultList();
            entityManager.getTransaction().commit();
            return hallgatokList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
