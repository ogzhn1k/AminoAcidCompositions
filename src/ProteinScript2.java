import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class ProteinScript2 {

	public static void main(String[] args) {
		String fileRName = "C:\\WorkSpace-Java\\BIL466\\iva_raw.txt";
		String fileWName = "C:\\WorkSpace-Java\\BIL466\\protA.txt";
		HashMap<Character, Float> probMap = new HashMap<Character, Float>();
		char lineArray[];
		String lastLine = null;

		try {
			FileReader inputFile = new FileReader(fileRName);
			FileWriter outputFile = new FileWriter(fileWName);
			
			BufferedReader bufferReader = new BufferedReader(inputFile);
			BufferedWriter bufferWriter = new BufferedWriter(outputFile);
			
			PrintWriter fileOut = new PrintWriter(bufferWriter);
			
			String line = bufferReader.readLine();
			String entireLine="";
			
			while(line != null) {
				lastLine = line;
				lineArray = line.toCharArray();
				if(lineArray[0] != '>') 
					entireLine += line;
				
				else {
					System.out.println(entireLine);
					int counter = 0;
					lineArray = entireLine.toCharArray();
					
					for(int i=0 ; i<lineArray.length ; i++) {
						for(int j=i ; j<lineArray.length ; j++) {
							if(lineArray[i] == lineArray[j])
								counter++;
									
						}// End of 2nd for
						
						if(probMap.containsKey(lineArray[i]) == false) {
							//System.out.println(counter + " ");
							probMap.put(lineArray[i], ((float)counter)/(lineArray.length));
							if((i+1) != lineArray.length )
								   fileOut.print(((float)counter)/(lineArray.length)+",");
								else
									fileOut.print(((float)counter)/(lineArray.length));
							//fileOut.print(((float)counter)/(lineArray.length)+",");
						}
						else {
							
							if((i+1) != lineArray.length )
								   fileOut.print(probMap.get(lineArray[i])+",");
								else
									fileOut.print(probMap.get(lineArray[i]));
						}
						
						counter = 0;
					}// End of 1st for
					
					//probMap.forEach((key,value) -> fileOut.print(value+","));
					fileOut.print("\r\n");
					entireLine = ""; 
					probMap.clear();
				}	
				
				line = bufferReader.readLine();
				
			}// End of While
			System.out.println(lastLine);

			
			bufferReader.close();
			fileOut.close();
				
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
