package application.dao;

import application.Climber;
import application.Dao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClimberDao implements Dao<Climber> {

    private EntityManager manager;

    public ClimberDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        manager.persist(climber);
    }

    @Override
    public void update(Climber climber) {
        manager.merge(climber);
    }

    public List<Climber> getClimbersByAge(int from, int to) {
        TypedQuery<Climber> query = manager.createQuery(
                "SELECT g FROM Climber g WHERE g.climberAge >= :from AND g.climberAge < :to", Climber.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Climber> climberList = (List<Climber>) query.getResultList();
        return climberList;
    }
}