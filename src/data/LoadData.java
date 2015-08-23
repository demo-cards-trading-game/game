package data;
 import demo.Warrior;
 import demo.List;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

 

 
public class LoadData {
    
 static public List Data ;
 
 public LoadData()
 {
	Data =new List();
	 
 }
 
  public static void cargaContenido(String archivo) throws FileNotFoundException, IOException 
    {
        String cadena , concat;
        
        Warrior Created;
        Scanner s = null;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
      ;
        
        while(  (cadena = b.readLine())!=null ) {
            //System.out.println(cadena);
           
            s=new Scanner(cadena);
            Created=new Warrior();
            
            
            if(s.hasNext()){

                Created.SetId(s.next());
                concat="";
                while(!s.hasNextInt()){
                 concat=concat.concat(s.next()); 
                concat=concat.concat(" "); 
                }
                Created.SetName(concat);
                Created.SetCost( Integer.parseInt(s.next()));
                Created.SetLimit(Integer.parseInt(s.next()));
                Created.SetSource(s.next());
                Created.SetHp(Integer.parseInt(s.next()));
                Created.SetMp(Integer.parseInt(s.next()));
                Created.SetClass(s.next());
                Created.SetSup(Integer.parseInt(s.next()));
                concat="";
                while(s.hasNext()){
                 concat=concat.concat(s.next()); 
                concat=concat.concat(" "); 
                }
                            
                Created.SetDescription(concat);
               
               
          Data.insertar(1,Created);
          
            } 
            
        }
       
       b.close();
    }
     /*public static void main(String[] args) throws IOException 
     {
       cargaContenido("Warriors.in");
        
        Data.imprimir();
      }
      */
    
}
   
 
 
 
 
 
 
 
 
