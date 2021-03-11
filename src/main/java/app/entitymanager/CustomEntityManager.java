package app.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomEntityManager implements AutoCloseable {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("neptun-mysql");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getInstance() {
        return entityManager;
    }

    @Override
    public void close() {
        if (entityManagerFactory != null) {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}
