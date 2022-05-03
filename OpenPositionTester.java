////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    P10 Open Position
//Course:   CS 300 Spring 2022
//
//Author:   Kolby Boesel
//Email:    kboesel@wisc.edu
//Lecturer: Mouna Kacem
//

//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:   None
//Online Sources:  Piazza, zyBooks
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of
 * Application, ApplicationIterator, ApplicationQueue and OpenPosition classes
 * in the assignment.
 *
 */
public class OpenPositionTester {

	/**
	 * This method tests and makes use of the Application constructor, getter
	 * methods, toString() and compareTo() methods.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testApplication() {

		Boolean check1 = false;
		Boolean check2 = false;
		Boolean check3 = false;

		Application tempApp = new Application("Kolby", "kolbyzboesel@gmail.com", 95);
		Application tempAppLower = new Application("Kolby", "kolbyzboesel@gmail.com", 90);
		Application tempAppHigher = new Application("Kolby", "kolbyzboesel@gmail.com", 99);

		try {
			Application tempNoName = new Application(null, "kolbyzboesel@gmail.com", 99);

			return false;
		} catch (IllegalArgumentException e) {
		}

		try {
			Application tempNoEmail = new Application("Kolby", null, 99);

			return false;
		} catch (IllegalArgumentException e) {
		}

		try {
			Application tempNoAt = new Application("Kolby", "kolbyzboeselgmail.com", 99);

			return false;
		} catch (IllegalArgumentException e) {
		}

		try {
			Application tempAtOverload = new Application("Kolby", "kolbyzboesel@@@gmail.com", 99);

			return false;
		} catch (IllegalArgumentException e) {

		}

		try {
			Application tempInvalidScoreLow = new Application("Kolby", "kolbyzboesel@gmail.com", -1);

			return false;
		} catch (IllegalArgumentException e) {

		}

		try {
			Application tempInvalidScoreHigh = new Application("Kolby", "kolbyzboesel@gmail.com", 101);

			return false;
		} catch (IllegalArgumentException e) {

		}

		try {
			Application tempValidScoreLow = new Application("Kolby", "kolbyzboesel@gmail.com", 0);
		} catch (Exception e) {
			return false;
		}

		try {
			Application tempValidScoreHigh = new Application("Kolby", "kolbyzboesel@gmail.com", 100);
		} catch (Exception e) {
			return false;
		}

		if (tempApp.getName().trim().equals("Kolby") && tempApp.getEmail().trim().equals("kolbyzboesel@gmail.com")
				&& tempApp.getScore() == 95) {
			check1 = true;
		}

		if (tempApp.compareTo(tempAppLower) > 0 && tempApp.compareTo(tempAppHigher) < 0) {
			check2 = true;
		}

		if (tempApp.toString().trim().equals("Kolby:kolbyzboesel@gmail.com:95")) {
			check3 = true;
		}

		if (check1 == true && check2 == true && check3 == true) {
			return true;
		}

		return false;

	}

	/**
	 * This method tests and makes use of the ApplicationIterator class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testApplicationIterator() {

		Application test1 = new Application("Kolby", "kolbyzboesel@gmail.com", 94);
		Application test2 = new Application("Kolby", "kolbyzboesel@gmail.com", 91);
		Application test3 = new Application("Kolby", "kolbyzboesel@gmail.com", 99);
		Application test4 = new Application("Kolby", "kolbyzboesel@gmail.com", 34);
		Application test5 = new Application("Kolby", "kolbyzboesel@gmail.com", 31);
		Application test6 = new Application("Kolby", "kolbyzboesel@gmail.com", 64);

		ApplicationQueue testQueue = new ApplicationQueue(6);

		testQueue.enqueue(test1);
		testQueue.enqueue(test2);
		testQueue.enqueue(test3);
		testQueue.enqueue(test4);
		testQueue.enqueue(test5);
		testQueue.enqueue(test6);

		ApplicationIterator testIterator = new ApplicationIterator(testQueue);

		if (testIterator.next() == test5 && testIterator.next() == test4 && testIterator.next() == test6
				&& testIterator.next() == test2 && testIterator.next() == test1 && testIterator.next() == test3) {
			return true;
		}

		return false;
	}

	/**
	 * This method tests and makes use of the enqueue() and dequeue() methods in the
	 * ApplicationQueue class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testEnqueueDequeue() {
		try {
			Boolean check1 = false;
			Boolean check2 = false;
			Boolean check3 = false;
			Boolean check4 = false;
			Boolean check5 = false;

			Application test1 = new Application("Kolby", "kolbyzboesel@gmail.com", 94);
			Application test2 = new Application("Kolby", "kolbyzboesel@gmail.com", 91);
			Application test3 = new Application("Kolby", "kolbyzboesel@gmail.com", 99);
			Application test4 = new Application("Kolby", "kolbyzboesel@gmail.com", 54);

			ApplicationQueue testQueue = new ApplicationQueue(3);

			try {
				Application test5 = null;
				testQueue.enqueue(test5);
				return false;
			} catch (NullPointerException e) {
				check1 = true;
			}

			testQueue.enqueue(test1);
			testQueue.enqueue(test2);
			testQueue.enqueue(test3);

			try {
				testQueue.enqueue(test4);
				return false;
			} catch (IllegalStateException e) {
				check2 = true;
			}

			if (testQueue.dequeue() == test2) {
				check3 = true;
			}

			if (testQueue.dequeue() == test1 && testQueue.dequeue() == test3) {
				check4 = true;
			}
			try {
				testQueue.dequeue();
			} catch (NoSuchElementException e) {
				check5 = true;
			}

			if (check1 == true && check2 == true && check3 == true && check4 == true && check5 == true) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * This method tests and makes use of the common methods (isEmpty(), size(),
	 * peek()) in the ApplicationQueue class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */
	public static boolean testCommonMethods() {
		Boolean check1 = false;
		Boolean check2 = false;
		Boolean check3 = false;
		Boolean check4 = false;
		Boolean check5 = false;

		try {
			ApplicationQueue testQueue = new ApplicationQueue(0);

		} catch (IllegalArgumentException e) {
			check1 = true;
		}

		Application test1 = new Application("Kolby", "kolbyzboesel@gmail.com", 94);
		Application test2 = new Application("Kolby", "kolbyzboesel@gmail.com", 91);
		Application test3 = new Application("Kolby", "kolbyzboesel@gmail.com", 99);
		ApplicationQueue testQueue = new ApplicationQueue(3);

		if (testQueue.isEmpty() == true && testQueue.size() == 0) {
			check2 = true;
		}

		try {
			testQueue.peek();
		} catch (NoSuchElementException e) {
			check3 = true;
		}

		testQueue.enqueue(test1);

		if (testQueue.isEmpty() == false && testQueue.size() == 1 && testQueue.peek() == test1) {
			check4 = true;
		}

		testQueue.enqueue(test2);
		testQueue.enqueue(test3);

		if (testQueue.isEmpty() == false && testQueue.size() == 3 && testQueue.peek() == test2) {
			check5 = true;
		}

		if (check1 == true && check2 == true && check3 == true && check4 == true && check5 == true) {
			return true;
		}

		return false;
	}

