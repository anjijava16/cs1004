Name: Kai-Zhan Lee
UNI: kl2792
Assignment 4 README

a) (Fibonacci)

public int a: the term (n-2) in the Fibonacci series.

public int b: the term (n-1) in the Fibonacci series.

public int sum: the term n in the Fibonacci series (the sum of a and b).

public Fibonacci(int a, int b): sets a and b to their given values, and sets
                                sum to their sum.

public static void main(String[] args):
    1. If args.length == 0, prints an error and exits.
    2. Otherwise, assumes the first argument is an integer in string form,
       tries to use Integer.parseInt() to parse it out, and places it in a
       variable named n.
    3. Calculates the Fibonacci series from 2 to (n+1).
    4. Prints out the values of the Fibonacci series from 2 to (n+1). 

b) (Counter1)

private int counter: the counter of the class; stores the counter's value.

public void increment(): increases counter by 1. A mutator method.

public int getCount(): returns the value of the counter. An accessor method.

c) (Counter2)

private int counter: the counter of the class; stores the counter's value.

private int increment: the amount increment() increments counter each time
                       it is used.

public Counter2(int increment): sets counter to 0 and increment to the given
                                value.

public void increment(): increases counter by the increment value. A mutator
                         method.

public int getCount(): returns the value of the counter. An accessor method.

d) (Positive)

private int v: the value of the positive integer.

public Positive(int number): sets the v, the value of the integer, to the given
                             number.

public boolean divides(int number): returns true if v divides the given number.
                                    Otherwise, returns false.

public boolean multipleOf(int number): returns true if the given number divides
                                       v. Otherwise, returns false.

public int gcd(Positive number): returns the greatest common divisor of v and
                                 the value of the given Positive.
                                 
public boolean isPrime(): returns true if v is prime. Otherwise, returns false.
                          See proof.

public Positive factor(): returns the smallest non-1 factor. If 1 is the only
                          factor (i.e. v == 1), returns 1. See proof.

public String toString(): returns the String value of v.

Proof:
    Given:
        1) v is an integer.
        2) v is not divisible by any of the numbers from 2 to v/2 inclusive.
    Prove:
        3) v is prime.
    Proof:
        4) By 2), to determine if v is prime, we must only test the numbers
           from v/2 to v.
        5) Define integer n = any number from v/2 to v exclusive.(v/2<n && n<v)
        6) n > v/2
        7) v = 2 * (v/2)
        8) v < 2 * n
        9) v/n < 2
        10) integer v is divisible by integer n if and only if n * n' = v and
            n' is an integer.
        11) By 5), n' > 1.
        12) By 9), n' < 2.
        13) By 11) and 12), n' is not an integer.
        14) By 13) and 10), v is not divisible by n.
        15) By 4) and 14), 3) is true.