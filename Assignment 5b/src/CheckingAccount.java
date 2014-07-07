/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 5 (CheckingAccount)
 */

/**
 * CheckingAccount is the basic class for some kinds of checking accounts.
 * 
 * @author Kai-Zhan Lee
 * @see Account
 * 
 */

public class CheckingAccount extends Account {

	/**
	 * A checking account's interest rate, 0.1%.
	 */
	protected double interestRate = 0.001;

	/**
	 * A checking account's withdrawal fee, $0.00.
	 */
	protected double withdrawalFee = 0.00;

	/**
	 * @param account
	 *            A given second account to which money may be transferred.
	 * @return The transfer fee from a checking account to a given second
	 *         account, in dollars.
	 */
	public double getTransferFee(Account account) {
		if (account instanceof CheckingAccount) {
			if (name.equals(account.name) && surname.equals(account.surname))
				return 0.00;
			else
				return 5.00;
		}
		// I didn't make this an 'else' statement in case I wanted to add
		// another subclass of Account later.
		if (account instanceof SavingsAccount) {
			if (name.equals(account.name) && surname.equals(account.surname))
				return 0.00;
			else
				return 10.00;
		}
		return 0.00;
	}

}
