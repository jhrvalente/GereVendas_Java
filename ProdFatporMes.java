import java.util.Arrays;
import java.io.Serializable;

public class ProdFatporMes implements Serializable
{
    /**
     * Variaveis da instancia
     */
    private Faturacao[] pfm;
    
    /**
     * Construtor por defeito
     * @return
     */
    public ProdFatporMes(){
        pfm = null;
    }
    /**
     * Construtor por copia
     * @return
     */
    public ProdFatporMes(ProdFatporMes p){
        pfm = new Faturacao[12];
        int i;
        for(i = 0; i < 12; i++){
            pfm[i] = p.get(i).clone();
        }
    }
    
    /**
     * Adiciona uma nova Faturacao, de um dado mes, a instancia
     * @param f
     * @param m
     */
    public void add(Faturacao f, int m){
        if(pfm == null) pfm = new Faturacao[12];
        if(f == null) pfm[m-1] = null;
        if(f != null) pfm[m-1] = f.clone();
    }
    
    /**
     * Devolve a Faturacao de um dado mes
     * @param i
     * @return
     */
    public Faturacao get(int i){
        return pfm[i].clone();
    }
    
    /**
     * Cria uma copia da instancia
     * @return
     */
    public ProdFatporMes clone(){
        return new ProdFatporMes(this);
    }
    
    /**
     * Cria uma String com a informacao da instancia
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(pfm == null) return "O produto nao foi comprado";
        else{
            int i, count = 0;
            for(i = 0; i < 12; i++){
                if(pfm[i] != null){ 
                    sb.append("--Mes ").append(i+1).append(":--\n");
                    sb.append("Total Faturado: ").append(pfm[i].getReceitas()).append("\n");
                    sb.append("Número de Vendas: ").append(pfm[i].getNumeroVendas()).append("\n");
                    count++;
                }
            }
            if(count == 0) sb.append("O produto nao foi comprado");
            return sb.toString();
        }
    }
    
    /**
     * Verifica se um dado objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        ProdFatporMes p = (ProdFatporMes) o;
        boolean b = true;
        int i;
        for(i = 0; i < 12 && b; i++){
            b = pfm[i].equals(p.get(i));
        }
        return b;
    }
    
    /**
     * hashCode
     * @return 
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{pfm});
    }
}