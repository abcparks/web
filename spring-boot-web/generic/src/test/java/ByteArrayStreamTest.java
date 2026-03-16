import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by WCY on 2021/9/22
 */
public class ByteArrayStreamTest {
    public static void main(String[] args) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream("Hello World!".getBytes());
        byte[] bs = new byte[1024];
        bis.read(bs);
        System.out.println(new String(bs));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write("TOMCAT!".getBytes());

        bos.close();
        bs = bos.toByteArray();
        System.out.println(new String(bs));
    }
}
