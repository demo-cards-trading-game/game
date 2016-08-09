package demo;
import data.LoadData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class deck {
    public   Card[]  cards  = new Card[40];
    private boolean[]  verif  = new boolean[40];
    public LoadData list;

    public int cardsLeft(){
        return length;
    }

    class Node {
        Card info;
        Node next;
    }

    private Node root;
    public int length;

    public deck() throws IOException {
        root = null;
        length =0;
        list =new LoadData();
    }

    void init(){
        for (int i=0;i<40;i++){
            verif[i]=false;
            cards[i]= getCard(i);
        }
    }
    public void shuffleDeck() {
        int a,b;
        init();
        Random randomGenerator = new Random();
	 	
        root = null;
        length =0;

        for(int i=0 ;i< 20;i++){
            a = randomGenerator.nextInt(cards.length-1);
            b=randomGenerator.nextInt(cards.length-1);

            if(verif[a]){
                a= findAnother();
            }

            if(verif[b]){
                b= findAnother();
            }

            exchange(a,b);
            this.addCard( cards[a]);
            this.addCard( cards[b]);
        }
    }
    public int findAnother(){
        int i=0;
        int x;
        Random randomGenerator = new Random();

        do{
            x = randomGenerator.nextInt(cards.length-1);
            if(verif[x]){
                x =i;
                i++;
            }
        }while(verif[x]);
        return(x);
    }

    private void exchange(int i, int r) {
        Card swamp = new Card();
        swamp.asignar(cards[i]);
        cards[i].asignar(cards[r]);
        cards[r].asignar(swamp);
        verif[i]=true;
        verif[r]=true;
    }

    public Card getCard(int pos){
        Card info;

        if (pos == 1){
            info = root.info;
        }else{
            Node travel;
            travel = root;
            for (int f = 2 ; f <= pos  ; f++)
                travel = travel.next;
            info=travel.info;
        }
        return info;
    }

    public Card getCardAndExtract(int pos){
        Card info;
        Node sig;
        Node ant;
        if (pos == 1){
            info = root.info;
            root = root.next;
        }else{
            Node reco;
            ant = root;
            reco=ant.next;
            sig=reco.next;
            for (int f = 2 ; f < pos  ; f++){
                ant=ant.next;
                reco = ant.next;
                sig = reco.next;
            }
            info=reco.info;
            ant.next =sig;
        }
        length--;
        return info;
    }

    public int getPosCard(String id){
        int pos=-1;
        for(int i=0; i<this.cardsLeft(); i++){
            if(this.getCard(i).getId().equals(id)){
                pos=i;
            }
        }
        return pos;
    }

    public void addCard(Card c){
        Node newNode= new Node();
        newNode.info= c;
        if(root ==null){
            newNode.next =null;
            root =newNode;
        }else{
            newNode.next = root;
            root = newNode;
        }
        length++;
    }

    public Card extractCard(){
        Card card=new Card();
        if(root !=null){
            card= root.info;
            root = root.next;
            length--;
        }
        return card;
    }

    public void load(String deckName)throws IOException{
        String string;
        int number = 0,times=0;
        Scanner s;
        FileReader f = new FileReader(deckName);
        BufferedReader b = new BufferedReader(f);

        while(  (string = b.readLine())!=null ){
            s=new Scanner(string);
            if(s.hasNext()){
                number= Integer.parseInt(s.next());
                times= Integer.parseInt(s.next());
            }

            for (int i=1;i<=times;i++){
                addCard(list.Data.Consultar(number));
            }
        }
        shuffle();
    }
    public void shuffle(){
        for (int i =0; i<40;i++){
            cards[i]= getCard(i);
        }
        shuffleDeck();
    }
}