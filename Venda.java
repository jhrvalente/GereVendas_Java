import java.io.Serializable;
import java.util.Arrays;

public class Venda implements Serializable{
    
    /** Código do produto */
    private String cod_produto;
    /** Código do cliente */
    private String cod_cliente;
    /** Regima da venda */
    private String regime_venda;
    /** Mês */
    private int mes;
    /** Quantidade */
    private int quantidade;
    /** Filial */
    private int filial;
    /** Preço */
    private double preco;
    
    /**
     * Construtor parametrizado 
     * @param prod
     * @param prc
     * @param quant
     * @param np
     * @param cli
     * @param m
     * @param fil
     */
    public Venda(String prod, double prc, int quant, String np, String cli, int m, int fil){
        this.cod_produto=prod;
        this.cod_cliente=cli;
        this.regime_venda=np;
        this.mes=m;
        this.quantidade=quant;
        this.filial=fil;
        this.preco=prc;
    }
    /**
     * Construtor por cópia
     * @param v
     */
    public Venda(Venda v){
        this.cod_produto=v.getCodigoProduto();
        this.cod_cliente=v.getCodigoCliente();
        this.regime_venda=v.getRegimeVenda();
        this.mes=v.getMes();
        this.quantidade=v.getQuantidade();
        this.filial=v.getFilial();
        this.preco=v.getPreco();
    }
    
    /**
     * Obter código do produto
     * @return
     */
    public String getCodigoProduto(){
        return this.cod_produto;
    }
    
    public String getCodigoCliente(){
        return this.cod_cliente;
    }
    
    public String getRegimeVenda(){
        return this.regime_venda;
    }
    
    public int getMes(){
        return this.mes;
    }
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public int getFilial(){
        return this.filial;
    }
    
    public double getPreco(){
        return this.preco;
    }
        
    /**Clone*/
    public Venda clone(){
        return new Venda(this);
    }
        
    /**toString*/
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("CodProd: ").append(this.cod_produto).append("; ");
        sb.append("CodCli: ").append(this.cod_cliente).append("; ");
        sb.append("RegVenda: ").append(this.regime_venda).append("; ");
        sb.append("Mes: ").append(this.mes).append("; ");
        sb.append("Preco: ").append(this.preco).append("; ");
        sb.append("Quant: ").append(this.quantidade).append("; ");
        sb.append("Filial: ").append(this.filial).append(".\n");
        return sb.toString();
    }
    
    /**Equals*/
    public boolean equals(Object o){
        if(this==o) return true;
        
        if(o==null || this.getClass()!=o.getClass()) return false;
        
        Venda v=(Venda) o;
        if(this.cod_produto!=v.getCodigoProduto()) return false;
        if(this.cod_cliente!=v.getCodigoCliente()) return false;
        if(this.regime_venda!=v.getRegimeVenda()) return false;
        if(this.mes!=v.getMes()) return false;
        if(this.quantidade!=v.getQuantidade()) return false;
        if(this.filial!=v.getFilial()) return false;
        if(this.preco!=v.getPreco()) return false;
        
        return true;
    }
    
    public int hashCode(){
        return Arrays.hashCode(new Object[]{cod_produto,cod_cliente,regime_venda,mes,quantidade,filial,preco});
    }
    
    }