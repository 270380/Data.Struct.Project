package Practice.java;


import java.io.*;


public class Practice {
    public static void main(String[] args) {
        /*String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\transactions.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                System.out.print(value[0].substring(1,value[0].length()-1)+" ");
                System.out.print(value[1].substring(1,value[1].length()-1)+" ");
                System.out.print(value[2].substring(1,value[2].length()-1)+" ");
                System.out.print(value[3].substring(1,value[3].length()-1));
                System.out.println();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }*/

        try {
            FileWriter myWriter = new FileWriter("E:\\filename.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void readCsv() {
        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\people.csv"));
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                System.out.print(value[0]);
                System.out.print(value[1]);
                System.out.print(value[2]);
                System.out.print(value[3]);
                System.out.print(value[4]);
                System.out.print(value[5]);
                System.out.print(value[6]);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String[] parse(String line) { // use split or Scanner
        return line.split(",");
    }
}
