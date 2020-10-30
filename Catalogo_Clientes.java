import java.util.Arrays;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Catalogo_Clientes implements Serializable {
    
    /**
     * Catálogo de clientes
     */
    private HashSet<String> catClientes;
    /**
     * Construtor vazio
     */
    public Catalogo_Clientes(){
        catClientes = new HashSet<>();
    }
    /**
     * Construtor por cópia
     * @param c
     */
    public Catalogo_Clientes(Catalogo_Clientes c){
        catClientes = c.getCClientes();
    }
    /**
     * Obter catálogo de clientes
     * @re  turn
     */
    public HashSet<String> getCClientes(){
            HashSet<String> copia = new HashSet<>();
                catClientes.stream().forEach(x->copia.add(x));
                return copia;
    }
    /**
     * Substituir catálogo de clientes
     * @param catalogo
     */
    public void setCatClientes(HashSet<String> catalogo){
            catalogo.stream().forEach(x->catClientes.add(x));
    }
    
    public void addCatClientes(String cliente){
            if(catClientes==null) catClientes = new HashSet<>();
        catClientes.add(cliente);
    }
    
    public boolean existe(String s){
	   return catClientes.contains(s);
	   }
    
    /**
     * Equals
     */
    public boolean equals(Object o){
        if(this == o) return true;
        if(this == null || this.getClass() != o.getClass()) return false;
        Catalogo_Clientes c = (Catalogo_Clientes) o;
        return catClientes.retainAll(c.getCClientes());
    }
    /**
     * Clone
     */
    public Catalogo_Clientes clone(){
        return new Catalogo_Clientes(this);
    }   
    /**
     * toString 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = catClientes.iterator();
        while(it.hasNext()){
            String str = (String) it.next();
            sb.append(str);
            sb.append("\n");
        }
        return sb.toString();
    }
    /**
     * hashCode 
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{catClientes});
    }

    
    
}
