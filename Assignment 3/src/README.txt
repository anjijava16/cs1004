Name: Kai-Zhan Lee
UNI: kl2792
Assignment 3 README

a)

divides(int dividend, int divisor): returns whether the divisor divides into
									the dividend
	returns: if divisor divides into dividend, true, and otherwise, false.

abs(int num): returns the absolute value of a number.
	returns: if num is negative, (-1 * num) , and otherwise, (num).

max(int num1, int num2): returns the larger of two integers.
	returns: if num1 is greater than num2, (num1), and otherwise, (num2).

maxMagnitude(int num1, int num2): returns the integer (two total) with the
								  largest magnitude.
	returns: if the absolute value of num1 is greater than that of num2,
			 (num1), and otherwise, (num2).

factor(int num): returns the smallest factor of a number.
	returns: the smallest factor of num, after using a for loop to find it.

b)

int counter: The static counter of the class, which can be altered using
			 Assignment3b's methods.

increment(): increases the value of counter by exactly 1.

getCount(): returns the value of the counter.

c)

int counter: The static counter of the class, which can be altered using
			 Assignment3c's methods. (initial value is 0)

int lastCount: A static preservation of what the counter's value was the last
			   time getCount() was used (and if getCount() had not been used,
			   the value is 0).

int sinceLastCount: A static integer that describes how many times increment()
					has been used since the last use of getCount() (initial
					value is 0).

increment(): increases the value of counter by exactly 1.

getCount(): sets lastCount to counter, sets sinceLastCount to 0, and returns
			counter.

lastCount(): returns lastCount.

sinceLastCount(): returns sinceLastCount.

d)

int[] ints: the int array used in all of the class's methods.

odds(): returns the sum of every other number, starting with the first, ints[0].
		If there are no values in ints (i.e. ints.length == 0), then odds()
		returns 0.

evens(): returns the sum of every other number, starting with the second,
		 ints[1]. If there are no even values in ints (i.e. ints.length < 1),
		 then evens() returns 0.

product(): returns the product of all numbers. If there are no values in ints
		   (i.e. ints.length = 0), returns 1.

parity(): if the sum of all of the numbers is odd, returns true, and
		  otherwise, returns false. (see piazza)