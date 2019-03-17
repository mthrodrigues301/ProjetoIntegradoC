package Default;

import java.io.*;

public class Labirinto {
    public static void main(String[] args) {
      
      BufferedReader in = null;

      // try{
      //   Pilha<Coordenada> pilhas1 = new Pilha<Coordenada>(10);
      // }
      // catch(Exception ex){
      //   System.out.println("ERRO!");
      // }

      Arquivo arquivo = new Arquivo();

      do{
        try
        {
            System.out.print("Digite o caminho do arquivo: ");
            arquivo.setCaminho(Teclado.getUmString());
            in = arquivo.lerArquivo();
        }
        catch(Exception ex)
        {
          System.err.println("Erro ao ler o arquivo ou caminho invalido! Tente novamente ...");
        }
      }while(arquivo.isValidCriarArquivo());

      try
      {
        arquivo.carregarArquivo(in);

        if((arquivo.getEntrada() && arquivo.getQtdEntrada() == 1) && (arquivo.getSaida()) && arquivo.getQtdSaida() == 1)
            System.out.println("Arquivo valido, existe entrada e saida!");
          else 
          	throw new Exception("Arquivo valido! Deve conter uma entrada e uma saida");
        
        System.out.println("Quantidade de linhas: " + arquivo.getQtdLinhas());

        System.out.println("Quantidade de colunas na primeira linhas: " + arquivo.getQtdPrimeiraColuna());

        System.out.println("Quantidade de colunas nas demais linhas: " + arquivo.getQtdColunas());
        
        in.close();        
       }catch(Exception ex){
        	System.err.println(ex.getMessage());
       }
    }
}