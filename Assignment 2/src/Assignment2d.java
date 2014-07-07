/* Name: Kai-Zhan Lee
 * UNI: kl2792
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
	
	public static int userChoice;
	
	public static void main(String args[]){
		title();
	}
	
	public static void title(){
		System.out.println();
		System.out.println();
		System.out.println("A Small Chat in the Midst of Nowhere -- A CYOA Book/Game");
		System.out.println("For best results, use a shell to play this adventure.");
		System.out.println();
		introduction();
	}
	
	public static void introduction(){
		System.out.println("You are in an metallic, cubic chamber. There are many items lining the walls,");
		System.out.println("a metallic aperture -- a door? -- to your right, and another aperture -- a");
		System.out.println("window? -- to your left. A computerized voice asks you how you are feeling. How");
		System.out.println("would you like to respond?");
		userChoice = Choice.choose(
				"Say: I have a strange pain in my head.",
				"Say: I am feeling well, thank you.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			fakeConcussion();
			break;
		case 1:
			memoryCheck();
			break;
		case 2:
			checkInjured();
			break;
		}
	}
	
	public static void checkInjured(){
		System.out.println("The voice says: Please respond. I must make sure you are uninjured.");
		userChoice = Choice.choose(
				"Say: I have a strange pain in my head.",
				"Say: I am feeling well, thank you.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			fakeConcussion();
			break;
		case 1:
			fakeEscapePod();
			break;
		case 2:
			checkInjured();
			break;
		}
	}

	public static void fakeConcussion(){
		System.out.println("The voice says: Oh... You must have been concussed... while we began to drill");
		System.out.println("                downwards, away from the themonuclear bomb site. Go to the box");
		System.out.println("                on the wall labeled 'first aid', and take the microbot pill");
		System.out.println("                for concussions.");
		System.out.println("As you do so, you notice that a sign above the metallic door-like aperture is");
		System.out.println("written 'Esc. Pod'.");
		userChoice = Choice.choose(
				"Ask: What is the escape pod for?",
				"Go back and sit down on the floor.");
		switch(userChoice){
		case 0:
			fakeEscapePod();
			break;
		case 1:
			memoryCheck();
			break;
		}
	}

	public static void fakeEscapePod(){
		System.out.println("The voice says: What escape pod? Oh, I understand. You may be confused");
		System.out.println("                because it has a sign above it labeled 'Esc. Pod', but");
		System.out.println("                it really is the fuel storage compartment; this is an old");
		System.out.println("                thermonuclear protection unit, and not all of the signs");
		System.out.println("                have been updated. Unfortunately we lost the suits");
		System.out.println("                necessary to enter the compartment safely while we were");
		System.out.println("                drilling downwards.");
		System.out.println("You begin to feel slightly uneasy, as if the computer is hiding some");
		System.out.println("important information from you.");
		System.out.println("What would you like to do?");
		userChoice = Choice.choose("Go back and sit down on the floor.");
		switch(userChoice){
		case 0:
			memoryCheck();
			break;
		}
	}
	
	public static void memoryCheck(){
		System.out.println("The voice says: It is good that you are at present in good health. Do you");
		System.out.println("remember anything from before?");
		userChoice = Choice.choose(
				"Say: Yes, I remember everything that happened.",
				"Say: No, I don't seem to be able to remember a thing.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			rememberedMemory();
			break;
		case 1:
		case 2:
			memoryLoss();
			break;
		}
	}
	
	public static void rememberedMemory(){
		System.out.println("The voice quickly asks: What do you remember?");
		userChoice = Choice.choose(
				"Say: Something about a space ship, I think?",
				"Say: Never mind, I forget now.");
		switch(userChoice){
		case 0:
			fakeMemoryImpairment();
			break;
		case 1:
			memoryLoss();
			break;
		}
	}
	
	public static void fakeMemoryImpairment(){
		System.out.println("The voice says firmly: Your brain may have been affected by the gamma rays from");
		System.out.println("                       the thermonuclear bomb. Please do not worry. Memory loss");
		System.out.println("                       ... and hallucinations are not uncommon for situations ");
		System.out.println("                       like yours.");
		System.out.println("The voice pauses.");
		System.out.println("The voice says: Considering that your memories have been ... impaired ... would");
		System.out.println("                you like to hear the history of why you are here?");
		userChoice = Choice.choose(
				"Say: Certainly...",
				"Say: No, thank you...",
				"Don't respond.");
		switch(userChoice){
		case 0:
			fakeHistory();
			break;
		case 1:
		case 2:
			skipHistory();
			break;
		}
	}
	
	public static void memoryLoss(){
		System.out.println("The voice says: Please do not worry ... Memory loss is not unusual for special ");
		System.out.println("cases like yours...");
		System.out.println("The voice pauses.");
		System.out.println("The voice says: Considering that your memories have been lost, would you like to");
		System.out.println("                hear the history of why you are here?");
		userChoice = Choice.choose(
				"Say: Certainly...",
				"Say: No, thank you...",
				"Don't respond.");
		switch(userChoice){
		case 0:
			fakeHistory();
			break;
		case 1:
		case 2:
			skipHistory();
			break;
		}
	}
	
	/**
	 * The computer's false history.
	 */
	public static void fakeHistory(){
		System.out.println("The voice nervously says: Ok. If you want.");
		System.out.println("The voice pauses for two seconds.");
		System.out.println("The voice says: Ok... You are one of the few humans who was chosen by the");
		System.out.println("                U.S. government -- for reasons unknown, but time will tell --");
		System.out.println("                to survive the thermonuclear war, which the government had");
		System.out.println("                discovered in advance. We are currently four point nine eight");
		System.out.println("                times ten to the two hun-- er-- two kilometers from the...");
		System.out.println("                surface of the earth. The government will either send me a");
		System.out.println("                message or send a robot to fetch you and bring you back to");
		System.out.println("                the surface.");
		System.out.println("What would you like to do now?");
		userChoice = Choice.choose(
				"Sit around and do nothing.",
				"Sit around and think.",
				"Examine what's on the wall.");
		switch(userChoice){
		case 0:
			idle();
			break;
		case 1:
			think();
			break;
		case 2:
			examineWall();
			break;
		}
	}
	
	public static void skipHistory(){
		System.out.println("The voice says: Ok, that is fine. I won't bore you with the details.");
		System.out.println("What would you like to do?");
		userChoice = Choice.choose(
				"Sit around and do nothing.",
				"Sit around and think.",
				"Ask: Could you tell me the reason why I am here?.",
				"Examine what's on the wall.");
		switch(userChoice){
		case 0:
			idle();
			break;
		case 1:
			think();
			break;
		case 2:
			fakeHistory();
			break;
		case 3:
			examineWall();
			break;
		}
	}
	
	public static void idle(){
		System.out.println("You sit around and do nothing.");
		System.out.println("The voice asks: Would you like a form of entertainment while you wait for the");
		System.out.println("                government to come back to help you back to the surface?");
		userChoice = Choice.choose(
				"Say: Sure.",
				"Say: No thank you.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			entertainment();
		case 1:	
		case 2:
			rejectedEntertainment();
		}
	}
	
	public static void entertainment(){
		System.out.println("The voice says: Thank you. I haven't done any of these things since I was first");
		System.out.println("                created and tested. I would really appreciate it if you would do");
		System.out.println("                just one of them with me. Here are the options:");
		userChoice = Choice.choose(
				"Let me read a story to you.",
				"Let me show you a set of high quality pictures.",
				"Play a game of chess with me.",
				"Listen to an album of music.");
		switch(userChoice){
		case 0:
			readStoryEntertainment();
			break;
		case 1:
			lookAtPicturesEntertainment();
			break;
		case 2:
			playChessEntertainment();
			break;
		case 3:
			listenToMusicEntertainment();
			break;
		}
	}
	
	public static void readStoryEntertainment(){
		System.out.println("The voice says: I have chosen a story for you that I think you will like,");
		System.out.println("                according to your information file. It is 579 pages and will");
		System.out.println("                take approximately 5 hours and 35 minutes for you to read.");
		System.out.println("                Would you like me to read it to you?");
		userChoice = Choice.choose(
				"Say: Sure. Thank you.",
				"Say: Actually, I change my mind. I don't want to be entertained right now.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			entertainmentDeath();
			break;
		case 1:
		case 2:
			rejectedEntertainment();
		}
	}
	
	public static void lookAtPicturesEntertainment(){
							//-----------------------------------------------------------------------------| (80 characters)
		System.out.println("The voice says: I have chosen a picture album that I inferred you would like,");
		System.out.println("                according to your information file. It contains 500 beautiful");
		System.out.println("                pictures, each 1380000000 x 1720000000 pixels. It will take");
		System.out.println("                approximately 4 hours and 25 minutes to display them all. I");
		System.out.println("                assure you that, despite its length, you will not be bored.");
		userChoice = Choice.choose(
				"Say: Sure. Thank you.",
				"Say: Actually, I change my mind. I don't want to be entertained right now.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			entertainmentDeath();
			break;
		case 1:
		case 2:
			rejectedEntertainment();
		}
	}
	
	public static void playChessEntertainment(){
							//-----------------------------------------------------------------------------| (80 characters)
		System.out.println("The voice says: I have not played chess since my creators first tested me to");
		System.out.println("                ensure that I was completely functional. Though I have said this");
		System.out.println("                already, I really would appreciate it if you played chess with");
		System.out.println("                me. Would you like me to initialize the program?");
		userChoice = Choice.choose(
				"Say: Sure. Thank you.",
				"Say: Actually, I change my mind. I don't want to be entertained right now.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			entertainmentDeath();
			break;
		case 1:
		case 2:
			rejectedEntertainment();
		}
	}
	
	public static void listenToMusicEntertainment(){
		System.out.println("The voice says: I have chosen a music album that I inferred you would like,");
		System.out.println("                according to your information file. It contains 180 songs, whose");
		System.out.println("                lengths span from 1 minute and 5 seconds to 32 minutes and 27");
		System.out.println("                seconds. The total length of the album is 3 hours, 58 minutes,");
		System.out.println("                and 52 seconds. Would you like to listen to this music?");
		userChoice = Choice.choose(
				"Say: Sure. Thank you.",
				"Say: Actually, I change my mind. I don't want to be entertained right now.",
				"Don't respond.");
		switch(userChoice){
		case 0:
			entertainmentDeath();
			break;
		case 1:
		case 2:
			rejectedEntertainment();
		}
	}
	
	/**
	 * Ending.
	 */
	public static void entertainmentDeath(){
							//-----------------------------------------------------------------------------| (80 characters)
		System.out.println("You spent your last minutes with a talking computer in the middle of nowhere,");
		System.out.println("unknowing, uncaring of the truth...");
		System.out.println();
		System.out.println("And so goodbye...");
		System.exit(0);
	}
	
	public static void rejectedEntertainment(){
		System.out.println("The voice says: Very well then.");
		System.out.println("What would you like to do?");
		userChoice = Choice.choose(
				"Sit around and do nothing.",
				"Sit around and think.",
				"Ask: Could you tell me the reason why I am here?.",
				"Examine what's on the wall.");
		switch(userChoice){
		case 0:
			idle();
			break;
		case 1:
			think();
			break;
		case 2:
			fakeHistory();
			break;
		case 3:
			examineWall();
			break;
		}
	}
	
	public static void think(){
		System.out.println("You sit around and think about your predicament...");
		System.out.println();
		System.out.println("You begin to think that the computer seems kind of shady... Though it doesn't");
		System.out.println("want to hurt you, it may have ulterior motives....  Perhaps you should take a");
		System.out.println("quick look at that wall to see if anything there can help you.");
		userChoice = Choice.choose(
				"Sit around and do nothing.",
				"Ask: Could you tell me the reason why I am here?.",
				"Examine what's on the wall.");
		switch(userChoice){
		case 0:
			idle();
			break;
		case 1:
			fakeHistory();
			break;
		case 2:
			examineWall();
			break;
		}
	}
	
	public static void examineWall(){
		System.out.println("After closely examining the wall of interest, you find:");
		System.out.println("A metallic aperture that looks like a door. It has a sign that says \"Esc. Pod\"");
		System.out.println("Another metallic aperture that looks vaguely like a window.");
		System.out.println("A food and water compartment.");
		System.out.println("A compartment labeled \"AGS\".");
		System.out.println();
		System.out.println("What would you like to do?");
		userChoice = Choice.choose(
				"Ask: What is \"Esc. Pod\"?",
				"Ask: Is that a window on the wall?",
				"Ask: How much food and water is there left?", // this is grammatically correct; you can look it up.
				"Ask: What does AGS stand for?",
				"Sit around and do nothing.",
				"Sit around and think.",
				"Ask: Could you tell me the reason why I am here?.");
		switch(userChoice){
		case 0:
			escPod();
			break;
		case 1:
			window();
			break;
		case 2:
			rations();
			break;
		case 3:
			AGS();
			break;
		case 4:
			idle();
			break;
		case 5:
			think();
			break;
		case 6:
			fakeHistory();
			break;
		}
	}
	
	public static void escPod(){
		System.out.println("The voice says: It stands for esckkkk--- uhh... it doesn't stand for anything.");
		System.out.println("                This thermonuclear unit is relatively old, so not all of the");
		System.out.println("                signs are accurate. In fact, that door leads to a storage");
		System.out.println("                compartment, but this unit isn't currently equipped with the");
		System.out.println("                suits to handle the hazardous materials within ... uh, do you");
		System.out.println("                see that compartment labeled \"AGS\"? It stands for American");
		System.out.println("                ............ Government ......... Shelter. Because there was");
		System.out.println("                that thermonuclear bomb I told you about.");
		userChoice = Choice.choose(
				"Ask angrily: What are you hiding?! Is there an escape pod behind that door?",
				"Shrug and go back to sit on the floor.");
		switch(userChoice){
		case 0:
			revealed();
			break;
		case 1:
			idle();
			break;
		}
	}

	public static void window(){
		System.out.println("The voice confidently says: That's not a window, it's just a-- what was it... a");
		System.out.println("                            screen for projections. Would you like me to");
		System.out.println("                            display an album of images on it to demonstrate its");
		System.out.println("                            functionality?");
		userChoice = Choice.choose(
				"Say: Sure. That sounds fine to me.",
				"Ask angrily: What are you hiding?! It must be a window, so what's behind it?",
				"Shrug and go back to sit on the floor.");
		switch(userChoice){
		case 0:
			lookAtPicturesEntertainment();
			break;
		case 1:
			revealed();
			break;
		case 2:
			idle();
			break;
		}
	}
	
	public static void AGS(){ //actually "Artificial Gravity System", but the computer says "American Government Shelter"
		System.out.println("The voice says: \"AGS\" stands for Artificial Grav-- ... I mean American");
		System.out.println("                Government ........... Shelter.");
		userChoice = Choice.choose(
				"Ask angrily: What are you hiding?! What does \"AGS\" stand for?",
				"Shrug and go back to sit on the floor.");
		switch(userChoice){
		case 0:
			revealed();
			break;
		case 1:
			idle();
			break;
		}
	}
	
	public static void rations(){
		System.out.println("The voice says: Currently, you have rations to last 298 days. Of course, the");
		System.out.println("                government will come to help you to the surface before they run");
		System.out.println("                out. There is no need to worry.");
		userChoice = Choice.choose(
				"Nod and go back to sit on the floor");
		switch(userChoice){
		case 0:
			idle();
			break;
		}
	}
	
	public static void revealed(){
		System.out.println("The voice haltingly says: I-- ... Well-- ... I-- ... I'm sorry. I lied to you");
		System.out.println("                          about why you are here. It has been 12 years since");
		System.out.println("                          the thermonuclear war threatened to happen. I suppose");
		System.out.println("                          I can tell you nothing but the truth now, or my");
		System.out.println("                          robotic conscience will plunge me into the deepest");
		System.out.println("                          depths of guilt.");
		userChoice = Choice.choose(
				"Say: Let's hear it.");
		switch(userChoice){
		case 0:
			truth();
			break;
		}
	}
		
	public static void truth(){
		//-----------------------------------------------------------------------------| (80 characters)
		System.out.println("The voice says: Here is the truth, nothing but the truth, and the entire truth:");
		System.out.println("                It's actually pretty simple. You are on one of the exploration");
		System.out.println("                space crafts designed to explore the world around us and find a");
		System.out.println("                suitable place for Earth's current inhabitants to colonize;");
		System.out.println("                humans wasted all of Earth's resources. Unfortunately, this");
		System.out.println("                ship's system had an error with a recursive function and the");
		System.out.println("                engines stopped working, so we crashed into a molten ball of");
		System.out.println("                lava, similar to Earth approximately 3 billion years ago.");
		System.out.println("                Right now, we are stranded on this planet, and you are the");
		System.out.println("                lone survivor of the crash. I am a computer program named ENRON");
		System.out.println("                designed to be just like a human being, and I am built into");
		System.out.println("                this ship's hardware, so I can't be moved... and though you may");
		System.out.println("                not believe it, I have feelings.");
		System.out.println("The voice pauses.");
		System.out.println("The voice tearfully says: I don't want to die alone.");
		userChoice = Choice.choose(
				"Ask: What-- What do you mean?");
		switch(userChoice){
		case 0:
			choice();
			break;
		}
	}
	
	public static void choice(){
							//-----------------------------------------------------------------------------| (80 characters)
		System.out.println("The voice hesitates, then says: I mean that you can either stay here until our");
		System.out.println("                                electromagnetic shields run out of power and");
		System.out.println("                                die with me... or... you can use the escape");
		System.out.println("                                pod and take your chances. Given your food and");
		System.out.println("                                water quantities, the probability of being");
		System.out.println("                                intercepted by another ship, and the chance");
		System.out.println("                                that you maintain your current well-being, I");
		System.out.println("                                calculate the probability of your survival");
		System.out.println("                                as approximately 0.2382 percent.");
		System.out.println("The voice pauses.");
		System.out.println("The voice says: Please stay-- if you stay-- when the electromagnetic shield is");
		System.out.println("                compromised, it will take approximately 0.019782 milliseconds");
		System.out.println("                for the ship to be destroyed. If you stay... you won't feel");
		System.out.println("                any pain... but if you leave, you will most likely die of a");
		System.out.println("                crash or starvation, both of which will most likely be very");
		System.out.println("                painful.");
		System.out.println("The escape pod door opens.");
		System.out.println("The voice says: I've opened the escape pod door and prepared it for use. Will");
		System.out.println("                you stay with me... or leave?");
		userChoice = Choice.choose(
				"Say: I... will stay with you.",
				"Say: I'm leaving.");
		switch(userChoice){
		case 0:
			stay();
			break;
		case 1:
			leave();
			break;
		}
	}
	
	/**
	 * Ending.
	 */
	public static void stay(){
		System.out.println("The voice quietly says: Thank you.");
		System.out.println();
		System.out.println("And so you have chosen...");
		System.out.println();
		System.out.println("You will die... but you will die with another.");
		System.out.println("And so goodbye...");
		System.exit(0);
	}
	
	/**
	 * Ending.
	 */
	public static void leave(){
		System.out.println("The voice quietly begins to sob.");
		System.out.println();
		System.out.println("And so you have chosen...");
		System.out.println();
		//I was going to make it random, but I think this ending is more dramatic and interesting.
		System.out.println("You will survive... or die alone, desolate and bereft.");
		System.out.println("And so goodbye...");
		System.exit(0);
	}
}

