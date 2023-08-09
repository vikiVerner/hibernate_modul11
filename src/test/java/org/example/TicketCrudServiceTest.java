package org.example;

import client.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import planet.Planet;

import java.util.List;

class TicketCrudServiceTest {
    TicketCrudService ticketCrudService;
    Session session;
    @BeforeEach
    public void beforeEach() {
        String connectionUrl = "jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1";
        new DatabaseInitService().createTable(connectionUrl);
        session = HibernateUtil.getInstance().getSessionFactory().openSession();
        ticketCrudService = new TicketCrudService();
    }
    @Test
    public void testThatCreateTicketWorkedProperly(){
        Transaction transaction = session.beginTransaction();
            Client byId = session.get(Client.class,1L);
            Planet from = session.get(Planet.class, "MERC1");
            Planet to = session.get(Planet.class, "VEN0");
            Ticket ticket = new Ticket();
            ticket.setFromPlanet(from);
            ticket.setToPlanet(to);
            ticket.setClient(byId);
            ticket.setCreatedAt("2023-06-09 13:14:15.000000");
            long ticketId = ticketCrudService.createTicket(ticket);
        transaction.commit();
        Assertions.assertEquals(11,ticketId);
    }

    @Test
    public void testThatDeleteWorkedProperly(){
        Transaction transaction = session.beginTransaction();
            ticketCrudService.deleteTicketById(11);
            Assertions.assertNull(session.get(Ticket.class, 11));
        transaction.commit();

    }
    @Test
    public void testThatGetByIdWorkedProperly(){
        Ticket expected = ticketCrudService.getById(1L);
        Ticket actual = session.get(Ticket.class, 1L);
        Assertions.assertEquals(expected.getId(),actual.getId());
    }
    @Test
    public void testThatChangeClientInExistingTicketWorked(){
        Transaction transaction = session.beginTransaction();
            Client newClient = session.get(Client.class, 4L);
            ticketCrudService.changeClientInExistingTicket(1L,newClient);
            Ticket changedTicket = session.get(Ticket.class, 1L);
            Assertions.assertEquals(changedTicket.getClient().getName(),newClient.getName());
        transaction.commit();
    }
    @Test
    public void testThatChangePlanetInExistingTicketWorked(){
        Transaction transaction = session.beginTransaction();
            Planet newPlanet = session.get(Planet.class, "VEN0");
            ticketCrudService.changePlanetInExistingTicket(1L,newPlanet);
            Ticket changedTicket = session.get(Ticket.class, 1L);
            Assertions.assertEquals(changedTicket.getToPlanet().getName(),newPlanet.getName());
        transaction.commit();
    }
    @Test
    public void testThatGetAllTicketsByClientWorked(){
        Client client = session.get(Client.class, 6L);
        List<Ticket> allTicketsByClient = ticketCrudService.getAllTicketsByClient(client);
        Assertions.assertEquals(1,allTicketsByClient.size());
    }
    @Test
    public void testThatGetAllTicketsByFromPlanetWorked(){
        Planet planet = session.get(Planet.class, "E4R7");
        List<Ticket> allTicketsByFromPlanet = ticketCrudService.getAllTicketsByFromPlanet(planet);
        Assertions.assertEquals(2,allTicketsByFromPlanet.size());
    }
    @Test
    public void testThatGetAllTicketsByToPlanetWorked(){
        Planet planet = session.get(Planet.class, "E4R7");
        List<Ticket> allTicketsByToPlanet = ticketCrudService.getAllTicketsByToPlanet(planet);
        Assertions.assertEquals(2,allTicketsByToPlanet.size());
    }
    @AfterEach
    public void afterEach(){
        session.close();
    }
}