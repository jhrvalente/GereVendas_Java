import java.io.Serializable;

public class ParCliNumProd implements Serializable, Comparable<ParCliNumProd>{
    /**
     * Variaveis da instancia
     */
    private String cli;
    private int numProd;
    
    /**
     * Construtor parametrizado
     * @param s
     * @param i
     * @return
     */
    public ParCliNumProd(String s, int i){
        cli = s;
        numProd = i;
    }
    /**
     * Construtor por copia
     * @param p
     * @return
     */
    public ParCliNumProd(ParCliNumProd p){
        cli = p.getCliente();
        numProd = p.getNumProd();
    }
    
    /**
     * Devolve o numero de produtos
     * @return 
     */
    public int getNumProd(){return numProd;}
    /**
     * Devolve o codigo do cliente
     * @return
     */
    public String getCliente(){return cli;}
    
    /**
     * Compara um objecto ParCliNumProd com a instancia
     * @param p
     * @return
     */
    public int compareTo(ParCliNumProd p){
        if(numProd > p.getNumProd()) return -1;
        if(numProd < p.getNumProd()) return 1;
        else return cli.compareTo(p.getCliente());
    }
    
    /**
     * Cria uma string com a informacao da instancia
     * @return
     */
    public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append(cli).append("\n");
       sb.append("Quantos: ").append(numProd).append("\n");
       return sb.toString();
    }
    
    /**
     * Cria uma copia da instancia
     * @return
     */
    public ParCliNumProd clone(){
        return new ParCliNumProd(this);
    }
    
    /**
     * hashCode
     * @return
     */
    public int hashCode(){return cli.hashCode();}
    
    /**
     * Verifica se um objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        ParCliNumProd p = (ParCliNumProd) o;
        return cli.equals(p.getCliente()) && numProd == p.getNumProd();
    }
}