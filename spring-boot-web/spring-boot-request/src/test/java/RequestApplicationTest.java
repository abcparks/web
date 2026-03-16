import cn.alex.RequestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by WCY on 2021/7/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RequestApplication.class)
public class RequestApplicationTest {

    // 读取resource类路径下文件
    @Test
    public void classPathResourceRead() {
        ClassPathResource classPathResource = new ClassPathResource("static/1.png");
        try (InputStream inputStream = classPathResource.getInputStream();
             OutputStream outputStream = new FileOutputStream("d://1.png")) {
            int len;
            byte[] bs = new byte[1024];
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
