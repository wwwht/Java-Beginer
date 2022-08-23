package com.atsiwei;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * Description 图片旋转、翻转处理
 */
public class ImageRotate
{
    static int Up_Down_Reverse = 0;
    static int Left_Right_Reverse = 1;
    /**
     * 旋转图片为指定角度
     *
     * @param bufferedimage
     *            目标图像
     * @param degree
     *            旋转角度
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,final int degree){
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2 + (w>h?(w-h)/2:(h-w)/2));
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
//        ImageIO.write(img, "JPEG", new File(filename));
        return img;
    }

    /***
     * 镜像处理，输入image和方式，返回翻转的新image
     * type = 0 表示上下翻转，type = 1 表示左右翻转
     */
    public static BufferedImage imageMisro(BufferedImage bufferedimage,int type)
    {
        try
        {
            //用到了自己写的方法
//            BufferedImage  bufferedimage = ImageToBufferedImage(image);

            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();

            int[][] datas = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    datas[j][i] = bufferedimage.getRGB(j, i);
                }
            }
            int[][] tmps = new int[w][h];
            if (type == 0) {
                for (int i = 0, a = h - 1; i < h; i++, a--) {
                    for (int j = 0; j < w; j++) {
                        tmps[j][a] = datas[j][i];
                    }
                }
            } else if (type == 1) {
                for (int i = 0; i < h; i++) {
                    for (int j = 0, b = w - 1; j < w; j++, b--) {
                        tmps[b][i] = datas[j][i];
                    }
                }
            }
            for (int i = 0; i < h; i++){
                for (int j = 0; j<w ;j++){
                    bufferedimage.setRGB(j, i, tmps[j][i]);
                }
            }

//            ImageIO.write(bufferedimage, "JPEG", new File(filename));
//            Image newImage = (Image)bufferedimage;
            return bufferedimage;
            //ImageIO.write(bufferedimage, "jpg", file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Image转换成BufferedImage
    public static BufferedImage ImageToBufferedImage( Image image )
    {
        BufferedImage  bufferedimage = new BufferedImage
                (image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics g =  bufferedimage.createGraphics();
        g.drawImage(image, 0, 0, null);	//这里，大家可能会有疑问，似乎没有对bufferedimage干啥
        g.dispose(); 					//但是是正确的，g调用drawImage就自动保存了
        return bufferedimage;
    }
    //BufferedImage 转换成Image类型
    public static Image BufferedImageToImage( BufferedImage b )
    {
        return (Image)b;
    }
}