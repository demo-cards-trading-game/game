package demo;
import demo.Card;

public  class List{
 //clase noob de nodo                   
 class Nodo {
        Card info;
        Nodo ant,sig;
       
        
        public Nodo() /*falto esto*/
        {
        ant=sig=null;
        info=new Card();
        }
    }
    
    private Nodo raiz;
    private Nodo ultimo; //para poder insertar nodos al final directamente
    public static   int cantidad;
    
    
    public List() {
        raiz=null;
      cantidad=0;
    }
    
    public int getCantidad(){
    	return this.cantidad;
    }
    
   public void insertar (int pos, Card x)
    {
        if (pos <= cantidad  + 1)    {
            Nodo nuevo = new Nodo ();
            nuevo.info.asignar(x);
            if (pos == 1){
              
            
              nuevo.sig = raiz;
              if (raiz!=null){
               
                raiz.ant=nuevo;
                    
              }
                raiz = nuevo;
                
              if(cantidad==0){ //en este caso el primer elemento tambien es el ultimo
            	  ultimo = nuevo;
              }  
            } else
                if (pos == cantidad  + 1)    {
                    Nodo reco = raiz;
                    while (reco.sig != null) {
                        reco = reco.sig;
                    }
                    reco.sig = nuevo;
                    nuevo.ant=reco;
                    nuevo.sig = null;
                    
                    ultimo=nuevo;
                } else {
                    Nodo reco = raiz;
                    for (int f = 1 ; f <= pos - 2 ; f++)
                        reco = reco.sig;
                    Nodo siguiente = reco.sig;
                    reco.sig = nuevo;
                    nuevo.ant=reco;
                    nuevo.sig = siguiente;
                    siguiente.ant=nuevo;
                    
                }
                cantidad=cantidad+1;
        }
    }

   public void insertarUlt(Card x){
	   //this.insertar((this.getCantidad()+1), x); //solucion facil xD
	   if(ultimo!=null)
	   { Nodo nuevo = new Nodo ();
       nuevo.info.asignar(x);
       ultimo.sig=nuevo;
       nuevo.ant=ultimo;
       nuevo.sig=null;
       ultimo=nuevo;
	   }else
	   {
		   insertar(1,x);
		   
	   }
       cantidad++;
   }
   
    public Card extraer (int pos) {
         Card informacion=new Card();
      if (pos <= cantidad )    {
          
            if (pos == 1) {
                informacion = raiz.info;
                raiz = raiz.sig;
                if (raiz!=null)
                    raiz.ant=null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                reco.sig = prox.sig;
                Nodo siguiente=prox.sig;
                if (siguiente!=null)
                    siguiente.ant=reco;
                informacion = prox.info;
            }
           
        }
         return informacion;
    }
    
    public Card Consultar(int pos)
    {
    	Card informacion=new Card();
    	if (pos == 1) 
    	{
            informacion = raiz.info;
    	}else
    	{
    		Nodo reco;
            reco = raiz;
            for (int f = 2 ; f <= pos  ; f++)
                reco = reco.sig;
            informacion=reco.info;
    	}
    	return informacion;
    }

    public void borrar (int pos)
    {
        if (pos <= cantidad )    {
            if (pos == 1) {
                raiz = raiz.sig;
                if (raiz!=null)
                    raiz.ant=null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                prox=prox.sig;
                reco.sig = prox;
                if (prox!=null)
                    prox.ant=reco;
            }
        }
    }
    
    public void intercambiar (int pos1, int pos2) {
        if (pos1 <= cantidad  && pos2 <= cantidad )    {
            Nodo reco1 = raiz;
            for (int f = 1 ; f < pos1 ; f++)
                reco1 = reco1.sig;
            Nodo reco2 = raiz;
            for (int f = 1 ; f < pos2 ; f++)
                reco2 = reco2.sig;
            Card aux = new Card();
              aux.asignar(reco1.info);
            reco1.info.asignar( reco2.info);
            reco2.info = aux;
        }
    }

    public void imprimir ()
     {
      int contador=1;
        Nodo reco = raiz;
        while (reco != null) {
            System.out.print ("Card # " + contador);
            reco.info.PrintCard();
            System.out.println();
            reco = reco.sig;
            contador++;
        }
        System.out.println();
    }
}
