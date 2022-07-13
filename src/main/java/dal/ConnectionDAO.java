package dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("traitement-data");

    public static EntityManager getConnectionDAO() {
        return entityManagerFactory.createEntityManager();
    }

    public ConnectionDAO() {}
}
