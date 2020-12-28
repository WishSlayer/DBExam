package application;

import application.dao.ClimberDao;
import application.dao.GroupDao;
import application.dao.MountainDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();

        ClimberDao climberDao = new ClimberDao(manager);
        MountainDao mountainDao = new MountainDao(manager);
        GroupDao groupDao = new GroupDao(manager);

        // Climbers
        Climber climber1 = new Climber("Vasya", "Moscow", 20);
        Climber climber2 = new Climber("Vova", "Moscow", 35);
        Climber climber3 = new Climber("Dmitriy", "Saint-Petersburg", 18);
        Climber climber4 = new Climber("Katya", "Novgorod", 22);
        Climber climber5 = new Climber("Ksenya", "Tbilisi", 45);
        Climber climber6 = new Climber("Dasha", "Kostroma", 75);

        // Mountains
        Mountain mountain1 = new Mountain("Everest", "Russia", 5500);
        Mountain mountain2 = new Mountain("GoraGorisha", "United States of America", 9000);
        Mountain mountain3 = new Mountain("Minigorka", "China", 3000);

        // Groups
        LocalDate startDate1 = LocalDate.of(2021, Month.JANUARY, 20);
        LocalDate startDate2 = LocalDate.of(2021, Month.APRIL, 13);
        Group group1 = new Group(mountain1, true, startDate1, 5);
        Group group2 = new Group(mountain2, true, startDate2, 3);

        // Manager
        manager.getTransaction().begin();
        climberDao.add(climber1);
        climberDao.add(climber2);
        climberDao.add(climber3);
        climberDao.add(climber4);
        climberDao.add(climber5);
        climberDao.add(climber6);
        mountainDao.add(mountain1);
        mountainDao.add(mountain2);
        mountainDao.add(mountain3);
        manager.getTransaction().commit();

        System.out.println(climberDao.getClimbersByAge(30, 80));
        System.out.println(mountainDao.getMountainsByCountry("Russia"));
    }
}
