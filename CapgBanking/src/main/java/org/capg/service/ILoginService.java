package org.capg.service;

import java.util.List;

import org.capg.model.Account;
import org.capg.model.Customer;
import org.capg.model.LoginBean;
import org.capg.model.Transaction;

public interface ILoginService {
	
	public Customer isValidLogin(LoginBean loginBean);
	public boolean createCustomer(Customer customer);
	public Account createAccount(Account account);
	public List<Account> getaccountdetails(String string);
	public List<Account> getAllaccountdetails(String string);
	public Account getdetails_ToAccount(long accNo);
	public void createToaccount(Account toAccount);
	public void createTransaction(Transaction trans);
	public List<Transaction> getAlltransactions(int custId);
	public void updateAccount(Account toAccount);

}
