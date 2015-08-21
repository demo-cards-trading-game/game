

public class Player
{
  private int Life;
  private String Name;
  
  public Player()
  {
   Life=2000;
  }
  
  public void Lower(int damage)
  {
    this.Life=Life-damage;
  }
  
  private void SetName(String RecievedName)
  {
    this.Name=RecievedName;
  
  }






}