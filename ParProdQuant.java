
import java.io.Serializable;

public class ParProdQuant implements Serializable, Comparable<ParProdQuant>
{
    /**
     * Variaveis da instancia
     */
    private String produto;
    private int quantidade;
    
    /**
     * Construtor parametrizado
     * @param p
     * @return
     */
    public ParProdQuant(String p){
        produto = p;
        quantidade = 0;
    }
    /**
     * Construtor por copia
     * @param pq
     * @return
     */
    public ParProdQuant(ParProdQuant pq){
        produto = pq.getProduto();
        quantidade = pq.getQuantidade();
    }
    
    /**
     * Devolve o codigo do produto
     * @return
     */
    public String getProduto(){
        return produto;
    }
    /**
     * Devolve a quantidade vendida do produto
     * @return
     */
    public int getQuantidade(){
        return quantidade;
    }
    
    /**
     * Adiciona nova informacao, relativa a quantidade vendida
     * @param q
     */
    public void add(int q){
        quantidade += q;
    }
    
    /**
     * Devolve uma copia da instancia
     * @return
     */
    public ParProdQuant clone(){
        return new ParProdQuant(this);
    }
    
    /**
     * Devolve uma String com a informacao da instancia
     * @return
     */
    public String toString(){
        return produto;
    }
    
    /**
     * Verifica se um dado objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        ParProdQuant pq = (ParProdQuant) o;
        return produto.equals(pq.getProduto()) && quantidade == pq.getQuantidade();
    }
    
    /**
     * Compara duas instancias de ParProdQuant
     * @param pq
     * @return
     */
    public int compareTo(ParProdQuant pq){
        if(quantidade > pq.getQuantidade()) return 1;
        if(quantidade < pq.getQuantidade()) return -1;
        else return produto.compareTo(pq.getProduto());
    }
    
    /**
     * Devolve o hashCode da instancia
     * @return
     */
    public int hashCode(){return produto.hashCode();}
}
