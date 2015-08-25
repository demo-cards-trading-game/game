package data;
 import demo.Card;
 import demo.List;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

 

 
public class LoadData {
    
 static public List Data ;
 

  public static void cargaWarriors(String archivo) throws FileNotFoundException, IOException 
    {
        String cadena , concat;
        
        Card Created;
        Scanner s = null;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
      ;
        
        while(  (cadena = b.readLine())!=null ) {
            //System.out.println(cadena);
           
            s=new Scanner(cadena);
            Created=new Card();
            
            
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
                Created.SetType("Warrior");
                Created.SetCardNumber(List.cantidad+1);
          Data.insertar(1,Created);
          
            } 
            
        }
       
       b.close();
    }
  public static void cargaDisruptions(String archivo) throws FileNotFoundException, IOException 
  {
      String cadena , concat;
      
      Card Created;
      Scanner s = null;
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
    ;
      
      while(  (cadena = b.readLine())!=null ) {
          //System.out.println(cadena);
         
          s=new Scanner(cadena);
          Created=new Card();
          
          
          if(s.hasNext()){

              Created.SetId(s.next());//carga el sid 
              concat="";
              while(!s.hasNextInt())//esto carga el nombre 
              {
               concat=concat.concat(s.next()); 
              concat=concat.concat(" "); 
              }
              Created.SetName(concat);
              
              Created.SetCost( Integer.parseInt(s.next()));//costo 
              Created.SetLimit(Integer.parseInt(s.next()));//limite
              Created.SetSource(s.next());//carga el elemento 
              Created.SetClass(s.next());
              concat="";
              while(s.hasNext()){
               concat=concat.concat(s.next()); 
              concat=concat.concat(" "); 
              }
                          
              Created.SetDescription(concat);
              Created.SetType("Disruption");
              Created.SetCardNumber(List.cantidad+1);
        Data.insertar(1,Created);
        
          } 
          
      }
     
     b.close();
  }
  public static void cargaEvents(String archivo) throws FileNotFoundException, IOException 
  {
      String cadena , concat;
      
      Card Created;
      Scanner s = null;
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
    ;
      
      while(  (cadena = b.readLine())!=null ) {
          //System.out.println(cadena);
         
          s=new Scanner(cadena);
          Created=new Card();
          
          
          if(s.hasNext()){

              Created.SetId(s.next());//carga el sid 
              concat="";
              while(!s.hasNextInt())//esto carga el nombre 
              {
               concat=concat.concat(s.next()); 
              concat=concat.concat(" "); 
              }
              Created.SetName(concat);
              
              Created.SetCost( Integer.parseInt(s.next()));//costo 
              Created.SetLimit(Integer.parseInt(s.next()));//limite
              Created.SetSource(s.next());//carga el elemento 
              Created.SetClass(s.next());
              concat="";
              while(s.hasNext()){
               concat=concat.concat(s.next()); 
              concat=concat.concat(" "); 
              }
                          
              Created.SetDescription(concat);
              Created.SetType("Event");
              Created.SetCardNumber(List.cantidad+1);
        Data.insertar(1,Created);
        
          } 
          
      }
     
     b.close();
  }
  
  public static void cargaPowers(String archivo) throws FileNotFoundException, IOException 
  {
      String cadena , concat;
      
      Card Created;
      Scanner s = null;
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
    ;
      
      while(  (cadena = b.readLine())!=null ) {
          //System.out.println(cadena);
         
          s=new Scanner(cadena);
          Created=new Card();
          
          
          if(s.hasNext()){

              Created.SetId(s.next());//carga el sid 
              concat="";
              while(!s.hasNextInt())//esto carga el nombre 
              {
               concat=concat.concat(s.next()); 
              concat=concat.concat(" "); 
              }
              Created.SetName(concat);
              
              Created.SetCost( Integer.parseInt(s.next()));//costo 
              Created.SetLimit(Integer.parseInt(s.next()));//limite
              Created.SetSource(s.next());//carga el elemento 
              Created.SetType(s.next());
              concat="";
              while(s.hasNext()){
               concat=concat.concat(s.next()); 
              concat=concat.concat(" "); 
              }
                          
              Created.SetDescription(concat);
              Created.SetType("Event");
              Created.SetCardNumber(List.cantidad+1);
        Data.insertar(1,Created);
        
          } 
          
      }
     
     b.close();
  }
  public LoadData() throws FileNotFoundException, IOException
  {
	  Data =new List();
	  cargaWarriors("Warriors.in"); 
	  cargaDisruptions("Disruptions.in");
      cargaEvents("Events.in");
      cargaPowers("Powers.in");
     
 	 
  }
  
  
    
}
   
 
 
 
 
 
 
 
 
