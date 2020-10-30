
import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class FaturacaoGlobal implements Serializable
{
    /**
     * Variaveis da instancia
     */
    private HashMap<Integer, HashMap<String, Faturacao>> fG;
    private int size;
    
    /**
     * Construtor parametrizado
     * @param s
     * @return
     */
    public FaturacaoGlobal(int s){
        fG = new HashMap<Integer, HashMap<String, Faturacao>>(12,1);
        size = s;
    }
    /**
     * Construtor por copia
     * @param f
     * @return
     */
    public FaturacaoGlobal(FaturacaoGlobal f){
        fG = f.getFatGlob();
        size = f.size();
    }
    
    /**
     * Devolve o tamanho inicial de cada HashMap interior
     * @return
     */
    public int size(){return size;}
    /**
     * Devolve uma copia da estrutura onde e guardada a informacao relativa a faturacao
     * @return
     */
    public HashMap<Integer, HashMap<String, Faturacao>> getFatGlob(){
        HashMap<Integer, HashMap<String, Faturacao>> map = new HashMap<>();
        int i;
        for(i = 1; i < 13; i++){
            HashMap<String, Faturacao> aux = new HashMap<>();
            for(Faturacao f:fG.get(i).values()){
                aux.put(f.getProduto(),f.clone());
            }
            map.put(i,aux);
        }
        return map;
    }
    
    /**
     * Cria e adiciona uma nova Faturacao
     * @param pr
     * @param q
     * @param p
     * @param m
     * @param fl
     */
    public void add(String pr, int q, double p, int m, int fl){
        if(fG.containsKey(m)){
            HashMap<String, Faturacao> h = (HashMap<String, Faturacao>) fG.get(m);
            if(h.containsKey(pr)){
                Faturacao f = (Faturacao) h.get(pr);
                f.add(q,p,fl);
                h.replace(pr,f.clone());
            }
            else h.put(pr,new Faturacao(pr,q,p,fl));
            fG.replace(m,h);
        }
        else{
            HashMap<String, Faturacao> h = new HashMap<String, Faturacao>(size,1);
            h.put(pr,new Faturacao(pr,q,p,fl));
            fG.put(m,h);
        }
    }
    
    /**
     * Devolve o numero total de vendas realizadas num dado mes
     * @param m
     * @return
     */
    public int vendasMes(int m){
        HashMap<String, Faturacao> map = (HashMap<String, Faturacao>) fG.get(m);
        int v = map.values().stream()
                            .mapToInt(f -> f.getNumeroVendas())
                            .sum();
        return v;
    }
    
    /**
     * Verifica se um dado produto foi comprado
     * @param pq
     * @return
     */
    public boolean foiComprado(String pr){
        Iterator<HashMap<String, Faturacao>> i = fG.values().iterator();
        boolean b = false;
        while(i.hasNext() && !b){
            b = i.next().containsKey(pr);
        }
        return b;
    }
    
    /**
     * Devolve a Faturacao de um dado produto num dado mes
     * @param prod
     * @param m
     * @return
     */
    public Faturacao faturacaoMensal(String prod, int m){
        Faturacao f = (Faturacao) fG.get(m).get(prod);
        if(f != null) return f.clone();
        else return null;
    }
    
    /**
     * Devolve um Par Produto-Quantidade, com a quantidade total vendida de um produto
     * @param prod
     * @return
     */
    public ParProdQuant totalComprado(String prod){
        ParProdQuant pq = new ParProdQuant(prod);
        int i;
        for(i=1;i<13;i++){
            HashMap<String, Faturacao> map = fG.get(i);
            if(map.containsKey(prod)){
                Faturacao f = (Faturacao) map.get(prod);
                pq.add(f.getQuantidade());
            }
        }
        return pq;
    }
    
    /**
     * Devolve a Faturacao total num dado mes, numa dada filial
     * @param m
     * @param f
     * @return
     */
    public double fatMensalF(int m, int f){
        HashMap<String, Faturacao> map = (HashMap<String, Faturacao>) fG.get(m);
        double v = map.values().stream()
                            .mapToDouble(fat -> fat.getReceitas(f))
                            .sum();
        return v;
    }
    
    /**
     * Cria uma copia da instancia
     * @return
     */
    public FaturacaoGlobal clone(){return new FaturacaoGlobal(this);}
    
    /**
     * Cria uma String com a informacao da instancia
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i;
        for(i = 1; i < 13; i++){
            sb.append("Mes ").append(i).append(":").append("\n");
            for(HashMap<String, Faturacao> h:fG.values()) {
                for(String s:h.keySet()){
                    sb.append(s).append("\n");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    /**
     * Verifica se um objecto e igual a instancia
     * @return
     */
    public boolean equals(Object o){
        if(this==o) return true;
        
        if(o==null || this.getClass()!=o.getClass()) return false;
        
        FaturacaoGlobal f = (FaturacaoGlobal) o;
        return (fG.equals(f.getFatGlob()) && size == f.size());
    }
    
    /**
     * hashCode
     * @return 
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{fG});
    }
}
