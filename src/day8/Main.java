package day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import util.ReadInputHelper;

public class Main {
	public static void main(String[] args) {
		int value = -10000;
		ArrayList<String> lines = new ReadInputHelper(8).getLines();
		HashMap<String, Register> registers = new HashMap<>();
		HashSet<Register> registersSet = new HashSet<>();
		for (int i = 0; i < lines.size(); i++) {
			String[] parts = lines.get(i).split(" ");
			String name = parts[0];
			if (!registers.containsKey(name)) {
				Register temp = new Register(name);
				registers.put(name, temp);
				registersSet.add(temp);
			}
		}

		for (int i = 0; i < lines.size(); i++) {
			String[] parts = lines.get(i).split(" ");
			System.out.println(lines.get(i));
			String targetName = parts[0];
			String operation = parts[1];
			int opValue = Integer.parseInt(parts[2]);
			String evaName = parts[4];
			String evOp = parts[5];
			int evValue = Integer.parseInt(parts[6]);

			if (registers.get(evaName).evaluate(evOp, evValue))
				registers.get(targetName).operation(operation, opValue);
			
			if(registers.get(targetName).value > value)
				value = registers.get(targetName).value;
		}

//		for (Entry<String, Register> entry : registers.entrySet()) {
//			if(entry.getValue().value > value){
//				value = entry.getValue().value;
//			}
//		}
		
		System.out.println(value);
	}
	
	
}

class Register {
	public int value = 0;
	public String name;

	public Register(String name) {
		this.name = name;
	}

	public boolean evaluate(String op, int v) {
		switch (op) {
		case "==":
			return value == v;
		case ">=":
			return value >= v;
		case "<=":
			return value <= v;
		case ">":
			return value > v;
		case "<":
			return value < v;
		case "!=":
			return value != v;
		}
		return false;
	}

	public void operation(String op, int value) {
		if (op.equals("inc"))
			this.value += value;
		else
			this.value -= value;
	}

	public boolean equals(Object o) {
		Register other = (Register) o;
		return this.name.equals(other.name);
	}

}
