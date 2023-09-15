package ru.dvoretckii.EntityManager;

import ru.dvoretckii.Entities.Owner;

import javax.persistence.EntityManager;

public class OwnerEntityManager {
    public void createOwner(Owner owner) {
        EntityManager em = JPAUtility.getEntityManager();
        em.getTransaction().begin();
        em.persist(owner);
        em.getTransaction().commit();
        em.close();
        JPAUtility.close();
    }

    public Owner readOwner(Long id) {
        EntityManager em = JPAUtility.getEntityManager();
        Owner owner = em.find(Owner.class, id);
        JPAUtility.close();
        return owner;
    }

    public void updateOwner(Owner owner) {
        EntityManager em = JPAUtility.getEntityManager();
        Owner oldOwner = em.find(Owner.class, owner.getOwner_id());
        em.getTransaction().begin();
        oldOwner.setName(owner.getName());
        oldOwner.setOwner_birth_date(owner.getOwner_birth_date());
        oldOwner.setOwnedCats(owner.getOwnedCats());
        em.getTransaction().commit();
        em.close();
        JPAUtility.close();
    }

    public void deleteOwner(Owner owner) {
        EntityManager em = JPAUtility.getEntityManager();
        Owner oldOwner = em.find(Owner.class, owner.getOwner_id());
        em.getTransaction().begin();
        em.remove(oldOwner);
        em.getTransaction().commit();
        em.close();
        JPAUtility.close();
    }
}
