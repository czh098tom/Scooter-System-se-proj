package data;

import java.util.LinkedList;

/**
 * Controller for pay fine operation.
 * @author Zihao Chen
 */
public class PayFineContract {
	
	/**
	 * Store Fine amount for each time.
	 */
	public static final double singleFineAmount=100.00;
	
	/**
	 * Store user in operation.
	 */
	private User user;
	/**
	 * Store all transactions.
	 */
	private LinkedList<Transaction> transactions;
	
	/**
	 * Initialize the contract with ID of a user.
	 * @param userid : ID of a user. Must be validated before using.
	 */
	public PayFineContract(String userid) {
		DataBase db=DataBase.getCurrent();
		this.user=db.getUserByID(userid);
		this.transactions=db.getTransactions();
	}

    /**
     * Get the total fine count unpaid.
     * @return An integer value of fine count.
     */
    public int sumUnpaidFine() {
    	int count=0;
    	for(Transaction t : transactions) {
    		if(t.getUserID().equals(user.getId())) {
    			if(t.isFine())count++;
    			else if(t.isPayFine())count--;
    		}
    	}
    	return count;
    }
	
    /**
     * Pay the fine and commit it to file.
     */
	public void pay() {
		int c=sumUnpaidFine();
		for(int i=0;i<c;i++) {
			transactions.add(new Transaction(Transaction.TYPE_PAY_FINE,user.getId(),Transaction.NAN_ID));
		}
		DataBase.getCurrent().writeToFile();
	}
}
