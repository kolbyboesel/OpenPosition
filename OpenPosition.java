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

/**
 * A application handler of an open position using priority queue. Only saves a
 * new Application when the queue is not full, or when it can replace older,
 * lower-scored ones with its higher scores.
 */
public class OpenPosition {
	private String positionName;
	private ApplicationQueue applications; // the priority queue of all applications
	private int capacity; // the number of vacancies

	/**
	 * Creates a new open position with the given capacity
	 * 
	 * @param capacity the number of vacancies of this position
	 * @throws IllegalArgumentException with a descriptive error message if the
	 *                                  capacity is not a positive integer
	 */
	public OpenPosition(String positionName, int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Error: Capacity is not a positive integer");
		}

		this.positionName = positionName;
		this.capacity = capacity;
		applications = new ApplicationQueue(capacity);

	}

	public String getPositionName() {
		return this.positionName;
	}

	/**
	 * Tries to add the given Application to the priority queue of this position.
	 * return False when the new Application has a lower score than the
	 * lowest-scored Application in the queue.
	 * 
	 * @return Whether the given Application was added successfully
	 */
	public boolean add(Application application) {
		if (applications.size() >= capacity) {
			if (applications.peek().getScore() < application.getScore()) {
				applications.dequeue();
				applications.enqueue(application);
				return true;
			} else {
				return false;
			}
		} else {
			applications.enqueue(application);
			return true;
		}

	}

	/**
	 * Returns the list of Applications in the priority queue.
	 * 
	 * @return The list of Applications in the priority queue, in increasing order
	 *         of the scores.
	 */
	public String getApplications() {
		return applications.toString();
	}

	/**
	 * Returns the total score of Applications in the priority queue.
	 * 
	 * @return The total score of Applications in the priority queue.
	 */
	public int getTotalScore() {
		ApplicationIterator scoreCounter = new ApplicationIterator(applications);
		int totalScore = 0;
		for (int i = 0; i < applications.size(); i++) {
			totalScore += scoreCounter.next().getScore();
		}
		return totalScore;

	}

}