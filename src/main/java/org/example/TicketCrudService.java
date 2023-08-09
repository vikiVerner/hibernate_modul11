package org.example;

import client.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import planet.Planet;

import java.util.List;

public class TicketCrudService {
    public long createTicket(Ticket ticket) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(ticket);

            transaction.commit();
        }
        return ticket.getId();
    }

    public Ticket getById(long id) {

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }

    }

    public List<Ticket> getAllTicketsByClient(Client client) {

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Ticket> result = session
                    .createQuery("from Ticket where client = :client", Ticket.class);
            result.setParameter("client", client);
            return result.getResultList();
        }
    }

    public List<Ticket> getAllTicketsByFromPlanet(Planet planet) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Ticket> result = session
                    .createQuery("from Ticket where fromPlanet = :fromPlanet", Ticket.class);
            result.setParameter("fromPlanet", planet);
            return result.getResultList();
        }
    }

    public List<Ticket> getAllTicketsByToPlanet(Planet planet){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Query<Ticket> result = session
                    .createQuery("from Ticket where toPlanet = :toPlanet", Ticket.class);
            result.setParameter("toPlanet",planet);
            return  result.getResultList();
        }
    }

    public void changeClientInExistingTicket(long ticketId,Client newClient){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, ticketId);
            ticket.setClient(newClient);
            session.persist(ticket);
            transaction.commit();
        }
    }

    public void changePlanetInExistingTicket(long ticketId,Planet newPlanet){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, ticketId);
            ticket.setToPlanet(newPlanet);
            session.persist(ticket);
            transaction.commit();
        }
    }

    public void deleteTicketById(long ticketId){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, ticketId);
            session.remove(ticket);
            transaction.commit();
        }
    }
}
