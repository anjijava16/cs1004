/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 5 (Account)
 */

/**
 * Account is the basic class for all monthly compounded bank accounts.
 * 
 * @author Kai-Zhan Lee
 * 
 */

abstract public class Account {

	/**
	 * The account's owner's name.
	 */
	protected String name;

	/**
	 * The account's owner's surname.
	 */
	protected String surname;

	/**
	 * The interest rate of the account.
	 */
	protected double interestRate;

	/**
	 * The account's balance, in dollars.
	 */
	protected double balance;

	/**
	 * The account's withdrawal fee, in dollars.
	 */
	protected double withdrawalFee;

	/**
	 * @return The account's interest rate.
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * @return The account's balance, in dollars.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return The withdrawal fee.
	 */
	public double getWithdrawalFee() {
		return withdrawalFee;
	}

	/**
	 * @param account
	 *            A given second account to which money may be transferred.
	 * @return The transfer fee to a given second account, in dollars.
	 */
	public abstract double getTransferFee(Account account);

	/**
	 * @param account
	 *            A given second account to which money may be transferred.
	 * @return Whether a transfer to the second account is possible.
	 */
	public boolean canTransfer(Account account, double amount) {
		if (getBalance() < getTransferFee(account))
			return false;
		if (amount < 0)
			return false;
		if (balance < amount + getTransferFee(account))
			return false;
		return true;
	}

	/**
	 * Deposits a positive amount of money into this account.
	 * 
	 * @param amount
	 *            The positive amount of money to be deposited.
	 * @return Whether the deposit is possible.
	 */
	public boolean deposit(double amount) {
		if (amount < 0)
			return false;
		balance += amount;
		return true;
	}

	/**
	 * Withdraws a positive amount of money from this account.
	 * 
	 * @param amount
	 *            The positive amount of money to be withdrawn.
	 * @return Whether the withdrawal is possible.
	 */
	public boolean withdraw(double amount) {
		if (amount < 0)
			return false;
		if (balance < amount + getWithdrawalFee())
			return false;
		balance -= getWithdrawalFee();
		balance -= amount;
		return true;
	}

	/**
	 * Transfers a positive amount of money from this account into the parameter
	 * account.
	 * 
	 * @param account
	 *            The account to which money will be transfered.
	 * @param amount
	 *            The positive amount of money to be transfered.
	 * @return Whether the transfer is possible.
	 */
	public boolean transfer(Account account, double amount) {
		if (!canTransfer(account, amount))
			return false;
		balance -= getTransferFee(account);
		balance -= amount;
		account.deposit(amount);
		return true;
	}

	/**
	 * Applies one month's interest. If the rate is r, then we multiply the
	 * principle by (1.0 + (r/12.0)).
	 */
	public void compound() {
		balance *= (1.0 + (interestRate / 12.0));
	}

}
