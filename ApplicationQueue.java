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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Applications.
 * Guarantees the min-heap invariant, so that the Application at the root should
 * have the lowest score, and children always have a higher or equal score as
 * their parent. The root of a non-empty queue is always at index 0 of this
 * array-heap.
 */
public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {
	private Application[] queue; // array min-heap of applications representing this priority queue
	private int size; // size of this priority queue

	/**
	 * Creates a new empty ApplicationQueue with the given capacity
	 * 
	 * @param capacity Capacity of this ApplicationQueue
	 * @throws IllegalArgumentException with a descriptive error message if the
	 *                                  capacity is not a positive integer
	 */
	public ApplicationQueue(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Error: Capacity is not a positive integer");
		}

		size = 0;
		queue = new Application[capacity];

	}

	/**
	 * Checks whether this ApplicationQueue is empty
	 * 
	 * @return {@code true} if this ApplicationQueue is empty
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the size of this ApplicationQueue
	 * 
	 * @return the size of this ApplicationQueue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds the given Application to this ApplicationQueue and use the percolateUp()
	 * method to maintain min-heap invariant of ApplicationQueue. Application should
	 * be compared using the Application.compareTo() method.
	 * 
	 * 
	 * @param o Application to add to this ApplicationQueue
	 * @throws NullPointerException  if the given Application is null
	 * @throws IllegalStateException with a descriptive error message if this
	 *                               ApplicationQueue is full
	 */
	@Override
	public void enqueue(Application o) {
		if (o == null) {
			throw new NullPointerException();
		}

		if (queue.length <= size) {
			throw new IllegalStateException("Error: This ApplicationQueue is full");
		}

		queue[size] = o;

		percolateUp(size);

		size++;

	}

	/**
	 * Removes and returns the Application at the root of this ApplicationQueue,
	 * i.e. the Application with the lowest score.
	 * 
	 * @return the Application in this ApplicationQueue with the smallest score
	 * @throws NoSuchElementException with a descriptive error message if this
	 *                                ApplicationQueue is empty
	 */
	@Override
	public Application dequeue() {
		if (isEmpty() == true) {
			throw new NoSuchElementException("Error: This ApplicationQueue is empty");
		}

		Application lowestScore = queue[0];
		queue[0] = queue[size - 1];
		size--;
		percolateDown(0);

		return lowestScore;
	}

	/**
	 * An implementation of percolateDown() method. Restores the min-heap invariant
	 * of a given subtree by percolating its root down the tree. If the element at
	 * the given index does not violate the min-heap invariant (it is due before its
	 * children), then this method does not modify the heap. Otherwise, if there is
	 * a heap violation, then swap the element with the correct child and continue
	 * percolating the element down the heap.
	 * 
	 * This method may be implemented recursively OR iteratively.
	 * 
	 * @param i index of the element in the heap to percolate downwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	private void percolateDown(int i) {
		if (i > size) {
			throw new IndexOutOfBoundsException();
		}
		int leftChildIndex = (2 * i) + 1;
		int rightChildIndex = (2 * i) + 2;
		if (leftChildIndex >= size) {
			return;
		}

		if (queue[i].getScore() < queue[rightChildIndex].getScore()
				&& queue[i].getScore() < queue[leftChildIndex].getScore()) {
			return;
		} else if (queue[leftChildIndex] == null && queue[rightChildIndex] != null) {
			Application tempApp = queue[0];
			queue[0] = queue[rightChildIndex];
			queue[rightChildIndex] = tempApp;
			percolateDown(rightChildIndex);
		} else if (queue[rightChildIndex] == null && queue[leftChildIndex] != null) {
			Application tempApp = queue[0];
			queue[0] = queue[leftChildIndex];
			queue[leftChildIndex] = tempApp;
			percolateDown(leftChildIndex);
		} else if (queue[leftChildIndex].getScore() < queue[rightChildIndex].getScore()) {
			Application tempApp = queue[0];
			queue[0] = queue[leftChildIndex];
			queue[leftChildIndex] = tempApp;
			percolateDown(leftChildIndex);
		} else if (queue[leftChildIndex].getScore() > queue[rightChildIndex].getScore()) {
			Application tempApp = queue[0];
			queue[0] = queue[rightChildIndex];
			queue[rightChildIndex] = tempApp;
			percolateDown(rightChildIndex);
		}

	}

	/**
	 * An implementation of percolateUp() method. Restores the min-heap invariant of
	 * the tree by percolating a leaf up the tree. If the element at the given index
	 * does not violate the min-heap invariant (it occurs after its parent), then
	 * this method does not modify the heap. Otherwise, if there is a heap
	 * violation, swap the element with its parent and continue percolating the
	 * element up the heap.
	 * 
	 * This method may be implemented recursively OR iteratively.
	 * 
	 * Feel free to add private helper methods if you need them.
	 * 
	 * @param i index of the element in the heap to percolate upwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	private void percolateUp(int i) {
		int index = i;
		while (index > 0) {
			int parentIndex = (i - 1) / 2;
			if (queue[index].getScore() >= queue[parentIndex].getScore()) {
				return;
			} else {
				Application tempApp = queue[parentIndex];
				queue[parentIndex] = queue[index];
				queue[index] = tempApp;
				index = parentIndex;
			}
		}
	}

	/**
	 * Returns the Application at the root of this ApplicationQueue, i.e. the
	 * Application with the lowest score.
	 * 
	 * @return the Application in this ApplicationQueue with the smallest score
	 * @throws NoSuchElementException if this ApplicationQueue is empty
	 */
	@Override
	public Application peek() {
		if (isEmpty() == true) {
			throw new NoSuchElementException("Error: This ApplicationQueue is empty");
		}

		return queue[0];
	}

	/**
	 * Returns a deep copy of this ApplicationQueue containing all of its elements
	 * in the same order. This method does not return the deepest copy, meaning that
	 * you do not need to duplicate applications. Only the instance of the heap
	 * (including the array and its size) will be duplicated.
	 * 
	 * @return a deep copy of this ApplicationQueue. The returned new application
	 *         queue has the same length and size as this queue.
	 */
	public ApplicationQueue deepCopy() {
		ApplicationQueue deepCopy = new ApplicationQueue(size);
		for (int i = 0; i < size; i++) {
			deepCopy.enqueue(queue[i]);
		}

		return deepCopy;
	}

	/**
	 * Returns a String representing this ApplicationQueue, where each element
	 * (application) of the queue is listed on a separate line, in order from the
	 * lowest score to the highest score.
	 * 
	 * This implementation is provided.
	 * 
	 * @see Application#toString()
	 * @see ApplicationIterator
	 * @return a String representing this ApplicationQueue
	 */
	@Override
	public String toString() {
		StringBuilder val = new StringBuilder();

		for (Application a : this) {
			val.append(a).append("\n");
		}

		return val.toString();
	}

	/**
	 * Returns an Iterator for this ApplicationQueue which proceeds from the
	 * lowest-scored to the highest-scored Application in the queue.
	 * 
	 * This implementation is provided.
	 * 
	 * @see ApplicationIterator
	 * @return an Iterator for this ApplicationQueue
	 */
	@Override
	public Iterator<Application> iterator() {
		return new ApplicationIterator(this);
	}
}