package org.capg.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.capg.model.Account;
import org.capg.model.AccountType;
import org.capg.model.Customer;
import org.capg.model.LoginBean;
import org.capg.model.Transaction;



public class LoginDaoImpl implements ILoginDao{

	@Override
	public Customer isValidLogin(LoginBean loginBean) {
		
		String sql="select * from Customer2 where email=? and passwrd=?";
		try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
			
			pst.setString(1, loginBean.getUserName());
			pst.setString(2, loginBean.getUserPassword());
			
			ResultSet rs=pst.executeQuery();
			
			if(rs.next()) {
				Customer customer=new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				return customer;
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	private Connection getMysqlDbConnection() {
		
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "India123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}


	@Override
	public boolean createCustomer(Customer customer) {
		int customerId=0;
		boolean flag=false;
	String sql="insert into Customer2(firstName,lastName,dateOfBirth, email,mobileNo,passwrd)"+
						" values(?,?,?,?,?,?)";
		
	try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
			
		
		pst.setString(1, customer.getFirstName());
		pst.setString(2, customer.getLastName());
		pst.setDate(3, Date.valueOf(customer.getDateOfBirth()));
		pst.setString(4, customer.getEmailId());
		pst.setString(5,customer.getMobile());
		pst.setString(6,customer.getCustomerPwd());	
		
		int count=pst.executeUpdate();
		if(count>0)
			flag=true;
		
		if(flag) {
			String sqlMax="select max(customerId) from customer";
			try(PreparedStatement pst1=getMysqlDbConnection().prepareStatement(sqlMax)) {
				ResultSet rs= pst1.executeQuery();
				if(rs.next())
					customerId=rs.getInt(1);
				
				
				String sqlAdd="insert into address(addressline1,addressline2,city,state,pincode,customerId) values(?,?,?,?,?,?)";
				
				try(PreparedStatement pst2=getMysqlDbConnection().prepareStatement(sqlAdd)) {
					pst2.setString(1, customer.getAddress().getAddressLine1());
					pst2.setString(2, customer.getAddress().getAddressLine2());
					pst2.setString(3, customer.getAddress().getCity());
					pst2.setString(4, customer.getAddress().getState());
					pst2.setString(5, customer.getAddress().getPincode());
					pst2.setInt(6, customerId);
					
					int count1=pst2.executeUpdate();
					if(count1>0)
						flag=true;
					else
						flag=false;
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			flag=false;
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return flag;
	}


	@Override
	public Account createAccount(Account account) {
		
		Long accountNo=null;
		
		String sqlaccNum="select max(accountNumber) from account";
		try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sqlaccNum)){
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				accountNo=Long.valueOf(rs.getInt(1));
				if(accountNo==0)
					accountNo=100000l;
				else
					accountNo+=1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		account.setAccountNumber(accountNo);
	//System.out.println(account);
		String sql="insert into account(accountNumber,accountType,openingDate,openingBalance,description,customerId) values(?,?,?,?,?,?);";
		try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)){
			
			pst.setLong(1, accountNo);
			pst.setString(2, account.getAccountType().toString());
			pst.setDate(3,Date.valueOf(account.getOpeningDate()));
			pst.setDouble(4, account.getOpeningBalance());
			pst.setString(5, account.getDescription());
			pst.setInt(6, account.getCustomer().getCustomerId());
			int count=pst.executeUpdate();
			if(count>0)
				return account;
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}


	@Override
	public List<Account> getaccountdetails(String string) {
		
		Customer c=new Customer();
	List<Account> accounts=new ArrayList<>();
	int custoId=Integer.parseInt(string);
	c.setCustomerId(custoId);

	String sql="select * from account where customerId=?";		
	try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
			pst.setInt(1, custoId);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Account acnt=new Account();
				acnt.setAccountNumber(rs.getInt(1));
				acnt.setCustomer(c);				
				acnt.setAccountType(AccountType.valueOf(rs.getString(3)));		
				accounts.add(acnt);
				
			}
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return accounts;
	}




	@Override
	public List<Account> getAllaccountdetails(String string) {
		
		List<Account> accounts=new ArrayList<>();
		int custoId=Integer.parseInt(string);
		String sql="select * from account where customerId!=?";		
		
		try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
			pst.setInt(1, custoId);
				ResultSet rs=pst.executeQuery();
				
				while(rs.next()) {
					Customer c=new Customer();
				
					
//					if(rs.getInt(2)!=custoId) {
						Account acnt=new Account();
					acnt.setAccountNumber(rs.getInt(1));
					
					c.setCustomerId(rs.getInt(2));
				acnt.setAccountType(AccountType.valueOf(rs.getString(3)));
//					acnt.setCustomer(c);
//					acnt.setDescription(rs.getString(6));
					acnt.setOpeningBalance(rs.getInt(5));
//					String str=rs.getString(4);
//					int date=Integer.parseInt(str.substring(1, 2));
//					int month=Integer.parseInt(str.substring(3, 5));
//					int year=Integer.parseInt(str.substring(6, 10));
//					acnt.setOpeningDate(LocalDate.of(year, month, date));
					accounts.add(acnt);
					}
					
		}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return accounts;
		
		
	
	}


	@Override
	public Account getdetails_ToAccount(long accNo) {
		Account Toaccount=new Account();
		String sql="select * from account where accountNumber=?;";		
		
		try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
			pst.setLong(1, accNo);
				ResultSet rs=pst.executeQuery();				
				while(rs.next()) {
				Toaccount.setAccountNumber(accNo);
				Customer cu=new Customer();
				cu.setCustomerId(rs.getInt(2));
				Toaccount.setCustomer(cu);
				Toaccount.setAccountType(AccountType.valueOf(rs.getString(3)));
				Toaccount.setDescription(rs.getString(6));
				Toaccount.setOpeningBalance(rs.getDouble(5));
			
				String date1=rs.getDate(4).toString();
				String[] arr=(date1.split("-"));
				int date=Integer.parseInt(arr[2]);
				int month=Integer.parseInt(arr[1]);
				int year=Integer.parseInt(arr[0]);
				Toaccount.setOpeningDate(LocalDate.of(year, month, date));
		
	
				}
}catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		//System.out.println("getdetails_toacc:"+Toaccount);
		return Toaccount;
	}


	@Override
	public void createToaccount(Account toAccount) {
		Long accountNo=toAccount.getAccountNumber();
		Double bal=toAccount.getOpeningBalance();
		String sql="delete from account where accountNumber=?";	
		try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
		pst.setInt(1,Integer.parseInt( accountNo.toString()));
		int count=pst.executeUpdate();
		if(count>0) {
			createAccount(toAccount);
		}
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


	@Override
	public void createTransaction(Transaction trans) {
	
		String sql="insert into transaction(transactionDate,fromAccountNumber,toAccountNumber,amount,transactionType,description,customer_id) values(?,?,?,?,?,?,?)";
try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)){
	
	
			pst.setDate(1, Date.valueOf(LocalDate.now()));
			Long frmacc=trans.getFromAccount().getAccountNumber();
			Long toacc=trans.getToAccount().getAccountNumber();
			pst.setInt(2, Integer.parseInt(frmacc.toString()));
			pst.setInt(3, Integer.parseInt(toacc.toString()));
			pst.setDouble(4, trans.getAmount());
			pst.setString(5, trans.getTransactionType());
			pst.setString(6, trans.getDescription());
			pst.setInt(7, trans.getCustomerId());
			int count=pst.executeUpdate();
			if(count>0) {
			//	System.out.println("creating"+trans);
				}
}catch (SQLException e) {
		
		e.printStackTrace();
	}
	}


