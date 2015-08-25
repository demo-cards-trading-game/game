package demo;

public class deck{
 
  /******************revisar**********************/
  

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
 
 //sugiero un constructor que genere un deck ya barajeado 
 //es mas eficiente y no se usan tantas estructuras
 //en el vector todas las cardas deben estar previamente declaradas 
 //con Cards c = new Cards();
 public deck(Card[] cards, int n){
	 shuffle(cards); //se barajea el vector antes de insertarse en el deck
	 for (int i=0;i<n;i++){
		 this.insertar(cards[i]);
	 }
 }
 
 //shuffle
public void shuffle(Card[] cards) {
	 int n= cards.length;
	 int aux;
	 for(int i=0;i<n;i++){
		 int r= i+(int)(Math.random()*(n-i)); //entre i y n-1
		 exch(cards,i,r);
	 }
 }

private void exch(Card[] cards, int i, int r) {
	// TODO Auto-generated method stub
	Card swamp = new Card();
	swamp.asignar(cards[i]);
	cards[i].asignar(cards[r]);
	cards[r].asignar(swamp);
}

//consulta
 public Card verPila(){
  return raiz.info;
 }
 
 public int getLongitud(){
	 return longitud;
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
