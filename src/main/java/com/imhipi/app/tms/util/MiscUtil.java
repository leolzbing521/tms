package com.imhipi.app.tms.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Random;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.util.ClassUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author fcy
 */
public class MiscUtil {
    
    final static int BUFFER_SIZE = 4096;
    
    public static InputStream getImage(String imageUrl) {
        InputStream is = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setReadTimeout(20000);
            urlConn.connect();
            is = urlConn.getInputStream();
            if (HttpURLConnection.HTTP_OK == urlConn.getResponseCode()) {
                return is;
            }
        } catch (MalformedURLException ex) {
        } catch (IOException ex) {
        }
        return is;
    }
    
    public static byte[] inputStreamToByte(InputStream in) throws IOException{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = in.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);
        data = null;  
        return outStream.toByteArray();  
    }
    
    @SuppressWarnings("rawtypes")
	public static String classToDbName(Class clazz) {
        String shortName = ClassUtils.getShortName(clazz);
        StringBuilder buf = new StringBuilder(shortName.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1))
                    && Character.isUpperCase(buf.charAt(i))
                    && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }
    
    public static String MD5(byte[] o) {
        String ret = null;
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            md.update(o);
            ret = Hex.toHexString(md.digest());
        } catch (Exception e) {
        }
        return ret;
    }
    
    public static byte[] base64ToByte(String os) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] byteData = null;
        try {
            byteData = decoder.decodeBuffer(os);
            for (int i = 0; i < byteData.length; i++) {
                if (byteData[i] < 0) {
                    byteData[i] += 256;
                }
            }
        } catch (IOException ex) {
        }
        return byteData;
    }
    
    public static String byteToBase64(byte[] ob) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(ob);
    }
    
    /** 
     * 概率选择 
     * @param keyChanceMap key为唯一标识，value为该标识的概率，是去掉%的数字 
     * @return 被选中的key。未选中返回null 
     */  
     public static boolean chanceSelect(Float rate) {  
		Integer sum = 100;
		// 从1开始
		Integer rand = new Random().nextInt(sum) + 1;

		int rateInt = (int) (rate * 100);
		// 选中
		if (rand <= rateInt) {
			return true;
		}

		return false;
     }
    
    public static void main(String[] args){
    	float rate = 0.333f;
    	System.out.println((int)(rate * 100));
    }
    
    
    
}
