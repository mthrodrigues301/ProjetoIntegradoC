import java.io.*;

public class Labirinto {
    public static void main(String[] args) {
      
      BufferedReader in = null;
      String caminho;
      String str;

      // Pilha<Coordernadas> pilhas1 = new Pilha<Coordernadas>();
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

        System.out.println("Quantidade de linhas: " + arquivo.getQtdLinhas());

        System.out.println("Quantidade de colunas na primeira linhas: " + arquivo.getQtdPrimeiraColuna());

        System.out.println("Quantidade de colunas nas demais linhas: " + arquivo.getQtdColunas());

        if(arquivo.getEntrada() && arquivo.getSaida())
          System.out.println("Arquivo valido, existe entrada e saida!");
        else 
          if(arquivo.getEntrada())
            System.out.println("Arquivo invalido! Existe somente entrada!");  
          else
            if(arquivo.getSaida())
              System.out.println("Arquivo invalido! Existe somente saida!");
            else
              System.out.println("Arquivo invalido! Nao existe entrada nem saida!");
        
        in.close();
        }
        catch(Exception ex){
          System.err.println(ex.getMessage());
        }
    }
}