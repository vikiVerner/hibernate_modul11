package client;

import org.example.DatabaseInitService;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientCrudServiceTest {
    ClientCrudService clientCrudService;
    Session session;
    @BeforeEach
    public void beforeEach() {
        String connectionUrl = "jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1";
        new DatabaseInitService().createTable(connectionUrl);
        session = HibernateUtil.getInstance().getSessionFactory().openSession();

        clientCrudService = new ClientCrudService();
    }

    @Test
    public void thatDeleteByIdWorkedProperly(){
        Transaction transaction = session.beginTransaction();
        clientCrudService.deleteClientById(3L);
        Assertions.assertNull(session.get(Client.class, 3L));
        transaction.commit();
    }

    @Test
    public void thatCreateClientWorkedProperly(){
        Transaction transaction = session.beginTransaction();
            Client client = new Client();
            client.setName("TestClient");
            clientCrudService.createClient(client);
            long id = client.getId();
            Client actual = session.get(Client.class, id);
        transaction.commit();
        Assertions.assertEquals(client.getName(),actual.getName());
    }

    @Test
    public void thatGetByIdWorkedProperly(){
        Client byId = clientCrudService.getById(1L);
        String expected = byId.getName();
        Assertions.assertEquals("John Doe",expected);
    }

    @Test
    public void thatChangeByIdWorkedProperly(){
        Transaction transaction = session.beginTransaction();
            clientCrudService.changeClient(1L,"Anna");
            Client client = session.get(Client.class, 1L);
        transaction.commit();
        Assertions.assertEquals("Anna",client.getName());
    }
    @AfterEach
    public void afterEach(){
        session.close();
    }

}