package data;
import java.util.regex.Pattern;

/**
 * Store data of a user.
 * @author ZIHAO CHEN
 */
public class User{

	/**
	 * Regular expression pattern for QMID.
	 */
    private static final Pattern QMID_PAT=Pattern.compile("^\\d{9}$");
	/**
	 * Regular expression pattern for E-mail.
	 */
    private static final Pattern EMAIL_PAT=Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    
    /**
     * Store ID of a user.
     */
    private String id;
    /**
     * Store name of a user.
     */
    private String name;
    /**
     * Store E-mail for a user.
     */
    private String email;
    
    /**
     * Default constructor for serialization. Never call this because ID is immutable.
     */
    public User() {}
    
    /**
     * Initialize {@link User} by ID, name and E-mail.
     * @param id : The ID of user. Desired to be unique and validated.
     * @param name : The name of user.
     * @param email : The E-mail of user. Desired to be validated.
     */
    public User(String id,String name,String email) {
    	this.id=id;
    	this.name=name;
    	this.email=email;
    }
    
    /**
     * Set the name of the user.
     * @param name : The name to change to.
     */
    public void setName(String name){
        this.name=name;
    }
    
    /**
     * Set the email of the user. Do not cast validation.
     * @param email : The E-mail to change to.
     */
    public void setEmail(String email){
        this.email=email;
    }
    
    /**
     * This method does nothing. Just JavaBean object needed.
     * @param id : No meanings.
     */
    public void setID(String id){
    	//Cannot be called
    }
    
    /**
     * Get the ID of the user.
     * @return The ID of the user. Desired to be validated before.
     */
    public String getID(){
        return id;
    }
    
    /**
     * Get the name of the user.
     * @return The name of user.
     */
    public String getName(){
        return name;
    }

    /**
     * Get the E-mail of the user.
     * @return The E-mail of the user. Desired to be validated before.
     */
    public String getEmail(){
        return email;
    }

    /**
     * Check whether a given QMID is valid.
     * @param s : The {@link String} to validate.
     * @return A boolean, True for valid.
     */
    public static boolean checkQMID(String s){
        return QMID_PAT.matcher(s).find();
    }

    /**
     * Check whether a given E-mail is valid.
     * @param s : The {@link String} to validate.
     * @return A boolean, True for valid.
     */
    public static boolean checkEmail(String s){
        return EMAIL_PAT.matcher(s).find();
    }
}
