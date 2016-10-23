/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kyle.Lewer
 */
@Entity
@Table(name="wishlists")
@XmlRootElement
public class WishlistItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@ManyToOne
    private Member user;
	@Id
    @ManyToOne
    private Book book;
    
	private Boolean shouldNotify = true;

	public Member getMember() {
		return user;
	}

	public void setMember(Member member) {
		this.user = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Boolean getShouldNotify() {
		return shouldNotify;
	}

	public void setShouldNotify(Boolean shouldNotify) {
		this.shouldNotify = shouldNotify;
	}
	
	
 
}
