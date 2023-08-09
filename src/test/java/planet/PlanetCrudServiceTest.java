package planet;

import org.example.DatabaseInitService;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudServiceTest {
    PlanetCrudService planetCrudService;
    Session session;

    @BeforeEach
    public void beforeEach() {
        String connectionUrl = "jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1";
        new DatabaseInitService().createTable(connectionUrl);
        session = HibernateUtil.getInstance().getSessionFactory().openSession();

        planetCrudService = new PlanetCrudService();
    }

    @Test
    public void thatDeleteByIdWorkedProperly() {
        Transaction transaction = session.beginTransaction();
        planetCrudService.deletePlanetById("MERC1");
        Assertions.assertNull(session.get(Planet.class, "MERC1"));
        transaction.commit();
    }

    @Test
    public void thatCreatePlanetWorkedProperly() {
        Transaction transaction = session.beginTransaction();
        Planet planet = new Planet();
        planet.setName("TestPlanet");
        planet.setId("TEST1");
        planetCrudService.createPlanet(planet);
        String id = planet.getId();
        Planet actual = session.get(Planet.class, id);
        transaction.commit();
        Assertions.assertEquals(planet.getName(), actual.getName());
    }

    @Test
    public void thatGetByIdWorkedProperly() {
        Planet byId = planetCrudService.getById("MERC1");
        String expected = byId.getName();
        Assertions.assertEquals("Mercury", expected);
    }

    @Test
    public void thatChangeByIdWorkedProperly() {
        Transaction transaction = session.beginTransaction();
        planetCrudService.changePlanet("MERC1", "Anna");
        Planet planet = session.get(Planet.class, "MERC1");
        transaction.commit();
        Assertions.assertEquals("Anna", planet.getName());
    }
    @AfterEach
    public void afterEach(){
        session.close();
    }

}