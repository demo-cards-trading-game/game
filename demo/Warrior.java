package demo;


public class Warrior extends Card 
{
  private int Hp ;
  private int Mp ;
  
  
  /**********************modificadores****************************/
  public Warrior()
  {
    this.SetId(0);
    this.SetLimit(0);
    this.SetCost(0);
    this.Mp=0;
    this.Hp=0;
  }
  public void SetHp(int New)
  {
    this.Hp=New;
  }
   public void SetMp(int New)
  {
    this.Mp=New;
  }
  
   /**********************funciones de consulta****************************/
  public int GetHp()
  {
    return(this.Hp);
  }
  public int GetMp()
  {
    return(this.Mp);
  }
  
  
  
  
  
  
  
  
  
}