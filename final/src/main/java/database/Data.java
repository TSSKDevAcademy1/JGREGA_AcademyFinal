package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import Other.Login;
import Other.Structure;
import userPack.User;

@ManagedBean
@Named
public class Data {

	User userdata = User.getInstance();
	Login log = Login.getInstance();

	int backSize = 0;

	public List<Structure> users = new ArrayList<>();

	public List<Structure> usersSort = new ArrayList<>();

	public List<Integer> usedId = new ArrayList<Integer>();

	private List<Integer> levels = new ArrayList<Integer>();

	public static final String URL = "jdbc:mysql://localhost:3306/jgrega";
	public static final String USER = "JGREGA"; // JGREGA
	public static final String PASSWORD = "pcr7gxC2CfrS6EVuRnRc";  // je potrebne naplnit databazu na virtualke (min/max jeden manager_id=0)
	// Aplikacia bola vytvorena na domacom PC s pouzitim vlastnej Databazy															

	public static final String QUERY_SAVE = "INSERT INTO user (login, password,mail,name,surname,registration_date,login_count,last_login,is_active,manager_id)"
			+ " VALUES (?, ?,?, ?, ?,?, ?, ?, ?, ?)";
	public static final String QUERY_UPDATE = "UPDATE user SET login=?, password=?,mail=?,name=?,surname=?,registration_date=?,login_count=?,last_login=?,is_active=?";
	public static final String QUERY_LOAD = "SELECT Id, login, password,mail,name,surname,registration_date,login_count,last_login,is_active,manager_id FROM user ";
	public static final String QUERY_LOAD_LOG = "SELECT Id, login, password,mail,name,surname,registration_date,login_count,last_login,is_active,manager_id FROM user WHERE login=?";

	public static final String QUERY_DROP = "DROP TABLE user";

	java.sql.Timestamp dateReg = new java.sql.Timestamp(new java.util.Date().getTime());

	public void loadLogin() {
		Connection conection;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			conection = DriverManager.getConnection(URL, USER, PASSWORD);

			Statement stmt = conection.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT Id, login, password,mail,name,surname,registration_date,login_count,last_login,is_active,manager_id FROM user WHERE login='"
							+ log.getLogin() + "'");

			if (rs.next()) {
				userdata.setId(rs.getInt(1));
				userdata.setLogin(rs.getString(2));
				userdata.setPassword(rs.getString(3));
				userdata.setMail(rs.getString(4));
				userdata.setName(rs.getString(5));
				userdata.setSurname(rs.getString(6));
				userdata.setRegistration_date(rs.getDate(7));
				userdata.setLogin_count(rs.getInt(8));
				userdata.setLast_login(rs.getDate(9));
				userdata.setIs_active(rs.getBoolean(10));
				userdata.setManager_id(rs.getInt(11));
			}

			rs.close();
			stmt.close();
			conection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Structure> load() { // String

		Connection conection;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}

			conection = DriverManager.getConnection(URL, USER, PASSWORD);

