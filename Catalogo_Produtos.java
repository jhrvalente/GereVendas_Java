import java.util.Arrays;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Catalogo_Produtos implements Serializable {
	
	/**
	 * Catálogo de Produtos
	 */
	private HashSet<String> catProdutos;
	/**
	 * Construtor vazio
	 */
	public Catalogo_Produtos(){
		catProdutos = new HashSet<>();
	}
	/**
	 * Construtor por cópia
	 * @param c
	 */
	public Catalogo_Produtos(Catalogo_Produtos c){
		catProdutos = c.getCatProdutos();
	}
	/**
	 * Obter catálogo de produtos
	 * @return
	 */
	public HashSet<String> getCatProdutos(){
		     HashSet<String> copia = new HashSet<>();
             catProdutos.stream().forEach(x->copia.add(x));
             return copia;
	}
	/**
	 * Substituir catálogo de produtos
	 * @param catalogo
	 */
	public void setCatProdutos(HashSet<String> catalogo){
		    catalogo.stream().forEach(x->catProdutos.add(x));
	}
	/**
	 * Adicionar produto ao catálogo
	 * @param prod
	 */
	public void addCatProdutos(String prod){
            if(catProdutos==null) catProdutos = new HashSet<>();
		catProdutos.add(prod);
	}
	
	public boolean existe(String s){
	   return catProdutos.contains(s);
	   }
	public int size(){return catProdutos.size();}
	
	/**
	 * Equals
	 */
	public boolean equals(Object o){
		if(this == o) return true;
		if(this == null || this.getClass() != o.getClass()) return false;
		Catalogo_Produtos c = (Catalogo_Produtos) o;
		return catProdutos.retainAll(c.getCatProdutos());
	}
	/**
	 * Clone
	 */
	public Catalogo_Produtos clone(){
		return new Catalogo_Produtos(this);
	}	
	/**
	 * toString 
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = catProdutos.iterator();
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
		return Arrays.hashCode(new Object[]{catProdutos});
	}

	
	
}
