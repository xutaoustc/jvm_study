package io;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 输入流：数据源到程序    InputStream, Reader
        // 输出流：程序到目的地    OutputStream, Writer

        // FileInputStream/FileReader  FileOutputStream/FileWriter
        // ByteArrayInputStream ByteArrayOutputStream

        // BufferedInputStream/BufferedReader  BufferedOutputStream/BufferedWriter
        // DataInputStream      DataOutputStream    便携方式将原始的java数据类型写入输出流
        // ObjectInputStream    ObjectOutputStream

        // InputStreamReader    OutputStreamWriter   字节流->字符流  指定编码


        testObjectStream();



//        File confArchive = File.createTempFile("testrun-", ".zip",
//                new File("/tmp"));
//        System.out.println(confArchive.getAbsolutePath());
    }

    private static void testByteArrayStream() throws IOException {
        java.net.URL uri = Main.class.getResource("/images.jpeg");
        InputStream is = uri.openStream();
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream();

        copy(is, bOutput);

        byte[] picBytes = bOutput.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(picBytes);
        FileOutputStream fos = new FileOutputStream(new File("/tmp/a.jpg"));

        copy(bis, fos);
    }

    private static void testDataStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF("编码辛酸泪，谁解其中味");
        dos.writeInt(18);
        dos.writeBoolean(false);


        byte[] byteArr = baos.toByteArray();
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(byteArr));
        String str = dis.readUTF();
        int i = dis.readInt();
        boolean b = dis.readBoolean();

        System.out.println(str);
        System.out.println(i);
        System.out.println(b);
    }

    private static void testObjectStream() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeUTF("编码辛酸泪，谁解其中味");
        oos.writeObject("编码辛酸泪，谁解其中味");


        byte[] byteArr = baos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArr));
        String str = (String)ois.readObject();

        System.out.println(str);
;
    }


    private static void copy(InputStream is , OutputStream os) throws IOException {
        int i = 0;
        while((i = is.read()) != -1){
            os.write(i);
        }

        close(is, os);
    }

    private static void close(Closeable... closeables) throws IOException {
        for(Closeable c : closeables){
            c.close();
        }
    }
}
