/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kyle.lewer
 */
@Entity
@Table(name = "members")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Member.findByPermissionGroup", query = "SELECT m FROM Member m where m.permissionGroup = :permissionGroup"),})
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic(optional = false)
	private String password;
	@Basic(optional = false)
	private String permissionGroup;
	@Basic(optional = false)
	private String firstName;
	@Basic(optional = false)
	private String surname;

	@Column(unique = true)
	@Basic(optional = false)
	private String email;

	@Column(name = "JOINDATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp joinDate;
	@Basic(optional = false)
	private String shippingAddressLine1;
	private String shippingAddressLine2;
	@Basic(optional = false)
	private String shippingPostcode;
	@Basic(optional = false)
	private String shippingCity;
	@Basic(optional = false)
	private String shippingState;
	@Basic(optional = false)
	private String billingAddressLine1;
	private String billingAddressLine2;
	@Basic(optional = false)
	private String billingCity;
	@Basic(optional = false)
	private String billingState;
	@Basic(optional = false)
	private String billingPostcode;
	private boolean receiveNewletter = false;

	@ManyToMany
	private List<Interest> userInterests;

	@OneToMany
	private List<AuctionNotificationRequest> auctionNotificationRequest;

	public boolean getReceiveNewletter() {
		return receiveNewletter;
	}

	public void setReceiveNewletter(boolean receiveNewletter) {
		this.receiveNewletter = receiveNewletter;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(String permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShippingAddressLine1() {
		return shippingAddressLine1;
	}

	public void setShippingAddressLine1(String shippingAddressLine1) {
		this.shippingAddressLine1 = shippingAddressLine1;
	}

	public String getShippingAddressLine2() {
		return shippingAddressLine2;
	}

	public void setShippingAddressLine2(String shippingAddressLine2) {
		this.shippingAddressLine2 = shippingAddressLine2;
	}

	public String getShippingPostcode() {
		return shippingPostcode;
	}

	public void setShippingPostcode(String shippingPostcode) {
		this.shippingPostcode = shippingPostcode;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getBillingAddressLine1() {
		return billingAddressLine1;
	}

	public void setBillingAddressLine1(String billingAddressLine1) {
		this.billingAddressLine1 = billingAddressLine1;
	}

	public String getBillingAddressLine2() {
		return billingAddressLine2;
	}

	public void setBillingAddressLine2(String billingAddressLine2) {
		this.billingAddressLine2 = billingAddressLine2;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingPostcode() {
		return billingPostcode;
	}

	public void setBillingPostcode(String billingPostcode) {
		this.billingPostcode = billingPostcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	@XmlTransient
	public List<AuctionNotificationRequest> getAuctionNotificationRequest() {
		return auctionNotificationRequest;
	}

	public void setAuctionNotificationRequest(List<AuctionNotificationRequest> auctionNotificationRequest) {
		this.auctionNotificationRequest = auctionNotificationRequest;
	}

	@XmlTransient
	public List<Interest> getUserInterests() {
		return userInterests;
	}

	public void setUserInterests(List<Interest> userInterests) {
		this.userInterests = userInterests;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member() {
	}

	public Member(String email, String password, String group) {
		this.setEmail(email);
		this.setPassword(securePassword(password));
		this.setPermissionGroup(group);
	}
	
	public String shippingAddressString(){
		StringBuilder address = new StringBuilder();
		if (!getBillingAddressLine2().isEmpty()) {
			address.append(getBillingAddressLine2()).append("\n");
		}
		if (!getBillingAddressLine2().isEmpty()) {
			address.append(getBillingAddressLine2()).append("\n");
		}
		address.append(getBillingCity()).append("\n");
		address.append(getShippingState()).append(" ");
		address.append(getShippingPostcode());
		return address.toString();
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
		if (!(object instanceof Member)) {
			return false;
		}
		Member other = (Member) object;
		return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
	}

	@Override
	public String toString() {
		return "javaeetutorial.dukesbookstore.entity.Member[ id=" + id + " ]";
	}

	private String securePassword(String password) {
		try {
			StringBuilder hexString = new StringBuilder();

			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte byteData[] = md.digest();

			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			System.out.println("Sucessfully encrypted password :)");
			return hexString.toString();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println("Failed to encrypt password!");
			return "";
		}
	}

}
