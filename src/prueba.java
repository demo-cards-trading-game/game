import demo.Card;
import demo.deck;

import java.io.IOException;

import data.LoadData;



public class prueba {
  public static void main(String[] args)
  {
  LoadData x=null;
  
  try {
	x=new LoadData();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  
   
   
   deck d = new deck();
   for(int i =1; i<5; i++){
	   d.insertar(LoadData.Data.Consultar(i));
   }
   for(int i =1; i<5; i++)
	   d.extraerR().PrintCard();
   
   
  }
}