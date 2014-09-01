package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

	public static void save(String fileName, List<String> lines) {
		if (fileName != null && lines.size() > 0) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(new File(fileName));
				for (String line : lines) {
					fw.append(line + "\n");
				}
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
