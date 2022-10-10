package com.intuit.commons.utils;

import lombok.experimental.UtilityClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Scanner;

@UtilityClass
public class IOUtils {

    public static InputStream toInputStream(String fileContent, Charset charset) {
        return new ByteArrayInputStream(fileContent.getBytes(charset));
    }

    public static byte[] toByteArray(InputStream fileContentInputStream) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        // read bytes from the input stream and store them in the buffer
        while ((len = fileContentInputStream.read(buffer)) != -1) {
            // write bytes from the buffer into the output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }

    public static String getResourceContentAsString(String resourceFilePath) {
        String content1;
        try (Scanner s = new Scanner(Objects.requireNonNull(IOUtils.class
                .getClassLoader()
                .getResourceAsStream(resourceFilePath)))
                .useDelimiter("\\A")) {
            content1 = s.hasNext() ? s.next() : "";
        }
        return content1;
    }

}
