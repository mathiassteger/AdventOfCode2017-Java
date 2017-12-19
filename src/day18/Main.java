package day18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import util.ReadInputHelper;

public class Main {
	public static LinkedBlockingQueue<ShadowLong> messagesOf0 = new LinkedBlockingQueue<>(),
			messagesOf1 = new LinkedBlockingQueue<>();

	public static int speed = 0;
	
	public static void main(String[] args) {
		int integer = 18;
		ArrayList<String> lines = new ReadInputHelper(integer).getLines();
		ArrayList<String> lines2 = new ReadInputHelper(integer).getLines();

		Program0 p0 = new Program0(lines);
		Program1 p1 = new Program1(lines2);

		p0.start();
		p1.start();

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.println(p0.locked + ", " + p1.locked);

			if (p0.locked && p1.locked) {
				System.out.println("---------------------------------------------------------");
				System.out.println("Program 1 sendcount: " + p1.sendCount);
				System.exit(0);
			}
		}
	}
}

class ShadowLong {
	public long value;

	public ShadowLong(long value) {
		this.value = value;
	}
}

class Program0 extends Thread {
	HashMap<String, Long> list = new HashMap<>();
	ArrayList<String> lines;
	boolean rcved = false;
	long lastPlayedFrequency = 0;
	int sendCount = 0;
	boolean locked = false;

	public Program0(ArrayList<String> lines) {
		this.setDaemon(true);
		list.put("b", 0l);
		list.put("i", 0l);
		list.put("a", 0l);
		list.put("f", 0l);
		list.put("p", 0l);
		this.lines = lines;
	}

	@Override
	public void run() {
		for (int i = 0; i < lines.size() && !rcved; i++) {
			try {
				Thread.sleep(Main.speed);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("\t\t\t\t\t\t\t\tProgram 0: ");
			System.out.print(lines.get(i));

			String[] temp = lines.get(i).split(" ");

			if (temp[0].equals("rcv"))
				System.out.println();

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
			case "add": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				long result = list.get(temp[1]) + value;
				list.put(temp[1], result);
			}
				break;
			case "snd": {
				long value = 0;
				sendCount++;
				try {
					value = Long.parseLong(temp[1]);
				} catch (NumberFormatException e) {
					value = list.get(temp[1]);
				}

				System.out.println(" " + value);

				try {
					Main.messagesOf0.put(new ShadowLong(value));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				break;
			case "jgz": {
				long value = 0;

				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				long threshold = 0l;

				try {
					threshold = Long.parseLong(temp[1]);
				} catch (NumberFormatException e) {
					threshold = list.get(temp[1]);
				}

				if (threshold > 0)
					i += value - 1;
				else
					System.out.print(", skipping jump");
			}
				break;
			case "mod": {
				long value1 = list.get(temp[1]);

				long value2 = 0;

				try {
					value2 = Long.parseLong(temp[2]);
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
				this.locked = true;
				try {
					list.put(temp[1], Main.messagesOf1.take().value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("\n\t\t\t\t\t\t\t\tProgram 0 received! Value of program: " + temp[1] + ": "
						+ list.get(temp[1]));
				this.locked = false;
			}
				break;
			case "mul": {
				long value1 = list.get(temp[1]);
				long value2 = 1;
				try {
					value2 = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value2 = list.get(temp[2]);
				}

				list.put(temp[1], value1 * value2);
			}
				break;
			default:
				System.out.println("Forgot: " + temp[0]);
			}

			if (!temp[0].equals("snd") && !temp[0].equals("rcv"))
				System.out.println(", value of program: " + list.get(temp[1]));
			else
				System.out.println();
		}
		locked = true;
	}
}

class Program1 extends Thread {
	HashMap<String, Long> list = new HashMap<>();
	ArrayList<String> lines;
	boolean rcved = false;
	long lastPlayedFrequency = 0;
	int sendCount = 0;
	boolean locked = false;

	public Program1(ArrayList<String> lines) {
		this.setDaemon(true);
		list.put("b", 0l);
		list.put("i", 0l);
		list.put("a", 0l);
		list.put("f", 0l);
		list.put("p", 1l);
		this.lines = lines;
	}

	@Override
	public void run() {
		for (int i = 0; i < lines.size() && !rcved; i++) {
			try {
				Thread.sleep(Main.speed);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			System.out.print("Program 1: ");
			System.out.print(lines.get(i));
			String[] temp = lines.get(i).split(" ");
			if (temp[0].equals("rcv"))
				System.out.println();
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
			case "add": {
				long value = 0;
				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				long result = list.get(temp[1]) + value;
				list.put(temp[1], result);
			}
				break;
			case "snd": {
				long value = 0;
				sendCount++;
				try {
					value = Long.parseLong(temp[1]);
				} catch (NumberFormatException e) {
					value = list.get(temp[1]);
				}

				System.out.println(" " + value);
				try {
					Main.messagesOf1.put(new ShadowLong(value));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				break;
			case "jgz": {
				long value = 0;

				try {
					value = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value = list.get(temp[2]);
				}

				long threshold = 0l;

				try {
					threshold = Long.parseLong(temp[1]);
				} catch (NumberFormatException e) {
					threshold = list.get(temp[1]);
				}

				if (threshold > 0)
					i += value - 1;
				else
					System.out.print(", skipping jump");
			}
				break;
			case "mod": {
				long value1 = list.get(temp[1]);

				long value2 = 0;

				try {
					value2 = Long.parseLong(temp[2]);
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
				this.locked = true;
				try {
					list.put(temp[1], Main.messagesOf0.take().value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("\nProgram 1 received! Value of program: " + temp[1] + ": " + list.get(temp[1]));
				this.locked = false;
			}
				break;
			case "mul": {
				long value1 = list.get(temp[1]);
				long value2 = 1;
				try {
					value2 = Long.parseLong(temp[2]);
				} catch (NumberFormatException e) {
					value2 = list.get(temp[2]);
				}

				list.put(temp[1], value1 * value2);
			}
				break;
			default:
				System.out.println("Forgot: " + temp[0]);
			}
			if (!temp[0].equals("snd") && !temp[0].equals("rcv"))
				System.out.println(", value of program: " + list.get(temp[1]));
			else
				System.out.println();
		}
		locked = true;
	}
}