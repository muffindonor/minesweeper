package mines;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Mines {
	private Place[][] mineField;
	private int fieldHeight, fieldWidth, openSquares, safeSquares;
	private boolean showAll = false;

	// These variables help with mines placement//
	private Random random = new Random();
	private HashSet<Place> minesSet = new HashSet<>();
	private boolean doneSettingUp = false;

	public Mines(int height, int width, int numMines) {
		fieldHeight = height;
		fieldWidth = width;
		safeSquares = (height) * (width) - numMines;
		mineField = new Place[height][width];
		createField(numMines);

	}

	// creates the mine field by assigning a Place type object into each index
	// randomly assign each mine an index
	private void createField(int numMines) {
		for (int i = 0; i < fieldHeight; i++) {
			for (int j = 0; j < fieldWidth; j++) {
				mineField[i][j] = new Place(i, j, fieldHeight, fieldWidth);
			}
		}
		while (numMines != 0) {
			int row = random.nextInt(fieldHeight);
			int col = random.nextInt(fieldWidth);

			if (addMine(row, col)) {
				numMines--;
			}
		}
		// call function that updates mines' neighbours
		includeMines();
		doneSettingUp = true;

	}

	public boolean addMine(int x, int y) {
		if (!mineField[x][y].isMine) {
			mineField[x][y].isMine = true;
			minesSet.add(mineField[x][y]);
		} else {
			return false;
		}
		// if mines are added after board is set up
		// call includeMines again
		if (doneSettingUp) {
			includeMines();
			safeSquares--;

		}
		return true;
	}

	public boolean open(int x, int y) {

		if (!insideBounds(x, y) || mineField[x][y].isOpen || mineField[x][y].recursedOver) {
			return false;
		}

		if (!mineField[x][y].isMine) {
			mineField[x][y].isOpen = true;
			mineField[x][y].recursedOver = true;
			openSquares++;
			// If possible, attempt to open neighbors
			if (mineField[x][y].numSurroundingMines == 0) {

				open(x - 1, y); // West neighbour
				open(x - 1, y - 1); // North-West neighbour
				open(x, y - 1); // North neighbour
				open(x + 1, y - 1); // North-East neighbour
				open(x + 1, y); // East neighbour
				open(x + 1, y + 1); // South-East neighbour
				open(x, y + 1); // South neighbour
				open(x - 1, y + 1); // South-West neighbour
			}
			return true;
		}
		return false;

	}

	// Function goes over each mine's location in matrix
	// Adds +1 to every mine's neighbour
	// Sets addedToSet flag = true if mine (avoids future repetition)
	private void includeMines() {
		Iterator<Place> minesIterator = minesSet.iterator();
		while (minesIterator.hasNext()) {
			Place place = minesIterator.next();
			int x = place.x;
			int y = place.y;
			// check if mine wasn't already added
			if (!place.addedToSet) {
				// Nested loop to go over entire field
				for (int i = 0; i < fieldHeight; i++) {
					for (int j = 0; j < fieldWidth; j++) {
						// check if place is mine's neighbor (distance <= 1)
						if ((Math.abs(x - i) <= 1) && (Math.abs(y - j) <= 1)) {
							mineField[i][j].numSurroundingMines++;
						}
					}
				}
				// end of loop
				place.addedToSet = true;
			}
		}

	}

	private boolean insideBounds(int x, int y) {
		if (x > fieldHeight - 1 || x < 0 || y > fieldWidth - 1 || y < 0) {
			return false;
		}
		return true;
	}

	public void toggleFlag(int x, int y) {
		if ((!mineField[x][y].isFlag)) {
			mineField[x][y].isFlag = true;
		}
		else {
			mineField[x][y].isFlag = false;
		}

	}

	public boolean isDone() {
		return openSquares == safeSquares;

	}

	public String get(int x, int y) {
		Place square = mineField[x][y];
		boolean tempIsOpen = square.isOpen;


		// if showAll() was called
		if (showAll) {
			tempIsOpen = true;
		}

		if (tempIsOpen) {
			if (square.isMine) {
				return "X";
			} else if (square.numSurroundingMines > 0) {

				return "" + square.numSurroundingMines;
			}
			return " ";
		}
		// Square is closed
		else {
			if (square.isFlag) {
				return "F";
			}
			return ".";
		}

	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fieldHeight; i++) {
			for (int j = 0; j < fieldWidth; j++) {
				sb.append(get(i, j));
			}
			sb.append("\n");

		}
		return sb.toString();
	}

	// Sub class for each index in mine field
	private class Place {
		private int x, y, numSurroundingMines = 0;
		private boolean isMine = false, isOpen = false, addedToSet = false, isFlag = false, recursedOver = false;;

		public Place(int x, int y, int fieldHeight, int fieldWidth) {
			if (x > fieldHeight - 1 || x < 0 || y > fieldWidth - 1 || y < 0) {
				throw new IllegalArgumentException("Bad point");
			}
			this.x = x;
			this.y = y;

		}
		
//		public Place(Place other) {
//			this.x = other.x;
//			this.y = other.y;
//			this.isOpen = other.isOpen;
//			this.isFlag = other.isFlag;
//			this.isMine = other.isMine;
//			this.numSurroundingMines = other.numSurroundingMines;
//		}


		@Override
		public int hashCode() {
			int h = (x + y) * 31;
			return h + h % 2;
		}

		@Override
		public boolean equals(Object o) {
			Place tempPlace = (Place) o;
			return ((x == tempPlace.x) && (y == tempPlace.y));

		}
//
//		@Override
//		public String toString() {
//			return this.toString();
//
//		}

	}

}
