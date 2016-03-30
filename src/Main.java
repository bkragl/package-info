import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		if (args.length < 1) {
			JOptionPane.showMessageDialog(null, "No input file given");
			return;
		}
		
		String file = args[0];
		Vector<String> packages = new Vector<String>();

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					packages.add(line);
				}
			}

			new PackagesFrame(packages);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
}
