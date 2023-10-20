import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteThreeByteValuesToBinaryFile {

    public static void main(String[] args) {
        int[] values = {255, 65535, 16777215, 16777214}; // Przykładowe wartości 3-bajtowe.
        String fileName = "values.bin";

        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            for (int i = 0; i < values.length; i++) {
                int value = values[i];
                byte[] bytes = new byte[3];
                bytes[0] = (byte) ((value >> 16));
                bytes[1] = (byte) ((value >> 8));
                bytes[2] = (byte) (value);
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
