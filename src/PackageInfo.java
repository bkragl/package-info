import java.io.IOException;

public class PackageInfo {

	public static String info(String p) throws IOException {
		return SystemCommand.run("apt-cache show " + p);
	}

	public static String depends(String p) throws IOException {
		return SystemCommand.run("apt-cache depends --installed " + p);
	}

	public static String rdepends(String p) throws IOException {
		return SystemCommand.run("apt-cache rdepends --installed " + p);
	}
	
}
