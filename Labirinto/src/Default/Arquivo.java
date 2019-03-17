package Default;

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
            }   
        
            while((this.linhaAtual = arquivo.readLine()) != null){
            	if(this.isValidColunas(this.linhaAtual.length()) && this.isValidLinhas())
            		this.qtdColunas = this.linhaAtual.length();
            	else
            		throw new Exception("Arquivo invalido! " + this.getQtdLinhas() + " linha do labirinto com coluna(s) invalida(s)!");
            	
            	//if(!this.isValidExtremidades(this.getQtdLinhas()))
            		//throw new Exception("Arquivo invalido! Nao existe entrada e saida ou nao localizadas nas extremidades!");
            
            	this.setQtdLinhas(1);
            }   
          
            if(!this.isValidQtdLinhas())
            	throw new Exception("Arquivo invalido! Linhas do labirinto nao coincidem com o total.");
            
            arquivo.close();
        }
        catch(Exception ex) {
        	throw new Exception(ex.getMessage());
        }
    }

    public boolean isValidColunas(int tamanhoColuna){
        if(tamanhoColuna == this.qtdPrimeiraColuna)
            return true;
        
        return false;
    }

/*    public boolean isValidLabirinto()throws Exception{
    	try {
    		if(this.ArquivoGerado!= null) {
    			if(!isValidExtremidades())
                    throw new Exception("Arquivo invalido! Nao existe entrada e saida ou nao localizadas nas extremidades!");	
    		}
    		else
    		{
    			return false;
    		}
    	}catch(Exception ex) {
			throw new Exception(ex.getMessage());
    	} 
    	
    	return true;
    }*/

    public boolean isValidQtdLinhas(){
        if(this.qtdTotalLinhas == this.qtdLinhas)
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
    
    
    private boolean isValidExtremidades() {
    	
    	return false;
    }
    
    
    private boolean verificaEntradaSaida(int linha)throws Exception {    	
    	System.out.println("Quantidade de Linhas: " + this.qtdEntrada);
    	System.out.println("Quantidade de Linhas parametro: " + linha);
    	if(linha == 1 || linha == this.qtdTotalLinhas) {
    		if(this.linhaAtual.contains("E")) {
    			this.entrada = true;
    			this.qtdEntrada+= 1;
    			System.out.println("Entrada: " + this.qtdEntrada);
    		}
    		else if(this.linhaAtual.contains("S")) {
    			this.saida = true;
    			this.qtdSaida+= 1;
    			System.out.println("Saida: " + this.qtdEntrada);
    		}
    	}
    	else {
    		System.out.print("Entramos no else");
    		for(int i = 0; i < this.linhaAtual.length(); i++) {
    			if(i == 0) {
    				if(this.linhaAtual.charAt(i) == 'E'){
        				this.entrada = true;
            			this.qtdEntrada+= 1;
            			System.out.println("Entrada: " + this.qtdEntrada);
        			}
    				
    				if(this.linhaAtual.charAt(i) == 'S'){
        				this.saida = true;
            			this.qtdSaida+= 1;
            			System.out.println("Saida: " + this.qtdEntrada);
        			}
    			}else if(i == this.linhaAtual.length()) {
    				System.out.println("Entreamos no ELSE IF ");
    				System.out.println("I : " + i);
    				if(this.linhaAtual.charAt(i) == 'E'){
        				this.entrada = true;
            			this.qtdEntrada+= 1;
            			System.out.println("Entrada: " + this.qtdEntrada);
        			}
    				
    				if(this.linhaAtual.charAt(i) == 'S'){
        				this.saida = true;
            			this.qtdSaida+= 1;
            			System.out.println("Saida: " + this.qtdEntrada);
        			}
    			}
    		}
    	}
    	
    	if((this.qtdEntrada == 1 && this.entrada == true) || (this.qtdSaida == 1 && this.saida == true))
    		return true;
    	
    	return false;
    }
}