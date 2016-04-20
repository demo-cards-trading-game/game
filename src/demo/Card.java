package demo;
public class Card
{
  private String SetNumber; /*id de la carta*/
  private int Cost;    /*costo de mana */
  private String Name; /*nombre de la carta*/
  private String Class; /*clase de la carta */
  private int Limit;   /*el limite de esa carta que puede haber en un deck*/
  private String Source; /*el elemento de la carta */
  private String Description;
  private int Hp;
  private int Mp;
  private int Sup;
  private String Type;
  private int CardNumber;

  public Card()/*constructor*/
  {
    this.SetNumber="";
    this.Limit=0;
    this.Cost=0;
   }
  /**********************funciones de consulta****************************/

  public String GetType()
  {
    return(this.Type);
  }
  
  public String Getid()
  {
    return(this.SetNumber);
  }
  public int GetCost()
  {
    return(this.Cost);
  }
  public String GetDescription()
  {
    return(this.Description);
  }
  public String GetName()
  {
    return(this.Name);
  }
  public String GetSource()
  {
  return (this.Source);
  }
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
  public int GetCardNumber()
  {
    return(this.CardNumber);
  }
    /**********************modificadores****************************/
  public void SetCardNumber(int New)
  {
    this.CardNumber=New;
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
  public void SetType(String New)
  {
    this.Type=New;
  }  
  public void SetDescription(String New)
  {
    this.Description=New;
  }  
  public void SetId(String New)
  {
    this.SetNumber=New;
  }
  public void SetCost(int New)
  {
    this.Cost=New;
  }
  public void SetName(String New)
  {
    this.Name=New;
  }
  public void SetSource(String New)
  {
    this.Source=New;
  }
  public void SetClass(String New)
  {
    this.Class=New;
  }
  public void SetLimit(int New)
  {
    this.Limit=New;
  } 
  /***************************  otros **********************************/
  public void PrintCard() /*imprime toda la info de las cartas (para pruebas)*/
  {
    System.out.println("\nCard Info");
    System.out.println("SetNumber = "+this.SetNumber);
    System.out.println("Cost = "+this.Cost);
    System.out.println("Type= " +this.Type);
    System.out.println("Name = " + this.Name);
    System.out.println("Source = "+this.Source);
    System.out.println("Class = "+this.Class);
    System.out.println("Limit per Deck = "+this.Limit);
    System.out.println("Description= "+this.Description);
    System.out.println("Hp = "+this.Hp);
    System.out.println("Mp = "+this.Mp);
    System.out.println("Support = " +this.Sup);
    System.out.println("Number = " +this.GetCardNumber());
  }
  
  public void asignar( Card b)/*a = b */
  {
       this.SetNumber=b.SetNumber; /*id de la carta*/
       this.Cost=b.Cost;    /*costo de mana */
       this.Name=b.Name;  /*nombre de la carta*/
       this.Class=b.Class; /*clase de la carta */
       this.Limit=b.Limit;   /*el limite de esa carta que puede haber en un deck*/
       this.Source=b.Source;
       this.Description=b.Description;
       this.Hp=b.Hp;
       this.Mp=b.Mp;
       this.Sup=b.Sup;
       this.Type=b.Type;
       this.CardNumber=b.CardNumber;
  }
}