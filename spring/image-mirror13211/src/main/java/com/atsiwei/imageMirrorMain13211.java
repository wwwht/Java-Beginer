package com.atsiwei;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.Base64;

import javax.swing.*;

public class imageMirrorMain13211 {
    public static void main(String[] args) throws Exception {
//        Image image = new ImageIcon("D:/test.jpg").getImage();
        Base64.Encoder encoder = Base64.getEncoder();
        String filepath = "D:/test.jpg";
        byte[] imgBytes = image2Bytes(filepath);
        final String encodedText = encoder.encodeToString(imgBytes);
//        System.out.println(encodedText);

        boolean res = GenerateImg.GenerateMapImage(encodedText, "D://testout.jpg", 4);
        /*
         * 镜像处理，输入image和方式，返回翻转的新image
         * type = 0 表示上下翻转，type = 1 表示左右翻转
         */
//        Image m = ImageRotate.imageMisro(image, 1, "D://testt2.jpg");
//        Image mm = ImageRotate.rotateImage(image, 90, "D://testRotate.jpg");	//向左旋转。这里只能填90,180,270
    }


    public static byte[] image2Bytes(String imgSrc) throws Exception
    {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);

        fin.close();
        return bytes;
    }
}
