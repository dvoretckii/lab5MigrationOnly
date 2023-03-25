package ru.dvoretckii.Sessions;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dvoretckii.Entities.Cat;

public class CatSession {

    public void createCat(Cat cat) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cat);
        tx1.commit();
        session.close();
    }

    public Cat readCat(Long id) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Cat cat = session.get(Cat.class, id);
        session.close();
        return cat;
    }

    public void updateCat(Cat cat) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cat);
        tx1.commit();
        session.close();
    }

    public void deleteCat(Cat cat) {
        Session session = SessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cat);
        tx1.commit();;
        session.close();
    }
}