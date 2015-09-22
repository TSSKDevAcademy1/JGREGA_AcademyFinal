package Controlers;

import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import Other.Login;
import Other.Validation;
import database.Data;
import userPack.User;

@ManagedBean
@Model
public class Controler {

	User userdata = User.getInstance();

	Login log = Login.getInstance();

	private String buttonId;

	public void print() {
		System.out.println("hello");
	}

	public void save() {
		Data datas = new Data();
		Validation validation = new Validation();

		if (validation.mail() && validation.login() && validation.password() && validation.name()
				&& validation.surname() && (userdata.getRepeatPassword()).equals(userdata.getPassword())) {
			datas.save(userdata);
		} else {
			System.out.print("Bad inputs");
		}
	}

	public void update() {
		Data datas = new Data();
		Validation validation = new Validation();

		if (validation.mail() && validation.login() && validation.password() && validation.name()
				&& validation.surname() && (validation.manId() || userdata.getManager_id() == 0)
				&& (userdata.getRepeatPassword()).equals(userdata.getPassword())) {
			datas.update(userdata);
		} else {
			System.out.print("Bad inputs");
		}
	}

	public String isFlagged() {
		Data datas = new Data();
		datas.loadLogin();

		if (userdata.isIs_active()) {
			return "now you are logged In!! [" + userdata.getLogin() + "]";
		} else {
			return "please login!!";
		}
	}

	// Listener na Tlacidla
	public void commandClicked(ActionEvent event) {

		buttonId = event.getComponent().getId();

		if (buttonId.equals("btn1")) {
			Data datas = new Data();
			datas.flagged(false, 0);
		}

	}

	public String padding(int index) {

		int pixels = 20;

		return "" + pixels * index + "px";
	}

	public String editable() {
		Data datas = new Data();
		datas.loadLogin();

		if ((log.getLogin()).equals(userdata.getLogin()) && (log.getPassword()).equals(userdata.getPassword())) {
			return "editUser";
		} else {
			return "noLogged";
		}
	}

	public boolean skusobna() {
		Data datas = new Data();
		datas.loadLogin();
		datas.sortedList();
		if ((log.getLogin()).equals(userdata.getLogin()) && (log.getPassword()).equals(userdata.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean sorting(int index) {
		Data datas = new Data();
		datas.load();

		if (datas.users.size() >= index) {
			return true;
		} else {
			return false;
		}
	}

	public String isRegistred() {

		Data datas = new Data();
		datas.load();
		datas.loadLogin();

		if ((log.getLogin()).equals(userdata.getLogin()) && (log.getPassword()).equals(userdata.getPassword())) {
			datas.flagged(true, 1); /// ak je prihlaseny je flaged
			return "Welcome " + userdata.getName() + " " + userdata.getSurname() + " ";
		} else {
			return "You are not registered OR loggedIn";
		}
	}

	public User getUserdata() {
		return userdata;
	}

	public void setUserdata(User userdata) {
		this.userdata = userdata;
	}

	public Login getLog() {
		return log;
	}

	public void setLog(Login log) {
		this.log = log;
	}
}
