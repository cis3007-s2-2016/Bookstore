/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.web.managedbeans.MemberSessionBean;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.ws.http.HTTPException;

/**
 *
 * @author Kyle.Lewer
 */
public abstract class AbstractFacade<T> {
    
// use stateful memberSession to check if the user is a customer
    // if the application required large scale and to be distributed across multiple services
    // this would need to be transitioned to a stateless authentication method
    @Context
    HttpServletResponse response;
    
    @Context
    SecurityContext security;
    
    private static final Logger logger = Logger.getLogger("dukesbookstore.service.AbstractFacade");
    
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    
    public void create(T entity) {
        if(! isLoggedInUser())
        {
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return;
        }
        if(! getEntityManager().contains(entity))
        {
            getEntityManager().persist(entity);
            getEntityManager().flush();
        }
    }

    public void edit(T entity) {
        if(! isLoggedInUser()){
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return;
        }
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }
    
    public void remove(T entity) {
        if(isLoggedInUser()){
            getEntityManager().remove(getEntityManager().merge(entity));
            getEntityManager().flush();
        }else{
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
        }
    }

    public T find(Object id) {
        if(isLoggedInUser())
            return getEntityManager().find(entityClass, id);
        else
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            
        throw new HTTPException(401);
    }

    public List<T> findAll() {
        if(isLoggedInUser()){
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return getEntityManager().createQuery(cq).getResultList();
        }else{
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
        }
        throw new HTTPException(401);
    }

    public List<T> findRange(int[] range) {
        if(isLoggedInUser()){
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
        }else{
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
        }
        throw new HTTPException(401);
    }

    public int count() {
        if(isLoggedInUser()){
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(getEntityManager().getCriteriaBuilder().count(rt));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } else {
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            throw new HTTPException(401);
        }
        
    }
    
    private boolean isLoggedInUser()
    {
        return (security.isUserInRole("admin") 
                || security.isUserInRole("customer"));
    }
    
}
