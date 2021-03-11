package app.repository;

import app.entity.Hallgato;
import app.entitymanager.CustomEntityManager;
import javax.persistence.EntityManager;

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

    public boolean hallgatoModositasa(String nev, String ev, String neptunKod) {
        try {
            Hallgato hallgato = (Hallgato) entityManager.createQuery("UPDATE Hallgato h SET h.nev = :nev WHERE h.neptun_kod = :neptun_kod")
                    .setParameter("nev", nev)
                    //.setParameter("szuletesi_ev", ev)
                    .setParameter("neptun_kod", neptunKod)
                    .getSingleResult();
            entityManager.getTransaction().begin();
            entityManager.refresh(hallgato);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean hallgatoTorles(String neptunKod){
        try{
            Hallgato hallgato = (Hallgato) entityManager.createQuery("SELECT h FROM Hallgato h WHERE h.neptun_kod = :neptun_kod")
                    .setParameter("neptun_kod", neptunKod)
                    .getSingleResult();
            entityManager.getTransaction().begin();
            entityManager.remove(hallgato);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }

    }

}
