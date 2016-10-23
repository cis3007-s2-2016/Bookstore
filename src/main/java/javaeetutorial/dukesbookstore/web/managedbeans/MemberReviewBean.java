/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.List;
import javaeetutorial.dukesbookstore.ejb.MemberReviewManagerBean;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.SellerReview;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

/**
 *
 * @author Kyle.Lewer
 */
@Named("memberReviewBean")
@ViewScoped
public class MemberReviewBean implements Serializable{
    Logger logger = Logger.getLogger(MemberReviewBean.class.getTypeName());
    
    @Inject
    MemberReviewManagerBean memRevMgr;
    
    @Inject
    MemberSessionBean memberSession;
    SellerReview review = null;
    Boolean reviewingUser = false;

    public SellerReview getReview() {
        return review;
    }


    public Boolean getReviewingUser() {
        return reviewingUser;
    }

    public void setReviewingUser(Boolean reviewingUser) {
        this.reviewingUser = reviewingUser;
    }
    /**
     * Creates a new instance of MemberReviewBean
     */
    public MemberReviewBean() {
    }
    
    public List<SellerReview> getReviewsOf(Member m)
    {
        logger.info("get reviews of");
        return memRevMgr.getReviewsSellerHas(m);
    }
    
    public void onSubmitReview(AjaxBehaviorEvent event)
    {
        logger.info("on submit review memeber review");
        memRevMgr.saveNewReview(review);
        reviewingUser = false;
        review = null;
    }
    
    public void onOpenReviewForm(Member toReview)
    {
        
        logger.info("Open review form");
        if(reviewingUser)
        {
            logger.info("Review form already open...");
//            return;
        }
        this.reviewingUser =true;
        this.review = new SellerReview();
        this.review.setReviewee(memberSession.getUser());
        review.setRating(0f);
        review.setReviewed(toReview);
    }
    
    public void onCloseReviewForm()
    {
        logger.info("close review form");
        review = null;
        reviewingUser = false;
    }
    
    public void onUpdateRating(int rating){
        if(reviewingUser)
        {
            logger.info("setting rating to: " + rating);
            review.setRating((float) rating);
        }
    }
    
        
    public String getSafeReviewTitle(SellerReview rev)
    {
        if(null == rev.getReview() || rev.getReview().length() < 20)
        {
            return "Review";
        }
        else
            return rev.getReview().substring(0,20);
    }
    
    public String getSafeReviewText(SellerReview rev)
    {
        if(null == rev.getReview() || rev.getReview().length() == 0)
        {
            return "No review was provided";
        }
        else return review.getReview();
    }
    
}
