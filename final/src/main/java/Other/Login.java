package Other;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

@Named
@XmlRootElement
@SessionScoped
public class Login {

	private static Login log = new Login();

	private Login() {
	}

	public static Login getInstance() {
		return log;
	}

	private String login;

	private String password;

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

}
