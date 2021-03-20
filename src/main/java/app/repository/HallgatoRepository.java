package app.repository;

import app.entity.Hallgato;
import app.entity.Tantargy;
import app.entitymanager.CustomEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

public class HallgatoRepository {
    private static final EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }

    public void save(Hallgato hallgato) {
        entityManager.getTransaction().begin();
        entityManager.persist(hallgato);
        entityManager.getTransaction().commit();
    }

    public Hallgato selectHallgato(String neptunKod) {
        try {
            return (Hallgato) entityManager.createQuery("SELECT h FROM Hallgato h WHERE h.neptun_kod = :neptun_kod")
                    .setParameter("neptun_kod", neptunKod)
                    .getSingleResult();
        } catch (Exception e) {
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

    public boolean hallgatoTorles(String neptunKod) {
        try {
            Hallgato hallgato = selectHallgato(neptunKod);
            entityManager.getTransaction().begin();
            entityManager.remove(hallgato);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public List<Hallgato> selectMindenHallgato() {

        try {
            return entityManager.createQuery("SELECT h FROM Hallgato h")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveTantargyak(String neptun_kod, List<Tantargy> tantargy){
        try{
            Hallgato hallgato = (Hallgato) entityManager.createQuery("SELECT h FROM Hallgato h WHERE neptun_kod = :neptun_kod")
                    .setParameter("neptun_kod", neptun_kod)
                    .getSingleResult();
            hallgato.setTantargyak(tantargy);
            entityManager.getTransaction().begin();
            entityManager.persist(hallgato);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void removeTantargyak(String neptun_kod, List<Tantargy> tantargy){
        try{
            Hallgato hallgato = (Hallgato) entityManager.createQuery("SELECT h FROM Hallgato h WHERE neptun_kod = :neptun_kod")
                    .setParameter("neptun_kod", neptun_kod)
                    .getSingleResult();
            hallgato.removeTantargyak(tantargy);
            entityManager.getTransaction().begin();
            entityManager.persist(hallgato);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
