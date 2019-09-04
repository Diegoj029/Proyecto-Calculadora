/**
 *
 * @author Alejandra Barrera Mesa, Diego Pérez Reyes, Fernanda Loaeza Viadas, Santiago Payro Costilla
 * @version 1.0
 * 
 * <pre>
 * 
 *  Clase EmptyCollectionException
 *  
 *  Muestra un error cuando un objeto de la interfaz PilaADT esta vacia
 * </pre>
 */
public class EmptyCollectionException extends RuntimeException{
        public EmptyCollectionException() {
            super("Coleccion Vacia");
        }

        public EmptyCollectionException(String message) {
            super(message);
        }
    }