	@Override
	public List<Transaction> getAllTransactions(int custId) {
		List<Transaction> trans=new ArrayList<>();
		
		Account frmaccount=new Account();
		Account toaccount=new Account();
		
	int customerId=custId;
		String sql="select * from transaction where customer_id=?";
		try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
			pst.setInt(1, customerId);
			ResultSet rees= pst.executeQuery();
			while(rees.next()) {
				Transaction transaction=new Transaction();
				transaction.setTransactionId(rees.getInt(1));
				String date1=rees.getDate(2).toString();
				String[] arr=(date1.split("-"));
				int date=Integer.parseInt(arr[2]);
				int month=Integer.parseInt(arr[1]);
				int year=Integer.parseInt(arr[0]);
				transaction.setTansactionDate(LocalDate.of(year, month, date));
				frmaccount=getdetails_ToAccount(rees.getInt(3));
				toaccount=getdetails_ToAccount(rees.getInt(4));
				transaction.setFromAccount(frmaccount);
				transaction.setToAccount(toaccount);
				transaction.setAmount(rees.getDouble(5));
				transaction.setTransactionType(rees.getString(6));
				transaction.setDescription(rees.getString(7));
				transaction.setCustomerId(customerId);
			//	System.out.println("getTransaction:"+transaction);
				trans.add(transaction);
			}
		
	}catch (SQLException e) {
		
		e.printStackTrace();
	}
		return trans;
	}


	@Override
	public void updateAccount(Account toAccount) {
		Double openingbal=toAccount.getOpeningBalance();
		long accNo=toAccount.getAccountNumber();
		 String sql="Update account set openingBalance=? where accountNumber=? ";
			
			
			try(PreparedStatement pst=getMysqlDbConnection().prepareStatement(sql)) {
				pst.setDouble(1,openingbal );
				pst.setLong(2, accNo);
				int count=pst.executeUpdate();
//				if(count<=0){
//					System.out.println();
//				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}
		
	
	
}
	
