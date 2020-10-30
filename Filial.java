
import java.io.Serializable;
import java.util.*;

public class Filial implements Serializable
{    
    /**
     * Map com o cliente e respetivas compras anuais
     */
    private HashMap<String,InfoAnual> infoGasto;
    /**
     * Construtor vazio
     */
    public Filial(){
        infoGasto = new HashMap<>();
    }
    /**
     * Constutor por copia
     * @param f 
     */
    public Filial(Filial f){
        infoGasto = f.getInfoGasto();
    }
    /**
     * Obter o mapeamento cliente/informacoes de vendas
     * @return 
     */
    public HashMap<String,InfoAnual> getInfoGasto(){
           HashMap<String,InfoAnual> copia = new HashMap<>();
           infoGasto.forEach((a,b)->copia.put(a,b.clone()));
           return copia;
    }
    /**
     * Obter mapeamento faturado/cliente, ordenado por faturaca£o
     * @return 
     */
    public TreeMap<Double,String> maisFaturado(){
        TreeMap<Double,String> hm = new TreeMap<>();
        infoGasto.entrySet().stream().forEach((m) -> {
           hm.put(m.getValue().getFaturado(),m.getKey());
        });
        return hm;
    }
    /**
     * Inserir produto no respetivo cliente
     * @param v 
     */ 
    public void insereProdutoPorCliente(Venda v){
        String cliente = v.getCodigoCliente();
        String produto = v.getCodigoProduto();
        InfoAnual im = new InfoAnual();
        if(infoGasto.containsKey(cliente)){
            InfoAnual tm = infoGasto.get(cliente);
            tm.add(v.clone());
            infoGasto.put(cliente, tm.clone());
        }
        else {
            InfoAnual g = new InfoAnual();
            g.add(v.clone());
            infoGasto.put(cliente,g.clone());
        }
    }
    /**
     * Procura cliente e retorna a classe que contem as compras desse cliente
     * @param cliente
     * @return 
     */
    public InfoAnual procuraCliente(String cliente){
        for(Map.Entry<String,InfoAnual> me: infoGasto.entrySet())
            if(me.getKey().equals(cliente)) return me.getValue().clone();
        return null;
    } 
    /**
     * Obter o numero de compras total 
     * @return 
     */
    public int numCompras(){
        int cmp=0;
        return infoGasto.values().stream().mapToInt(x->x.numComprasTotal()).sum();
    }
    /**
     * Obter um mapeamento mes/numero de compras
     * @return 
     */
    public HashMap<Integer,Integer> cliCompMes(){
        HashMap<Integer,Integer> hm= new HashMap<>(12);
        int i=1;
        while(i!=13){
            hm.put(i++,0);
        }
        infoGasto.entrySet().stream().forEach((me) -> {
            me.getValue().mesesComprou().stream().forEach((k) -> {
                int x=hm.get(k);
                hm.put(k,x+1);
            });
        });
        return hm;
    }
    
    /**
     * Equals
     * @param o
     * @return 
     */
    public boolean equals(Object o){
        if(this==o) return true;
        
        if(o==null || this.getClass()!=o.getClass()) return false;
        
        Filial v=(Filial) o;
        return (infoGasto.equals(v.getInfoGasto()));
    }
    /**
     * toString
     * @return 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        infoGasto.forEach((a,b)->sb.append("CodCliente: ").append(a).append(b.clone()));
        return sb.toString();
    }
    
    /**
     * Clone
     * @return 
     */
    public Filial clone(){
        return new Filial(this);
    }
    /**
     * hashCode
     * @return 
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{infoGasto});
    }
}
