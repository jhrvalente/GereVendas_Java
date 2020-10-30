import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;


public class UltimoFichLido implements Serializable{

    /**
     * Ficheiro lido
     */
    private String fichLido;
    
    /**
     * Vendas erradas
     */
    private int vendasErradas;
    
    /**
     * Total de produtos 
     */
    private int totProdutos;
    
    /**
     * Set dos produtos diferentes comprados
     */
    private Set<String> difProdutos;
    
    
    /**
     * Número total de clientes
     */
    private int nclientes;
    
    
    /**
     * Set dos clientes diferentes que compraram
     */
    private Set<String> cliDifs;
    
    
    /**
     * Compras preco igual a zero
     */
    private int comprasZero;
    
    /**
     * Faturacao total
     */
    private double fatTotal;
    
    /**
     * Construtor parametrizado
     * @param fichLido
     * @param vendasErradas
     * @param totProdutos
     * @param nclientes
     * @param comprasZero
     * @param fatTotal
     * @return
     */
    public UltimoFichLido(String fichLido, int vendasErradas, int totProdutos, int nclientes, int comprasZero, double fatTotal) {
        this.fichLido = fichLido;
        this.vendasErradas = vendasErradas;
        this.totProdutos = totProdutos;
        this.difProdutos = new HashSet<>();
        this.nclientes = nclientes;
        this.cliDifs = new HashSet<>();
        this.comprasZero = comprasZero;
        this.fatTotal = fatTotal;
    }
    
    /**
     * Construtor vazio
     * @return
     */
    public UltimoFichLido(){
        this("",0,0,0,0,0.0);
    }
    
    /**
     * Construtor por copia
     * @param ufl
     * @return
     */
    public UltimoFichLido(UltimoFichLido ufl){
        fichLido = ufl.getFichLido();
        vendasErradas = ufl.getVendasErradas();
        totProdutos = ufl.getTotProdutos();
        difProdutos = ufl.getDifProdutos();
        nclientes = ufl.getNClientes();
        cliDifs = ufl.getCliDifs();
        comprasZero = ufl.getComprasZero();
        fatTotal = ufl.getFatTotal();
    }
    
    /**
     * Devolve o nome do ficheiro lido
     * @return
     */
    public String getFichLido(){
        return fichLido;
    }
    
    /**
     * Devolve o numero de vendas erradas
     * @return
     */
    public int getVendasErradas(){
        return vendasErradas;
    }
    
    /**
     * Devolve o numero total de produtos
     * @return
     */
    public int getTotProdutos(){
        return totProdutos;
    }
    
    /**
     * Devolve um Set com os diferentes produtos que foram comprados
     * @return
     */
    public Set<String> getDifProdutos(){
        Set <String> copia = new HashSet<>();
        for(String str:difProdutos) {
            copia.add(str);
        }
        return copia;
    }
    
    /**
     * Devolve um Set com os diferentes clientes que compraram
     * @return
     */
    public Set<String> getCliDifs() {
        Set <String> copia = new HashSet<>();
        for(String str:cliDifs) {
            copia.add(str);
        }
        return copia;
    }
    
    /**
     * Devolve o numero de clientes
     * @return
     */
    public int getNClientes(){
        return nclientes;
    }
    
    /**
     * Devolve o numero de compras a zero
     * @return
     */
    public int getComprasZero(){
        return comprasZero;
    }
    
    /**
     * Devolve a Faturacao Total
     * @return
     */
    public double getFatTotal(){
        return fatTotal;
    }
    
    /**
     * Altera o nome do ficheiro lido
     * @param fl
     */
    public void setFichLido(String fl){
        if(fichLido == null) fichLido = new String();
            fichLido = fl;
    }
    
    /**
     * Incrementa numero de clientes
     */
    public void incNClientes(){
        nclientes++;
    }
    
    /**
     * Incrementa o numero de vendas erradas
     */
    public void incVendasErradas(){
        vendasErradas++;
    }
    
    /**
     * Adiciona a faturacao total
     * @param ft
     */
    public void setFatTotal(double ft){
        fatTotal+=ft;
    }
    
    /**
     * Incrementa numero de compras a 0
     */
    public void incComprasZero(){
        comprasZero++;
    }
    
    /**
     * Incrementa numero de produtos
     */
    public void incNProdutos(){
        totProdutos++;
    }
    
    /**
     * Adiciona um produto
     * @param codProd
     */
    public void addProduto(String codProd) {
        difProdutos.add(codProd);
    }
    
    /**
     * Adiciona um cliente
     * @param codcli
     */
    public void addCliente(String codCli) {
        cliDifs.add(codCli);
    }
    
    /**
     * Verifca se um objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(this == o) return true;
        
        if(o==null || this.getClass()!=o.getClass()) return false;
        
        UltimoFichLido ufl = (UltimoFichLido) o;
        
        return(fichLido.equals(ufl) && vendasErradas == ufl.getVendasErradas() && totProdutos == ufl.getTotProdutos() 
                    && difProdutos.retainAll(ufl.getDifProdutos()) && nclientes == ufl.getNClientes() && comprasZero == ufl.getComprasZero()
                        && cliDifs.retainAll(ufl.getCliDifs()) && fatTotal == ufl.getFatTotal());
    }
    
    /**
     * Cria uma copia da instancia
     * @return
     */
    public UltimoFichLido clone(){
        return new UltimoFichLido(this);
    }
    
    /**
     * Cria uma string com a informacao da instancia
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do ficheiro: ").append(fichLido); 
        sb.append("\n");
        sb.append("Nº de vendas erradas: ").append(vendasErradas); 
        sb.append("\n");
        sb.append("Total de Produtos: ").append(totProdutos); 
        sb.append("\n");
        sb.append("Nº de Produtos diferentes comprados: ").append(difProdutos.size()); 
        sb.append("\n");
        sb.append("Nº de Produtos não comprados: ").append(totProdutos - difProdutos.size());
        sb.append("\n");
        sb.append("Nº total de clientes: ").append(nclientes); 
        sb.append("\n");
        sb.append("Nº total de clientes que compraram: ").append(cliDifs.size()); 
        sb.append("\n");
        sb.append("Nº total de clientes que não compraram: ").append(nclientes - cliDifs.size()); 
        sb.append("\n");
        sb.append("N. de compras a preço zero: ").append(comprasZero); 
        sb.append("\n");
        sb.append("Faturação total: ").append(fatTotal);
        sb.append("\n");
        
        return sb.toString();
    }

    /**
     * hashCode
     * @return
     */
    public int hashCode(){return fichLido.hashCode();}
}
