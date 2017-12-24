package day23;

import java.util.ArrayList;
import java.util.HashMap;

import util.ReadInputHelper;

public class Main {
	static int speed = 0;

	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(23).getLines();

		HashMap<String, Long> list = new HashMap<>();
		list.put("a", 1l);
		list.put("b", 0l);
		list.put("c", 0l);
		list.put("d", 0l);
		list.put("e", 0l);
		list.put("f", 0l);
		list.put("g", 0l);
		list.put("h", 0l);

		for (int i = 0; i < lines.size() && i > -1; i++) {
			try {
				Thread.sleep(Main.speed);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("Programcount: " + (i+1) + ", ");
			System.out.print(lines.get(i));

			String[] temp = lines.get(i).split(" ");

			switch (temp[0]) {
			case "set": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}
				list.put(temp[1], value);
			}
				break;
			case "sub": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				long result = list.get(temp[1]) + value;
				System.out.print(", substracting: " + list.get(temp[1]) + " and " + value + " = " + result);
				list.put(temp[1], result);
			}
				break;
			case "mul": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				long result = list.get(temp[1]) * value;
				list.put(temp[1], result);
			}
				break;
			case "jnz": {
				long value = 0;

				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}
				long threshold = 0;

				try {
					threshold = Long.parseLong(temp[1]);
				} catch (NumberFormatException e) {
					threshold = list.get(temp[1]);
				}

				if (threshold != 0)
					i += value - 1;
				else
					System.out.print(", skipping jump");
			}
				break;

			default:
				System.out.println("Forgot: " + temp[0]);
			}
			System.out.println(", value of program: " + list.get(temp[1]));
		}

		System.out.println(list.get("h"));
	}
}

class Program extends Thread {
	HashMap<String, Long> list = new HashMap<>();
	ArrayList<String> lines;
	boolean locked = false;
	int counter = 0;

	public Program(ArrayList<String> lines) {
		this.setDaemon(true);
		list.put("a", 0l);
		list.put("b", 0l);
		list.put("c", 0l);
		list.put("d", 0l);
		list.put("e", 0l);
		list.put("f", 0l);
		list.put("g", 0l);
		list.put("h", 0l);
		this.lines = lines;
	}

	@Override
	public void run() {
		for (int i = 0; i < lines.size(); i++) {
			try {
				Thread.sleep(Main.speed);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("Program: ");
			System.out.print(lines.get(i));

			String[] temp = lines.get(i).split(" ");

			switch (temp[0]) {
			case "set": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}
				list.put(temp[1], value);
			}
				break;
			case "sub": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				long result = list.get(temp[1]) - value;
				list.put(temp[1], result);
			}
				break;
			case "mul": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				counter++;
				long result = list.get(temp[1]) * value;
				list.put(temp[1], result);
			}
				break;
			case "jnz": {
				long value = 0;

				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}
				long threshold = 0;

				try {
					threshold = Long.parseLong(temp[1]);
				} catch (NumberFormatException e) {
					threshold = list.get(temp[1]);
				}

				if (threshold != 0)
					i += value - 1;
				else
					System.out.print(", skipping jump");
			}
				break;

			default:
				System.out.println("Forgot: " + temp[0]);
			}

			System.out.println(counter);
		}
	}
}