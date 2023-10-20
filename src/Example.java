import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Example {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        String fileName = "example.bin";
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeLong(0);
            for (int i = 0; i < array.length; i++) {
                dos.writeByte((byte) array[i]);
            }
            dos.writeLong(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//                byte[] valueInBytes = new byte[byteLength];
//
//                dis.readFully(valueInBytes); // odczytanie tyle Bajtów, ile ma długości tablica
//
//                int maska = getMask(byteLength);
//
//                int value = 0;
//                int move = valueInBytes.length-1;
//                for (int j = 0; j < valueInBytes.length; j++) {
//                    value += ((valueInBytes[j] << (8 * move)) &0xFF); // maska zamienia wartości na unsigned (bez minusów)
//
//                    move--;
//                }
//                value &= maska;
//                primesCounted.add(value);


//    public static int getMask(int length) {
//        int mask = 0;
//        for (int i = 0; i < length; i++) {
//            mask = (mask << 8) | 0xFF; // dodaje 0xFF do każdego Bajta, dla 2B będzie maska 0xFFFF itd...
//        }
//        return mask;
//    }