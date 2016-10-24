/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.List;
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
   @Resource
   TimerService timerService;
   
   @EJB
   MemberSaleManager memSaleManager;
   
   @PostConstruct
   public void initTimers()
   {
       LocalDateTime now = LocalDateTime.now();
       
       List<SaleUsed> activeAuctions = memSaleManager.getAllSales().stream()
               .filter((SaleUsed s) -> s.getSaletype().equals("auction") 
                       && ( ! s.isComplete()))
               .collect(Collectors.toList());
       
       activeAuctions.stream().forEach((SaleUsed s) -> {
           
//           timerService.createCalendarTimer(new ScheduleExpression().end(getEndDate(s.getDatelisted(), s.getDuration())), new TimerConfig(s, true));
       });
       
   }
   
   private LocalDateTime ldtFromDate(Date date)
   {
       return LocalDateTime.from(date.toLocalDate());
   }
   
   private Date getEndDate(Date date, int daysFrom)
   {
       
      ZoneOffset zo = ZoneOffset.ofHours(10);
      Date d = new Date(date.getTime());
      return new Date(d.toLocalDate().plusDays(daysFrom).atStartOfDay().toEpochSecond(zo));
   }
   
   private Date calenderToDate(Calendar cal)
   {
       Date d = (Date) Date.from(cal.toInstant());
       return null;
   }

    @Override
    public void ejbTimeout(Timer timer) {
        SaleUsed sale = (SaleUsed) timer.getInfo();
        
    }
}
