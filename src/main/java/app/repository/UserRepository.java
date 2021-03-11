package app.repository;

import app.entity.User;
import app.entitymanager.CustomEntityManager;

import javax.persistence.EntityManager;

public class UserRepository {

    private static EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }

    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public boolean existsByNameAndPassword(String name, String password) {
        try {
            entityManager.createQuery("SELECT u FROM User u WHERE u.nev = :name AND u.jelszo = :password")
                    .setParameter("name", name)
                    .setParameter("password", password)
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

}
