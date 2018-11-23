package org.capg.service;

import java.util.List;

import org.capg.dao.ILoginDao;
import org.capg.dao.LoginDaoImpl;
import org.capg.model.Account;
import org.capg.model.Customer;
import org.capg.model.LoginBean;
import org.capg.model.Transaction;

public class LoginServiceImpl implements ILoginService{

	private ILoginDao loginDao=new LoginDaoImpl();
	
	@Override
	public Customer isValidLogin(LoginBean loginBean) {
		/*if(loginBean.getUserName().equals("tom") && 
				loginBean.getUserPassword().equals("tom123")) {
			return true;
		}*/
		
		/*if(loginDao.isValidLogin(loginBean))
			return true;*/
		
		return loginDao.isValidLogin(loginBean);
	}

	@Override
	public boolean createCustomer(Customer customer) {
		
		System.out.println( loginDao.createCustomer(customer));
		
		return loginDao.createCustomer(customer);
	}

	@Override
	public Account createAccount(Account account) {
		System.out.println( loginDao.createAccount(account));
		
		return loginDao.createAccount(account);
	}

	@Override
	public List<Account> getaccountdetails(String string) {
		System.out.println( loginDao.getaccountdetails(string));
		return loginDao.getaccountdetails(string);
	}

	@Override
	public List<Account> getAllaccountdetails(String string) {
		// TODO Auto-generated method stub
		return loginDao.getAllaccountdetails(string);
	}

	@Override
	public Account getdetails_ToAccount(long accNo) {
		// TODO Auto-generated method stub
		return loginDao.getdetails_ToAccount(accNo);
	}

	@Override
	public void createToaccount(Account toAccount) {
		
		loginDao.createToaccount(toAccount);
	}

	@Override
	public void createTransaction(Transaction trans) {
		
		loginDao.createTransaction( trans);
	}

	@Override
	public List<Transaction> getAlltransactions(int custId) {
		List<Transaction> transaction=loginDao.getAllTransactions(custId);
		return transaction;
		
	}

	@Override
	public void updateAccount(Account toAccount) {
		loginDao.updateAccount(toAccount);
		
	}

	
}
