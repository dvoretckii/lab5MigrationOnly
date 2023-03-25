package ru.dvoretckii.Sessions;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Owner;


public class OwnerSession {

    public void createOwner(Owner owner) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(owner);
        tx1.commit();
        session.close();
    }

    public Owner readOwner(Long id) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Owner owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }

    public void updateOwner(Owner owner) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(owner);
        tx1.commit();
        session.close();
    }

    public void deleteOwner(Owner owner) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(owner);
        tx1.commit();
        session.close();
    }
}