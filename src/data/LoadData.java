package data;
import demo.Card;
import demo.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadData {
	public List Data ;

	public void cargaWarriors(String archivo) throws IOException
	{
		String cadena , concat;
		Card Created;
		Scanner s;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);

		while(  (cadena = b.readLine())!=null ) {
			s=new Scanner(cadena);
			Created=new Card();

			if(s.hasNext()){
				Created.setId(s.next());
				concat="";
				while(!s.hasNextInt()){
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}
				Created.setName(concat);
				Created.setCost( Integer.parseInt(s.next()));
				Created.setLimit(Integer.parseInt(s.next()));
				Created.setSource(s.next());
				Created.setHp(Integer.parseInt(s.next()));
				Created.setMp(Integer.parseInt(s.next()));
				Created.setClass(s.next());
				Created.setSup(Integer.parseInt(s.next()));
				concat="";
				while(s.hasNext()){
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}
				Created.setDescription(concat);
				Created.setType("Warrior");
				Created.setCardNumber(Data.cantidad+1);
				Data.insertarUlt(Created);
			} 
		}
		b.close();
	}
	public  void cargaDisruptions(String archivo) throws IOException
	{
		String cadena , concat;
		Card Created;
		Scanner s;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);

		while(  (cadena = b.readLine())!=null ) {
			s=new Scanner(cadena);
			Created=new Card();

			if(s.hasNext()){
				Created.setId(s.next());
				concat="";
				while(!s.hasNextInt())
				{
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}
				Created.setName(concat);
				Created.setCost( Integer.parseInt(s.next()));
				Created.setLimit(Integer.parseInt(s.next()));
				Created.setSource(s.next());
				Created.setClass(s.next());
				concat="";
				while(s.hasNext()){
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}

				Created.setDescription(concat);
				Created.setType("Disruption");
				Created.setCardNumber(Data.cantidad+1);
				Data.insertarUlt(Created);
			} 
		}
		b.close();
	}
	public  void cargaEvents(String archivo) throws IOException
	{
		String cadena , concat;
		Card Created;
		Scanner s;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);

		while(  (cadena = b.readLine())!=null ) {
			s=new Scanner(cadena);
			Created=new Card();
			if(s.hasNext()){
				Created.setId(s.next());
				concat="";
				while(!s.hasNextInt())
				{
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}
				Created.setName(concat);
				Created.setCost( Integer.parseInt(s.next()));
				Created.setLimit(Integer.parseInt(s.next()));
				Created.setSource(s.next());
				Created.setClass(s.next());
				concat="";
				while(s.hasNext()){
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}
				Created.setDescription(concat);
				Created.setType("Event");
				Created.setCardNumber(Data.cantidad+1);
				Data.insertarUlt(Created);
			} 
		}
		b.close();
	}

	public void cargaPowers(String archivo) throws IOException
	{
		String cadena , concat;
		Card Created;
		Scanner s;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		while(  (cadena = b.readLine())!=null ) {
			s=new Scanner(cadena);
			Created=new Card();

			if(s.hasNext()){
				Created.setId(s.next());
				concat="";
				while(!s.hasNextInt())
				{
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}
				Created.setName(concat);
				Created.setCost( Integer.parseInt(s.next()));
				Created.setLimit(Integer.parseInt(s.next()));
				Created.setSource(s.next());
				Created.setType(s.next());
				concat="";
				while(s.hasNext()){
					concat=concat.concat(s.next()); 
					concat=concat.concat(" "); 
				}
				Created.setDescription(concat);
				Created.setType("Event");
				Created.setCardNumber(Data.cantidad+1);
				Data.insertar(1,Created);
			} 
		}
		b.close();
	}
	public LoadData() throws IOException
	{
		Data =new List();
		cargaWarriors("Warriors.in"); 
		cargaDisruptions("Disruptions.in");
		cargaEvents("Events.in");
		cargaPowers("Powers.in");
	}
}