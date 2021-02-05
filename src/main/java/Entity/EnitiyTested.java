/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Facade.Cfacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mathi
 */
public class EnitiyTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer c1 = new Customer("J.K. Rowling", "xd");
        Customer c2 = new Customer("Georg R. R. Martin", "lol");
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
            System.out.println(Cfacade.getFacade(emf).getAll().toString());
            System.out.println(Cfacade.getFacade(emf).getNumberOfCustomers());
            Cfacade.getFacade(emf).createCustmoer("xd","xd");

            //Verify that books are managed and has been given a database id
        } finally {
            em.close();
        }

    }
}
