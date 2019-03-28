import java.util.regex.Pattern;

public class User{

    private static final Pattern QMID_PAT=Pattern.compile("^\\d{9}$");
    private static final Pattern EMAIL_PAT=Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    private String id;
    private String name;
    private String email;

    //private Scooter taking;
    
    public User(String id,String name,String email) {
    	this.id=id;
    	this.name=name;
    	this.email=email;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public static boolean checkQMID(String s){
        return QMID_PAT.matcher(s).find();
    }

    public static boolean checkEmail(String s){
        return EMAIL_PAT.matcher(s).find();
    }
    public static void main(String[] args) {
    	//check Email format 1@2.3
    	System.out.println(User.checkEmail("1@2.3"));//true
    	System.out.println(User.checkEmail("@2.3"));//false
    	System.out.println(User.checkEmail("1@.3"));//false
    	System.out.println(User.checkEmail("1@2."));//false
    	//check QMID format with only 9 digits
    	System.out.println(User.checkQMID("123456789"));//true
    	System.out.println(User.checkQMID("1234567890"));//false
    	System.out.println(User.checkQMID("abcdefghi"));//false
    	System.out.println(User.checkQMID("12345678"));//false
    	if(User.checkEmail("")&&User.checkQMID("")) {
    		//add to database
    	}
    }
}
