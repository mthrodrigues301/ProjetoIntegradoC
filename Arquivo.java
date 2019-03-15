import java.io.BufferedReader;
import java.io.FileReader;

public class Arquivo{

    private int qtdTotalLinhas = 0, qtdLinhas = 0, qtdPrimeiraColuna = 0, qtdColunas = 0;
    private String caminho;
    private BufferedReader ArquivoGerado;
    private boolean entrada, saida;
    private String linhaAtual;

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
        this.setQtdTotalLinhas(Integer.parseInt(arquivo.readLine()));
        
        this.linhaAtual = arquivo.readLine();
        if(isValidLinhas() == true){
            this.setQtdPrimeiraColuna(this.linhaAtual.length());
            this.setQtdLinhas(1);
        }   
        
        while((this.linhaAtual = arquivo.readLine()) != null){
            if(this.isValidColunas(this.linhaAtual.length()) && this.isValidLinhas())
                this.qtdColunas = this.linhaAtual.length();
            else
                throw new Exception("Arquivo invalido! " + this.getQtdLinhas() + " linha do labirinto com coluna(s) invalida(s)!");
            
            if(this.linhaAtual.contains("E") || this.linhaAtual.contains("e"))
                this.entrada = true;
            
            if(this.linhaAtual.contains("S") || this.linhaAtual.contains("s"))
                this.saida = true;    
            
            this.setQtdLinhas(1);
          }   
          
        if(!this.isValidQtdLinhas())
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

    public boolean isValidQtdLinhas(){
        if(this.qtdTotalLinhas == this.qtdLinhas)
            return true;
        
        return false;
    }

    private boolean isValidLinhas(){
        for(int i = 0; i < this.linhaAtual.length(); i++){
            if(this.linhaAtual.charAt(i) == '#' || this.linhaAtual.charAt(i) == 'E' || this.linhaAtual.charAt(i) == 'S' || this.linhaAtual.charAt(i) == ' ')
                continue;    
            else
                return false;
        }

        return true;
    }
}