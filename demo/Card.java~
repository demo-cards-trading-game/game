package demo;
public class Card
{

  private int SetNumber; /*id de la carta*/
  private int Cost;    /*costo de mana */
  private String Name; /*nombre de la carta*/
  private String Class; /*clase de la carta */
  private int Limit;   /*el limite de esa carta que puede haber en un deck*/
  private String Source; /*el elemento de la carta */
  /*template el fondo gay de la carta ...depende del tipo de carta*/
  
  
  public Card()/*constructor*/
  {
    this.SetNumber=0;
   }
  /**********************funciones de consulta****************************/
  public int Getid()
  {
    return(this.SetNumber);
  }
  public int GetCost()
  {
    return(this.Cost);
  }
  
  public String GetName()
  {
    return(this.Name);
  }

  public String GetSource()
  {
  return (this.Source);
  }
  
  public String Getclass()
  {
    return (this.Class);
  }
  
  public int GetLimit()
  {
  return (this.Limit);
  }
  
    /**********************modificadores****************************/
  public void SetId(int New)
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
    System.out.println(this.SetNumber);
    System.out.println(this.Cost);
    System.out.println(this.Name);
    System.out.println(this.Source);
    System.out.println(this.Class);
    System.out.println(this.Limit);
    
  }
  
  public void asignar(Card a, Card b)/*a = b */
  {
       a.SetNumber=b.SetNumber; /*id de la carta*/
       a.Cost=b.Cost;    /*costo de mana */
       a.Name=b.Name;  /*nombre de la carta*/
       a.Class=b.Class; /*clase de la carta */
       a.Limit=b.Limit;   /*el limite de esa carta que puede haber en un deck*/
       a.Source=b.Source;
  }
}