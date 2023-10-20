import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class kapitest {

    public static void main(String[] args) throws IOException {
        read();
        System.out.println(odczytane);
    }

    public static ArrayList<Integer> odczytane = new ArrayList<Integer>();

    public static void read() throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream("src/generacja.bin"));
        {
            int bytelength = 1;
            while (in.available() > 0) { //czy doszlismy do konca pliku
                long ilosc = in.readLong();
                for (int i = 0; i < ilosc; i++) {
                    int res = 0;
                    if (bytelength == 1) {
                        res = in.read();
                        odczytane.add(res);
                    } else if (bytelength == 2) {
                        res += in.read() << 8;
                        res += in.read();
                        odczytane.add(res);
                    } else if (bytelength == 3) {
                        res += in.read() << 16;
                        res += in.read() << 8;
                        res += in.read();
                        odczytane.add(res);
                    } else {
                        res += in.read() << 24;
                        res += in.read() << 16;
                        res += in.read() << 8;
                        res += in.read();
                        odczytane.add(res);
                    }
                }
                bytelength++;
            }
            in.close();


        }
    }
}
