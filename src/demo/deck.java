package demo;
import java.util.Random;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import data.LoadData;
import demo.List.Nodo;
public class deck{
 
  /******************revisar**********************/
	
	private Card[]  cards  = new Card[40];
	private LoadData lista;
	
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
  try {
	lista=new LoadData();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 }
 
 //sugiero un constructor que genere un deck ya barajeado 
 //es mas eficiente y no se usan tantas estructuras
 //en el vector todas las cardas deben estar previamente declaradas 
 //con Cards c = new Cards();
 public deck(Card[] cards, int n)
 {
	 shuffle(cards); //se barajea el vector antes de insertarse en el deck
	 for (int i=0;i<n;i++){
		 this.insertar(cards[i]);
	 }
 }
 
 //shuffle
public void shuffle(Card[] cards) {
	 int n= cards.length;
	 int mayor, aux, menor;
	  Random rnd = new Random();
	 for(int j=0; j<n; j++){
		  for(int i=0;i<n;i++){
			 mayor =n-i;
			 menor=i;
			 if(mayor<i){
				 aux= menor;
				 menor = mayor;
				 mayor =menor;
			 }
			 int r= (int)(rnd.nextDouble()*mayor+menor); //entre i y n-1
			 
			 exch(cards,i,r);
		 }
	 }
	 
	 for(int i=0;i<n;i++){
		 recorrerYmover();
	 }
 }

private void exch(Card[] cards, int i, int r) {
	// TODO Auto-generated method stub
	Card swamp = new Card();
	swamp.asignar(cards[i]);
	cards[i].asignar(cards[r]);
	cards[r].asignar(swamp);
}

public void recorrerYmover(){
	Random rnd = new Random();
	 for(int i=0; i<cards.length-2; i++){
		 if(cards[i].GetType()==cards[i+1].GetType()){//tratar de que no se vean repetidas a su alrededor
			 this.exch(cards, i, (int)(rnd.nextDouble()*(cards.length-1)+0));
			 System.out.println("h");
		 }
	 }
}

//consulta
 public Card verPila(){
  return raiz.info;
 }
 
 public Card Consultar(int pos)
 {
 	Card informacion=new Card();
 	if (pos == 1) 
 	{
         informacion = raiz.info;
 	}else
 	{
 		nodo reco;
         reco = raiz;
         for (int f = 2 ; f <= pos  ; f++)
             reco = reco.sig;
         informacion=reco.info;
 	}
 	return informacion;
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
 public void Load(String nombredeck)throws FileNotFoundException, IOException 
 {
	 	
	   String cadena;
       int numero = 0,veces=0;
       Card Created;
       Scanner s = null;
       FileReader f = new FileReader(nombredeck);
       BufferedReader b = new BufferedReader(f);
       
       while(  (cadena = b.readLine())!=null ) 
       {
    	   s=new Scanner(cadena);
           Created=new Card();
           
           if(s.hasNext())
           {
        	   numero= Integer.parseInt(s.next());
        	   veces= Integer.parseInt(s.next());
           }
           for (int i=1;i<=veces;i++){
           insertar(lista.Data.Consultar(numero));
           }
    	   
    	   
    	   
       }
	 
 }
 public void barajear()
 {
	//organizamos las cartas de una manera aleatoria antes de barajear
	/* Card [] aux = new Card[longitud];
	 int [] verificar = new int[longitud];
	 for(int i=1; i<=longitud; i++){
		 aux[i].asignar(cards[i]);
		 verificar[i]=0; //significa que esta disponible el espacio
	 }
	 int cont =1;
	 int num;
	 Random rd = new Random();
	 while(cont <= longitud){ //itera hasta que no exista espacios por insertar
		 num= (int)(rd.nextDouble() * (longitud-1)+0); //numero aleatorio entre 0 a longitud-1
		 if(verificar[num]==0){ //verifica espacios vacios
			 cards[num].asignar(aux[num]); //se llena el vector original
			 verificar[num]=1; 
			 cont++;
		 }
	 }
	 */
	 
	 //////////////////////////////////////////////////
	 
	 for (int i =0; i<40;i++)
	 {
		 cards[i]=Consultar(i);
	 }
	 
	 shuffle(cards);
	 new deck();

	 for (int i =0; i<40;i++)
	 {
		insertar( cards[i]);
	 }
	 
	 
	 
	 
 }
 
 public void imprimir(){ //cantidad de cartas
  System.out.println(longitud);
 }
}
