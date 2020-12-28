package application.dao;

import application.Dao;
import application.Mountain;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        TypedQuery query = manager.createQuery(
                "SELECT g FROM Mountain g WHERE g.mountainCountry = :mountainCountry", Mountain.class);
        query.setParameter("mountainCountry", mountainCountry);
        List<Mountain> mountainList = (List<Mountain>) query.getResultList();
        return mountainList;
    }
}
