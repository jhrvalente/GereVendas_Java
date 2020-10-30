
import java.util.Arrays;
import java.io.Serializable;


public class InfosVenda implements Serializable{
    /**
     * Quantidade vendida
     */
    private int quantidade;
    /**
     * Preço do produto vendido
     */
    private double preco;
    /**
     * Mes em que se realizou a venda
     */
    private int mes;
    /**
     * Construtor vazio
     */
    public InfosVenda(){
        quantidade=0;
        preco=0;
        mes=0;
    }
    
     /**
     * Construtor parametrizado
     * @param preco
     * @param quantidade 
     */
    public InfosVenda(double preco, int quantidade){
        this.quantidade=quantidade;
        this.preco=preco;
    }
    
    /**
     * Construtor por cópia
     * @param iv 
     */
    public InfosVenda(InfosVenda iv){
        quantidade=iv.getQuantidade();
        preco=iv.getPreco();
        mes=iv.getMes();
    }
    /**
     * Obter quantidade
     * @return 
     */
    public int  getQuantidade(){
        return quantidade;
    }
    /**
     * Obter preço
     * @return 
     */
    public double  getPreco(){
        return preco;
    }
    /**
     * Obter mes
     * @return 
     */
    public int getMes(){
        return mes;
    }

    /**
     * Alterar quantidade
     * @param qt 
     */
    public void setQuantidade(int qt){
        quantidade=qt;
    }
    /**
     * Alterar preço
     * @param pr 
     */
    public void setPreco(double pr){
        preco=pr;
    }
    /**
     * Alterar mes
     * @param mes 
     */
    public void setMes(int mes){
        this.mes=mes;
    }
    
    /**
     * toString
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Mes: ").append(this.mes).append("; ");
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
        InfosVenda v=(InfosVenda) o;
        if(this.mes!=v.getMes()) return false;
        if(this.quantidade!=v.getQuantidade()) return false;
        if(this.preco!=v.getPreco()) return false;
        return true;
    }
    
    
    /**
     * Método clone
     * @return 
     */
    public InfosVenda clone(){
        return new InfosVenda(this);
    }
    /**
     * hashCode
     * @return 
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{quantidade,preco,mes});
    }
}