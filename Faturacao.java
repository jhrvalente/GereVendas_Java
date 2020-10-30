import java.io.Serializable;

public class Faturacao implements Serializable
{
    /**
     * Variaveis da instancia
     */
    private String produto;
    private int quantidade[], nVendas[];
    private double receita[];
    
    
    /**
     * Construtor parametrizado 
     * @param pr
     * @param q
     * @param f
     * @param p
     */
    public Faturacao(String pr, int q, double p, int f){
        produto = pr;
        nVendas = new int[3];
        quantidade = new int[3];
        receita = new double[3];
        nVendas[0] = 0; nVendas[1] = 0; nVendas[2] = 0;
        quantidade[0] = 0; quantidade[1] = 0; quantidade[2] = 0;
        if(q>0) nVendas[f-1] = 1;
        quantidade[f-1] = q;
        receita[f-1] = p*q;
    }
    /**
     * Construtor por copia 
     * @param f
     */
    public Faturacao(Faturacao f){
        produto = f.getProduto();
        nVendas = new int[3];
        quantidade = new int[3];
        receita = new double[3]; 
        quantidade[0] = f.getQuantidade(1);
        nVendas[0] = f.getNumeroVendas(1);
        receita[0] = f.getReceitas(1);
        quantidade[1] = f.getQuantidade(2);
        nVendas[1] = f.getNumeroVendas(2);
        receita[1] = f.getReceitas(2);
        quantidade[2] = f.getQuantidade(3);
        nVendas[2] = f.getNumeroVendas(3);
        receita[2] = f.getReceitas(3);
    }
    
    /**
     * Obter codigo do produto
     * @return
     */
    public String getProduto(){return produto;}
    /**
     * Obter quantidade total vendida
     * @return
     */
    public int getQuantidade(){return quantidade[0] + quantidade[1] + quantidade[2];}
    /**
     * Obter total faturado
     * @return
     */
    public double getReceitas(){return receita[0] + receita[1] + receita[2];}
    /**
     * Obter numero total de vendas
     * @return
     */
    public int getNumeroVendas(){return nVendas[0] + nVendas[1] + nVendas[2];}
    /** 
     * Obter quantidade total vendida numa dada filial
     * @return
     */
    public int getQuantidade(int f){return quantidade[f-1];}
    /**
     * Obter total faturado numa dada filial
     * @return
     */
    public double getReceitas(int f){return receita[f-1];}
    /**
     * Obter numero total de vendas numa dada filial
     * @return
     */
    public int getNumeroVendas(int f){return nVendas[f-1];}
    
    /**
     * Adicionar dados de nova venda
     * @param q
     * @param f
     * @param p
     */
    public void add(int q, double p, int f){
        quantidade[f-1] += q;
        receita[f-1] += p*q;
        nVendas[f-1] += 1;
    }
    /**
     * Verifica se um objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Faturacao f = (Faturacao) o;
        if(produto.equals(f.getProduto()) == false) return false;
        if(receita[0] != f.getReceitas(1)) return false;
        if(receita[1] != f.getReceitas(2)) return false;
        if(receita[2] != f.getReceitas(3)) return false;
        if(quantidade[0] != f.getQuantidade(1)) return false;
        if(quantidade[1] != f.getQuantidade(2)) return false;
        if(quantidade[2] != f.getQuantidade(3)) return false;
        if(nVendas[0] != f.getNumeroVendas(1)) return false;
        if(nVendas[1] != f.getNumeroVendas(2)) return false;
        if(nVendas[2] != f.getNumeroVendas(3)) return false;
        return true;
    }
    /**
     * Cria uma copia da instancia
     * @return
     */
    public Faturacao clone(){
        return new Faturacao(this);
    }
    
    /**
     * Gera uma string com a informacao da instancia
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Produto: ").append(produto).append("\n");
        sb.append("Quantidade Vendida: ").append(quantidade).append("\n");
        sb.append("Total Faturado: ").append(receita).append("\n");
        sb.append("Numero de Vendas: ").append(nVendas).append("\n");
        return sb.toString();
    }
    
    /**
     * Devolve o hashCode da instancia
     * @return
     */
    public int hashCode(){return produto.hashCode();}
}
