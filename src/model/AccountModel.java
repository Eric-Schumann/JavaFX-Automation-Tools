package model;

import lib.AccountType;

public class AccountModel {
	
	private AccountType accountType;
	private String accountNumber;
	private String accountSymbol;
	
	public AccountModel(AccountType type, String name) {
		this.accountType = type;
		this.accountNumber = name;
		
		resolveAccountTypeToSymbol(this.accountType);
		
	}
	
	private void resolveAccountTypeToSymbol(AccountType type) {
		
		switch (type) {
			case CHECKING: 
				this.accountSymbol = "D";
				break;
			case CUSTOMER:
				this.accountSymbol = "$";
				break;
			case LOAN:
				this.accountSymbol = "L";
				break;
			case SAVINGS:
				this.accountSymbol = "S";
				break;
			case TIME_DEPOSIT:
				this.accountSymbol = "T";
				break;
		}
		
	} 
	
	//getters
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public String getAccountSymbol() {
		return this.accountSymbol;
	}
	
	//setters
	public void setAccountSymbol(String symbol) {
		this.accountSymbol = symbol;
	} 
}
