package demo;
import data.LoadData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class deck{
    /******************revisar**********************/
	
    public   Card[]  cards  = new Card[40];
    private boolean[]  verif  = new boolean[40];
    public LoadData lista;
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
    /*************************************************************/
    class nodo{ //clase auxiliar para la pila
        Card info;
        nodo sig;
    }
 
    private nodo raiz; //primera carta del deck
    public int longitud; //tamaño del deck

    //constructor
    public deck(){
        raiz= null;
        longitud=0;
        try {
            lista=new LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void init()
    {
        for (int i=0;i<40;i++)
        {
            verif[i]=false;
        }
    }
    public void shuffle() {
        int a,b;
        init();
        Random randomGenerator = new Random();
	 	
        raiz= null;//aca se deberia desuir lo que esta en la lista
        longitud=0;
        for(int i=0 ;i< 20;i++)
        {
            a = randomGenerator.nextInt(cards.length-1);
            b=randomGenerator.nextInt(cards.length-1);
            if(verif[a])
            {
                a=findanother();
            }
            if(verif[b])
            {
                b=findanother();
            }
            exch(a,b);
            this.insertar( cards[a]);
            this. insertar( cards[b]);
        }
    }
    public int findanother()
    {
        int i=0;
        Random randomGenerator = new Random();
        int x;
        do
        {
            x = randomGenerator.nextInt(cards.length-1);//genera un siguiente aleatoriamente
		
            if(verif[x])//se genera el siguiente iterativamente
            {
                x =i;
                i++;
            }
        }while(verif[x]);//ninguno de las dos formas lo encontro
        return(x);
    }

    private void exch( int i, int r) {
        Card swamp = new Card();
        swamp.asignar(cards[i]);
        cards[i].asignar(cards[r]);
        cards[r].asignar(swamp);
        verif[i]=true;
        verif[r]=true;
    }

    public Card Consultar(int pos)
    {
        Card informacion;
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

    public Card ConsultarYextraer(int pos)
    {
        Card informacion;
        nodo sig;
        nodo ant;
        if (pos == 1)
        {
            informacion = raiz.info;
            raiz=raiz.sig;
        }else
        {
            nodo reco;
            ant = raiz;
            reco=ant.sig;
            sig=reco.sig;
            for (int f = 2 ; f < pos  ; f++){
                ant=ant.sig;
                reco = ant.sig;
                sig = reco.sig;
            }
            informacion=reco.info;
            ant.sig=sig;
        }
        longitud--;
        return informacion;
    }

    public int posCard(String id){
        int pos=-1;
        for(int i=0; i<this.cardsLeft(); i++){
            if(this.Consultar(i).Getid().equals(id)){
                pos=i;
            }
        }
        return pos;
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

        longitud++;
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
    public void Load(String nombredeck)throws IOException //de aca sale con 40 cartas
    {
        String cadena;
        int numero = 0,veces=0;
        Scanner s;
        FileReader f = new FileReader(nombredeck);
        BufferedReader b = new BufferedReader(f);

        while(  (cadena = b.readLine())!=null )
        {
            s=new Scanner(cadena);
            if(s.hasNext())
            {
                numero= Integer.parseInt(s.next());
                veces= Integer.parseInt(s.next());
            }
            for (int i=1;i<=veces;i++){
                insertar(lista.Data.Consultar(numero));
            }
        }
        barajear();
    }
    public void barajear()
    {
        for (int i =0; i<40;i++)
        {
            cards[i]=Consultar(i);
        }
        shuffle();
    }
}
