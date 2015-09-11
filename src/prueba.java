import demo.Card;
import demo.List;
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
  
  //x.Data.imprimir();
  	Card [] c = new Card[15];
   deck d = new deck();
   for(int i =1; i<5; i++){
	   c[i]= new Card();
	   c[i].asignar(LoadData.Data.Consultar(i));
   }
   for(int i =1; i<5; i++){
	   c[i].PrintCard();
   }
   /*for(int i =1; i<5; i++){
	   d.insertar(LoadData.Data.Consultar(i));
   }
   for(int i =1; i<5; i++)
	   d.extraerR().PrintCard();
   
  */
	  
	/*Card c1 = new Card();
	c1.SetId("1");
	Card c2 = new Card();
	c2.SetId("2");
	Card c3 = new Card();
	c3.SetId("3");
	Card c4 = new Card();
	c4.SetId("43");
	List l = new List();
	
	l.insertar(1, c1);
	l.insertar(1, c2);
	l.insertar(1, c3);
	l.insertar(4, c3);
	l.insertarUlt(c4);
	l.imprimir();*/
  }

}