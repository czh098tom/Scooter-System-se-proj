package data;

import java.util.LinkedList;

/**
 * Controller for user registration operation.
 * @author Zihao Chen
 */
public class RegistrationContract {
	/**
	 * Store ID to register.
	 */
	private String userID;
	/**
	 * Store full name to register.
	 */
	private String fullName;
	/**
	 * Store e-mail to register.
	 */
	private String email;
	
	/**
	 * Initialize the contract with userID, full name and email.
	 * @param userID : ID of the user to register, must be validated before.
	 * @param fullName : Full name of the user to register.
	 * @param email : Email of the user to register, must be validated before.
	 */
	public RegistrationContract(String userID,String fullName,String email) {
		this.userID=userID;
		this.email=email;
		this.fullName=fullName;
	}

    /**
     * Register a {@link User} to current database by given data.
     * @return an object typed {@link User}.
     */
	public User register() {
		LinkedList<User> users=DataBase.getCurrent().getUsers();
		User u=new User(userID,fullName,email);
		users.add(u);
		DataBase.getCurrent().writeToFile();
		return u;
	}
}
