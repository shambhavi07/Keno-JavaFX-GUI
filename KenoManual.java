import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class KenoManual {
	
	public static String showText(int choice) throws IOException
	{
		String filename;
		
		if(choice == 0)
			filename = "src/main/resources/rules.txt";
		else
			filename = "src/main/resources/odds.txt";
		
		Scanner scanner = new Scanner(Paths.get(filename), StandardCharsets.UTF_8.name());
		String content = scanner.useDelimiter("\\A").next();
		scanner.close();
		return content;
	}
	
}
