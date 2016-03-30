import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemCommand {
	
	public static String run(String command) throws IOException {
		String s = null;
		StringBuffer buffer = new StringBuffer();

		Process p = Runtime.getRuntime().exec(command);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((s = stdInput.readLine()) != null) {
			buffer.append(s);
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
}
