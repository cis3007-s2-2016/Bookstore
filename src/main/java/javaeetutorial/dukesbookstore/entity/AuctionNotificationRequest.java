package javaeetutorial.dukesbookstore.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "auction_notification_requests")
@Entity
public class AuctionNotificationRequest implements Serializable{

	
    @Column(name = "BOOK_ISBN", nullable = false)
	@Id
    private String isbn;

    @Column(name = "MEMBER_ID", nullable = false)
	@Id
    private long memberId;

	
	
	
	
	
	
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

    
}