			Statement stmt = conection.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY_LOAD);

			while (rs.next()) {
				Structure structure = new Structure();

				structure.setId(rs.getInt(1));
				structure.setLogin(rs.getString(2));
				structure.setPassword(rs.getString(3));
				structure.setMail(rs.getString(4));
				structure.setName(rs.getString(5));
				structure.setSurname(rs.getString(6));
				structure.setRegistration_date(rs.getDate(7));
				structure.setLogin_count(rs.getInt(8));
				structure.setLast_login(rs.getDate(9));
				structure.setIs_active(rs.getBoolean(10));
				structure.setManager_id(rs.getInt(11));

				users.add(structure);
			}

			rs.close();
			stmt.close();
			conection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	// User register
	public void save(User user) {

		Connection conection;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			conection = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement stmt = conection.prepareStatement(QUERY_SAVE);

			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getMail());
			stmt.setString(4, user.getName());
			stmt.setString(5, user.getSurname());
			stmt.setTimestamp(6, dateReg);
			stmt.setInt(7, 0);
			stmt.setDate(8, new Date(0));
			stmt.setBoolean(9, true);
			stmt.setInt(10, user.getManager_id());

			stmt.executeUpdate();

			stmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// is_active update
	public void flagged(boolean flag, int plus) {

		Connection conection;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");

			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			conection = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement stmt = conection.prepareStatement(
					"UPDATE user SET login_count=?,last_login=?,is_active=? WHERE login='" + log.getLogin() + "'");

			stmt.setInt(1, (userdata.getLogin_count()) + plus);
			stmt.setTimestamp(2, dateReg);
			stmt.setBoolean(3, flag);

			stmt.executeUpdate();

			stmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(User user) {

		Connection conection;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");

			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			conection = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement stmt = conection.prepareStatement(
					"UPDATE user SET login=?, password=?,mail=?,name=?,surname=?,manager_id=? WHERE login='"
							+ log.getLogin() + "'");

			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getMail());
			stmt.setString(4, user.getName());
			stmt.setString(5, user.getSurname());
			stmt.setInt(6, user.getManager_id());

			stmt.executeUpdate();

			stmt.close();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Structure> getUsers() {
		return users;
	}

	public void setUsers(List<Structure> users) {
		this.users = users;
	}

	public List<Structure> getUsersSort() {
		return usersSort;
	}

	public void setUsersSort(List<Structure> usersSort) {
		this.usersSort = usersSort;
	}

	public List<Integer> getLevels() {
		return levels;
	}

	public void setLevels(List<Integer> levels) {
		this.levels = levels;
	}

	// Usporiadanie registrovanych uzivatelov pre potreby ich vypisu DAY2 (bez
	// REKURZIE)
	public List<Structure> sortedList() {
		load();

		int a = 0;
		int i = 0;
		int level = 0;
		while (usersSort.size() < users.size()) {
			if (backSize == 0) {
				a++;
			}
			if (i == 0) {
				for (int j = 0; j < users.size(); j++) {
					if (users.get(j).getManager_id() == 0) {
						backSize = usersSort.size();
						usersSort.add(users.get(j));
						usedId.add(users.get(j).getId());
						levels.add(level);
						i++;
					}
				}
			} else {
				for (int j = 0; j < users.size(); j++) {
					if (usedIds(users.get(j).getId())) {
						if (usersSort.get(i - 1).getId() - backSize == users.get(j).getManager_id()
								&& usedIds(usersSort.get(i - 1).getId() - backSize) == false) {

							usersSort.add(users.get(j));
							usedId.add(users.get(j).getId());
							i++;
							j = users.size();

							if (usersSort.get(i - 1).getManager_id() == usersSort.get(i - 2).getId()) {
								levels.add(levels.get(i - 2).intValue() + 1);
							} else if (usersSort.get(i - 1).getManager_id() == usersSort.get(i - 2).getManager_id()) {
								levels.add(levels.get(i - 2).intValue());
							} else {
								for (int q = 0; q < usersSort.size(); q++) {
									if (usersSort.get(i - 1).getManager_id() == usersSort.get(q).getManager_id()) {
										levels.add(levels.get(q).intValue());
										q = usersSort.size();
									}
								}
							}

						} else if (j == users.size() - 1) {
							for (int k = 0; k < users.size(); k++) {
								if (usedIds(users.get(k).getId())
										&& (usersSort.get(i - 1).getManager_id() == users.get(k).getManager_id())) {
									usersSort.add(users.get(k));
									usedId.add(users.get(k).getId());
									i++;
									k = users.size();

									for (int q = 0; q < usersSort.size(); q++) {
										if (usersSort.get(i - 1).getManager_id() == usersSort.get(q).getManager_id()) {
											levels.add(levels.get(q).intValue());
											q = usersSort.size();
										}
									}
								}
							}
						}
					}
				}
			}
			back(a, i);
		}
		return usersSort;
	}

	// Ak sa dane Id nachadza uz v Sortovanom liste tak vrati FALSE inak TRUE
	public boolean usedIds(int id) {
		int find = 0;
		for (int l = 0; l < usedId.size(); l++) {
			if (usedId.get(l) == id)
				find++;
		}
		if (find == 1) {
			return false;
		} else {
			return true;
		}
	}

	// Funkcia pre navrat na nizsi level
	public void back(int size, int i) {

		if (size > i) {
			backSize = backSize + 1;

		} else {
			backSize = 0;
		}
	}
}

// CREATE TABLE user(
// Id int(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
// login varchar(255) UNIQUE NOT NULL,
// password varchar(255) NOT NULL,
// mail varchar(255) UNIQUE NOT NULL,
// name varchar(255) NOT NULL,
// surname varchar(255) NOT NULL,
// registration_date DATETIME NOT NULL,
// login_count int(10) UNSIGNED NOT NULL,
// last_login DATETIME NOT NULL,
// is_active BOOLEAN NOT NULL,
// manager_id int(10) UNSIGNED NOT NULL
// );
