import java.io.Serializable;

public class ParCliGasto implements Serializable, Comparable<ParCliGasto> {
    /**
     * Variaveis de instancia
     */
    private String cli;
    private double gasto;
    private int quantidade;

    /**
     * Construtor parametrizado
     * @param s
     * @param i
     * @return
     */
    public ParCliGasto(String s, double i,int qt){
        cli = s;
        gasto = i;
        quantidade = qt;
    }
    /**
     * Construtor por copia
     * @param pg
     * @return
     */
    public ParCliGasto(ParCliGasto pg){
        cli = pg.getCliente();
        gasto = pg.getGasto();
    }
    
    /**
     * Devolve quanto foi gasto
     * @return
     */
    public double getGasto(){return gasto;}
    /**
     * Devolve o codigo do cliente
     * @return
     */
    public String getCliente(){return cli;}
    /**
     * Devolve a quantidade que foi comprada
     * @return
     */
    public int getQuantidade(){return quantidade;}
    
    /**
     * Alterada quanto foi gasto
     * @param g
     */
    public void setGasto(double g){gasto=g;}
    /**
     * Alterada o codigo do cliente
     * @param c
     */
    public void setCliente(String c){cli=c;}
    /**
     * Alterada a quantidade comprada
     * @param qt
     */
    public void setQt(int qt){quantidade=qt;}
    
    /**
     * Compara um objecto ParCliGasto com a instancia
     * @param p
     * @return
     */
    public int compareTo(ParCliGasto p){
        if(quantidade > p.getQuantidade()) return -1;
        if(quantidade < p.getQuantidade()) return 1;
        else return cli.compareTo(p.getCliente());
    }

    /**
     * Cria uma String com a informacao da instancia
     * @return
     */
    public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Cliente: ").append(cli).append("\n");
       sb.append("Quantidade: ").append(quantidade).append("\n");
       sb.append("Total Gasto: ").append(gasto).append("\n");
       return sb.toString();
    }
    
    /**
     * Cria uma copia da instancia
     * @return
     */
    public ParCliGasto clone(){
        return new ParCliGasto(this);
    }
    
    /**
     * Verifica se um objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        ParCliGasto p = (ParCliGasto) o;
        return cli.equals(p.getCliente()) && gasto == p.getGasto() && quantidade == p.getQuantidade();
    }
    
    /**
     * hashcode
     * @return
     */
    public int hashCode(){return cli.hashCode();}
}