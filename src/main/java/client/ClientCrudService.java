package client;

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientCrudService {
    public void createClient(Client client){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                session.persist(client);
            transaction.commit();
        session.close();
    }
    public void deleteClientById(long id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            System.out.println("client.getName() = " + client.getName());
            session.remove(client);
            transaction.commit();
        }
    }
    public Client getById(long id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){

            return session.get(Client.class, id);
        }
    }
    public void changeClient(long id, String name){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            client.setName(name);
            session.persist(client);
            transaction.commit();
        }
    }
}
