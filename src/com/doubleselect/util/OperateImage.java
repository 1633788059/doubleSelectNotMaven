package com.doubleselect.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class OperateImage {

    // ===源图片路径名称如：c:\1.jpg
    private String srcpath;

    // ===剪切图片存放路径名称。如：c:\2.jpg
    private String subpath;

    public void setSrcpath(String srcpath) {
        this.srcpath = srcpath;
    }

    public void setSubpath(String subpath) {
        this.subpath = subpath;
    }

    // ===剪切点x坐标
    private int x;
    private int y;

    // ===剪切点宽度
    private int width;
    private int height;

    public OperateImage() {}

    /*
     * 判断x，y的与0的大小,防止出错
     */
    public OperateImage(int x, int y, int width, int height) {
    	if(x<0){
    		this.x=0;
    	}
    	else{
    		this.x = x;
    	}
    	if(x<0){
    		this.y=0;
    	}
    	else{
    		this.y = y;
    	}
        this.width = width;
        this.height = height;
    }

    //对图片裁剪，并把裁剪完蛋新图片保存 。
    public void cut() throws IOException {
    	 String finalName=srcpath.substring(srcpath.lastIndexOf(".")+1);
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            // 读取图片文件
            is = new FileInputStream(srcpath);

            //System.out.println("x:"+x);
            //System.out.println("y:"+y);
            //System.out.println("width:"+width);
            //System.out.println("height:"+height);
            
            /**
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
             * (例如 "jpeg" 或 "tiff")等 。
             */
            //Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            //System.out.println("finalName"+finalName);
            
            //扩展
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(finalName);
            ImageReader reader = it.next();

            // 获取图片流
            iis = ImageIO.createImageInputStream(is);

            /**
             * iis:读取源。true:只向前搜索 将它标记为 ‘只向前搜索’。
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             */
            reader.setInput(iis, true);

            /**
             * 描述如何对流进行解码的类 用于指定如何在输入时从 Java Image I/O
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回ImageReadParam 的实例。
             */
            ImageReadParam param = reader.getDefaultReadParam();

            //图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。
            Rectangle rect = new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

            //使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将它作为一个完整的 BufferedImage 返回。
            BufferedImage bi = reader.read(0, param);

            // 保存新图片
            //ImageIO.write(bi, "jpg", new File(subpath));
            ImageIO.write(bi,finalName, new File(subpath));
        } finally {
            if (is != null){
                is.close();
            }
            if (iis != null){
                iis.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String name = "d:\\images\\1.jpg";
        OperateImage o = new OperateImage(40, 40, 100, 100);
        o.setSrcpath(name);
        o.setSubpath("D:\\images\\11.jpg");
        o.cut();
    }

}