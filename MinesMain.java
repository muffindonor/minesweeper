package mines;

public class MinesMain {

	public static void main(String[] args) {
		Mines m = new Mines(4, 4, 0);
		m.addMine(1, 1);
		m.addMine(2, 3);
		m.addMine(3, 3);

		m.open(2, 2);

		m.open(3, 0);

		m.toggleFlag(3, 3);

		m.toggleFlag(3, 3);
		m.setShowAll(true);
		System.out.println(m);
		m.setShowAll(false);
		m.open(1, 2);
		System.out.println(m);

	}

}
