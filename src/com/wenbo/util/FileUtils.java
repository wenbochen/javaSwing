package com.wenbo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/** 
 * 文件操作工具包
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class FileUtils 
{

	/**
	 * 读取文本文件
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String read(String fileName ) 
	{
		try 
		{
			File file = new File(fileName);
			FileInputStream in = new FileInputStream(file);
			return readInStream(in);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";
	} 
	
	private static String readInStream(FileInputStream inStream)
	{
		try 
		{
		   ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		   byte[] buffer = new byte[512];
		   int length = -1;
		   while((length = inStream.read(buffer)) != -1 )
		   {
			   outStream.write(buffer, 0, length);
		   }
		   
		   outStream.close();
		   inStream.close();
		   return outStream.toString();
		} 
		catch (IOException e)
		{
		}
		return null;
	}
	
	public static File createFile( String folderPath, String fileName )
	{
		File destDir = new File(folderPath);
		if (!destDir.exists()) 
		{
			destDir.mkdirs();
		}
		return new File(folderPath,  fileName + fileName );
	}
	/**
	 * 文件复制
	 * @param sour 源文件
	 * @param tofile 目标文件
	 */
	public static void CopyFile(File sour,File tofile){
		BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
    
            try {
				inBuff = new BufferedInputStream(new FileInputStream(sour));
	            outBuff = new BufferedOutputStream(new FileOutputStream(tofile));

	            // 缓冲数组
	            byte[] b = new byte[1024 * 4];
	            int len;
	            while ((len = inBuff.read(b)) != -1) {
	                outBuff.write(b, 0, len);
	            }
	            // 刷新此缓冲的输出流
	            outBuff.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	         
					try {
						   // 关闭流
			            if (inBuff != null)
						inBuff.close();
			            if (outBuff != null)
			                outBuff.close();
			   
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

      
		
	}
	/**
	 * 根据文件绝对路径获取文件名
	 * @param filePath
	 * @return
	 */
	public static String getFileName( String filePath )
	{
		if( TextUtils.isEmpty(filePath) )	return "";
		return filePath.substring( filePath.lastIndexOf( File.separator )+1 );
	}
	/**
	 * 根据文件的绝对路径获取文件名但不包含扩展名
	 * @param filePath
	 * @return
	 */
	public static String getFileNameNoFormat( String filePath){
		if(TextUtils.isEmpty(filePath)){
			return "";
		}
		int point = filePath.lastIndexOf('.');
		return filePath.substring(filePath.lastIndexOf(File.separator)+1,point);
	}
	
	/**
	 * 获取文件扩展名
	 * @param fileName
	 * @return
	 */
	public static String getFileFormat( String fileName )
	{
		if( TextUtils.isEmpty(fileName) )	return "";
		
		int point = fileName.lastIndexOf( '.' );
		return fileName.substring( point+1 );
	}
	
	/**
	 * 获取文件大小
	 * @param filePath
	 * @return
	 */
	public static long getFileSize( String filePath )
	{
		long size = 0;
		
		File file = new File( filePath );
		if(file!=null && file.exists())
		{
			size = file.length();
		} 
		return size;
	}
	
	/**
	 * 获取文件大小
	 * @param size 字节
	 * @return
	 */
	public static String getFileSize(long size) 
	{
		if (size <= 0)	return "0";
		java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
		float temp = (float)size / 1024;
		if (temp >= 1024) 
		{
			return df.format(temp / 1024) + "M";
		}
		else 
		{
			return df.format(temp) + "K";
		}
	}

	/**
	 * 转换文件大小
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

	/**
	 * 获取目录文件大小
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
	    if (!dir.isDirectory()) {
	    	return 0;
	    }
	    long dirSize = 0;
	    File[] files = dir.listFiles();
	    for (File file : files) {
	    	if (file.isFile()) {
	    		dirSize += file.length();
	    	} else if (file.isDirectory()) {
	    		dirSize += file.length();
	    		dirSize += getDirSize(file); //递归调用继续统计
	    	}
	    }
	    return dirSize;
	}
	
	/**
	 * 获取目录文件个数
	 * @param f
	 * @return
	 */
	public long getFileList(File dir){
        long count = 0;
        File[] files = dir.listFiles();
        count = files.length;
        for (File file : files) {
            if (file.isDirectory()) {
            	count = count + getFileList(file);//递归
            	count--;
            }
        }
        return count;  
    }
	
	public static byte[] toBytes(InputStream in) throws IOException 
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    int ch;
	    while ((ch = in.read()) != -1)
	    {
	    	out.write(ch);
	    }
	    byte buffer[]=out.toByteArray();
	    out.close();
	    return buffer;
	}
	


}