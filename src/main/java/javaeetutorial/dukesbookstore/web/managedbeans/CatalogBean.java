package javaeetutorial.dukesbookstore.web.managedbeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javaeetutorial.dukesbookstore.ejb.CatalogManager;
import javaeetutorial.dukesbookstore.entity.Author;

/**
 * Created by matt on 15/10/2016.
 */
@Named("catalog")
@RequestScoped
public class CatalogBean implements Serializable {
	@EJB
	CatalogManager catalogManager;
	private String isbn;
	private String title;
	private String authors;
	private Part imageFile;
	private String category;
	private String publisher;
	private String publishedDate;
	private String format;
	private String Synopsis;
	private double costPrice;
	private double retailPrice;
	private int stock;
	private List<Author> authorsList;
	private String remoteImageFile;


	// getters & setters

	public CatalogManager getCatalogManager() {
		return catalogManager;
	}

	public void setCatalogManager(CatalogManager catalogManager) {
		this.catalogManager = catalogManager;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthorsList() {
		return authorsList;
	}

	public void setAuthorsList(List<Author> authorsList) {
		this.authorsList = authorsList;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public Part getImageFile() {
		return imageFile;
	}

	public void setImageFile(Part imageFile) {
		this.imageFile = imageFile;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSynopsis() {
		return Synopsis;
	}

	public void setSynopsis(String synopsis) {
		Synopsis = synopsis;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setRemoteImageFile(String remoteImageFile) {
		this.remoteImageFile = remoteImageFile;
	}

	public String getRemoteImageFile() {
		return remoteImageFile;
	}

	public String getIsbn() {

		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String addBook() {

		if (catalogManager.bookExists(getIsbn())) {
			return "/admin/add-new-book.xhtml?faces-redirect=true&book-exists=true";
		}
		try {
			processAuthors();
			getCatalogManager().createBook(getIsbn(), getTitle(), BigDecimal.valueOf(getCostPrice()), BigDecimal.valueOf(getRetailPrice()), sqlPublishedDate(), getSynopsis(), getStock(), getPublisher(), getCategory(), getFormat(), getAuthorsList(), this.thumbnail());
		} catch (Exception e) {
			System.out.println("Failed to add new Book:  " + e.getMessage());
			return "/admin/add-new-book.xhtml?faces-redirect=true&data-invalid=true";
		}
		return "/admin/add-new-book.xhtml?success=true&faces-redirect=true&includeViewParams=true";
	}
	
	


	private Date sqlPublishedDate() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date pubDate = formatter.parse(getPublishedDate());
			return new Date(pubDate.getTime());
		} catch (Exception e) {
			throw new RuntimeException("Date not in required format");
		}
	}

	private byte[] thumbnail() {

		try {
			InputStream input = getImageInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];
			for (int length; (length = input.read(buffer)) > 0; ) output.write(buffer, 0, length);
			return output.toByteArray();
		} catch (Exception e) {
			System.out.println("Error saving thumbnail image!" + e.getMessage());
			throw new RuntimeException("Error saving thumbnail image!" + e.getMessage());
		}
	}

	private InputStream getImageInputStream() {
		if (getImageFile() == null) {
			try {
				URL remoteImageUrl = new URL(this.getRemoteImageFile());
				HttpURLConnection httpConn = (HttpURLConnection) remoteImageUrl.openConnection();
				if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					System.out.println("Input stream for image download successfully established.");
					return httpConn.getInputStream();
				} else {
					System.out.println("Input stream for image download FAILED!.");
				}
			} catch (Exception e) {
				throw new RuntimeException("Failed to retrieve remote image");
			}
		}


		try {
			return getImageFile().getInputStream();
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve local image");
		}

	}

	private void processAuthors() {
		try {
			System.out.println("Authors:  " + getAuthors());
			String[] authorsArray = getAuthors().split(",");
			String[] names;
			setAuthorsList(new ArrayList<>());

			for (String author : authorsArray) {
				StringBuilder firstnames = new StringBuilder();
				String lastname = "";
				names = author.split(" ");
				for (int i = 0; i < names.length; i++) {
					if (names.length - 1 == i) {
						lastname = names[i];
					} else {
						firstnames.append(names[i]).append(" ");
					}
				}

				getAuthorsList().add(getCatalogManager().createOrFindAuthor(firstnames.toString().trim(), lastname.trim()));
			}

		} catch (Exception e) {
			throw new RuntimeException("Failed to add authors:  " + e.getMessage());
		}
	}


}

