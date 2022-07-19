package dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe qui effectue la connection
 */
public class ConnectionDAO {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("traitement-data");

    public static EntityManager getConnectionDAO() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Constructeur de ConnectionDAO
     */
    public ConnectionDAO() {}
}
