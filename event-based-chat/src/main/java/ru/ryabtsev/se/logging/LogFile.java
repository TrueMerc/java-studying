package ru.ryabtsev.se.logging;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple logging file class.
 */
public class LogFile implements Logable {

    @NotNull private String fileName = "";

    public LogFile(@NotNull final String fileName) {
        this.fileName = fileName;
    }

    @Override
    @SneakyThrows
    public  void clear() {
        Writer writer = new PrintWriter(fileName);
        writer.close();
    }

    @Override
    @SneakyThrows
    public void write(String string) {
        BufferedWriter out = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(fileName, true), "UTF-8") );
        try {
            out.append(string + "\r\n");
        } finally {
            out.close();
        }
    }

    @Override
    @SneakyThrows
    public List<String> readAll() {
        BufferedReader in = new BufferedReader( new InputStreamReader( new FileInputStream(fileName), "UTF-8") );
        List<String> result = new ArrayList<>();
        try {
            for(String newString = in.readLine(); !newString.equals("");  newString = in.readLine()) {
                result.add( newString );
            }
        }
        finally {
            in.close();
            return result;
        }
    }

   @Override
   @SneakyThrows
   public List<String> readLast(int stringsNumber) {
       List<String> result = new ArrayList<>();
       RandomAccessFile file = null;
       try {
           file = new RandomAccessFile(fileName, "r");
           long lastByte = file.length() - 1;
           StringBuilder sb = new StringBuilder();
           int line = 0;

           for (long filePointer = lastByte; filePointer != -1; filePointer--) {
               file.seek(filePointer);
               int readByte = file.readByte();

               if ( (readByte == 0xA && filePointer < lastByte) || (readByte == 0xD) && (filePointer < lastByte - 1) ) {
                   ++line;
                   String lastLine = sb.reverse().toString();
                   result.add( lastLine );
                   sb = new StringBuilder();
               }
               if (line >= stringsNumber) {
                   break;
               }
               sb.append((char) readByte);
           }
       } finally {
           file.close();
           return result;
       }
   }
}
