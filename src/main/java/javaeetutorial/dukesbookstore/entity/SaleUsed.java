/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.sql.Date;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.faces.convert.Converter;
import javax.validation.constraints.NotNull;

/**
 *
 * @author amanda hugnkiss
 */
@Entity
@Table(name = "sales_used")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM SaleUsed s"),
    @NamedQuery(name = "Sale.findById", query = "SELECT s FROM SaleUsed s WHERE s.id = :id"),
    @NamedQuery(name = "Sale.findByDatelisted", query = "SELECT s FROM SaleUsed s WHERE s.datelisted = :datelisted"),
    @NamedQuery(name = "Sale.findByDuration", query = "SELECT s FROM SaleUsed s WHERE s.duration = :duration"),
    @NamedQuery(name = "Sale.findByItemcondition", query = "SELECT s FROM SaleUsed s WHERE s.itemcondition = :itemcondition")
})
public class SaleUsed implements Serializable, Converter {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATELISTED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datelisted;

    @Column(name = "DURATION")
    private Integer duration;

    @Size(max = 255)
    @Column(name = "ITEMCONDITION")
    private String itemcondition;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "POSTAGE", nullable = true, precision = 7, scale = 2)    // Creates the database field with this size.
    private BigDecimal postage;

    @Column(name = "RESERVEPRICE", nullable = true, precision = 7, scale = 2)    // Creates the database field with this size.
    private BigDecimal reserveprice;

    @Column(name = "SALEPRICE", nullable = true, precision = 7, scale = 2)    // Creates the database field with this size.
    private BigDecimal saleprice;

    @Size(max = 255)
    @Column(name = "SALETYPE")
    private String saletype;

    @Column(name = "STARTPRICE", nullable = false, precision = 7, scale = 2)    // Creates the database field with this size.
    private BigDecimal startprice;

    @OneToMany(mappedBy = "saleidId")
    private Collection<AuctionBid> auctionBidsCollection;

    @JoinColumn(name = "BUYERID_ID", referencedColumnName = "ID")
    @ManyToOne
    private Member buyeridId;

    @JoinColumn(name = "ISBN_ISBN", referencedColumnName = "ISBN")
    @ManyToOne
    private Book isbn;

    @JoinColumn(name = "SELLERID_ID", referencedColumnName = "ID")
    @ManyToOne
    private Member selleridId;

    @Column(name = "AMOUNT", nullable = false, precision = 7, scale = 2)    // Creates the database field with this size.
    private BigDecimal amount;

    @Column(name = "COMMISSION", nullable = false, precision = 7, scale = 2)    // Creates the database field with this size.
    private BigDecimal commission;

    @Column(name = "PAID")
    private boolean paid;

    @Column(name = "COMPLETE", columnDefinition = "TINYINT DEFAULT 0")
    private boolean complete;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public SaleUsed() {
    }

    public SaleUsed(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDatelisted() {
        return datelisted;
    }

    public void setDatelisted(Calendar datelisted) {
        this.datelisted = datelisted;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getItemcondition() {
        return itemcondition;
    }

    public void setItemcondition(String itemcondition) {
        this.itemcondition = itemcondition;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public BigDecimal getReserveprice() {
        return reserveprice;
    }

    public void setReserveprice(BigDecimal reserveprice) {
        this.reserveprice = reserveprice;
    }

    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public String getSaletype() {
        return saletype;
    }

    public void setSaletype(String saletype) {
        this.saletype = saletype;
    }

    public BigDecimal getStartprice() {
        return startprice;
    }

    public void setStartprice(BigDecimal startprice) {
        this.startprice = startprice;
    }

    @XmlTransient
    public Collection<AuctionBid> getAuctionBidsCollection() {
        return auctionBidsCollection;
    }

    public void setAuctionBidsCollection(Collection<AuctionBid> auctionBidsCollection) {
        this.auctionBidsCollection = auctionBidsCollection;
    }

    public Member getBuyeridId() {
        return buyeridId;
    }

    public void setBuyeridId(Member buyeridId) {
        this.buyeridId = buyeridId;
    }

    public Book getIsbn() {
        return isbn;
    }

    public void setIsbn(Book isbn) {
        this.isbn = isbn;
    }

    public Member getSelleridId() {
        return selleridId;
    }

    public void setSelleridId(Member selleridId) {
        this.selleridId = selleridId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public boolean getPaymentstatus() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SaleUsed)) {
            return false;
        }
        SaleUsed other = (SaleUsed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    //@Override

    public Object getAsObject(FacesContext facesContext,
            UIComponent component, String value) {
        StringBuilder ISBN = new StringBuilder();
        ISBN.append(value);

        Book bookISBN = new Book(ISBN.toString());
        return bookISBN;
    }

    @Override
    public String toString() {
        return "Sale seller is: ";
    }

    @Override
    public String getAsString(FacesContext facesContext,
            UIComponent component, Object value) {
        return value.toString();
    }
}
