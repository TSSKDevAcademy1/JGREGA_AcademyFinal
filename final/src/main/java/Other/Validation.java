package Other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import userPack.User;

@ManagedBean
@Named
public class Validation {

	User userdata = User.getInstance();

	public boolean mail() {
		Pattern pattern = Pattern
				.compile("(([A-Za-z0-9]+)(.[A-Za-z0-9]+@)([A-Za-z0-9]+)+(.[A-Za-z0-9]+)+(.[A-Za-z]+))");
		Matcher matcher = pattern.matcher(userdata.getMail());
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public String mailVal() {
		try {
			if (mail()) {
				return "Valid";
			} else {
				return "Invalid";
			}
		} catch (Exception e) {

		}
		return "Invalid";
	}

	public boolean login() {
		Pattern pattern = Pattern.compile("([a-z0-9]+)");
		Matcher matcher = pattern.matcher(userdata.getLogin());

		if (matcher.matches() && userdata.getLogin().length() >= 4 && userdata.getLogin().length() <= 10) {
			return true;
		} else {
			return false;
		}
	}

	public String loginVal() {
		try {
			if (login()) {
				return "Valid";
			} else {
				return "Invalid";
			}
		} catch (Exception e) {

		}
		return "Invalid";
	}

	public boolean password() {
		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
		Matcher matcher = pattern.matcher(userdata.getPassword());

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public String passwordVal() {
		try {
			if (password()) {
				return "Valid";
			} else {
				return "Invalid";
			}
		} catch (Exception e) {

		}
		return "Invalid";
	}

	public boolean name() {
		Pattern pattern = Pattern.compile("(([A-Z])([a-z]+))");
		Matcher matcher = pattern.matcher(userdata.getName());

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public String nameVal() {
		try {
			if (name()) {
				return "Valid";
			} else {
				return "Invalid";
			}
		} catch (Exception e) {

		}
		return "Invalid";
	}

	public boolean surname() {
		Pattern pattern = Pattern.compile("(([A-Z])([a-z]+))");
		Matcher matcher = pattern.matcher(userdata.getSurname());

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public String surnameVal() {
		try {
			if (surname()) {
				return "Valid";
			} else {
				return "Invalid";
			}
		} catch (Exception e) {

		}
		return "Invalid";
	}

	public boolean manId() {

		if (userdata.getManager_id() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public String manIdVal() {
		try {
			if (manId()) {
				return "Valid";
			} else {
				return "Cant edit Manager_id of head manager";
			}
		} catch (Exception e) {

		}
		return "Invalid";
	}
}
