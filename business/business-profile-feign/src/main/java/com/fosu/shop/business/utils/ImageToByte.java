package com.fosu.shop.business.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * 图片流转字节流
 * @author Administrator
 */
public class ImageToByte {

    private byte[] bytes;

    public void fileToByte(File img, String formate) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, formate, baos);
            this.bytes = baos.toByteArray();
            System.err.println(bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }

    }
}
