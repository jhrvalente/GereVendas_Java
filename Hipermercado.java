import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.*;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Hipermercado implements Serializable {

    /**
     * Catologo de clientes
     */
    private Catalogo_Clientes catClientes;
    /**
     * Catologo de Produtos
     */
    private Catalogo_Produtos catProdutos;
    /**
     * Estruturas das Filiais
     */
    private Filial filial1;
    private Filial filial2;
    private Filial filial3;
    /**
     * Estrutura da Faturacao
     */
    private FaturacaoGlobal faturacaoGlobal;
    
    /**
     * Construtor por defeito
     */            
    public Hipermercado(){
        catClientes = new Catalogo_Clientes();
        catProdutos = new Catalogo_Produtos();
        filial1 = new Filial();
        filial2 = new Filial();
        filial3 = new Filial();
    }

    /**
     * Construtor por copia
     * @param h
     */
    public Hipermercado(Hipermercado h){
        catClientes = h.getCatClientes();
        catProdutos = h.getCatProdutos();
        filial1 = h.getFilial(1);
        filial2 = h.getFilial(2);
        filial3 = h.getFilial(3);
    }
    /**
     * Obter filial
     * @param i
     * @return 
     */
    public Filial getFilial(int i){
        switch(i){
            case 1: return filial1.clone();
            case 2: return filial2.clone();
            case 3: return filial3.clone();
        }
        return null;
    }
    
    /**
     * Devolve uma copia do Catalogo de Clientes
     * @return
     */
    public Catalogo_Clientes getCatClientes(){
        return catClientes.clone();
    }
    
    /**
     * Devolve uma copia do Catalogo de Produtos
     * @return
     */
    public Catalogo_Produtos getCatProdutos(){
        return catProdutos.clone();
    }
    
    /**
     * Insere uma novo cliente no Catalogo
     * @param cliente
     */
    public void insereCliente(String cliente){
        if(catClientes == null) catClientes= new Catalogo_Clientes();
        catClientes.addCatClientes(cliente);
    }
    
    /**
     * Insere um novo produto no Catalogo
     * @param produto
     */
    public void insereProduto(String produto){
        if(catProdutos == null) catProdutos = new Catalogo_Produtos();
        catProdutos.addCatProdutos(produto);
    }   
    
    /**
     * Insere uma nova venda na estrutura da Faturacao
     * @param v
     */
    public void insereFaturacao(Venda v){
        if(faturacaoGlobal == null) faturacaoGlobal = new FaturacaoGlobal(catProdutos.size());
        faturacaoGlobal.add(v.getCodigoProduto(),v.getQuantidade(),v.getPreco(), v.getMes(), v.getFilial());
    }

    /**
     * Insere uma nova venda nas estruturas das Filiais
     * @param v
     */
    public void insereFilial(Venda v){
            if(v.getFilial()==1){
                if(filial1 == null) filial1 = new Filial();
                filial1.insereProdutoPorCliente(v.clone());
            }
            if(v.getFilial()==2){
                if(filial2 == null) filial2 = new Filial();
                filial2.insereProdutoPorCliente(v.clone());
            }
            if(v.getFilial()==3){
                if(filial3 == null) filial3 = new Filial();
                filial3.insereProdutoPorCliente(v.clone());
            }
    }
  
    /**
     * Devolve uma instancia com a distribuicao da Faturacao de um produto por mes
     * @param prod
     * @return
     */
    public ProdFatporMes prodMensal(String prod){
        ProdFatporMes pfm = new ProdFatporMes();
        int i;
        for(i = 1; i < 13; i++){
            pfm.add(faturacaoGlobal.faturacaoMensal(prod,i),i);
        }
        return pfm;
    }
    
    /**
     * Devolve uma ListaStrings com os produtos que nao foram comprados
     * @return
     */
    public ListaStrings naoComprados(){
        TreeSet<String> ts = new TreeSet<>();
        HashSet<String> hs = catProdutos.getCatProdutos();
        ListaStrings ls = new ListaStrings();
        hs.stream().filter((s) -> (faturacaoGlobal.foiComprado(s) == false)).forEach((s) -> {
            ts.add(s);
        });
        ts.stream().forEach((s) -> {
            ls.add(s);
        });
        ls.actualizaIndices();
        return ls;
    }
    
    /**
     * Devolve o total faturado num dado mes, numa dada filial
     * @param mes
     * @param filial
     * @return
     */
    public double faturacaoMensalFilial(int mes, int filial){
        return faturacaoGlobal.fatMensalF(mes,filial);
    }
    
    /**
     * Devolve numero total de vendas num dado mes
     * @param m
     * @return
     */
    public int numeroVendasMes(int m){
        return faturacaoGlobal.vendasMes(m);
    }
    
    /**
     * Devolve uma ListaStrings com n produtos mais comprados
     * @param n
     * @return
     */
    public ListaStrings maisComprados(int n){
        TreeSet<ParProdQuant> ts = new TreeSet<>();
        HashSet<String> hs = catProdutos.getCatProdutos();
        int i = 0;
        ListaStrings ls = new ListaStrings();
        hs.stream().forEach((s) -> {
            ts.add(faturacaoGlobal.totalComprado(s).clone());
        });
        while(i < n){
            ParProdQuant pq = ts.last();
            ls.add(pq.toString());
            ts.remove(pq);
            i++;
        }
        ls.actualizaIndices();
        return ls;
    }
    /**
     * 
     * @param m
     * @param l
     * @return 
     */
    public List<String> retiraTres(NavigableMap<Double,String> m, List<String> l){
         Iterator<Map.Entry<Double,String>> it = m.entrySet().iterator();
         int i=0;
         while(it.hasNext() && i!=3){
             Map.Entry<Double,String> m2=it.next();
             String str = m2.getValue();
             l.add(str);
             i++;
         }
         return l;
     }
    /**
     * Obter lista com os três clientes que mais compram em cada filial
     * @return 
     */
    public List<String> tresMaisCompradoresFilial(){
         List<String> tmc = new ArrayList<>();
         NavigableMap<Double,String> f1 = filial1.maisFaturado().descendingMap();
         NavigableMap<Double,String> f2 = filial2.maisFaturado().descendingMap();
         NavigableMap<Double,String> f3 = filial3.maisFaturado().descendingMap();
         tmc = retiraTres(f1, tmc);
         tmc = retiraTres(f2, tmc);
         tmc = retiraTres(f3, tmc);
         return tmc;
    }   
    /**
     * Função auxiliar myAddAll
     * @param h1
     * @param h2
     * @return 
     */
    private HashSet<String> myAddAll(HashSet<String> h1,HashSet<String> h2){
        h2.stream().forEach(x->h1.add(x));
        return h1;
    }
    /**
     * Função auxiliar da xMaisCompraram 
     * Junta num map as compras dos clientes nas três filiais
     * @param aux
     * @param filial
     * @return 
     */
    private HashMap<String,HashSet<String>> xMc(HashMap<String,HashSet<String>> aux,int filial){
        Filial f = getFilial(filial);
        HashSet<String> hs = new HashSet<>();
        HashMap<String,InfoAnual> ig = f.getInfoGasto();
        for(Map.Entry<String,InfoAnual> m: ig.entrySet()){
            if(aux.containsKey(m.getKey())){
                hs = aux.get(m.getKey());
                hs = myAddAll(hs,m.getValue().clone().conjProdutos());
                HashSet<String> copia = new HashSet<>();
                copia = (HashSet<String>)hs.stream().collect(Collectors.toSet());
                aux.put(m.getKey(),copia);  
           }
            else aux.put(m.getKey(),m.getValue().clone().conjProdutos());
        }
        return aux;
    }  
    
    /**
     * Obter uma lista de strings com os x clientes que mais compraram
     * @param n
     * @return 
     */
    public ListaStrings xMaisCompraram(int n){
        int i=0;
        TreeMap<String,Integer> hm = new TreeMap<>();
        HashMap<String,HashSet<String>> aux = new HashMap<>();
        TreeSet<ParCliNumProd> ts = new TreeSet<>();
        ListaStrings ls = new ListaStrings();
        aux = xMc(aux,1);
        aux = xMc(aux,2);
        aux = xMc(aux,3);
        for(Map.Entry<String,HashSet<String>> m: aux.entrySet()){
              hm.put(m.getKey(),m.getValue().size());
        }
        hm.entrySet().stream().forEach((e) -> {
            ts.add(new ParCliNumProd(e.getKey(),e.getValue()));
        });
        Iterator<ParCliNumProd> it = ts.iterator();
        while(it.hasNext() && i!=n){
            StringBuilder sb = new StringBuilder();
            sb.append("Cliente: ").append(it.next().toString());
            ls.add(sb.toString());
            i++;
        }
        ls.actualizaIndices();
        return ls;        
    }
    /**
     * Auxiliar para obter os produtos mais comprados
     * @param prod
     * @param aux
     * @param i
     * @return 
     */ 
    private HashMap<String,ArrayList<InfosVenda>>  pMc(String prod,HashMap<String,ArrayList<InfosVenda>> aux,int i){
        ArrayList<InfosVenda> hs = new ArrayList<>(); 
        Filial f = getFilial(i);
        HashMap<String,InfoAnual> ig = f.getInfoGasto();
        for(Map.Entry<String,InfoAnual> m: ig.entrySet()){
            if(m.getValue().conjProdutos().contains(prod))
                    if(aux.containsKey(m.getKey())){
                        hs = aux.get(m.getKey());
                        hs.add(m.getValue().getPar(prod).clone());
                        ArrayList<InfosVenda> alv= new ArrayList<>();
                        hs.stream().forEach(x->alv.add(x.clone()));
                        aux.put(m.getKey(), alv);
                       }
                    else {
                        hs = new ArrayList<>();
                        hs.add(m.getValue().getPar(prod).clone());
                        aux.put(m.getKey(), hs);
                    }
                }
        return aux;
    }
    /**
     * Obter lista de strings com produtos mais comprados
     * @param prod
     * @param n
     * @return 
     */ 
    public ListaStrings prodMaisComp(String prod, int n){
        int i=0;
        
        TreeMap<String,Double> hm = new TreeMap<>();
        TreeMap<String,Integer> hmQt = new TreeMap<>();
        HashMap<String,ArrayList<InfosVenda>> aux = new HashMap<>();
        
        ListaStrings ls = new ListaStrings();
        TreeSet<ParCliGasto> ts = new TreeSet<>();
        TreeSet<ParCliGasto> ts2 = new TreeSet<>();
        aux = pMc(prod,aux,1);
        aux = pMc(prod,aux,2);
        aux = pMc(prod,aux,3);
        
        for(Map.Entry<String,ArrayList<InfosVenda>> m: aux.entrySet()){
          double d=0;
          int qt=0;
          Iterator<InfosVenda> it = m.getValue().iterator();
          while(it.hasNext()){
                InfosVenda p=it.next();
                d+=p.getQuantidade()*p.getPreco();
                qt+=p.getQuantidade();
		}
          if(hm.containsKey(m.getKey())){
                d+=hm.get(m.getKey());
                qt+=hmQt.get(m.getKey());
                hm.put(m.getKey(),d);
                hmQt.put(m.getKey(),qt);}
           else {
               hm.put(m.getKey(),d);
               hmQt.put(m.getKey(),qt);
           }
        }

        
        Iterator<Map.Entry<String,Double>> it1= hm.entrySet().iterator();
        Iterator<Map.Entry<String,Integer>> it2= hmQt.entrySet().iterator(); 
        while(it1.hasNext() && it2.hasNext()){
            Map.Entry<String,Double> p1 = it1.next();
            Map.Entry<String,Integer> p2 = it2.next();
            ts.add(new ParCliGasto(p1.getKey(),p1.getValue(),p2.getValue()));
        }
        //Retirar os n primeiros, se existirem
        if(ts.size()<n) n=ts.size();
        Iterator<ParCliGasto> it = ts.iterator();
        while(it.hasNext() && i!=n){
            ls.add(it.next().toString());
            i++;
        }
        ls.actualizaIndices();
        return ls;        
     } 
    /**
     * Auxiliar para a função que maiorQuantidade
     * @param quantComp
     * @param cliente
     * @param filial
     * @return 
     */
    private HashMap<String,Integer> mQ(HashMap<String,Integer> quantComp,String cliente,int filial){
        Filial f = getFilial(filial);
        f.procuraCliente(cliente).getProdutosComrados().entrySet().stream().forEach((me) -> {
            if(quantComp.containsKey(me.getValue())){
                Integer i = quantComp.get(me.getValue());
                quantComp.put(me.getValue(),i+me.getKey().getQuantidade());
            }
            else quantComp.put(me.getValue(),me.getKey().getQuantidade());
        });
        return quantComp;
    }
    /**
     * Obter lista de strings com os clientes que mais compraram
     * @param cliente
     * @return 
     */
    public ListaStrings maiorQuantidade(String cliente){
        HashMap<String,Integer> quantComp = new HashMap<>();
        TreeSet<ParCliNumProd> ts = new TreeSet<>();
        ListaStrings ls = new ListaStrings();
        
        quantComp= mQ(quantComp,cliente,1);
        quantComp= mQ(quantComp,cliente,2);
        quantComp= mQ(quantComp,cliente,3);
          
        for(Map.Entry<String,Integer> si:quantComp.entrySet()){
            ParCliNumProd pcp = new ParCliNumProd(si.getKey(),si.getValue());
            ts.add(pcp.clone());
        }
        for(ParCliNumProd p:ts){
            StringBuilder sb = new StringBuilder();
            sb.append("Produto: ").append(p.toString());
            ls.add(sb.toString());
        }
        ls.actualizaIndices();
        return ls;
    }
    
    /**
     * Map com as compras efetuados em todos os meses de um cliente
     * @param cliente
     * @return 
     */
    public HashMap<Integer,Integer> comprasPorMes(String cliente){
        HashMap<Integer,Integer> compMes = new HashMap<>(12,1);
        
        int i=1;
        while(i!=13)
            compMes.put(i++,0);
        filial1.procuraCliente(cliente).getProdutosComrados().entrySet().stream().forEach((me) -> {
            int j = compMes.get(me.getKey().getMes());
            compMes.put(me.getKey().getMes(),j+1);
            });
        filial2.procuraCliente(cliente).getProdutosComrados().entrySet().stream().forEach((me) -> {
            int j = compMes.get(me.getKey().getMes());
            compMes.put(me.getKey().getMes(),j+1);
            });
        filial3.procuraCliente(cliente).getProdutosComrados().entrySet().stream().forEach((me) -> {
            int j = compMes.get(me.getKey().getMes());
            compMes.put(me.getKey().getMes(),j+1);
             });
        return compMes;
        }
    /**
     * Número de produtos diferentes comprados por um cliente
     * @param cliente
     * @return 
     */
    public int produtosDistintos(String cliente){
            HashSet<String> hs = new HashSet<>();
            filial1.procuraCliente(cliente).getProdutosComrados().entrySet().stream().forEach((me) -> {
                hs.add(me.getValue());    
            });
            filial2.procuraCliente(cliente).getProdutosComrados().entrySet().stream().forEach((me) -> {
                    hs.add(me.getValue());
                });
            filial3.procuraCliente(cliente).getProdutosComrados().entrySet().stream().forEach((me) -> {
                    hs.add(me.getValue());
                });
            return hs.size();
    }
    /**
     * Obter o que um cliente gastou durante um ano em todas as filiais
     * @param cliente
     * @return 
     */
    public int gastoTotal(String cliente){
        int gasto=0;
        gasto+=filial1.procuraCliente(cliente).getFaturado();
        gasto+=filial2.procuraCliente(cliente).getFaturado();
        gasto+=filial3.procuraCliente(cliente).getFaturado();
        return gasto;
    }
    /**
     * Número distinto de clientes que comprou em cada mês
     * @return 
     */
    public HashMap<Integer,Integer> clientesDistMes(){
        HashMap<Integer,Integer> res = new HashMap<>();
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm = filial1.cliCompMes();
        for(Map.Entry<Integer,Integer> k: hm.entrySet()){
                int x=hm.get(k.getKey());
                hm.put(k.getKey(),x+1);
        }
        hm = filial2.cliCompMes();
        for(Map.Entry<Integer,Integer> k: hm.entrySet()){
                int x=hm.get(k.getKey());
                hm.put(k.getKey(),x+1);
        }
        hm = filial3.cliCompMes();
        for(Map.Entry<Integer,Integer> k: hm.entrySet()){
                int x=hm.get(k.getKey());
                hm.put(k.getKey(),x+1);
        }
        return hm;
    }
    
    /**
     * Validacao da venda
     * @param v
     * @return
     */
    public boolean valida_Venda(Venda v){
        boolean sucesso = true;
        if((v.getFilial() < 1 || v.getFilial() > 3) || (v.getMes() < 1 || v.getMes() > 12) 
                || (v.getPreco() < 0 || v.getPreco() > 999.99) || (v.getQuantidade() < 1 
                || v.getQuantidade() > 200) || (v.getRegimeVenda().equals("N") && v.getRegimeVenda().equals("P"))
                || (!catClientes.existe(v.getCodigoCliente()) 
                || (!catProdutos.existe(v.getCodigoProduto())))) {
            
                    sucesso = false;
        }
        return sucesso;
    }
        
}
