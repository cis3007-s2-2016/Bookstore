/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javaeetutorial.dukesbookstore.ejb.BookReviewManagerBean;
import javaeetutorial.dukesbookstore.entity.Book;
import javaeetutorial.dukesbookstore.entity.BookReview;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kyle.Lewer
 */
@Named("bookReviewBean")
@SessionScoped
public class BookReviewBean extends AbstractBean implements Serializable{
    Logger logger = Logger.getLogger(BookReviewBean.class.getTypeName());

    @EJB
    BookReviewManagerBean bookReviewManager;
    
    @Inject
    MemberSessionBean memberSession;
    
    @Inject
    BookstoreBean store;
    
    private boolean showReview = false;
    private BookReview bookReview = null;
    
    public BookReview getBookReview()
    {
        return bookReview;
    }
    public boolean getShowReview()
    {
        return this.showReview;
    }
    
    public void onReviewLinkAjax(AjaxBehaviorEvent event)
    {
        logger.info("OI M8");
        showReview = true;
        logger.info("Show review");
        bookReview = new BookReview();
        bookReview.setReviewer(memberSession.user());
        bookReview.setReviewed(store.getSelectedBook());
        bookReview.setReviewDate(new Date());
    }
    
    public void onCancelLinkAjax(AjaxBehaviorEvent event)
    {
        showReview = false;
        bookReview = null; //throw away bookReview
    }
    
   
    public void onSubmitReviewLinkAjax(AjaxBehaviorEvent event)
    {
        
        bookReviewManager.updateReview(bookReview);
        this.showReview = false;
        
    }
    
    public List<BookReview> getReviews(Book b)
    {
       return bookReviewManager.getReviews(b);
    }

    public List<Integer> getStarList(BookReview b)
    {
        //wat
        return IntStream.rangeClosed(1, (int) Math.floor(b.getRating())).boxed().collect(Collectors.toList());
       
    }
    
    
}
