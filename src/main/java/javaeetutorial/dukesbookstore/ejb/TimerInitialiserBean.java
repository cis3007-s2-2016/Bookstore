/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 *
 * @author Kyle.Lewer
 * 
 * purpose of this class is to create a bunch of timers that will handle auction 
 * expiry
 */
@Singleton
@Startup
public class TimerInitialiserBean implements TimedObject {
    
    Logger logger = Logger.getLogger(TimerInitialiserBean.class.getTypeName());
   @Resource
   TimerService timerService;
   
   @EJB
   MemberSaleManager memSaleManager;
   
   @PostConstruct
   public void initTimers()
   {
       Instant now = Instant.now();
       
       List<SaleUsed> activeAuctions = memSaleManager.getAllSales().stream()
               .filter((SaleUsed s) -> s.getSaletype().equals("auction") 
                       && ( ! s.isComplete()))
               .collect(Collectors.toList());
       
       
       activeAuctions.stream()
               .filter((SaleUsed s) -> ! alreadyExpired(s, now))
               .forEach((SaleUsed s) -> {
                    logger.info("Creating schedular to expire in: " + s.getDuration() + " days.");
                    timerService.createCalendarTimer(
                       new ScheduleExpression().end(getEndDate(s.getDatelisted(), s.getDuration())),
                       new TimerConfig(s,true)
                    );
                });
       
       activeAuctions.stream().filter((SaleUsed s) -> alreadyExpired(s, now))
               .forEach((SaleUsed s) -> {
                   memSaleManager.completeSale(s);
               });
       
   }
   private boolean alreadyExpired(SaleUsed s, Instant now)
   {
       return getEndDate(s.getDatelisted(), s.getDuration()).toInstant().isBefore(now);
   }
   private LocalDateTime ldtFromDate(Date date)
   {
       return LocalDateTime.from(date.toLocalDate());
   }
   
   private java.util.Date getEndDate(Calendar cal, int daysFrom)
   {
       
      ZoneOffset zo = ZoneOffset.ofHours(10);
      Period p = Period.ofDays(daysFrom);
      return  (java.util.Date) java.util.Date.from(cal.toInstant().plus(p));
     
   }
   
   private Date calenderToDate(Calendar cal)
   {
       Date d = (Date) Date.from(cal.toInstant());
       
       return null;
   }

    @Override
    public void ejbTimeout(Timer timer) {
        logger.info("Timed out");
        SaleUsed sale = (SaleUsed) timer.getInfo();
        memSaleManager.completeSale(sale);
        
    }
}
