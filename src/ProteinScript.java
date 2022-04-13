import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class ProteinScript {

	public static void main(String[] args) {
		String fileRName = "";
		String fileWName = "";
		HashMap<Character, Float> probMap = new HashMap<Character, Float>();
		int lineCount = 0;
		char lineArray[];
		
		try {
			FileReader inputFile = new FileReader(fileRName);
			FileWriter outputFile = new FileWriter(fileWName);
			
			BufferedReader bufferReader = new BufferedReader(inputFile);
			BufferedWriter bufferWriter = new BufferedWriter(outputFile);
			
			PrintWriter fileOut = new PrintWriter(bufferWriter);
			
			String line;
			
			while((line = bufferReader.readLine()) != null) {
				if(lineCount % 2 == 0) {
					
					System.out.println(line);
					int counter = 0;
					lineArray = line.toCharArray();
					
					for(int i=0 ; i<lineArray.length ; i++) {
						for(int j=i ; j<lineArray.length ; j++) {
							if(lineArray[i] == lineArray[j])
								counter++;
									
						}
						if(probMap.containsKey(lineArray[i]) == false) {
							System.out.println(counter + " ");
							probMap.put(lineArray[i], ((float)counter)/(lineArray.length));
						}
						counter = 0;
					}
					
					fileOut.println(lineCount + " ");
					probMap.forEach((key,value) -> fileOut.println(key + " : " + value));
					fileOut.println("\n\n\n");
					
				}
				lineCount++;
			}
			
			bufferReader.close();
			fileOut.close();
				
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
