package demo;

public class deck{
	
	class nodo{ //clase auxiliar para la pila
		int id;
		nodo sig;
	}
	
	private nodo raiz; //primera carta del deck
	private int longitud; //tamaño del deck 
	private nodo fin= new nodo();; //apuntador al ultimo nodo
	
	//constructor
	public deck(){
		raiz= null;
		longitud=0;
	}
	
	//consulta
	public int verPila(){
		return raiz.id;
	}
	
	//modificacion
	public void insertar(Card c){
		nodo nuevo= new nodo();
		nuevo.id= c.Getid();
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
	
	public int extraerR(){ //extrae retornando
		if(raiz!=null){
			int ident= raiz.id;
			raiz = raiz.sig;
			longitud--;
			return ident;
		}
		else{
			return Integer.MAX_VALUE;
		}
		
	}

	
	public void imprimir(){ //cantidad de cartas
		System.out.println(longitud);
	}
}
