

import java.io.Serializable;
import java.util.Arrays;

public class ParCliQuant implements Serializable, Comparable<ParCliQuant>
{
    /**
     * Variáveis de instância
     */
    private String cliente;
    private int quantidade;
    /**
     * Construtor por parametrizado 
     * @param p 
     */
    public ParCliQuant(String p,int qt){
        cliente = p;
        quantidade = qt;
    }
    /**
     * Construtor por cópia
     * @param pq 
     */
    public ParCliQuant(ParCliQuant pq){
        cliente = pq.getCliente();
        quantidade = pq.getQuantidade();
    }
    /**
     * Obter cliente
     * @return 
     */
    public String getCliente(){
        return cliente;
    }
    /**
     * Obter quantidade
     * @return 
     */
    public int getQuantidade(){
        return quantidade;
    }
    /**
     * Adicionar quantidade
     * @param q 
     */
    public void add(int q){
        quantidade += q;
    }
    /**
     * Clone
     * @return 
     */
    public ParCliQuant clone(){
        return new ParCliQuant(this);
    }
    /**
     * toString
     * @return 
     */
    public String toString(){
        return cliente;
    }
    /**
     * Equals
     * @param o
     * @return 
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        ParCliQuant pq = (ParCliQuant) o;
        return cliente.equals(pq.getCliente()) && quantidade == pq.getQuantidade();
    }
    /**
     * CompareTo
     * @param pq
     * @return 
     */
    public int compareTo(ParCliQuant pq){
        if(quantidade > pq.getQuantidade()) return -1;
        if(quantidade < pq.getQuantidade()) return 1;
        else return cliente.compareTo(getCliente());
    }
    
    /**
     * hashCode
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{quantidade,cliente});
    }
}


