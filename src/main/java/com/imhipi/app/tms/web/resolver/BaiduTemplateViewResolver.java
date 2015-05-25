package com.imhipi.app.tms.web.resolver;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

/**
 * render file as string for response
 * @author michael
 *
 */
public class BaiduTemplateViewResolver extends AbstractCachingViewResolver implements Ordered {

	private Logger logger = Logger.getLogger(BaiduTemplateViewResolver.class
            .getName());

    private int order = Integer.MIN_VALUE;

    // requested file location under web app
    private String prefix;

    // View
    private String viewName;
    
    private String suffix;


    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getOrder() {
        return this.order;
    }

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        if (prefix == null) {
            throw new Exception(
                    "No location specified for BaiduTemplateViewResolver.");
        }
        String requestedFilePath = prefix + viewName + suffix;
        Resource resource = null;
        String url = "";
        try {
            logger.finest(requestedFilePath);
            resource = getApplicationContext().getResource(requestedFilePath);
            url = resource.getURI().toString();
        } catch (Exception e) {
            // this exception should be catched and return null in order to call
            // next view resolver
            logger.finest("No file found for file: " + requestedFilePath);
            return null;
        }
        logger.fine("Requested file found: " + requestedFilePath + ", viewName:" + viewName);
        //get view from application content, scope=prototype
        try {
			BaiduTemplateView view = this.getApplicationContext().getBean(this.viewName, BaiduTemplateView.class);
			view.setUrl(requestedFilePath);
			view.setResponseContent(inputStreamTOString(resource.getInputStream(), "UTF-8"));
			return view;
		} catch (Exception e1) {
			return null;
		}
    }
    
    final static int BUFFER_SIZE = 4096;

    /**
     * Convert Input to String based on specific encoding
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String inputStreamTOString(InputStream in, String encoding)
            throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), encoding);
    }
}
