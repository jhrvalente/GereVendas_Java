
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ListaStrings implements Serializable
{
    /**
     * Variaveis da instancia
     */
    private int page, lastPage, pageSize, size;
    private ArrayList<String> lista;
    
    /**
     * Construtor por defeito
     * @return
     */
    public ListaStrings(){
        size = 0;
        page = 1;
        lastPage = 0;
        pageSize = 10;
        lista = new ArrayList<>();
    }
    public ListaStrings(ListaStrings ls){
        size = ls.size();
        page = ls.getPage();
        lastPage = ls.getLastPage();
        pageSize = ls.getPageSize();
        lista = ls.getLista();
    }
    
    /**
     * Devolve o tamanho da lista
     * @return
     */
    public int size(){return size;}
    /**
     * Devolve o numero da pagina actual
     * @return
     */
    public int getPage(){return page;}
    /**
     * Devolve o tamanho da pagina
     * @return
     */
    public int getPageSize(){return pageSize;}
    /**
     * Devolve o numero da ultima pagina
     * @return
     */
    public int getLastPage(){return lastPage;}
    /**
     * Devolve a lista de strings
     * @return
     */
    public ArrayList<String> getLista(){
        ArrayList<String> al = new ArrayList<>();
        for(String s:lista) al.add(s);
        return al;
    }
    /**
     * Altera o tamanho das paginas
     * @param s
     */
    public void setPageSize(int s){pageSize = s;}
    
    /**
     * Adiciona uma nova String a lista
     * @param o
     */
    public void add(String s){
        lista.add(s);
        size++;
    }
    
    /**
     * Actualiza os indices das paginas. Deve ser executada no fim de todas as insercoes
     */
    public void actualizaIndices(){
        lastPage = (size/pageSize) + 1;
        if(size - (lastPage-1)*pageSize == 0) lastPage--;
    }
    
    /**
     * Devolve a proxima pagina
     * @return
     */
    public Pagina nextPage(){
        if(page < lastPage) page++;
        int i, s = 0;
        if(page == lastPage) s = size - (page-1)*pageSize; 
        else s = pageSize;
        Pagina p = new Pagina(s);
        for(i = 0; i < s; i++){
            p.add(lista.get((page-1)*pageSize+i));
        }
        return p;
    }
    
    /**
     * Devolve a pagina anterior
     * @return
     */
    public Pagina prevPage(){
        if(page > 1) page--;
        int i, s = 0;
        if(size < pageSize) s = size; 
        else s = pageSize;
        Pagina p = new Pagina(s);
        for(i = 0; i < s; i++){
            p.add(lista.get((page-1)*pageSize+i));
        }
        return p;
    }
    
    /**
     * Devolve a primeira pagina
     * @return
     */
    public Pagina firstPage(){
        page = 1;
        int i, s = 0;
        if(size < pageSize) s = size; 
        else s = pageSize;
        Pagina p = new Pagina(s);
        for(i = 0; i < s; i++){
            p.add(lista.get((page-1)*pageSize+i));
        }
        return p;
    }
    
    /**
     * Devolve a ultima pagina
     * @return
     */
    public Pagina lastPage(){
        page = lastPage;
        int i, s = size - (page-1)*pageSize;
        Pagina p = new Pagina(s);
        for(i = 0; i < s; i++){
            p.add(lista.get((page-1)*pageSize+i));
        }
        return p;
    } 
    
    /**
     * Cria uma copia da instancia
     * @return 
     */
    public ListaStrings clone(){
        return new ListaStrings(this);
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
     * Verifica se um objecto e igual a instancia
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        ListaStrings ls = (ListaStrings) o;
        if(size != ls.size()) return false;
        if(page != ls.getPage()) return false;
        if(pageSize != ls.getPageSize()) return false;
        if(lastPage != ls.getLastPage()) return false;
        ArrayList<String> al = (ArrayList<String>) ls.getLista();
        boolean b = true;
        int i;
        for(i = 0; i < size && b;i++){
            b = lista.get(i).equals(al.get(i));
        }
        return b;
    }
    
    /**
     * hashCode
     * @return 
     */
    public int hashCode(){
        return Arrays.hashCode(new Object[]{lista});
    }
}
