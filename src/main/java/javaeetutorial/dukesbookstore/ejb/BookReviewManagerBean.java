/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.List;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.BookReview;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kyle.Lewer
 */
@Stateless
public class BookReviewManagerBean {
    @PersistenceContext
    EntityManager em;
    
    private static final String FIND_QUERY =
            "SELECT br FROM BookReview br WHERE br.reviewed = :isbn AND br.reviewer = :uid";
    
    private static final String LIST_BY_ISBN_QUERY = 
            "SELECT br FROM BookReview br WHERE br.reviewed = :isbn";
    
    private static final String LIST_BY_UID_QUERY =
            "SELECT br FROM BookReview br WHERE br.reviewer = :uid";
    
    public BookReview findBookReview(String ISBN, int userId)
    {
        TypedQuery<BookReview> query 
                = (TypedQuery<BookReview>) em.createQuery(FIND_QUERY);
        
        query.setParameter("isbn",ISBN);
        query.setParameter("uid", userId);
        
        return query.getSingleResult();
    }
    
    public List<BookReview> getReviews(Book b)
    {
        TypedQuery<BookReview> query 
                = (TypedQuery<BookReview>) em.createQuery(LIST_BY_ISBN_QUERY);
        
        query.setParameter("isbn", b);
        return query.getResultList();
    }
    
    public List<BookReview> getReviews(int userid)
    {
        TypedQuery<BookReview> query 
                = (TypedQuery<BookReview>) em.createQuery(LIST_BY_UID_QUERY);
        
        query.setParameter("uid", userid);
        return query.getResultList();
    }
    
    public BookReview updateReview(BookReview rev)
    {
        return em.merge(rev);
    }
}
