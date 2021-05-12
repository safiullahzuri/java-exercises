package ex25_18;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BitOutputStream implements AutoCloseable{
    private ObjectOutputStream outputStream;
    private int bits;
    private int position;

    public BitOutputStream(File file) throws IOException{
        outputStream = new ObjectOutputStream(new FileOutputStream(file));
    }

    public void writeObject(Object object) throws IOException{
        outputStream.writeObject(object);
    }

    public void writeInt(int n) throws IOException{
        outputStream.writeInt(n);
    }

    public void writeBit(char bit) throws IOException{
        bits = bits << 1;
        if (bit == '1'){
            bits = bits | 1;
        }
        position++;

        if (position == 8){
            outputStream.write((byte) bits);
            bits = 0;
            position = 0;
        }
    }

    public void writeBit(String bit) throws IOException{
        for (int i=0; i<bit.length(); i++){
            char c = bit.charAt(i);
            writeBit(c);
        }
    }



    @Override
    public void close() throws Exception {
        if (bits != 0){
            bits = bits << (8-position);
            outputStream.write((byte) bits);
        }
        outputStream.close();
    }
}
