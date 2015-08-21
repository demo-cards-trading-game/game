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
  /*template el fondo gay de la carta ...depende del tipo de carta*/
  
  
  public Card()/*constructor*/
  {
    this.SetNumber="";
    this.Limit=0;
    this.Cost=0;
   }
  /**********************funciones de consulta****************************/

  
  
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
  
  public String GetClass()
  {
    return (this.Class);
  }
  
  public int GetLimit()
  {
  return (this.Limit);
  }
 
  
    /**********************modificadores****************************/
   
  
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
   
    System.out.println("Name = " + this.Name);
    System.out.println("Source = "+this.Source);
    System.out.println("Class = "+this.Class);
    System.out.println("Limit per Deck = "+this.Limit);
    System.out.println("Description= "+this.Description);
    
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
  }
}