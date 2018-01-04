package day25;

import java.util.HashMap;

public class Main {
	static boolean[] tape = new boolean[1];
	static HashMap<Integer, State> states = new HashMap<>(); // A = 0,.., F = 5
	static State currentState;
	static int steps = 12261543;
	static int currentPos = 0;

	public static void main(String[] args) {
		states.put(0, new A());
		states.put(1, new B());
		states.put(2, new C());
		states.put(3, new D());
		states.put(4, new E());
		states.put(5, new F());
		
		currentState = states.get(0);
		
		for (int i = 0; i < steps; i++) {
			currentState = states.get(currentState.move());
		}
		
		int counter = 0;
		
		for(boolean b : tape){
			if(b)
				counter++;
		}
		
		System.out.println(counter);
	}

	static void goLeft() {
		currentPos--;
		if (currentPos < 0) {
			boolean[] newTape = new boolean[tape.length + 1];
			System.arraycopy(tape, 0, newTape, 1, tape.length);
			tape = newTape;
			currentPos++;
		}
	}

	static void goRight() {
		currentPos++;
		if (currentPos > tape.length - 1) {
			boolean[] newTape = new boolean[tape.length + 1];
			System.arraycopy(tape, 0, newTape, 0, tape.length);
			tape = newTape;
		}
	}
}

interface State {
	int move();
}

class A implements State {

	// If the current value is 0:
	// - Write the value 1.
	// - Move one slot to the right.
	// - Continue with state B.
	// If the current value is 1:
	// - Write the value 0.
	// - Move one slot to the left.
	// - Continue with state C.

	public int move() {
		if (!Main.tape[Main.currentPos]) {
			Main.tape[Main.currentPos] = true;
			Main.goRight();
			return 1;
		} else {
			Main.tape[Main.currentPos] = false;
			Main.goLeft();
			return 2;
		}
	}
}

class B implements State {

	// If the current value is 0:
	// - Write the value 1.
	// - Move one slot to the left.
	// - Continue with state A.
	// If the current value is 1:
	// - Write the value 1.
	// - Move one slot to the right.
	// - Continue with state C.

	public int move() {
		if (!Main.tape[Main.currentPos]) {
			Main.tape[Main.currentPos] = true;
			Main.goLeft();
			return 0;
		} else {
			Main.tape[Main.currentPos] = true;
			Main.goRight();
			return 2;
		}
	}
}

class C implements State {

	// If the current value is 0:
	// - Write the value 1.
	// - Move one slot to the right.
	// - Continue with state A.
	// If the current value is 1:
	// - Write the value 0.
	// - Move one slot to the left.
	// - Continue with state D.

	public int move() {
		if (!Main.tape[Main.currentPos]) {
			Main.tape[Main.currentPos] = true;
			Main.goRight();
			return 0;
		} else {
			Main.tape[Main.currentPos] = false;
			Main.goLeft();
			return 3;
		}
	}
}

class D implements State {

	// If the current value is 0:
	// - Write the value 1.
	// - Move one slot to the left.
	// - Continue with state E.
	// If the current value is 1:
	// - Write the value 1.
	// - Move one slot to the left.
	// - Continue with state C.

	public int move() {
		if (!Main.tape[Main.currentPos]) {
			Main.tape[Main.currentPos] = true;
			Main.goLeft();
			return 4;
		} else {
			Main.tape[Main.currentPos] = true;
			Main.goLeft();
			return 2;
		}
	}
}

class E implements State {

	// If the current value is 0:
	// - Write the value 1.
	// - Move one slot to the right.
	// - Continue with state F.
	// If the current value is 1:
	// - Write the value 1.
	// - Move one slot to the right.
	// - Continue with state A.

	public int move() {
		if (!Main.tape[Main.currentPos]) {
			Main.tape[Main.currentPos] = true;
			Main.goRight();
			return 5;
		} else {
			Main.tape[Main.currentPos] = true;
			Main.goRight();
			return 0;
		}
	}
}

class F implements State {

	// If the current value is 0:
	// - Write the value 1.
	// - Move one slot to the right.
	// - Continue with state A.
	// If the current value is 1:
	// - Write the value 1.
	// - Move one slot to the right.
	// - Continue with state E.

	public int move() {
		if (!Main.tape[Main.currentPos]) {
			Main.tape[Main.currentPos] = true;
			Main.goRight();
			return 0;
		} else {
			Main.tape[Main.currentPos] = true;
			Main.goRight();
			return 4;
		}
	}
}
