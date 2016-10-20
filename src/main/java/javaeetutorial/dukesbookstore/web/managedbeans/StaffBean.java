/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.BookRequestBean;
import javaeetutorial.dukesbookstore.ejb.MemberManager;
import javaeetutorial.dukesbookstore.ejb.MemberManagerBean;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.exception.BookNotFoundException;
import javaeetutorial.dukesbookstore.exception.BooksNotFoundException;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author kyle.lewer
 */
@Named("staff")
@SessionScoped
public class StaffBean extends AbstractBean {
    private static final Logger logger = Logger.getGlobal();
    @EJB
    MemberManager memberManager;
    @EJB
    BookRequestBean bookRequestBean;

    
    private String selectedISBN;
    private Book modifyBook;
    
    public List<Member> getStaffMembers()
    { 
        return memberManager.getStaff();
    }
    
    public Book getModifyBook(){
        return this.modifyBook;
    }

    public String getSelectedISBN() {
        return selectedISBN;
    }

    public void setSelectedISBN(String selectedISBN) {
        this.selectedISBN = selectedISBN;
    }
    
    public void ajaxOnChangeToModifyBook(AjaxBehaviorEvent event) throws BookNotFoundException, BooksNotFoundException
    {
        
        logger.info("I GOT Z AJAX OUT");
        //TODO Validation
        UISelectOne ui = (UISelectOne) event.getComponent();
//        this.setSelectedISBN((String) ui.getValue());
        this.modifyBook = bookRequestBean.getBook(selectedISBN);
//        logger.info(this.modifyBook.toString());
        
    }
    
    public void ajaxUpdateBook(AjaxBehaviorEvent event) 
    {
       modifyBook = bookRequestBean.mergeBook(modifyBook);
    }
}
