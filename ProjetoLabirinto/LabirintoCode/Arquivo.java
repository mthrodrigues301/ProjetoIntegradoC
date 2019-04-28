import java.io.BufferedReader;
import java.io.FileReader;

public class Arquivo{

    private int qtdTotalLinhas = 0, qtdLinhas = 0, qtdPrimeiraColuna = 0, qtdColunas = 0;
    private String caminho;
    private BufferedReader ArquivoGerado;
    private boolean entrada = false, saida = false;
    private int qtdEntrada = 0, qtdSaida = 0;
    private String linhaAtual;

    public void setCaminho(String caminho) throws Exception
    {
        if(caminho == null)
            throw new Exception("Erro!");

        this.caminho = caminho;
    }
    
    public void setQtdTotalLinhas(String linhas)throws Exception
    {
        try
        {
            this.qtdTotalLinhas = Integer.parseInt(linhas);
        }
        catch (NumberFormatException erro)
        {
        	throw new Exception("A quantidade de linhas do labirinto deve ser um valor inteiro!");
        }
    }

    public void setQtdLinhas(int qtdLinhas){
        this.qtdLinhas += qtdLinhas ;
    }
    
    public void setQtdEntrada(int qtdEntrada){
        this.qtdEntrada += qtdEntrada ;
    }
    
    public void setQtdSaida(int qtdSaida){
        this.qtdSaida += qtdSaida ;
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
    
    public int getQtdEntrada(){
        return this.qtdEntrada;
    }
    
    public int getQtdSaida(){
        return this.qtdSaida;
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

        if(this.isValidCriarArquivo())
            throw new Exception("Erro!");
        
        return this.ArquivoGerado;
    }

    public void carregarArquivo(BufferedReader arquivo) throws Exception
    {
        try{
            this.setQtdTotalLinhas(arquivo.readLine());
        
            this.linhaAtual = arquivo.readLine();
            if(isValidLinhas() == true){
            	this.setQtdPrimeiraColuna(this.linhaAtual.length());
            	this.setQtdLinhas(1);
            	this.verificaEntradaSaida(this.getQtdLinhas());
            	this.verificaBuracos(this.getQtdLinhas());
            }   
        
            while((this.linhaAtual = arquivo.readLine()) != null){
            	if(this.isValidColunas(this.linhaAtual.length()) && this.isValidLinhas()) {            		
            		this.qtdColunas = this.linhaAtual.length();
            	}
            	else
            		throw new Exception("Arquivo invalido! " + this.getQtdLinhas() + " linha do labirinto");
            	
            	this.setQtdLinhas(1);
            	this.verificaEntradaSaida(this.getQtdLinhas());
            	this.verificaBuracos(this.getQtdLinhas());
            }   
            
            arquivo.close();
        }
        catch(Exception ex) {
        	throw new Exception(ex.getMessage());
        }
    }

    private boolean isValidColunas(int tamanhoColuna){
        if(tamanhoColuna == this.qtdPrimeiraColuna)
            return true;
        
        return false;
    }

    private boolean isValidLinhas(){
        for(int i = 0; i < this.linhaAtual.length(); i++){
            if(this.linhaAtual.charAt(i) == '#' || this.linhaAtual.charAt(i) == 'E' 
            		|| this.linhaAtual.charAt(i) == 'S' || this.linhaAtual.charAt(i) == ' ')
                continue;    
            else
                return false;
        }

        return true;
    }    
    
    private void verificaEntradaSaida(int linha)throws Exception {    	
    	if(linha > this.qtdTotalLinhas)
    		throw new Exception("Arquivo invalido! Linhas do labirinto nao coincidem com o total.");
    	
    	if(linha == 1 || linha == this.qtdTotalLinhas) {
    		for(int i = 0; i < this.linhaAtual.length(); i++) {
    			if(this.linhaAtual.charAt(i) == 'E'){
    				this.entrada = true;
    				this.setQtdEntrada(1);
    			}
    			
    			if(this.linhaAtual.charAt(i) == 'S'){
    				this.saida = true;
        			this.setQtdSaida(1);
    			}
    		}
    	}
    	else {
    		for(int i = 0; i < this.linhaAtual.length(); i++) {
    			if(i == 0) {
    				if(this.linhaAtual.charAt(i) == 'E'){
        				this.entrada = true;
        				this.setQtdEntrada(1);
        			}
    				
    				if(this.linhaAtual.charAt(i) == 'S'){
        				this.saida = true;
        				this.setQtdSaida(1);
        			}
    			}
    			else if(i == this.linhaAtual.length()) {
    				if(this.linhaAtual.charAt(i) == 'E'){
        				this.entrada = true;
        				this.setQtdEntrada(1);
        			}
    				
    				if(this.linhaAtual.charAt(i) == 'S'){
        				this.saida = true;
        				this.setQtdSaida(1);
        			}
    			}
    		}
    	}
    }
    
    private void verificaBuracos(int linha)throws Exception {    	
    	if(linha > this.qtdTotalLinhas)
    		throw new Exception("Arquivo invalido! Linhas do labirinto nao coincidem com o total.");
    	
    	if(linha == 1 || linha == this.qtdTotalLinhas) {
    		for(int i = 0; i < this.linhaAtual.length(); i++) {
    			if(this.linhaAtual.charAt(i) == ' '){
    				throw new Exception("Labirinto invalido! Existe buraco nas extremidades!");
    			}
    		}
    	}
    	else {
    		for(int i = 0; i < this.linhaAtual.length(); i++) {
    			if(i == 0) {
    				if(this.linhaAtual.charAt(i) == ' '){
        				throw new Exception("Labirinto invalido! Existe buraco nas extremidades!");
        			}
    			}
    			else if(i == this.linhaAtual.length()) {
    				if(this.linhaAtual.charAt(i) == ' '){
        				throw new Exception("Labirinto invalido! Existe buraco nas extremidades!");
        			}
    			}
    		}
    	}
    }
}