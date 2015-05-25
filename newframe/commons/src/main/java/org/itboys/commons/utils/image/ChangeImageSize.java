package org.itboys.commons.utils.image;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.color.ColorSpace;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.lang3.StringUtils;

public class ChangeImageSize
{
	
	 public static boolean compressPic(String srcFilePath)  
	    {  
	       	File file = null;  
	        BufferedImage src = null;  
	        FileOutputStream out = null;  
	        ImageWriter imgWrier;  
	        ImageWriteParam imgWriteParams;  
	  
	        // 指定写图片的方式为 jpg  
	        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
	        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
	        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT  
	        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);  
	        // 这里指定压缩的程度，参数qality是取值0~1范围内，  
	        imgWriteParams.setCompressionQuality((float)0.5);  
	        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);  
	        ColorModel colorModel = ColorModel.getRGBdefault();  
	        // 指定压缩时使用的色彩模式  
	        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
	                .createCompatibleSampleModel(16, 16)));  
	  
	        try  
	        {  
	            if(StringUtils.isBlank(srcFilePath))  
	            {  
	                return false;  
	            }  
	            else  
	            {  
	                file = new File(srcFilePath);  
	                src = ImageIO.read(file);  
	                out = new FileOutputStream(srcFilePath);  
	  
	                imgWrier.reset();  
	                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造  
	                imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
	                // 调用write方法，就可以向输入流写图片  
	                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);  
	                out.flush();  
	                out.close();  
	            }  
	        }  
	        catch(Exception e)  
	        {  
	            e.printStackTrace();  
	            return false;  
	        }  
	        return true;  
	    }  
    /**
     * 缩放图像
     * @param srcImageFile 源图像文件地址
     * @param result       缩放后的图像地址
     * @param scale        缩放比例
     * @param flag         缩放选择:true 放大; false 缩小;
     */
    public static void scale(String srcImageFile,double width,double height,int Size)
    {        try
       {
        	 
        	File file=new File(srcImageFile);
        	
            BufferedImage src = ImageIO.read(file); // 读入文件
            
            double per;
            double w=width/src.getWidth();
            double h=height/src.getHeight();
            if (file.length()>Size*1024)
            {
            	if (w<h)
    			{
    				per=w;
    			}else {
    				per=h;
    			}
                width=(int)src.getWidth()*per;
                height=(int)src.getHeight()*per;
                /*int width = src.getWidth(); // 得到源图宽
                int height = src.getHeight(); // 得到源图长
                if (flag)
                {
                    // 放大
                    width = width * scale;
                    height = height * scale;
                }
                else
                {
                    // 缩小
                    width = width / scale;
                    height = height / scale;
                }*/
                Image image = src.getScaledInstance((int)width, (int)height, Image.SCALE_DEFAULT);
                BufferedImage tag = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(image, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                ImageIO.write(tag, "JPEG", new File(srcImageFile));// 输出到文件流
                compressPic(srcImageFile);
            }
            
           
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 图像切割
     * @param srcImageFile 源图像地址
     * @param descDir      切片目标文件夹
     * @param destWidth    目标切片宽度
     * @param destHeight   目标切片高度
     */
    public static void cut(String srcImageFile, String descDir, int destWidth, int destHeight)
    {
        try
        {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > destWidth && srcHeight > destHeight)
            {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                destWidth = 200; // 切片宽度
                destHeight = 150; // 切片高度
                int cols = 0; // 切片横向数量
                int rows = 0; // 切片纵向数量
                // 计算切片的横向和纵向数量
                if (srcWidth % destWidth == 0)
                {
                    cols = srcWidth / destWidth;
                }
                else
                {
                    cols = (int) Math.floor(srcWidth / destWidth) + 1;
                }
                if (srcHeight % destHeight == 0)
                {
                    rows = srcHeight / destHeight;
                }
                else
                {
                    rows = (int) Math.floor(srcHeight / destHeight) + 1;
                }
                // 循环建立切片
                // 改进的想法:是否可用多线程加快切割速度
                for (int i = 0; i < rows; i++)
                {
                    for (int j = 0; j < cols; j++)
                    {
                        // 四个参数分别为图像起点坐标和宽高
                        // 即: CropImageFilter(int x,int y,int width,int height)
                        cropFilter = new CropImageFilter(j * 200, i * 150, destWidth, destHeight);
                        img = Toolkit.getDefaultToolkit().createImage(
                                        new FilteredImageSource(image.getSource(), cropFilter));
                        BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics g = tag.getGraphics();
                        g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                        g.dispose();
                        // 输出为文件
                        ImageIO.write(tag, "JPEG", new File(descDir + "pre_map_" + i + "_" + j + ".jpg"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
     */
    public static void convert(String source, String result)
    {
        try
        {
            File f = new File(source);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, "JPG", new File(result));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 彩色转为黑白
     * @param source
     * @param result
     */
    public static void gray(String source, String result)
    {
        try
        {
            BufferedImage src = ImageIO.read(new File(source));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "JPEG", new File(result));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        //scale(,320,315);
    	compressPic("D://a.jpg");
    }

}