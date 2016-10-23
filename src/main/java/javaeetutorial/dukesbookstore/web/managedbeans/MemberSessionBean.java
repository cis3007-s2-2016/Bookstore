/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.ejb.MemberManager;
import javaeetutorial.dukesbookstore.entity.Member;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author matt
 */
@Named("memberSession")
@SessionScoped
public class MemberSessionBean implements Serializable {

	@EJB
	MemberManager memberManager;
	@Inject
	Wishlist wishlist;
	private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");
	private String username;
	private String password;
	private String oldPassword;
	private String newPassword1;
	private String newPassword2;
	private Member user;
	private int loginAttemptCount;
	private String newEmail;
	private String emailErrorMessage = "";

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getEmailErrorMessage() {
		return emailErrorMessage;
	}

	public void setEmailErrorMessage(String emailErrorMessage) {
		this.emailErrorMessage = emailErrorMessage;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Member getUser() {
		return user;
	}

	public void setUser(Member user) {
		this.user = user;
	}

	public int getLoginAttemptCount() {
		return loginAttemptCount;
	}

	private void setLoginAttemptCount(int loginAttemptCount) {
		this.loginAttemptCount = loginAttemptCount;
	}

	public MemberSessionBean() {
		setLoginAttemptCount(0);
	}

	public String login() {
		try {
			this.setUser(memberManager.find(this.getUsername()));
			String password = this.hashedPassword(this.getPassword());
			System.out.println(this.getPassword() + ",  " + password + "     " + getUser().getPassword());
			if (!password.equals(this.getUser().getPassword())) {
				throw new RuntimeException("Incorrect password");
			}
			this.setLoginAttemptCount(0);
			logger.info(this.getUser().getFirstName() + " logged in.");
			this.setNewEmail(user().getEmail());
			wishlist.updateWishlists();

			if (isAdmin()) {
				logger.info("Authenticated user is Admin. Returning admin panel");
				return "/admin/activity-summary";
			}
			if (isCustomer()) {
				logger.info("Authenticated user is Customer. Returning customer dashboard");
				return "/user/index";
			}

		} catch (Exception e) {
			logger.info("memberSession.info() : Error logging in:");
			logger.info(e.toString());
			logger.info(ExceptionUtils.getStackTrace(e));

			this.setUser(null);
			this.setUsername(null);
			this.setPassword(null);
			this.setLoginAttemptCount(this.getLoginAttemptCount() + 1);

			try {
				if (this.getLoginAttemptCount() > 5) {
					Thread.sleep(10000);
				}
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				logger.info("memberSessionBean.login():  Sleep thread interupted!" + e1);
				return "/login.xhtml?faces-redirect=true&error=true";
			}
		}
		return "/login.xhtml?faces-redirect=true&error=true";

	}

	public void logout() {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.invalidateSession();
			context.redirect(context.getRequestContextPath() + "/index.xhtml");
		} catch (Exception e) {
			System.out.println("Failed to log out! " + e.getMessage());
		}
	}

	public boolean isAdmin() {
		return this.getUser() != null && this.getUser().getPermissionGroup().equalsIgnoreCase("admin");
	}

	public boolean isCustomer() {
		return this.getUser() != null && this.getUser().getPermissionGroup().equalsIgnoreCase("customer");
	}

	public boolean hasPermissionGroup(String groupName) {
		if (groupName.equalsIgnoreCase("admin")) {
			return this.isAdmin();
		}
		if (groupName.equalsIgnoreCase("customer")) {
			return this.isCustomer();
		}
		return false;
	}

	public String changePassword() {
		logger.severe("Change password: " + getOldPassword() + getNewPassword1() + getNewPassword2());
		String password = hashedPassword(getOldPassword());
		if (!password.equals(getUser().getPassword())) {
			clearChangePasswordDetails();
			return "/user/change-password?error=true&faces-redirect=true&includeViewParams=true";
		} else if (!getNewPassword1().equals(getNewPassword2())) {
			clearChangePasswordDetails();
			return "/user/change-password?error=mismatch&faces-redirect=true&includeViewParams=true";
		} else {
			getUser().setPassword(this.hashedPassword(getNewPassword1()));
			memberManager.changePassword(user, hashedPassword(getNewPassword1()));
			clearChangePasswordDetails();
			return "/user/change-password?success=true&faces-redirect=true&includeViewParams=true";
		}

	}

	private void clearChangePasswordDetails() {
		setNewPassword1("");
		setNewPassword2("");
		setOldPassword("");
	}

	public Member user() {
		return this.getUser();
	}

	public String getFirstname() {
		if (this.getUser() == null) {
			return null;
		}
		return this.user().getFirstName();
	}

	public boolean isLoggedIn() {
		return this.getUser() != null;
	}

	public String editUser() {
		if (emailAlreadyRegistered()) {
			setNewEmail(getUser().getEmail());
			return "/user/details?error=email&faces-redirect=true";
		} else {
			getUser().setEmail(getNewEmail());
			memberManager.updateUser(getUser());
			return "/user/details?success=true&faces-redirect=true&includeViewParams=true";
		}
	}

	public void generateEmailExistsError() {
		if (getUser().getEmail().equalsIgnoreCase(getNewEmail().trim())) {
			setEmailErrorMessage("");
		} else if (emailAlreadyRegistered()) {
			setEmailErrorMessage("A user has already registered with that email");
		} else {
			setEmailErrorMessage("");
		}
	}

	private boolean emailAlreadyRegistered() {
		try {
			Member user = memberManager.find(getNewEmail().toLowerCase());
			if (user == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private String hashedPassword(String password) {
		byte byteData[];
		try {

			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byteData = md.digest();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println("Failed to encrypt password!");
			return "";
		}

		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();

	}

}
