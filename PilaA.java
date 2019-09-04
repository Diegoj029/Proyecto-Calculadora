
import java.util.ArrayList;

/**
 *
 * @author Alejandra Barrera Mesa, Diego Pérez Reyes, Fernanda Loaeza Viadas, Santiago Payro Costilla
 * @version 1.0
 * 
 * <pre>
 * 
 *  Clase PilaA
 *  
 *  Crea un objeto generico que da la funcionalidad de una estructura Pila a un arreglo
 * </pre>
 * 
 */
public class PilaA<T> implements PilaADT<T>{
    private int tope;
    private T[] pila;
    private final int MAX=20;

    public PilaA() {
        this.tope = -1;
        this.pila=(T[])new Object[MAX];
    }

    public PilaA(int tope, int MAX) {
        this.tope = tope;
        this.pila=(T[])new Object[MAX];
    }
    
    public boolean isEmpty(){
        return tope==-1;
    }
    
    private void expande(){
        T[] nuevo=(T[])new Object[pila.length*2];
        for(int i=0;i<=tope;i++)
            nuevo[i]=pila[i];
        pila=nuevo;
    }
    
    public void push(T dato){
        if(tope==pila.length-1)
            expande();
        tope++;
        pila[tope]=dato;
    }
    
    public T pop(){
        if(isEmpty())
            throw new EmptyCollectionException();
        else{
            T resul;
            resul=pila[tope];
            pila[tope]=null;
            tope--;
            return resul;
        }
    }
    
    public T peek(){
        if(isEmpty())
            throw new EmptyCollectionException();
        return pila[tope];
    }
    
    public static <T> PilaA eliminaRepetidos(PilaA p){
        if(!p.isEmpty()){
            PilaA<T> aux=new PilaA();
            
            while(!p.isEmpty())
                if(aux.peek()!=p.peek())
                    aux.push((T) p.pop());
            else
                    p.pop();
            
            while(!aux.isEmpty())
                p.push(aux.pop());
        }
        
        return p;
    }
    
    public static <T> PilaADT<T> inviertePila(PilaADT<T> p){
        PilaA<T> res=new PilaA();
        ArrayList<T> aux=new ArrayList<>();
        
        while(!p.isEmpty()){
            aux.add(p.pop());
        }
        for(int i=0;i<aux.size();i++)
            p.push(aux.get(i));
        
        return res;
    }
    
    public static <T> boolean p1Contp2(PilaADT<T> p1,PilaADT<T> p2){
        boolean res=true;
        ArrayList<T> ap1=new ArrayList<>();
        ArrayList<T> ap2=new ArrayList<>();
        
        while(!p1.isEmpty())
            ap1.add(p1.pop());
        while(!p2.isEmpty())
            ap2.add(p2.pop());
        
        for(int i=0;i<ap2.size();i++)
            if(!ap1.contains(ap2.get(i)))
                res=false;
        
        for(int i=ap1.size();i>0;i--)
            p1.push(ap1.get(i));
        for(int i=ap2.size();i>0;i--)
            p2.push(ap2.get(i));
        
        return res;
    }
    
    public static <T> int numElemntos(PilaADT<T> p){
        int tot=-1;
        if(p!=null){
            tot=0;
            PilaA<T> p2=new PilaA();

            while(!p.isEmpty()){
                p2.push(p.pop());
                tot++;
            }
            while(!p2.isEmpty())
                p.push(p2.pop());
        }
        return tot;
    }
}
