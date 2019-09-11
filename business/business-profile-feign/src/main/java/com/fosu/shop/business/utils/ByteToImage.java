package com.fosu.shop.business.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ByteToImage {

    static byte[] bytes;

    public void ByteToFile(byte[] bytes,String path,String formate)throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 = ImageIO.read(bais);
        try {
            //可以是jpg,png,gif格式
            File w2 = new File(path);
            //不管输出什么格式图片，此处不需改动
            ImageIO.write(bi1, formate, w2);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }
}
