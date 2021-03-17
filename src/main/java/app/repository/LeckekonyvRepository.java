package app.repository;

import app.entity.Hallgato;
import app.entitymanager.CustomEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LeckekonyvRepository {

    private static final EntityManager entityManager;

    static {
        entityManager = CustomEntityManager.getInstance();
    }



}