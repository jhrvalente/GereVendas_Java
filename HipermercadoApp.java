import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;


public class HipermercadoApp implements Serializable {
     
    
    private static boolean fichLidos;
    private static Hipermercado hip;
    private static UltimoFichLido ult;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        hip = new Hipermercado();
        fichLidos = false;
        ult = new UltimoFichLido();
        
        Menu mp, mci, mce;
        int opcao, op1, op2;
        HipermercadoApp hipApp = new HipermercadoApp();
        
        mp = hipApp.setMenuPrincipal();
        mci = hipApp.setMenuConsInt();
        mce = hipApp.setMenuConsEst();
        
        opcao = -1;
        boolean terminado = false, dados = false; 
        
        while(!terminado) {
        mp.executa();
        opcao = mp.getOpcao();
        
        switch(opcao) {
        case 1: hipApp.lerFicheiros();
                break;
        case 2: if(fichLidos || dados){
                    hipApp.setHeader("Consultas Estatisticas");
                    mce.executa();
                    op1 = mce.getOpcao();
                    switch(op1) {
                        case 1: if(!fichLidos) {
                                    System.out.println("Por favor carregue os ficheiros de Clientes, Produtos e Vendas!");
                                    hipApp.lerFicheiros();
                                }
                                hipApp.setHeader("Estatísticas");
                                System.out.println(ult.toString());
                                break;
                        case 2: hipApp.estatisticaGeral(hip);
                                break;
                        default: System.out.println("Opcao Invalida!");
                    }
                }
                else{
                    System.out.println("Dados nao foram carregados!");
                }
                break;
                
        case 3: if(fichLidos || dados){
                    hipApp.setHeader("Consultas Interactivas");
                    mci.executa();
                    op2 = mci.getOpcao();
                    switch(op2) {
                        case 1: hipApp.prodsNaoComprados(hip);
                                break;
                        case 2: hipApp.nVendasMes(hip);
                                break;
                        case 3: hipApp.compMes(hip);
                                break;
                        case 4: hipApp.prodPorMes(hip);
                                break;
                        case 5: hipApp.maisComprasdoCliente(hip);
                                break;
                        case 6: hipApp.nMaisComprados(hip);
                                break;
                        case 7: hipApp.listaMaisCompFilial(hip);
                                break;
                        case 8: hipApp.xClientesMaisComp(hip);
                                break;
                        case 9: hipApp.xCliMaisCompraramProd(hip);
                                break;
                    }
                }
                else{
                    System.out.println("Dados nao foram carregados!");
                }
                break;
        case 4: try{
                    hipApp.gravaEstado(hip);
                }
                catch(IOException e){
                    System.out.println("Nao foi possivel guardar o estado!");
                }
                break;
        case 5: try{
                    hip = hipApp.carregarDados();
                    dados = true;
                }
                catch(IOException e){
                    System.out.println("Nao foi possivel guardar o estado!");
                }
                catch(ClassNotFoundException e){
                    System.out.println("Nao foi possivel guardar o estado!");
                }
                break;
        case 0: terminado = true;
                System.exit(0);
                break;
        default: System.out.println("Opcao Invalida!");
        }
      }
       
    }
    /**
     * Apresentar respostas no ecra
     */
    private void setHeader(String str) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("                             " + str + "                                  ");
        System.out.println("--------------------------------------------------------------------------");
    }
    
    /**
     * Menu carregar ficheiros
     */
    private Menu setMenuPrincipal(){
        String[] op = { "--------------------------------------------------------------------------",
                        "                             GereVendas v1.0                              ",
                        "--------------------------------------------------------------------------",
                        "**************************************************************************",
                        "*      Seja bem-vindo a nossa aplicacao.                                 *",
                        "*      Seleccione uma das opcoes seguintes.                              *",
                        "**************************************************************************",
                        "*          1. Leitura de Ficheiros                                       *",
                        "*          2. Consultas Estatisticas                                     *",
                        "*          3. Consultas Interactivas                                     *",
                        "*          4. Guardar o Estado Actual                                    *",
                        "*          5. Carregar um Estado previamente Guardado                    *",
                        "*          0. Sair da aplicacao                                          *", 
                        "**************************************************************************"};
        
        Menu m=new Menu(op);
        return m;
    }
    
    /**
     * Constrói menu de consultas estatisticas
     */
    private Menu setMenuConsEst() {
         String[] op = {"           1. Obter estatisticas do Ultimo ficheiro de Vendas lido        ",           
                        "           2. Obter estatisticas Gerais                                   ",
                        "--------------------------------------------------------------------------"}; 
                        
       Menu m=new Menu(op);
       return m;
    }
    /**
     * Construir menu de consultas interativas
     */
    private Menu setMenuConsInt() {
         String[] op = {"           1. Obter lista ordenada alfabeticamente dos produtos que       ",                   
                        "              nao foram comprados                                         ",                
                        "--------------------------------------------------------------------------",
                        "           2. Obter o numero total global de vendas e o numero total      ",
                        "              de clientes distintos que as realizaram para um dado mes    ",
                        "--------------------------------------------------------------------------",
                        "           3. Para um dado cliente, obter o numero de compras,            ",
                        "              produtos comprados e o total gasto, em cada mes             ", 
                        "--------------------------------------------------------------------------",
                        "           4. Dado um Produto, obter o numero de vezes que foi comprado   ",
                        "              e o total facturado                                         " ,
                        "--------------------------------------------------------------------------",
                        "           5. Obter os produtos mais comprados por um dado cliente        ",
                        "--------------------------------------------------------------------------",
                        "           6. Obter o X produtos mais comprados durante todo o ano        ",
                        "--------------------------------------------------------------------------", 
                        "           7. Listar os 3 maiores compradores para cada filial            ",
                        "--------------------------------------------------------------------------",
                        "           8. Obter os X clientes que compraram mais produtos diferentes  ",
                        "--------------------------------------------------------------------------",
                        "           9. Determinar o conjunto dos X clientes que mais compraram     ",
                        "              um determinado produto                                      ",
                        "--------------------------------------------------------------------------"}; 
                        
        Menu m=new Menu(op);
        return m;
    }
    /**
     * Construir menu de leituras
     */
    private Menu setMenuLeituras(){
        String[] op = { "                                                                          ",
                        "       1. Ficheiros por defeito(Produtos.txt, Clientes.txt, Vendas_1M.txt)",
                        "       2. Ficheiros inseridos pelo utilizador                             ",
                        "                                                                          ",
                        "--------------------------------------------------------------------------"};
                        
       Menu m=new Menu(op);
       return m;
            
    }
    
    
    /**
     * Função que grava o estado da app
     * @param hip
     * @throws IOException 
     */
    private void gravaEstado(Hipermercado hip) throws IOException { 
        try {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hipermercado.dat")); 
        oos.writeObject(hip); 
        oos.flush(); 
        oos.close();
        System.out.println("Estado gravado com sucesso!");
        } catch(Exception e){
          e.printStackTrace();
        }
    }
    
    /**
     * Função que lê um estado
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private Hipermercado carregarDados()  throws IOException, ClassNotFoundException {
        Hipermercado hip;
            
        try{ 
              ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hipermercado.dat"));
      
              hip= (Hipermercado) ois.readObject();
                
              ois.close();
              System.out.println("Estado lido com sucesso!");
        } 
        catch(FileNotFoundException e){
              e.printStackTrace();
              hip = new Hipermercado();
        }
        return hip;
    }
    /**
     * Escolha dos ficheiros para leituta
     */
    private void lerFicheiros() {
        
        setHeader("Leitura de Ficheiros");
    
        
        String fichClientes, fichProdutos, fichVendas;
        fichClientes = fichProdutos = fichVendas = "";
        
        Menu ml = setMenuLeituras();
        ml.executa();
        int opcao = ml.getOpcao();
        
        switch(opcao) {
            case 1: fichClientes = "Clientes.txt";
                    fichProdutos = "Produtos.txt"; 
                    fichVendas = "Vendas_1M.txt";
                    break;
            case 2: System.out.println("Indique o ficheiro de clientes a ler: ");
                    fichClientes = Input.lerString();
                    System.out.println("Indique o ficheiro de produtos a ler: ");
                    fichProdutos = Input.lerString();
                    System.out.println("Indique o ficheiro de vendas a ler: ");
                    fichVendas = Input.lerString();
                    break;
            default: System.out.println("Opcao Invalida!");
        }
        
        Crono.start();
        carregaFicheiros(hip, fichProdutos, fichClientes, fichVendas);
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print()); 
        
        fichLidos = true;
        ult.setFichLido(fichVendas);
    }
    
    /**
     * Carregar ficheiros 
     * @param hip
     * @param fichProds
     * @param fichClientes
     * @param fichVendas
     */
    private void carregaFicheiros(Hipermercado hip, String fichProds,String fichClientes,String fichVendas){
        BufferedReader inStream = null; 
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(fichProds));
            while( (linha = inStream.readLine()) != null ){
                             hip.insereProduto(linha);
                             ult.incNProdutos();
            }
            
            inStream = new BufferedReader(new FileReader(fichClientes));
            while( (linha = inStream.readLine()) != null ){
                             hip.insereCliente(linha);
                             ult.incNClientes();
            }
                             
                             
            inStream = new BufferedReader(new FileReader(fichVendas));
            while( (linha = inStream.readLine()) != null ){
                    Venda v = parseLinhaVenda(hip, linha);
                    if(hip.valida_Venda(v) == true) {
                        hip.insereFilial(v.clone());
                        hip.insereFaturacao(v.clone());
                        ult.setFatTotal(v.getPreco()*v.getQuantidade());
                        if(v.getPreco() == 0) ult.incComprasZero();
                        ult.addProduto(v.getCodigoProduto());
                        ult.addCliente(v.getCodigoCliente());
                    }
                    else ult.incVendasErradas();
            }
        
            if(hip.getCatClientes().equals(hip.getCatClientes().getCClientes().clone())) System.out.println("IGUAL");
        
        
        }
        catch(IOException e) 
           { System.out.println(e.getMessage()); return ; }; 
    }  
    /**
     * Parse e validacao da linha
     * @param linha
     * @return
     */
    private Venda parseLinhaVenda(Hipermercado hip, String linha){
        String[] campos=linha.split(" ");
        int vErradas = 0, pZero = 0;
        double preco;
        int mes, filial, quantidade;
        try{
            preco=Double.parseDouble(campos[1]);
           }
        catch(NullPointerException | NumberFormatException exc){
           return null;
           }
        try{
            mes=Integer.parseInt(campos[5]);
           }
        catch(NullPointerException | NumberFormatException exc){
           return null;
           }
        try{
            quantidade=Integer.parseInt(campos[2]);
           }
        catch(NullPointerException | NumberFormatException exc){
           return null;
           }
        try{
            filial=Integer.parseInt(campos[6]);
           }
        catch(NullPointerException | NumberFormatException exc){
           return null;
           }
        return new Venda(campos[0], preco, quantidade, campos[3], campos[4], mes, filial);
        
    }
    /**
     * Obter estatisticas geral
     */
    private void estatisticaGeral(Hipermercado hip){
        Crono.start();
        int mes;
        HashMap<Integer,Integer> compMes =new HashMap<>(12,1);
        compMes = hip.clientesDistMes();
        ListaStrings ls = new ListaStrings();
        for(mes = 1; mes < 13; mes++){
           StringBuilder sb = new StringBuilder();
           sb.append("--Mes ").append(mes).append("--\n");
           double f1 = hip.faturacaoMensalFilial(mes,1), f2 = hip.faturacaoMensalFilial(mes,2), f3 = hip.faturacaoMensalFilial(mes,3);
           sb.append("Numero de Vendas: ").append(hip.numeroVendasMes(mes)).append("\n");
           sb.append("Numero de Clientes: ").append(compMes.get(mes)).append("\n");
           sb.append("Faturacao Filial 1: ").append(f1).append("\n");
           sb.append("Faturacao Filial 2: ").append(f2).append("\n");
           sb.append("Faturacao Filial 3: ").append(f3).append("\n");
           sb.append("Total: ").append(f1+f2+f3).append("\n");
           ls.add(sb.toString());
        }
        ls.setPageSize(4);
        ls.actualizaIndices();
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        printLista(ls);
    }
    /**
     * Obter produtos por mes
     */
    private void prodPorMes(Hipermercado hip){
        System.out.println("Insira um produto:");
        String p = Input.lerString();
        Crono.start();
        String s = hip.prodMensal(p).toString();
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        System.out.println(s);
    }
    /**
     * Obter produtos por nao comprados
     */
    private void prodsNaoComprados(Hipermercado hip){
        Crono.start();
        ListaStrings ls = hip.naoComprados();
        StringBuilder sb = new StringBuilder();
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        sb.append("Total: ").append(ls.size());
        System.out.println(sb.toString());
        printLista(ls);
    }
    /**
     * Obter lista de produtos que mais comprou
     */
    private void nVendasMes(Hipermercado hip){
        System.out.println("Insira um mes:");
        int mes = Input.lerInt();
        if(mes<1||mes>12) {
             System.out.println("Mes inválido!");
             return ;
        } 
        Crono.start();
        HashMap<Integer,Integer> compMes =new HashMap<>(12,1);
        compMes = hip.clientesDistMes();
        StringBuilder sb = new StringBuilder();
        sb.append("--Mes ").append(mes).append("--\n");
        sb.append("Numero de Vendas: ").append(hip.numeroVendasMes(mes)).append("\n");
        sb.append("Numero de Clientes: ").append(compMes.get(mes)).append("\n");
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        System.out.println(sb.toString());
    }
    /**
     * Obter x produtos mais vendidos no ano
     */
    private void nMaisComprados(Hipermercado hip){
        System.out.println("Quando produtos deseja visualizar");
        int n = Input.lerInt(), i = 0;
        Crono.start();
        ListaStrings ls = hip.maisComprados(n);
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        printLista(ls);
    }
    /**
     * Obter nome de quem fez mais compras por filial 
     */
    private void listaMaisCompFilial(Hipermercado hip){
        Crono.start();
        List<String> l = hip.tresMaisCompradoresFilial();
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        Iterator<String> it = l.iterator();
        int i=0;
        System.out.println("--Filial 1:-- ");
        while(it.hasNext() && i!=3){
        System.out.println(l.get(i));
        i++;
        }
        System.out.println("--Filial 2:-- ");
        while(it.hasNext() && i!=6){
        System.out.println(l.get(i));
        i++;
        }
        System.out.println("--Filial 3:-- ");
        while(it.hasNext() && i!=9){
        System.out.println(l.get(i));
        i++;
        }
    }
    /**
     * Obter os x clientes que mais compraram
     */
    private void xClientesMaisComp (Hipermercado hip){
        System.out.println("Indique o numero de clientes ");
        int n = Input.lerInt();
        Crono.start();
        ListaStrings ls = hip.xMaisCompraram(n);
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        printLista(ls);
    }
    /**
     * Obter os x que mais compraram o produto
     */
    private void xCliMaisCompraramProd(Hipermercado hip){
        System.out.println("Indique o produto ");
        String prod = Input.lerString();
        if(hip.getCatProdutos().existe(prod)==false) {
             System.out.println("Produto Inexistente!");
             return ;
         }
        System.out.println("Indique o numero de clientes ");
        int n = Input.lerInt();
        Crono.start();
        ListaStrings ls = hip.prodMaisComp(prod,n);
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        printLista(ls);
    }
    /**
     * Obter as maiores compras de um cliente
     */
    private void maisComprasdoCliente(Hipermercado hip){
        System.out.println("Indique um cliente?");
        String cliente = Input.lerString();
        if(hip.getCatClientes().existe(cliente)==false) {
             System.out.println("Cliente Inexistente!");
             return ;
         }
        Crono.start();
        ListaStrings ls = hip.maiorQuantidade(cliente);
        Crono.stop();
        System.out.println("Tempo decorrido: "+ Crono.print());
        printLista(ls);
    }
    /**
     * Obter estatisticas mensais de um cliente
     */
    private void compMes(Hipermercado hip){
         System.out.println("Indique um cliente ");
         String cliente = Input.lerString();
         if(hip.getCatClientes().existe(cliente)==false) {
             System.out.println("Cliente Inexistente!");
             return ;
         }
         Crono.start();
         HashMap<Integer,Integer> compMes = new HashMap<>(12,1);
         compMes=hip.comprasPorMes(cliente);
         int prodsDistintos=hip.produtosDistintos(cliente);
         int totGasto = hip.gastoTotal(cliente);
         Crono.stop();
         System.out.println("Tempo decorrido: "+ Crono.print());
         for(Map.Entry<Integer,Integer> m: compMes.entrySet())
             System.out.println("Mes "+m.getKey()+"\tNum. de Compras "+m.getValue());
         System.out.println(" ");
         System.out.println("Produtos distintos = "+prodsDistintos);
         System.out.println("Total gasto = "+totGasto);
    }
    /**
     * Imprimir lista de strings, com navegação
     */
    private void printLista(ListaStrings ls){
        String s = "first";
        Pagina p = null;
        while(!(s.equals("quit"))){
            if(s.equals("first")) p = ls.firstPage();
            if(s.equals("last")) p = ls.lastPage();
            if(s.equals("next")) p = ls.nextPage();
            if(s.equals("prev")) p = ls.prevPage();
            int tam = p.size(), i;
            for(i = 0; i < tam; i++){
                System.out.println(p.nextString());
            }
            System.out.println("next -- Proxima Pagina; prev -- Pagina Anterior;");
            System.out.println("first -- Primeira Pagina; last -- Ultima Pagina;");
            System.out.println("quit -- Regressar ao Menu");
            s = Input.lerString();
            p.setIndex(0);
        }
    }
}
