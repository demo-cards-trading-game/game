package demo;
public class Card
{
  private String SetNumber;
  private int Cost;
  private String Name;
  private String Class;
  private int Limit;
  private String Source;
  private String Description;
  private int Hp;
  private int Mp;
  private int Sup;
  private String Type;
  private int CardNumber;

  public Card()
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
  public void SetName(String New){this.Name=New;}
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

  public void asignar( Card b)
  {
       this.SetNumber=b.SetNumber;
       this.Cost=b.Cost;
       this.Name=b.Name;
       this.Class=b.Class;
       this.Limit=b.Limit;
       this.Source=b.Source;
       this.Description=b.Description;
       this.Hp=b.Hp;
       this.Mp=b.Mp;
       this.Sup=b.Sup;
       this.Type=b.Type;
       this.CardNumber=b.CardNumber;
  }
}