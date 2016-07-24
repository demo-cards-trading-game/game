package demo;
public  class List{
    class Nodo {
        Card info;
        Nodo ant,sig;

        public Nodo() {
            ant=sig=null;
            info=new Card();
        }
    }
    
    private Nodo raiz;
    private Nodo ultimo;
    public int cantidad;

    public List() {
        raiz=null;
        cantidad=0;
    }
    
    public int getCantidad(){
        return this.cantidad;
    }
    
    public void insertar (int pos, Card x){
        if (pos <= cantidad  + 1) {
            Nodo nuevo = new Nodo ();
            nuevo.info.asignar(x);
            if (pos == 1){
                nuevo.sig = raiz;
                if (raiz!=null){
                    raiz.ant=nuevo;
                }
                raiz = nuevo;
                
                if(cantidad==0){
                    ultimo = nuevo;
                }
            } else if (pos == cantidad  + 1){
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
        if(ultimo!=null){
            Nodo nuevo = new Nodo ();
            nuevo.info.asignar(x);
            ultimo.sig=nuevo;
            nuevo.ant=ultimo;
            nuevo.sig=null;
            ultimo=nuevo;
        }else{
            insertar(1,x);
        }
        cantidad++;
    }

    public Card Consultar(int pos){
        Card informacion=new Card();
        boolean encontrado=false;
        Nodo reco;
        reco = raiz;
        while(!encontrado && reco!=null){
            if(pos==reco.info.getCardNumber()){
                encontrado=true;
                informacion=reco.info;
            }
            reco = reco.sig;
        }
        return informacion;
    }
}