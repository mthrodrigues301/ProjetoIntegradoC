import java.io.*;

public class Programa {
    public static void main(String[] args) {
        try{           
            BufferedReader br
            = new BufferedReader(new FileReader("teste.txt"));
          
          String line;
          int qtd;

          while((line = br.readLine()) != null){
            String[] b = line.split("\n");
            qtd = Integer.parseInt(b[0]);

            System.out.println(qtd);
            System.out.println(line);
          }
          br.close();
        }
        catch(Exception ex){

        }
    }
}