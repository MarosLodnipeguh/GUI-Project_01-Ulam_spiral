import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class S28781Project01 extends Frame {

    public static void main(String[] args) throws IOException{

        String fileName = "src/primes.bin"; // ścieżka do pliku z liczbami pierwszymi

        System.out.print("\nGenerate new .bin file with primes? (y/n): ");
        Scanner s = new Scanner(System.in);
        if (Objects.equals(s.nextLine(), "y") ){
            System.out.println("Generating new .bin file...");

            setDimension(72*10); // dla jakiego kwadratu zostaną wyliczone i zapisane liczby pierwsze
            getPrimes(); // wypełnia tablice primes liczbami pierwszymi
            writeToFile(fileName); // zapisuje tablice primes do pliku, uwzględniając na początku ilość elementów dla danego Bajtu

        }

        readFromFile(fileName); // odczytuje plik i zapisuje odczytane liczby pierwsze do tablicy primesFromFile
        setWindowSize(700); // wielkość okna aplikacji
        new S28781Project01(); // tworzy nowe okno aplikacji

    }

    private static int dimension;
    private static int windowSize;
    private static void setDimension(int d){
        dimension = d;
    }private static void setWindowSize(int s){
        windowSize = s;
    }

    private static boolean checkPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> primes = new ArrayList<>(); // tablica liczb pierwszych

    private static void getPrimes(){ // wypełnia tablice primes
        for (int i = 2; i < Math.pow(dimension,2); i++) {
            if (checkPrime(i))
                primes.add(i);
        }
        System.out.println("\nPrimes have been counted");
    }

    private static int countBytes(int n) { // zwraca liczbę Bajtów potrzebną do zapisania liczby
        int count = 0;
        while (n > 0) {
            n >>= 8; // ile przesunięć wchodzi tyle Bajtów ma liczba
            count++;
        }
        return count;
    }

    private static void writeToFile(String fileName) throws IOException { // zapisuje tablice primes do pliku, uwzględniając na początku ilość elementów dla Bajtu
        FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fos);

        ArrayList<Integer> primesByByte = new ArrayList<>(); // tablica mówiąca ile elementów jest dla danej wielkości

        int Byte = 1;
        int count = 0;
        for (int p : primes) { // wypełnienie tablicy mówiącej o liczbie elementów dla danego Bajtu, index 0 = elementy 1B, index 1 = elementy 2B...
            int byteOfPrime = countBytes(p);
            if (byteOfPrime == Byte){
                count+=1;
            } else {
                primesByByte.add(count);
                count = 1; // dodanie liczby która spełnia po raz pierwszy warunek else (pierwsza liczba o 1 większym Bajcie)
                Byte+=1;
            }
        }
        primesByByte.add(count);

        System.out.println("Number of primes by Byte: "+ primesByByte);


        int maxByte = countBytes(primes.get(primes.size()-1)); // ile Bajtów ma największa liczba z tablicy
        System.out.println("Greatest number size: " + maxByte + "B");

        int currentByte = 1; // zaczynamy zapisywanie od wielkości 1B
        int loop = 0;
        for (int i = 0; i < maxByte; i++) {
            long primesByByteCount = primesByByte.get(i);

            dos.writeLong(primesByByteCount); // zapisanie ilości liczb pierwszych dla danego Bajta

            for (int j = loop; j < loop+primesByByteCount; j++) {
                int value = primes.get(j); // liczba pierwsza którą aktualnie zapisujemy

                for (int k = currentByte-1; k >= 0; k--) {
                    int res = value >> (8*k);
                    dos.write(res);
                }
            }
            loop += (int) primesByByteCount;
            currentByte++;
        }
        dos.close();
        fos.close();
        System.out.println("\nFile written successfully");
    }

    private static ArrayList<Integer> primesFromFile = new ArrayList<>(); // tabela do której zapisujemy odczytane liczby pierwsze z pliku

    private static void readFromFile(String fileName) throws IOException { // odczytuje plik i zapisuje odczytane liczby pierwsze do tablicy primesFromFile
        System.out.println("\nReading primes from .bin file...");

        FileInputStream fis = new FileInputStream(fileName);
        DataInputStream dis = new DataInputStream(fis);

        int currentByte = 1; // zaczynamy odczytywanie od wielkości 1B
        while(dis.available() > 0) {
            long primesByByteCount = dis.readLong(); // odczytanie ilości liczb pierwszych dla danego Bajta

            for (int i = 0; i < primesByByteCount; i++) {

                int res = 0;
                for (int j = currentByte-1; j > 0; j--) {
                    res += (dis.read() << (8*j));
                }
                res += dis.read();

                primesFromFile.add(res); // dodanie pełnej oczytanej liczby do tabeli odczytanych
            }
            currentByte++;
        }
        dis.close();
        fis.close();
    }

    private S28781Project01() {
        setSize(windowSize,windowSize);
        setVisible(true);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) { // wyrysowanie spirali, porównując aktualną liczbe spirali do wartości z tablicy primesFromFile
        g.setColor(Color.BLACK);

        System.out.println("\nWINDOW SIZE: " + getWidth() + " x " + getHeight());

        // współrzędne środka okna
        int x = getWidth() / 2;
        int y = (getHeight()+insets().top) / 2;

        // mniejszy bok okna
        int smallerDimension = Math.min(getWidth(), (getHeight()+ insets().top));

        int turn = 0;
        int number = 1;
        int index = 0;
        while (turn < smallerDimension/2){

            x++; // jeden w prawo - wejście w nowy obrót spirali
            number++;
            if (number == primesFromFile.get(index)){
                g.fillRect(x, y, 1, 1);
                index++;
            }

            for (int i = 0; i < 1+(turn*2); i++) { //1,3,5,7...
                y--; //góra
                number++;
                if (number == primesFromFile.get(index)){
                    g.fillRect(x, y, 1, 1);
                    index++;
                }
            }
            for (int i = 0; i < 2+(turn*2); i++) { //2,4,6,8...
                x--; //lewo
                number++;
                if (number == primesFromFile.get(index)){
                    g.fillRect(x, y, 1, 1);
                    index++;
                }
            }
            for (int i = 0; i < 2+(turn*2); i++) { //2,4,6,8...
                y++; //dół
                number++;
                if (number == primesFromFile.get(index)){
                    g.fillRect(x, y, 1, 1);
                    index++;
                }
            }
            for (int i = 0; i < 2+(turn*2); i++) { //2,4,6,8...
                x++; //prawo
                number++;
                if (number == primesFromFile.get(index)){
                    g.fillRect(x, y, 1, 1);
                    index++;
                }
            }
            turn++;
        }
    }
}
