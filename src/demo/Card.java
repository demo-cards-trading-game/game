package demo;
public class Card{
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

  public Card(){
    this.SetNumber="";
    this.Limit=0;
    this.Cost=0;
  }

  public String getType(){
    return(this.Type);
  }

  public String getId(){
    return(this.SetNumber);
  }

  public int getCost(){
    return(this.Cost);
  }

  public String getDescription(){
    return(this.Description);
  }

  public String getName(){
    return(this.Name);
  }

  public String getSource(){
    return (this.Source);
  }

  public int getHp(){
    return(this.Hp);
  }

  public int getMp(){
    return(this.Mp);
  }

  public int getSup(){
    return(this.Sup);
  }

  public int getCardNumber(){
    return(this.CardNumber);
  }

  public int getLimit(){
	    return(Limit);
 }
 
  public void setCardNumber(int New){
    this.CardNumber=New;
  }

  public void setHp(int New){
    this.Hp=New;
  }

  public void setMp(int New){
    this.Mp=New;
  }

  public void setSup(int New){
    this.Sup=New;
  }

  public void setType(String New){
    this.Type=New;
  }

  public void setDescription(String New){
    this.Description=New;
  }

  public void setId(String New){
    this.SetNumber=New;
  }

  public void setCost(int New){
    this.Cost=New;
  }

  public void setName(String New){
    this.Name=New;
  }

  public void setSource(String New){
    this.Source=New;
  }

  public void setClass(String New){
    this.Class=New;
  }

  public void setLimit(int New){
    this.Limit=New;
  }

  public void asignar( Card b){
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