package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadInputHelper {
	int fileNumber;

	public ReadInputHelper(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	public ArrayList<String> getLines() {
		ArrayList<String> lines = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("input" + fileNumber + ".txt"));
			try {
				String line = br.readLine();

				while (line != null) {
					lines.add(line);
					line = br.readLine();
				}
			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}
}
