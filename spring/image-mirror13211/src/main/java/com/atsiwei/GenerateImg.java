package com.atsiwei;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Base64;

public class GenerateImg {
    public static boolean GenerateMapImage(String imgStr , String filePath, int transType) {
        //处理base64字符串
        imgStr = imgStr.replaceAll(" ", "+").replace("data:image/png;base64,", "");
        //对字节数组字符串进行Base64解码并生成图片
        try {
            // jdk 版本问题，1.8改为BASE64Decoder
//            BASE64Decoder decoder = new BASE64Decoder();
            Base64.Decoder decoder = Base64.getDecoder();
//            byte[] b = decoder.decodeBuffer(imgStr);
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            /**
             *  0818 wht 新增
             *  镜像处理：
             *  1. 上下镜像
             *  2. 左右镜像
             *  3. 上下+左右镜像
             *  4. 不变
             * **/
            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            BufferedImage bufferImage = ImageIO.read(bis);
            BufferedImage resBufImg;
            if (transType == 1){ // 上下翻转
                resBufImg = ImageRotate.imageMisro(bufferImage, 0);
            }else if(transType == 2){ // 左右翻转
                resBufImg = ImageRotate.imageMisro(bufferImage, 1);
            }else if(transType == 3){ // 上下+左右
                BufferedImage bufImage = ImageRotate.imageMisro(bufferImage, 0);
                resBufImg = ImageRotate.imageMisro(bufImage, 1);
            }
            else{ // 不翻转
                resBufImg = bufferImage;
            }
            // 写入数据
            ImageIO.write(resBufImg, "JPEG", new File(filePath));
//            OutputStream out = new FileOutputStream(filePath);
//            //写入数据
//            out.write(b);
//            out.flush();
//            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
