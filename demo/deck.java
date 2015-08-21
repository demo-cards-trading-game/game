package demo;

public class deck{
 
  /******************revisar**********************/
  
  
    /**
      * Constructor.  Create an unshuffled deck of cards.
      */
     //public Deck()

     /**
      * Put all the used cards back into the deck,
      * and shuffle it into a random order.
      */
       public void shuffle()
     {
     
     
     }

     /**
      * As cards are dealt from the deck, the number of 
      * cards left decreases.  This function returns the 
      * number of cards that are still left in the deck.
      */
       public int cardsLeft()
     {
         return longitud;
     }

     /**
      * Deals one card from the deck and returns it.
      * @throws IllegalStateException if no more cards are left.
      */
     /*  public Card dealCard()
     {
     
     
     }
  */
  
  
  /*************************************************************/
  
  
  
  
  
 class nodo{ //clase auxiliar para la pila
  Card info;
  nodo sig;
 }
 
 private nodo raiz; //primera carta del deck
 static public int longitud; //tamaño del deck 
 private nodo fin= new nodo();; //apuntador al ultimo nodo
 
 //constructor
 public deck(){
  raiz= null;
  longitud=0;
 }
 
 //consulta
 public Card verPila(){
  return raiz.info;
 }
 
 //modificacion
 public void insertar(Card c){
  nodo nuevo= new nodo();
  nuevo.info= c;
  if(raiz==null){
   nuevo.sig=null;
   raiz=nuevo;
  }
  else{
   nuevo.sig=raiz;
   raiz = nuevo;
  }
  
  //agregar el apuntador al ultimo
  if(longitud==0){
   fin=nuevo;
  }
  
  longitud++;
 }
 
 public void extraer(){
  if(raiz!=null){
   raiz = raiz.sig;
  }
  longitud--;
 }
 
 public Card extraerR(){ //extrae retornando
  Card ident=new Card();
  if(raiz!=null){
  ident= raiz.info;
  raiz = raiz.sig;
  longitud--;
 
  }
 
  return ident;
 }

 
 public void imprimir(){ //cantidad de cartas
  System.out.println(longitud);
 }
}
