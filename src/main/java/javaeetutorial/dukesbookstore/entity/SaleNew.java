/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author matt
 */
@Table(name="sales_new")
@Entity
public class SaleNew implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name ="PURCHASE_DATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp purchaseDate;
	
	@Column(name = "POSTAGE_PRICE", nullable = false, precision = 7, scale = 2)    // Creates the database field with this size.
		@Basic(optional = false)
	private BigDecimal postagePrice;
	
	@Basic(optional = false)
	@JoinColumn(name = "PURCHASER", referencedColumnName = "ID")
	@ManyToOne
	private Member purchaser;
	
	@Basic(optional = false)
	@Column(name ="ADDRESS_SHIPPED_TO")
	private String addressShipped;
	
	@Basic(optional = false)
	@OneToMany
	private List<PurchasedItem> purchasedItems;
			
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getPostagePrice() {
		return postagePrice;
	}

	public void setPostagePrice(BigDecimal postagePrice) {
		this.postagePrice = postagePrice;
	}

	public Member getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(Member purchaser) {
		this.purchaser = purchaser;
	}

	public String getAddressShipped() {
		return addressShipped;
	}

	public void setAddressShipped(String addressShipped) {
		this.addressShipped = addressShipped;
	}

	public List<PurchasedItem> getPurchasedItems() {
		return purchasedItems;
	}

	public void setPurchasedItems(List<PurchasedItem> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}

	public SaleNew() {
		setPurchasedItems(new ArrayList<>());
	}
	
	public void add(PurchasedItem item){
		this.getPurchasedItems().add(item);
	}
	
	
	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SaleNew)) {
			return false;
		}
		SaleNew other = (SaleNew) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaeetutorial.dukesbookstore.entity.SaleNew[ id=" + id + " ]";
	}
	
}
