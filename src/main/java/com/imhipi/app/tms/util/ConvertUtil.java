package com.imhipi.app.tms.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * Utility class to convert one object to another.
 */
public final class ConvertUtil {
    
    protected static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private ConvertUtil() {
    }

    /**
     * Method to convert a ResourceBundle to a Map object.
     *
     * @param rb a given resource bundle
     * @return Map a populated map
     */
    public static Map<String, String> convertBundleToMap(ResourceBundle rb) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        return map;
    }

    /**
     * Method to convert a ResourceBundle to a Properties object.
     *
     * @param rb a given resource bundle
     * @return Properties a populated properties object
     */
    public static Properties convertBundleToProperties(ResourceBundle rb) {
        Properties props = new Properties();

        for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
            String key = keys.nextElement();
            props.put(key, rb.getString(key));
        }

        return props;
    }

    /**
     * Convenience method used by tests to populate an object from a
     * ResourceBundle
     *
     * @param obj an initialized object
     * @param rb a resource bundle
     * @return a populated object
     */
    public static Object populateObject(Object obj, ResourceBundle rb) {
        try {
            Map<String, String> map = convertBundleToMap(rb);
            BeanUtils.copyProperties(obj, map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception occurred populating object: " + e.getMessage());
        }

        return obj;
    }
    
    /**
	 * convert NULL into ""
	 * @param str
	 * @return
	 */
	public static String nvl(String str) {
		if(str == null) {
			return "";
		}
		return str;
	}
	
	public static String parseVersion(String version) {
		if(!StringUtils.hasText(version)) return null;
		String[] versionArr = version.split("\\.");
		String parseVer = "";
		int index = 0;
		for(String str : versionArr) {
			if(str.startsWith("0")) {
				parseVer += str.substring(1);
				if(index < versionArr.length - 1) parseVer += ".";
			}
			index ++;
		}
		return parseVer;
	}
	
	public static String formatVersion(String version) {
		if(!StringUtils.hasText(version)) return null;
		String[] versionArr = version.split("\\.");
		String formatVer = "";
		int index = 0;
		for(String str : versionArr) {
			if(str.length() < 2) {
				formatVer += "0" + str;
				if(index < versionArr.length - 1) formatVer += ".";
			}
			index ++;
		}
		return formatVer;
	}
	
	public static int generateRandom(int min, int max) {
		if(min == max) return min;
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}
	
	public static String generateRangeQueue(int min, int max) {
    	StringBuffer sb = new StringBuffer();
    	List<Integer> original = new ArrayList<Integer>();
    	for(int i  = min; i <= max; i ++) {
    		original.add(i);
    	}
    	min = 1;
    	max = max - min + 1;
    	while(original.size() > 0) {
    		Random random = new Random();
    		int randInt = (random.nextInt(max) % (max - min + 1) + min) - 1;
    		sb.append(original.get(randInt) + ",");
    		original.remove(randInt);
    		max -- ;
    	}
    	
    	sb.delete(sb.length() - 1, sb.length());
    	return sb.toString();
    }
	
	public static String convertDouble2Ratio(double d1, double d2) {
		DecimalFormat df = new DecimalFormat("##0.000%");    //##.00%   百分比格式，后面不足2位的用0补齐
		return df.format((d1)/(d2));
	}
	
	public static String exactDoubleWithoutPrecent(double d) {
		DecimalFormat df = new DecimalFormat("##0.00"); 
		return df.format(d * 100);
	}
	
}
