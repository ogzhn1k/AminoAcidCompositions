import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class DipeptidComposition {

	public static void main(String[] args) {
		String fileRName = "C:\\WorkSpace-Java\\BIL466\\iva_raw.txt";
		String fileWName = "C:\\WorkSpace-Java\\BIL466\\protA-D.txt";
		HashMap<String, Float> probMap = new HashMap<String, Float>();
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
			String firstDuo="";
			String secondDuo="";
			
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
						if(i+1 != lineArray.length)
						   firstDuo = Character.toString(lineArray[i]) +  Character.toString(lineArray[i+1]);
						for(int j=i ; j<lineArray.length ; j++) {
							if(j+1 != lineArray.length)
							    secondDuo = Character.toString(lineArray[j]) +  Character.toString(lineArray[j+1]);
							if(firstDuo.equals(secondDuo) == true)
								counter++;
			
						}// End of 2nd for
						
						if(probMap.containsKey(firstDuo) == false) {
							//System.out.println(counter + " ");
							probMap.put(firstDuo, ((float)counter)/(lineArray.length-1));
							//if((i+1) != lineArray.length )
								   fileOut.print(((float)counter)/(lineArray.length-1)+",");
								//else
									//fileOut.print(((float)counter)/(lineArray.length-1));
							//fileOut.print(((float)counter)/(lineArray.length)+",");
						}
						else {
							
							//if((i+1) != lineArray.length )
								   fileOut.print(probMap.get(firstDuo)+",");
								//else
									//fileOut.print(probMap.get(firstDuo));
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