import demo.Card;
import demo.deck;



public class prueba {
  public static void main(String[] args)
  {
   /*Card c1=new Card();
   c1.SetId("1");
   c1.SetClass("siren");
   c1.SetName("Waqus, the Plentiful");
   c1.SetCost(6);
   c1.SetLimit(4);
   c1.SetSource("water");
   c1.PrintCard();
   */
   Card[] c1 = new Card[5];
   for(int i =0; i<5; i++){
	   c1[i]=new Card();
	   c1[i].SetId(""+(i+1));
	   c1[i].SetClass("siren");
	   c1[i].SetName("Waqus, the Plentiful");
	   c1[i].SetCost(6);
	   c1[i].SetLimit(4);
	   c1[i].SetSource("water");
	 
   }
   
   deck d = new deck(c1, 5);
   for(int i =0; i<5; i++){
	   //System.out.println(c1[i].Getid());
	   System.out.println((d.extraerR()).Getid());
   }
  }
}