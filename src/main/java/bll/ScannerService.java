package bll;

import dal.entityDAO.ActeurDAO;
import dal.entityDAO.FilmDAO;

import javax.persistence.EntityManager;

public class ScannerService {

    private static ActeurDAO acteurDAO;
    private static FilmDAO filmDAO;

    public ScannerService(EntityManager em) {
        acteurDAO = new ActeurDAO(em);
        filmDAO = new FilmDAO(em);
    }

}
