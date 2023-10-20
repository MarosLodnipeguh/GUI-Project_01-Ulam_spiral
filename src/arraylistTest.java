import java.io.*;
import java.util.ArrayList;

public class arraylistTest {

    public static void main(String[] args) throws IOException {

        ArrayList <Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);

        System.out.println(test);
        System.out.println(test.get(0));

        test.remove(0);

        System.out.println(test.get(0));
        System.out.println(test.get(1));
        System.out.println(test);


        String fileName = "src/test1.bin";
        FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fos);

        long x = 7;
        int y = 271111;
//        fos.write(x);
        dos.writeLong(x);

        int ByteLenght = 1;
//        while (54)
        for (int i = ByteLenght-1; i >0; i--) {
            dos.write(y >> 8*i);
        }
        ByteLenght++;






    }

//    public static int[][] spiral  = new int[dimension][dimension];  //utwórz tablicę spirali
//    private static int wiersz = dimension/2; // [i] - początek na środku tabeli
//    private static int kolumna = dimension/2; // [j] - początek na środku tabeli
//    private static int liczba = 1;

//    private static class ruch { // Klasa wewnętrzna z metodami do poruszania sie po spirali
//        // Metody wewnętrzne
//        public void gora() {wiersz-=1;} //góra
//        public void lewo() {kolumna-=1;} //lewo
//        public void dol() {wiersz+=1;} //dół
//        public void prawo() {kolumna+=1;} //prawo
//        public void dodaj(){ // dodaje 1 do aktualnej liczby i przypisuje do aktualnej pozycji [i][j]
//            liczba++;
//            spiral[wiersz][kolumna] = liczba;
//        }
//    }
//
//    public static void makeSpiral(){    // przypisz wszystkie wartości do tablicy spirali
//        ruch r = new ruch(); // nowy obiekt zawierający metody poruszania się
//        spiral [dimension/2][dimension/2] = 1; // przypisz 1 do środka spirali
//
//        int obrot = 0;
//        while (obrot < (dimension/2)){
//            liczba++;
//            kolumna+=1; // prawo x1 - wejście w nowy obrót spirali
//            spiral[wiersz][kolumna] = liczba;
//
//            for (int i = 0; i < 1+obrot*2; i++) { //1,3,5,7...
//                wiersz-=1; //góra
//                r.dodaj();
//            }
//            for (int i = 0; i < 2+(obrot*2); i++) { //2,4,6,8...
//                kolumna-=1; //lewo
//                r.dodaj();
//            }
//            for (int i = 0; i < 2+(obrot*2); i++) { //2,4,6,8...
//                wiersz+=1; //dół
//                r.dodaj();
//            }
//            for (int i = 0; i < 2+(obrot*2); i++) { //2,4,6,8...
//                kolumna+=1; //prawo
//                r.dodaj();
//            }
//            obrot++;
//        }
//    }
//
}
