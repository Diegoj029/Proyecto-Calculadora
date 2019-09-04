import java.util.ArrayList;

/**
 *
 * @author Alejandra Barrera Mesa, Diego Pérez Reyes, Fernanda Loaeza Viadas, Santiago Payro Costilla
 * @version 3.1
 * 
 * <pre>
 * 
 *  Clase Calculadora
 *  
 *  Repositorio de metodos estaticos.
 *  Contiene metodos referentes al funcionamiento de un calculadora 
 * </pre>
 */
public class Calculadora {
    protected static String op="+-*/^";
    
    /**
     * Metodo que suma dos numeros
     * @param num1 Primer numero
     * @param num2 Segundo numero
     * @return double resultado
     */
    public static double suma(double num1, double num2){
        return num1+num2;
    }
    
    /**
     * Metodo que resta dos numeros
     * @param num1 Minuendo
     * @param num2 Sustraendo
     * @return double resultado
     */
    public static double resta(double num1, double num2){
        return num1-num2;
    }
    
    /**
     * Metodo que multiplica dos numeros
     * @param num1 Primer multiplo
     * @param num2 Segundo multiplo
     * @return double resultado
     */
    public static double multiplicacion(double num1, double num2){
        return num1*num2;
    }
    
    /**
     * Metodo que divide dos numeros
     * @param num1 Numerador
     * @param num2 Denominador
     * @return double resultado
     */
    public static double division(double num1, double num2){
        return num1/num2;
    }
    
    /**
     * Metodo que calcula la potencia de un numero dado un exponente
     * @param num Numero a potenciar
     * @param exp Exponente
     * @return double resultado
     */
    public static double potencia(double num, double exp){
        return Math.pow(num, exp);
    }
    
    /**
     * Metodo que busca que los parentesis esten equilibrados en un String
     * @param exp String a evaluar parentesis
     * @return <ul>
     *      <li>true: si los parentesis del String estan bien colocados</li>
     *      <li>false: si los parentesis del String no estan bien colocados</li>
     * </ul>
     */
    public static boolean validaParentesis(String exp) {
	boolean res=true;
	PilaA <Character> aux = new PilaA();
	
	int i=0;
	while(i<exp.length() && res) {
            if(exp.charAt(i)=='(')
		aux.push(exp.charAt(i));
            else
                if(!aux.isEmpty() && exp.charAt(i)==')') 
                    if(aux.peek()=='(')
			aux.pop();
                    else
                        res=false;
            i++;
	}
        
	return aux.isEmpty() && res; 
    }
    
    /**
     * Metodo que busca en un diccionario la jerarquia de un operador
     * @param signo Character operador
     * @return Int segun la jerarquia del operador
     */
    private static int buscaPrioridad(Character signo) {
	int res=0;
        
        Character[][] jerarquia = new Character[5][2];
        jerarquia[0][0]= '+'; jerarquia[0][1]='1';
        jerarquia[1][0]= '-'; jerarquia[1][1]='1';
        jerarquia[2][0]= '*'; jerarquia[2][1]='2';
        jerarquia[3][0]= '/'; jerarquia[3][1]='2';
        jerarquia[4][0]= '^'; jerarquia[4][1]='3';

	
	int k=0;
	while(k<jerarquia.length && !jerarquia[k][0].equals(signo))
            k++;
        
	if(k<jerarquia.length && jerarquia[k][0].equals(signo))
            res=(int)jerarquia[k][1];
        
        return res;
    }
    
