/* Name:
 * UNI:
 * CS 1004, Summer 2014
 * Programming Assignment 2d
 */

/* Instructions
 * Design a choose your own adventure
 * You should print some bit of story, then ask the user
 * what they want to do.
 * When you make your project in Eclipse, you should do the following:
 *   1) copy this file into the src directory
 *   2) copy the Choice.java file into the src directory
 * When you want to ask the user to choose between different adventure
 * options, you can use
 *   int userChoice = Choice.choose(
 *       "Choice1",
 *       "Choice2",
 *       "SomeOtherChoice"
 *   );
 * and userChoice will be 0 if the user chose Choice1, 1 if they chose
 * Choice2, and 2 if they chose SomeOtherChoice. Note that you can have
 * 1 option (just Choice1), two options, 20 options, etc. It works with
 * a variable number of values.
 *
 * There should be at least 12 different `pages' you can be
 * on and several different paths towards (possibly more than
 * one) ending. Note that you will receive bonus points based
 * on how fun / entertaining it is.
 * Each `page' should be its own action, by the way.
 */

public class Assignment2d {
  public static void main(String[] args) {
    start();
  }

  public static void start() {
    System.out.println("Welcome to the CS1004 CYOA game. Please choose among the following options:");

    int result = Choice.choose(
      "Upload a homework",
      "Hand in a quiz",
      "Ask a question on Piazza",
      "Start the next assignment."
    );

    switch (result) {
      case 0:
        homework();
        break;
      case 1:
        quiz();
        break;
      case 2:
        piazzaPost();
        break;
      case 3:
        nextHmwk();
        break;
    }
  }

  public static void homework() {
    System.out.println("Courseworks is down for maintenace. You cannot submit your homework in this manner.");
    System.out.println();
    System.out.println("What would you like to do?");

    int result = Choice.choose(
      "Email your homework to Matthew.",
      "Post your solutions on Piazza privately to Matthew.",
      "Post your solutions on Piazza privately to the Staff.",
      "Post your solutions publically on Piazza.",
      "Wait this courseworks thing out--you got other stuff to do."
    );

    switch (result) {
      case 0:
        emailHmwk();
        break;
      case 1:
        piazza();
        break;
      case 2:
        piazza();
        break;
      case 3:
        piazzaAll();
        break;
      case 4:
        start();
        break;
    }
  }

  public static void piazzaAll() {
    System.out.println(
      "Oops, you ruined the homework for everyone." + "\n" +
      "The class has been warned about posting things on Piazza, and now you posted the entire homework." + "\n" +
      "You should go visit the registrar to see how much of a refund you can still get on the course."
    );
  }

  public static void piazza() {
    System.out.println("Matthew tells you that you should email him your work, not post it here.");

    int result = Choice.choose("Email the homework", "Wait this courseworks thing out, that's what you wanted to do earlier anyway.");
    switch (result) {
      case 0:
        emailHmwk();
        break;
      case 1:
        start();
        break;
    }
  }

  public static void quiz() {
    System.out.println("Quizzes are due in class. Take some time to make sure your handwriting is nice and easy to read. Make this quiz a work of art!");

    int result = Choice.choose("Make it art, then decide what to do?", "Just wait and hand it in later.");
    switch (result) {
      case 0:
        System.out.println("Art created!");
        start();
      case 1:
        start();
    }
  }

  public static void piazzaPost() {
    System.out.println("You post a question about the next assignment and notice that it gets answered very quickly. What do you want to do?");

    int result = Choice.choose("Ask another question!", "Start the next homework assignment.");
    switch (result) {
      case 0:
        piazzaPost();
        break;
      case 1:
        nextHmwk();
        break;
    }
  }

  public static void nextHmwk() {
    System.out.println("You're being a real go-getter with this. Staying ahead of the game is a good idea! You win!");
  }

  public static void emailHmwk() {
    System.out.println("You email the homework, explaining that courseworks is down. You don't hear back within an hour and the deadline is in 45 minutes! WHAT DO YOU DO!?");

    int result = Choice.choose(
      "Trust that Matt is reasonable and if the homework doesn't make it, he'll ask you to send it again.",
      "Freak out. You're in college, you're supposed to freak out every now and then.",
      "Relax. Game of Thrones is coming to an end, you got that kind of stuff to keep up with, too."
    );

    switch (result) {
      case 0:
        trust();
        break;
      case 1:
        freakOut();
        break;
      case 2:
        relax();
        break;
    }
  }

  public static void trust() {
    System.out.println("You were right to trust Matt on this one. He was out having dinner cause he consumes energy like any other living being. All is well, and now you can relax with Game of Thrones");
    relax();
  }

  public static void freakOut() {
    System.out.println("You freak the heck out. Your roommates call campus security, and you're taken away to a place where you can relax and collect yourself. There's a nice TV, you wonder if Game of Thrones is on...");
    relax();
  }

  public static void relax() {
    System.out.println("FINALLY you get to really enjoy life and watch some GoT. Stupid Java, always in the way of what really matters...");
  }
}
