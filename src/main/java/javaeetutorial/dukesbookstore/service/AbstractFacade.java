/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.service;

import java.util.List;
import javaeetutorial.dukesbookstore.web.managedbeans.MemberSessionBean;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.xml.ws.http.HTTPException;

/**
 *
 * @author Kyle.Lewer
 */
public abstract class AbstractFacade<T> {
    
// use stateful memberSession to check if the user is a customer
    // if the application required large scale and to be distributed across multiple services
    // this would need to be transitioned to a stateless authentication method
    @SessionScoped
    MemberSessionBean memberSession;
    
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    
    public void create(T entity) {
        if(memberSession.isCustomer())
            getEntityManager().persist(entity);
        else
            throw new HTTPException(401);
    }

    public void edit(T entity) {
        if(memberSession.isCustomer())
            getEntityManager().merge(entity);
        else
            throw new HTTPException(401);
    }

    public void remove(T entity) {
        if(memberSession.isCustomer())
            getEntityManager().remove(getEntityManager().merge(entity));
        else
            throw new HTTPException(401);
    }

    public T find(Object id) {
        if(memberSession.isCustomer())
            return getEntityManager().find(entityClass, id);
        else
            throw new HTTPException(401);
    }

    public List<T> findAll() {
        if(memberSession.isCustomer()){
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return getEntityManager().createQuery(cq).getResultList();
        }else{
            throw new HTTPException(401);
        }
    }

    public List<T> findRange(int[] range) {
        if(memberSession.isCustomer())
        {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
        }else{
            throw new HTTPException(401);
        }
    }

    public int count() {
        if(memberSession.isCustomer()){
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(getEntityManager().getCriteriaBuilder().count(rt));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } else {
            throw new HTTPException(401);
        }
    }
    
}
