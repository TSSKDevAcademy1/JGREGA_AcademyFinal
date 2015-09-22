package userPack;

import java.util.Date;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

@Named
@XmlRootElement
@SessionScoped
public class User {
	private static User user = new User();

	private User() {
	}

	public static User getInstance() {
		return user;
	}

	private int Id;

	private String login;

	private String password;

	private String mail;

	private String name;

	private String surname;

	private Date registration_date;

	private int login_count;

	private Date last_login;

	private boolean is_active;

	private String repeatPassword;

	private int manager_id;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public int getLogin_count() {
		return login_count;
	}

	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
}
