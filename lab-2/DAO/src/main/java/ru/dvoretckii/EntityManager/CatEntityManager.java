package ru.dvoretckii.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Sessions.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CatEntityManager {
    public void createCat(Cat cat) {
        EntityManager em = JPAUtility.getEntityManager();
        em.getTransaction().begin();
        em.persist(cat);
        em.getTransaction().commit();
        em.close();
        JPAUtility.close();
    }

    public Cat readCat(Long id) {
        EntityManager em = JPAUtility.getEntityManager();
        Cat cat = em.find(Cat.class, id);
        JPAUtility.close();
        return cat;
    }

    public void updateCat(Cat cat) {
        EntityManager em = JPAUtility.getEntityManager();
        Cat oldCat = em.find(Cat.class, cat.getCat_id());
        em.getTransaction().begin();
        oldCat.setCat_name(cat.getCat_name());
        oldCat.setCat_breed(cat.getCat_breed());
        oldCat.setCat_birth_date(cat.getCat_birth_date());
        oldCat.setColor(cat.getColor());
        oldCat.setOwner(cat.getOwner());
        oldCat.setFriends(cat.getFriends());
        em.getTransaction().commit();
        em.close();
        JPAUtility.close();
    }

    public void deleteCat(Cat cat) {
        EntityManager em = JPAUtility.getEntityManager();
        Cat oldCat = em.find(Cat.class, cat.getCat_id());
        em.getTransaction().begin();
        em.remove(oldCat);
        em.getTransaction().commit();
        em.close();
        JPAUtility.close();
    }
}
