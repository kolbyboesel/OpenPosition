
public class OldMethod {
	
	
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
		} else if (queue[leftChildIndex].getScore() <= queue[rightChildIndex].getScore()) {
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
	
	
	
}