    /**
     * Crea la ecuacion Posfija a partir de una formula matematica
     * @param formula String que contiene la formula a evaluar
     * @return String Posfija con la ecuacion operable
     */
    public static String posFija(String formula){
        StringBuilder posfija=new StringBuilder();
        
        if(!formula.isEmpty()){
            PilaA<String> datos = new PilaA();
            PilaA<Character> signos = new PilaA();
            
            for(int i=0;i<formula.length();i++){
                char elem=formula.charAt(i);
                if(op.contains(elem+"")){
                    if(i>0 && elem=='-' && (op.contains(formula.charAt(i-1)+"") || formula.charAt(i-1)=='(')){
                        posfija.append(elem);
                    }else if(i==0 && elem=='-'){
                        posfija.append(elem);
                    }else{
                        if(posfija.charAt(posfija.length()-1)!=' ')
                            posfija.append(" ");
                        
                        if(!signos.isEmpty() && !signos.peek().equals('(')){
                            while(!signos.isEmpty() && Calculadora.buscaPrioridad(elem)<=Calculadora.buscaPrioridad(signos.peek())){
                                posfija.append(signos.pop());
                                if(posfija.charAt(posfija.length()-1)!=' ')
                                    posfija.append(" ");
                            }
                            signos.push(elem);
                        }else{
                            signos.push(elem);
                        }
                    }
                }else if(elem=='('){
                    if(i>0 && op.contains(formula.charAt(i-1)+"")){
                        signos.push(elem);
                    }else{
                        if(i>0 && formula.charAt(i-1)!='('){
                            signos.push('*');
                            signos.push(elem);
                            posfija.append(" ");
                        }else{
                            signos.push(elem);
                        }
                    }
                }else if(elem==')'){
                    while(!signos.isEmpty() && !signos.peek().equals('(')){
                        if(posfija.charAt(posfija.length()-1)!=' ')
                            posfija.append(" ");
                        
                        posfija.append(signos.pop());
                    }
                    
                    signos.pop();
                }else{
                    posfija.append(elem);
                }
            }
            
            while(!signos.isEmpty()){
                if(!signos.peek().equals('(')){
                    if(posfija.charAt(posfija.length()-1)!=' ')
                        posfija.append(" ");
                    posfija.append(signos.pop());
                }else
                    signos.pop();
            }
                    
        }
        
        if(posfija.charAt(posfija.length()-1)==' ')
            posfija.deleteCharAt(posfija.length()-1);
            
        return posfija.toString();
    }
    
    /**
     * Metodo que valida la operacion de la ecuacion Posfija
     * @param posfija String con la ecuacion a evaluar
     * @return <ul>
     *      <li>true: si es posible operar Posfija</li>
     *      <li>false: si no es posible operar Posfija</li>
     * </ul>
     */
    public static boolean validaPosfija(String posfija){
        posfija=posfija.replaceFirst(" ", "");
        return posfija.contains(" ");
    }
    
    /**
     * Metodo que resuelve las operaciones planteadas por una ecuacion de tipo Posfijo
     * @param posfija String con la ecuacion a evaluar
     * @return double con el resultado de la ecuacion Posfija
     */
    public static double operaPosfija(String posfija){

        double res=0;
        PilaA<Double> datos=new PilaA<>();
        ArrayList<Character> operador=new ArrayList<>();
        
        int i=0,esp=0;
        while(esp!=-1){
            esp=posfija.indexOf(' ');
            String elem;
            
            if(esp!=-1){
                elem=posfija.substring(0, esp);
            }else{
                elem=posfija;
            }
            posfija=posfija.substring(esp+1,posfija.length());
            
            if(op.contains(elem)){
                double num2=datos.pop();
                double num1=datos.pop();
                
                switch(elem){
                    case "+":
                        res=Calculadora.suma(num1, num2);
                        break;
                    case "-":
                        res=Calculadora.resta(num1, num2);
                        break;
                    case "*":
                        res=Calculadora.multiplicacion(num1, num2);
                        break;
                    case "/":
                        res=Calculadora.division(num1, num2);
                        break;
                    case "^":
                        res=Calculadora.potencia(num1, num2);
                        break;
                }
                datos.push(res);
            }else{
                datos.push(Double.parseDouble(elem));
            }
        }
        
        return res;
    }
}
