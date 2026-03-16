import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by WCY on 2021/3/14
 */
public class MyClassLoader extends ClassLoader {
    // 加载类文件所在的路径
    private String path;

    // 自定义类加载器的名字
    private String classLoaderName;

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    public byte[] loadClassData(String name) {
        String classFilePath = path + name + ".class";
        ByteArrayOutputStream bos = null;
        try (InputStream is = new FileInputStream(classFilePath)) {
            bos = new ByteArrayOutputStream();
            int len;
            byte[] bs = new byte[1024];
            while ((len = is.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }

    /*
        查找类文件
     */
    @Override
    protected Class<?> findClass(String name) {
        byte[] bs = loadClassData(name);
        return defineClass(name, bs, 0, bs.length);
    }

}
