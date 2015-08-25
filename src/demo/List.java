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
    public static   int cantidad;
    public List() {
        raiz=null;
      cantidad=0;
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
            } else
                if (pos == cantidad  + 1)    {
                    Nodo reco = raiz;
                    while (reco.sig != null) {
                        reco = reco.sig;
                    }
                    reco.sig = nuevo;
                    nuevo.ant=reco;
                    nuevo.sig = null;
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
/**************************esto aun no , no hemos creado nada para comparar una carta de otra *****************************/    
    /*public int mayor () {
        if (!vacia ()) {
            Card may = raiz.info;
            Nodo reco = raiz.sig;
            while (reco != null) {
                if (reco.info > may)
                    may = reco.id;
                reco = reco.sig;
            }
            return may;
        }
        else
            return Integer.MAX_VALUE;
    }
    /*
    public int posMayor() {
        if (!vacia ())    {
            int may = raiz.id;
            int x=1;
            int pos=x;
            Nodo reco = raiz.sig;
            while (reco != null){
                if (reco.id > may) {
                    may = reco.id;
                    pos=x;
                }
                reco = reco.sig;
                x++;
            }
            return pos;
        }
        else
            return Integer.MAX_VALUE;
    }
*/
/*    public int cantidad () esto es innecesario , mega ineficiente y para rematar si la lista es vacia da violacion de segmento 
    {
        int cant = 0;
        Nodo reco = raiz;
        while (reco != null) {
            reco = reco.sig;
            cant++;
        }
        return cant;
    }
 /*  no aun  
    public boolean ordenada() {
        if (cantidad()>1) {
            Nodo reco1=raiz;
            Nodo reco2=raiz.sig;
            while (reco2!=null) {
                if (reco2.id<reco1.id) {
                    return false;
                }
                reco2=reco2.sig;
                reco1=reco1.sig;
            }
        }
        return true;
    }
   */ /* hay que sobrecargar un funcion bool equals();
    public boolean existe(int x) {
        Nodo reco=raiz;
        while (reco!=null) {
            if (reco.id==x)
                return true;
            reco=reco.sig;
        }
        return false;
    }
    
    public boolean vacia ()
    {
        if (raiz == null)
            return true;
        else
            return false;
    }
    */
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
