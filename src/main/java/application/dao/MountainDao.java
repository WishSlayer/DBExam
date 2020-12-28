package application.dao;

import application.Dao;
import application.Mountain;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MountainDao implements Dao<Mountain> {

    private EntityManager manager;

    public MountainDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);
    }

    @Override
    public void update(Mountain mountain) {
        manager.merge(mountain);
    }

    public List<Mountain> getMountainsByCountry(String mountainCountry) {
        Query query = manager.createQuery("SELECT g FROM Mountain g WHERE g.country = :mountainCountry");
        List<Mountain> mountainList = (List<Mountain>) query.getResultList();
        return mountainList;
    }
}
