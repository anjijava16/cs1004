Name: Kai-Zhan Lee
UNI: kl2792
Assignment 5 README

a) Shapes
Overview:
	I trusted that if every class was placed in its proper package then all of
	the 'imports' and constructions would work out, which they did.
	Steps:
	    - Look at each class's 'package' declaration.
		- Place each class in the correct package, making sure to uncheck the
		  "update references" option in Eclipse (because those alter the code).

b) Account
	Overview:
		Account is the basic class for all monthly compounded bank accounts.
	Notes:
		- I used the 'double' type for all values, because Matthew recommended
		  it (even though this type is imprecise and should not be used in real
		  life for precise numbers).
		- I made Account abstract because of the getTransferFee() method; it is
		  impossible to clearly define this fee with specific values without
		  knowing more about the account.

c) SavingsAccount extends Account
	Overview:
		SavingsAccount is a class (not abstract) that represents some kinds of
		savings accounts. The interest rate is 2.0%, and the withdrawal fee is
		$1.00 (see getTransferFee(Account account) for more information about
		the transfer fees).
	Notes:
		- getTransferFee(Account account) does the following:
				account instanceof		owner is same		returns
			1.  SavingsAccount			true				0
			2. 	CheckingAccount			true				1
			3. 	SavingsAccount			false				5
			4.  CheckingAccount			false				10
		- Note: getTransferFee(Account account) checks whether the owner is the
		        same by comparing the names and surnames of 'this' account and
		        the parameter account.
		        

d) CheckingAccount extends Account
	Overview:
		CheckingAccount is a class (not abstract) that represents some kinds of
		savings accounts. The interest rate is 0.1%, and the withdrawal fee is
		$0.00 (see getTransferFee(Account account) for more information about
		the transfer fees).
	Notes:
		- getTransferFee(Account account) does the following:
				account instanceof		owner is same		returns
			1.  SavingsAccount			true				0
			2. 	CheckingAccount			true				0
			3. 	SavingsAccount			false				5
			4.  CheckingAccount			false				10