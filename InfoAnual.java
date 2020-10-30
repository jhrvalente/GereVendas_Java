
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;


public class InfoAnual implements Serializable
{

    /**
    *Compras de um determinado cliente
    */
    private HashMap<InfosVenda,String> produtosComprados;
    /**
     * Gasto total do cliente
     */
    private double faturado;
    /**
     * Construtor vazio
     */
    public InfoAnual(){
        produtosComprados = new HashMap<>();
        faturado = 0;
    }
    /**
     * Construtor parametrizado
     * @param prods 
     */
    public InfoAnual(HashMap<InfosVenda,String> prods){
        produtosComprados = new HashMap<>();
        prods.forEach((a,b)->produtosComprados.put(a.clone(),b));
    }
    /**
     * Construtor por cópia
     * @param im 
     */
    public InfoAnual(InfoAnual im){
        produtosComprados = im.getProdutosComrados();
        faturado = im.getFaturado();
    }
    
    /**
     * Total faturado
     * @return 
     */
    public double getFaturado(){
        return faturado;
    }
    /**
     * Retorna a classe com as informações sobre a venda de um produto
     * @param par
     * @return 
     */
    public InfosVenda getPar(String par){
        for(Map.Entry<InfosVenda,String> p: produtosComprados.entrySet()){
            if(p.getValue().equals(par)) return p.getKey().clone();
        }
        return null;
    }
    /**
     * Número de compras efetuadas por o cliente
     * @return 
     */
    public int numComprasTotal(){
        return produtosComprados.size();
    }
    /**
     * Obter conjunto com os meses em que o cliente efetuou compras
     * @return 
     */
    public HashSet<Integer> mesesComprou(){
        HashSet<Integer> hs = new HashSet<>();
        produtosComprados.keySet().stream().forEach(x->hs.add(x.getMes()));
        return hs;
    }
    /**
     * Obter o map com os produtos e informações das compras do cliente
     * @return 
     */ 
    public HashMap<InfosVenda,String> getProdutosComrados(){
        HashMap<InfosVenda,String> copia = new HashMap<>();
        produtosComprados.forEach((a,b)->copia.put(a.clone(),b));
        return copia;
    }
    /**
     * Obter um conjunto com os produtos comprados por o cliente
     * @return 
     */
    public HashSet<String> conjProdutos(){
        return (HashSet)produtosComprados.values().stream().collect(Collectors.toSet());
    }
    /**
     * Criar informações da venda 
     * @param v 
     */
    public void add(Venda v){
        if(produtosComprados==null) produtosComprados = new HashMap<>();
        InfosVenda ppq=new InfosVenda();
        ppq.setPreco(v.getPreco());
        ppq.setQuantidade(v.getQuantidade());
        ppq.setMes(v.getMes());
        produtosComprados.put(ppq.clone(),v.getCodigoProduto());
        faturado += v.getPreco()*v.getQuantidade();
    }
    /**
     * Clone
     * @return 
     */
    public InfoAnual clone(){
        return new InfoAnual(this);
    }
    /**
     * toString
     * @return 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Faturado: ").append(this.faturado).append("; ");
        produtosComprados.forEach((a,b)->sb.append("Produto ").append(b).append(" Infovenda: ").append(a.clone()));
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
        
        InfoAnual v=(InfoAnual) o;
        
        return (faturado==v.getFaturado() && produtosComprados.equals(v.getProdutosComrados()));
    }
    
}