/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.session;

import javaeetutorial.dukesbookstore.entity.BookWanted;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amanda hugnkiss
 */
@Stateless
public class BookWantedFacade extends AbstractFacade<BookWanted> {

    @PersistenceContext(unitName = "bookstorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookWantedFacade() {
        super(BookWanted.class);
    }
    
}
