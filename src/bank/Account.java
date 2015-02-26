package bank;

import java.util.HashMap;
import java.util.Map;

public class Account {

	private static int count = 0;
	/** Hash table of all accounts that have been created */
	private static Map<Integer,Account> accounts = new HashMap<Integer,Account>();
	
	/** Find account by ID */
	public static Account find(int id) {
		return accounts.get(id);	
	}

	final int id;
	private int balance;
	
	/** Create new account with unique ID and add it to global hash table */
	public Account() {
		synchronized(accounts) {
			id = count++;
			accounts.put(id, this);
		}
	}
	
	/** Negative amount indicates withdrawal */
	public synchronized void deposit(int amount) {
		balance += amount;
	}
	
	/** Transfer amount from this account to another. */
	public void transfer (Account to, int amount) {
            
            synchronized(this){
		deposit(-amount);
                
                synchronized(to){
                    to.deposit(amount);
                
                }
            }
	}
	
	public int balance() {
		return balance;
	}

	@Override
	public String toString() {
		return "ID:"+id+", balance:"+balance;
	}
}
