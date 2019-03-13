import java.io.BufferedReader;
import java.io.FileReader;

public class Arquivo{

    private int qtdTotalLinhas = 0, qtdLinhas = 0, qtdPrimeiraColuna = 0, qtdColunas = 0;
    private String caminho;
    private BufferedReader ArquivoGerado;
    private boolean entrada, saida;

    public void setCaminho(String caminho) throws Exception
    {
        if(caminho == null)
            throw new Exception("Erro!");

        this.caminho = caminho;
    }

    
    public void setQtdTotalLinhas(int qtdTotalLinhas){
        this.qtdTotalLinhas = qtdTotalLinhas;
    }

    public void setQtdLinhas(int qtdLinhas){
        this.qtdLinhas += qtdLinhas ;
    }

    public void setQtdPrimeiraColuna(int qtdPrimeiraColuna){
        this.qtdPrimeiraColuna = qtdPrimeiraColuna;
    }

    public String getCaminho(){
        return this.caminho;
    }
    
    public int getQtdTotalLinhas(){
        return this.qtdTotalLinhas;
    }

    public int getQtdLinhas(){
        return this.qtdLinhas;
    }

    public int getQtdColunas(){
        return this.qtdColunas;
    }

    public int getQtdPrimeiraColuna(){
        return this.qtdPrimeiraColuna;
    }

    public boolean getEntrada(){
        return this.entrada;
    }

    public boolean getSaida(){
        return this.saida;
    }

    public boolean isValidCriarArquivo(){
        if(this.ArquivoGerado != null)
            return false;

        return true;
    }

    public BufferedReader lerArquivo() throws Exception
    {   
        this.ArquivoGerado = new BufferedReader(new FileReader(caminho));

        if(this.ArquivoGerado == null)
            throw new Exception("Erro!");
        
        return this.ArquivoGerado;
    }

    public void carregarArquivo(BufferedReader arquivo) throws Exception
    {
        String str;
        int count = 2;
        int countLinhas = 1;
        this.setQtdTotalLinhas(Integer.parseInt(arquivo.readLine()));

        this.setQtdPrimeiraColuna(arquivo.readLine().length());
        this.setQtdLinhas(1);

        while((str = arquivo.readLine()) != null){
            if(isValidColunas(str.length()) && str.contains("#"))
                this.qtdColunas = str.length();
            else
                throw new Exception("Arquivo invalido! " + count + " linha do labirinto com coluna(s) invalida(s)!");
            
            if(str.contains("E") || str.contains("e"))
                this.entrada = true;
            
            if(str.contains("S") || str.contains("s"))
                this.saida = true;    
            
            this.setQtdLinhas(1);    
            count ++;
          }   
          
        if(!this.isValidLinhas())
            throw new Exception("Arquivo invalido! Linhas do labirinto nao coincidem com o total.");
        arquivo.close();
    }

    public boolean isValidColunas(int tamanhoColuna){
        if(tamanhoColuna == this.qtdPrimeiraColuna)
            return true;
        
        return false;
    }

    public boolean isValidLabirinto(){
        if(entrada == true && saida == true)
            return true;
        
        return false;
    }

    public boolean isValidLinhas(){
        if(this.qtdTotalLinhas == this.qtdLinhas)
            return true;
        
        return false;
    }
}