	/**
	 * This method tests and makes use of OpenPosition class.
	 * 
	 * @return true when this test verifies the functionality, and false otherwise
	 */

	public static boolean testOpenPosition() {
		Boolean check1 = false;
		Boolean check2 = false;
		Boolean check3 = false;
		Boolean check4 = false;
		Boolean check5 = false;

		try {
			OpenPosition temp = new OpenPosition("test", 0);
			return false;
		} catch (IllegalArgumentException e) {
		}

		Application test1 = new Application("Kolby", "kolbyzboesel@gmail.com", 97);
		Application test2 = new Application("Kolby", "kolbyzboesel@gmail.com", 91);
		Application test3 = new Application("Kolby", "kolbyzboesel@gmail.com", 94);
		Application test4 = new Application("Kolby", "kolbyzboesel@gmail.com", 54);
		Application test5 = new Application("Kolby", "kolbyzboesel@gmail.com", 99);
		OpenPosition tester = new OpenPosition("test", 3);
		if (tester.add(test1) == true && tester.add(test2) == true && tester.add(test3) == true) {
			check1 = true;
		}

		if (tester
				.getApplications().trim().equals("Kolby:kolbyzboesel@gmail.com:91" + "\n"
						+ "Kolby:kolbyzboesel@gmail.com:94" + "\n" + "Kolby:kolbyzboesel@gmail.com:97")
				&& tester.getTotalScore() == 282) {
			check2 = true;
		}

		if (tester.add(test4) == false) {
			check3 = true;
		}

		if (tester.add(test5) == true) {
			check4 = true;
		}

		if (tester
				.getApplications().trim().equals("Kolby:kolbyzboesel@gmail.com:94" + "\n"
						+ "Kolby:kolbyzboesel@gmail.com:97" + "\n" + "Kolby:kolbyzboesel@gmail.com:99")
				&& tester.getTotalScore() == 290) {
			check5 = true;
		}

		if (check1 == true && check2 == true && check3 == true && check4 == true && check5 == true) {
			return true;
		}
		return false;
	}

	/**
	 * This method calls all the test methods defined and implemented in your
	 * OpenPositionTester class.
	 * 
	 * @return true if all the test methods defined in this class pass, and false
	 *         otherwise.
	 */
	public static boolean runAllTests() {
		return testApplication() && testApplicationIterator() && testEnqueueDequeue() && testCommonMethods()
				&& testOpenPosition();
	}

	/**
	 * Driver method defined in this OpenPositionTester class
	 * 
	 * @param args input arguments if any.
	 */
	public static void main(String[] args) {
		System.out.println("testApplication() : " + testApplication());
		System.out.println("testApplicationIterator() : " + testApplicationIterator());
		System.out.println("testEnqueueDequeue() : " + testEnqueueDequeue());
		System.out.println("testCommonMethods() : " + testCommonMethods());
		System.out.println("testOpenPosition() : " + testOpenPosition());

		System.out.println("runAllTests() : " + runAllTests());
	}
}