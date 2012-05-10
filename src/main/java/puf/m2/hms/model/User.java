package puf.m2.hms.model;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.UserException;

public class User extends HmsEntity {

	private static final Map<Integer, User> USER_MAP = new CacheAwareMap<Integer, User>();

	@DbProp
	private String name;
	@DbProp
	private String password;
	@DbProp
	private String email;
	@DbProp
	private String role;
	@DbProp
	private boolean deleted;

	public User(String name, String password, String email, String role, boolean deleted) {

		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
		this.deleted = deleted; 
	}
	
	public User(String name, String password, String email, String role) {

		this(name, password, email, role, false);
	}

	public static User login(String username, String password) {
		final String queryTemplate = "select * from User where name = ''{0}'' and password = ''{1}''";

		try {
			ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate,
					username, password));

			if (rs != null) {
				int id = rs.getInt("id");
				User user = USER_MAP.get(id);
				if (user == null) {
					boolean deleted = rs.getInt("deleted") == 1 ? true : false;
					user = new User(username, password, rs.getString("email"),
							rs.getString("role"), deleted);
					user.id = id;
					USER_MAP.put(id, user);
				}
				return user;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}

	}

	public void save() throws UserException {
		try {
			super.save();
		} catch (HmsException e) {
			throw new UserException(e);
		}
		USER_MAP.put(id, this);
	}

	public void update() throws UserException {

		try {
			super.update();
		} catch (HmsException e) {
			throw new UserException(e);
		}
	}

	public String getUsername() {
		return name;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseremail() {
		return email;
	}

	public void setUseremail(String useremail) {
		this.email = useremail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
