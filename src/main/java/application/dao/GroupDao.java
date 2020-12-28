package application.dao;

import application.Dao;
import application.GroupUp;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDao implements Dao<GroupUp> {

    private EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(GroupUp group) {
        manager.persist(group);
    }

    @Override
    public void update(GroupUp group) {
        manager.merge(group);
    }

    public List<GroupUp> getGroupsByMountainName(String mountainName) {
        TypedQuery query = manager.createQuery(
                "SELECT g FROM GroupUp g WHERE g.mountain.mountainName = :mountainName", GroupUp.class);
        query.setParameter("mountainName", mountainName);
        List<GroupUp> groupList = (List<GroupUp>) query.getResultList();
        return groupList;
    }

    public List<GroupUp> getGroupsByIsOpen(Boolean isOpen) {
        TypedQuery query = manager.createQuery(
                "SELECT g FROM GroupUp g WHERE g.isOpen = :isOpen", GroupUp.class);
        query.setParameter("isOpen", isOpen);
        List<GroupUp> groupList2 = (List<GroupUp>) query.getResultList();
        return groupList2;
    }
}
