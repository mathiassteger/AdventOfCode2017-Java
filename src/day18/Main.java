package day18;

import java.util.ArrayList;
import java.util.HashMap;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> lines = new ReadInputHelper(18).getLines();
		HashMap<String, Long> list = new HashMap<>();

		list.put("b", 0l);
		list.put("i", 0l);
		list.put("a", 0l);
		list.put("f", 0l);
		list.put("p", 0l);

		boolean rcved = false;
		long lastPlayedFrequency = 0;

		for (int i = 0; i < lines.size() && !rcved; i++) {
			System.out.print(lines.get(i));

			String[] temp = lines.get(i).split(" ");

			switch (temp[0]) {
			case "set": {
				long value = 0;
				try {
					value = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}
				list.put(temp[1], value);
			}
				break;
			case "add": {
				long value = list.get(temp[1]) + Integer.parseInt(temp[2]);
				list.put(temp[1], value);
			}
				break;
			case "snd":
				lastPlayedFrequency = list.get(temp[1]);
				break;
			case "jgz": {
				long value = 0;

				try {
					value = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				if (list.get(temp[1]) > 0)
					i += value - 1;
				else
					System.out.print(", skipping jump");
			}
				break;
			case "mod": {
				long value1 = list.get(temp[1]);

				long value2 = 0;

				try {
					value2 = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value2 = list.get(temp[2]);
				}

				value2 = Math.abs(value2);

				if (value2 == 0)
					break;
				long value = value1 % value2;

				if (value < 0)
					value += value2;
				System.out.print(", " + value1 + "%" + value2 + "=" + value);
				list.put(temp[1], value);
			}
				break;
			case "rcv": {
				long value = (list.get(temp[1]));

				if (value > 0) {
					System.out.print(", rcved: ");
					rcved = true;
					System.out.print(lastPlayedFrequency);
				}
			}
				break;
			case "mul": {
				long value1 = list.get(temp[1]);
				long value2 = 1;
				try {
					value2 = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value2 = list.get(temp[2]);
				}

				list.put(temp[1], value1 * value2);
			}
				break;
			default:
				System.out.println("Forgot: " + temp[0]);
			}

			System.out.println(", value of program: " + list.get(temp[1]));
		}
	}
}

class Program {
	HashMap<String, Long> list = new HashMap<>();
	ArrayList<String> lines;
	boolean rcved = false;
	long lastPlayedFrequency = 0;
	
	public Program (ArrayList<String> lines) {
		list.put("b", 0l);
		list.put("i", 0l);
		list.put("a", 0l);
		list.put("f", 0l);
		list.put("p", 0l);
		this.lines = lines;
	}
	
	public void start(){
		for (int i = 0; i < lines.size() && !rcved; i++) {
			System.out.print(lines.get(i));

			String[] temp = lines.get(i).split(" ");

			switch (temp[0]) {
			case "set": {
				long value = 0;
				try {
					value = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}
				list.put(temp[1], value);
			}
				break;
			case "add": {
				long value = list.get(temp[1]) + Integer.parseInt(temp[2]);
				list.put(temp[1], value);
			}
				break;
			case "snd":
				lastPlayedFrequency = list.get(temp[1]);
				break;
			case "jgz": {
				long value = 0;

				try {
					value = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				if (list.get(temp[1]) > 0)
					i += value - 1;
				else
					System.out.print(", skipping jump");
			}
				break;
			case "mod": {
				long value1 = list.get(temp[1]);

				long value2 = 0;

				try {
					value2 = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value2 = list.get(temp[2]);
				}

				value2 = Math.abs(value2);

				if (value2 == 0)
					break;
				long value = value1 % value2;

				if (value < 0)
					value += value2;
				System.out.print(", " + value1 + "%" + value2 + "=" + value);
				list.put(temp[1], value);
			}
				break;
			case "rcv": {
				long value = (list.get(temp[1]));

				if (value > 0) {
					System.out.print(", rcved: ");
					rcved = true;
					System.out.print(lastPlayedFrequency);
				}
			}
				break;
			case "mul": {
				long value1 = list.get(temp[1]);
				long value2 = 1;
				try {
					value2 = Integer.parseInt(temp[2]);
				} catch (NumberFormatException e) {
					value2 = list.get(temp[2]);
				}

				list.put(temp[1], value1 * value2);
			}
				break;
			default:
				System.out.println("Forgot: " + temp[0]);
			}

			System.out.println(", value of program: " + list.get(temp[1]));
		}
	}
}