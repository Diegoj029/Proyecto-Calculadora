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
public interface PilaADT <T> {
    public void push(T dato);
    public T pop();
    public T peek();
    public boolean isEmpty();
}
