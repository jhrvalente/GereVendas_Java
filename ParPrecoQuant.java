import java.io.Serializable;
import java.util.Arrays;

public class ParPrecoQuant implements Serializable {
    /**
     * variavel de instancia quantidade
     */
    private int quantidade;
    /**
     * Variavel de instancia preco
     */
    private double preco;
    
    
    /**
     * Construtor vazio
     */
    public ParPrecoQuant(){
        quantidade=0;
        preco=0;
    }
    /**
     * Obter quantidade
     * @return 
     */
    public int  getQuantidade(){
        return quantidade;
    }
    /**
     * Construtor por copia
     */
    public ParPrecoQuant(ParPrecoQuant p){
        this.quantidade=p.getQuantidade();
        this.preco=p.getPreco();
    }
    
    /**
     * Obter preco
     * @return 
     */
    public double  getPreco(){
        return preco;
    }
    /**
     * Construtor parametrizado
     * @param preco
     * @param quantidade 
     */
    public ParPrecoQuant(double preco, int quantidade){
        this.quantidade=quantidade;
        this.preco=preco;
    }
    /**
     * Altera quantidade
     * @param qt 
     */
    public void setQuantidade(int qt){
        quantidade=qt;
    }
    /**
     * Altera preco
     * @param pr 
     */
    public void setPreco(double pr){
        preco=pr;
    }
    
    /**
     * Clone
     * @return 
     */
    public ParPrecoQuant clone(){
        return new ParPrecoQuant(this);
    }
        
    /**
     * toString
     * @return 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Preco: ").append(this.preco).append("; ");
        sb.append("Quant: ").append(this.quantidade).append("; ");
        return sb.toString();
    }
    
    /**
     * Equals
     * @param o
     * @return 
     */
    public boolean equals(Object o){
        if(this==o) return true;
        
        if(o==null || this.getClass()!=o.getClass()) return false;
        
        ParPrecoQuant v=(ParPrecoQuant) o;
        if(this.quantidade!=v.getQuantidade()) return false;
        if(this.preco!=v.getPreco()) return false;
        
        return true;
    }
    
    /**
     * hashcode
     * @return
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{quantidade,preco});
    }
}