package main.java.com.xjw.downLoadFile;

import java.io.*;

public class StreamUtils {
    /**
     * 功能：将输入流转换成byte[],即可以把文件的内容读入到数组
     *
     */
    public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();//创建输出流对象
        byte[] b = new byte[1024];
        int len;
        while ((len = inputStream.read(b)) != -1){
            bos.write(b,0,len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    public static String streamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){  //当读取到null时，就表示结束
            builder.append(line + "\r\n");
        }
        return builder.toString();
    }

}
