import demo.Card;



public class prueba {
  public static void main(String[] args)
  {
   Card c1=new Card();
   c1.SetId("1");
   c1.SetClass("siren");
   c1.SetName("Waqus, the Plentiful");
   c1.SetCost(6);
   c1.SetLimit(4);
   c1.SetSource("water");
   c1.PrintCard();
  }
}