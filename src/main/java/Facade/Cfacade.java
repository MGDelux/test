/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author mathi
 */
public class Cfacade {
    private static EntityManagerFactory emf;
    private static Cfacade instance;

    public Cfacade() {
    }

    public static Cfacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Cfacade();
        }
        return instance;
    }

    public void createCustmoer(String firstName, String lastName) {
        Customer cust = new Customer(firstName, lastName);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public Customer findCustomer(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cust = em.find(Customer.class, id);
            return cust;
        } finally {
            em.close();
        }
    }

    public List<Customer> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> uery = em.createQuery("Select customer from Customer customer", Customer.class);
            return uery.getResultList();

        } finally {
            em.close();
        }

    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList().size();
        } finally {
            em.close();
        }

    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> uery = em.createQuery("Select customer (customer.lastName) from customer", Customer.class);
            return uery.getResultList();

        } finally {
            em.close();
        }
    }

   
}
