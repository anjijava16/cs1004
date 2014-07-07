/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 5 (SavingsAccount)
 */

/**
 * SavingsAccount is the basic class for some kinds of savings accounts.
 * 
 * @author Kai-Zhan Lee
 * @see Account
 * 
 */

public class SavingsAccount extends Account {

	/**
	 * A saving account's interest rate, 2.0%.
	 */
	protected double interestRate = 0.02;

	/**
	 * A savings account's withdrawal fee, $1.00.
	 */
	protected double withdrawalFee = 1.00;

	/**
	 * @param account
	 *            A given second account to which money may be transferred.
	 * @return The transfer fee from a savings account to a given second
	 *         account, in dollars.
	 */
	public double getTransferFee(Account account) {
		if (account instanceof SavingsAccount) {
			if (name.equals(account.name) && surname.equals(account.surname))
				return 0.00;
			else
				return 5.00;
		}
		// I didn't make this an 'else' statement in case I wanted to add
		// another subclass of Account later.
		if (account instanceof CheckingAccount) {
			if (name.equals(account.name) && surname.equals(account.surname))
				return 1.00;
			else
				return 10.00;
		}
		return 0.00;
	}

}
