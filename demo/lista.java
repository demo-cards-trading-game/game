package demo;

public class lista{
	class Nodo {
        int id;
        Nodo ant,sig;
    }
    
    private Nodo raiz;
    
    public lista() {
        raiz=null;
    }
      
   public void insertar (int pos, int x)
    {
        if (pos <= cantidad () + 1)    {
            Nodo nuevo = new Nodo ();
            nuevo.id = x;
            if (pos == 1){
                nuevo.sig = raiz;
                if (raiz!=null)
                    raiz.ant=nuevo;
                raiz = nuevo;
            } else
                if (pos == cantidad () + 1)    {
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
        }
    }

    public int extraer (int pos) {
        if (pos <= cantidad ())    {
            int informacion;
            if (pos == 1) {
                informacion = raiz.id;
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
                informacion = prox.id;
            }
            return informacion;
        }
        else
            return Integer.MAX_VALUE;
    }

    public void borrar (int pos)
    {
        if (pos <= cantidad ())    {
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
        if (pos1 <= cantidad () && pos2 <= cantidad ())    {
            Nodo reco1 = raiz;
            for (int f = 1 ; f < pos1 ; f++)
                reco1 = reco1.sig;
            Nodo reco2 = raiz;
            for (int f = 1 ; f < pos2 ; f++)
                reco2 = reco2.sig;
            int aux = reco1.id;
            reco1.id = reco2.id;
            reco2.id = aux;
        }
    }
    
    public int mayor () {
        if (!vacia ()) {
            int may = raiz.id;
            Nodo reco = raiz.sig;
            while (reco != null) {
                if (reco.id > may)
                    may = reco.id;
                reco = reco.sig;
            }
            return may;
        }
        else
            return Integer.MAX_VALUE;
    }
    
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

    public int cantidad ()
    {
        int cant = 0;
        Nodo reco = raiz;
        while (reco != null) {
            reco = reco.sig;
            cant++;
        }
        return cant;
    }
    
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
    
    public void imprimir ()
    {
        Nodo reco = raiz;
        while (reco != null) {
            System.out.print (reco.id + "-");
            reco = reco.sig;
        }
        System.out.println();
    }
}
