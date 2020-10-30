
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Pagina implements Serializable
{
    /**
     * Variaveis da instancia
     */
    private int size, index;
    private ArrayList<String> lista;
    
    /**
     * Construtor parametrizado
     * @param s
     * @return
     */
    public Pagina(int s){
        size = s;
        index = 0;
        lista = new ArrayList<>();
    }
    /**
     * Construtor por copia
     * @param p
     * @return
     */
    public Pagina(Pagina p){
        size = p.size();
        index = p.currentIndex();
        lista = p.getLista();
    }
    /**
     * Altera o indice da proxima String a ser lida
     * @param i
     */
    public void setIndex(int i){
        if(i >= 0 && i < size) index = i;
    }
    /**
     * Devolve o tamanho da lista
     * @return
     */
    public int size(){return size;}
    /**
     * Devolve o indice actual
     * @return
     */
    public int currentIndex(){return index;}
    /**
     * Devolve a lista de Strings
     * @return
     */
    public ArrayList<String> getLista(){
        ArrayList<String> aux = new ArrayList<>();
        for(String s:lista){
            aux.add(s);
        }
        return aux;
    }
    
    /**
     * Adiciona uma String a lista
     * @param s
     */
    public void add(String s){
        lista.add(s);
    }
    
    /**
     * Devolve a proxima String
     * @return
     */
    public String nextString(){
        if(index < size){
            String s = lista.get(index); 
            index++;
            return s;
        }
        else return null;
    }
    
    /**
     * Devolve uma copia da lista
     * @return
     */
    public Pagina clone(){
        return new Pagina(this);
    }
    
    /**
     * Verifica se um objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Pagina p = (Pagina) o;
        if(size != p.size()) return false;
        if(index != p.currentIndex()) return false;
        boolean b = true;
        int i;
        ArrayList<String> al = p.getLista();
        for(i = 0; i < size && b; i++){
            lista.get(i).equals(al.get(i));
        }
        return b;
    }
    
    /**
     * Cria uma String com a informacao da instancia
     * @return 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(String s:lista) sb.append(s).append("\n");
        return sb.toString();
    }
    
    /**
     * hashCode
     * @return 
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{lista});
    }
}
