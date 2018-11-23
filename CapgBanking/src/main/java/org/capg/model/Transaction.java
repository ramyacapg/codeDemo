package org.capg.model;

import java.time.LocalDate;

public class Transaction {
private long transactionId;
private LocalDate tansactionDate;
private Account fromAccount;
private Account toAccount;
private double amount;
private String transactionType;
private String description;
private int customerId;
public Transaction() {
	super();
}

public Transaction(long transactionId, LocalDate tansactionDate, Account fromAccount, Account toAccount, double amount,
		String transactionType, String description, int customerId) {
	super();
	this.transactionId = transactionId;
	this.tansactionDate = tansactionDate;
	this.fromAccount = fromAccount;
	this.toAccount = toAccount;
	this.amount = amount;
	this.transactionType = transactionType;
	this.description = description;
	this.customerId = customerId;
}

public int getCustomerId() {
	return customerId;
}

public void setCustomerId(int customerId) {
	this.customerId = customerId;
}

public long getTransactionId() {
	return transactionId;
}
public void setTransactionId(long transactionId) {
	this.transactionId = transactionId;
}
public LocalDate getTansactionDate() {
	return tansactionDate;
}
public void setTansactionDate(LocalDate tansactionDate) {
	this.tansactionDate = tansactionDate;
}
public Account getFromAccount() {
	return fromAccount;
}
public void setFromAccount(Account fromAccount) {
	this.fromAccount = fromAccount;
}
public Account getToAccount() {
	return toAccount;
}
public void setToAccount(Account toAccount) {
	this.toAccount = toAccount;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

@Override
public String toString() {
	return "Transaction [transactionId=" + transactionId + ", tansactionDate=" + tansactionDate + ", fromAccount="
			+ fromAccount + ", toAccount=" + toAccount + ", amount=" + amount + ", transactionType=" + transactionType
			+ ", description=" + description + ", customerId=" + customerId + "]";
}


}
