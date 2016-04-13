package demo;

public class Hand
{
    public Card[]   hand = new Card[5];
    static public int current;
  
    public Hand()/*constructor ...crea la mano vacia*/
    {
        int i;
        for (i=0;i<5;i++)
        {
            hand[i]=new Card();
        }
    }
  
    public void discard(int pos)
    {
        int i;
     
        for (i=pos;i<current;i++)
        {
            hand[i]=hand[i+1];
        }
        current=current-1;
    }
    
    public void draw(Card a)
    {
        hand[current]=a;
        current=current+1;
    }
    
    public Card GetCard(int pos)
    {
        return(hand[pos]);
    }
}