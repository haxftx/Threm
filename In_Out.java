import java.io.*;
import java.util.Scanner;

public class In_Out {
	public static void main(String[] arg) throws IOException  {
		File file = new File("therm.in"); 
		Scanner sc = new Scanner(file);
		sc.useDelimiter("");
		if(!sc.hasNextLine()) {//verific daca nu e gol fisierul
			sc.close();
			return;
		}
		String line;
		String[] input = new String[10];
		line = sc.nextLine();
		input = line.split(" ");
		int nr_camere = Integer.parseInt(input[0]);
		double temp_glb = Double.parseDouble(input[1]);
		double umid_glb = 0;
		long tempstamp;
		if(input.length == 4) {
			umid_glb = Double.parseDouble(input[2]);
			tempstamp = Long.parseLong(input[3]);
		}
		else
			tempstamp = Long.parseLong(input[2]);
		Date info = new Date(nr_camere, temp_glb, umid_glb, tempstamp);
		for(int i = 0; i < nr_camere; i++) {//citesc persoanele din fisier
			if(!(sc.hasNextLine())) break;
			line = sc.nextLine();
			input = line.split(" ");
			info.add(new Camera(input[0], input[1], Double.parseDouble(input[2])));
		  }
		  while(sc.hasNextLine()) {//citesc comenzele din fisier
			  input = (sc.nextLine()).split(" ");
			  if(input[0].equals("OBSERVE")) {//functia observe
				  info.observe(input[1], Long.parseLong(input[2]), Double.parseDouble(input[3]), true);
			  }
			  if(input[0].equals("OBSERVEH")) {//funtia observeh
				  info.observe(input[1], Long.parseLong(input[2]), Double.parseDouble(input[3]), false);
			  }
			  if(input[0].equals("TRIGGER")) {//functia ttigger heat
				  info.trigger_heat();
			  }
			  if(input[0].equals("TEMPERATURE")) {//funtia temperature
				  info.setTemp(Double.parseDouble(input[1]));
			  }
			  if(input[0].equals("LIST")) {//functia list
				 info.list(input[1], Long.parseLong(input[2]), Long.parseLong(input[3]));
					  System.out.println();
			  }
		  }
		  sc.close();
		  }
}