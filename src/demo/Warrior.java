package demo;
import demo.Card;  
public class Warrior extends Card
  {
  private int Hp;
  private int Mp;
  private int Sup;
     public int GetHp()
     {
       return(this.Hp);
     }
     public int GetMp()
     {
       return(this.Mp);
     }
     public int GetSup()
     {
       return(this.Sup);
     }
     public void SetHp(int New)
     {
       this.Hp=New;
     }
     public void SetMp(int New)
     {
       this.Mp=New;
     }
     public void SetSup(int New)
     {
       this.Sup=New;
     }
     
 @Override public void PrintCard() /*imprime toda la info de las cartas (para pruebas)*/
  {
    System.out.println("\nCard Info");
    System.out.println("SetNumber = "+Getid());
    System.out.println("Cost = "+ GetCost());
    System.out.println("Hp = "+this.Hp);
    System.out.println("Mp = "+this.Mp);
    System.out.println("Support = " +this.Sup);
    System.out.println("Name = " + GetName());
    System.out.println("Source = "+GetSource());
    System.out.println("Class = "+ GetClass());
    System.out.println("Limit per Deck = "+GetLimit());
    System.out.println("Description= "+GetDescription());
    
  }
  public void asignar( Warrior b)/*a = b */
  {
       
       super.asignar(b ); 
    
       this.Hp=b.Hp;
       this.Mp=b.Mp;
       this.Sup=b.Sup;
  }
     
   } 