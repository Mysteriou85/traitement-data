package bll;

import bo.entity.Acteur;
import bo.entity.Film;
import dal.TraitementDAO;

public class TraitementDataService {

    Film film;
    private TraitementDAO dao;

    public TraitementDataService(TraitementDAO dao) {
        this.dao = dao;
    }

    

}
