/**
 * 
 */
package com.visionet.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import jodd.JoddDefault;
import jodd.io.StreamUtil;


/**
 * @author xiaoning 2014-4-5 下午2:06:16
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private final byte[] body;

	/**
	 * @param request
	 * @throws IOException
	 */
	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		 request.setCharacterEncoding("UTF-8");
		 //System.out.println("JoddDefault.encoding"+JoddDefault.encoding);
		body = StreamUtil.readBytes(request.getReader(), JoddDefault.encoding);
	}

	@Override  
    public BufferedReader getReader() throws IOException {  
        return new BufferedReader(new InputStreamReader(getInputStream()));  
    }  
  
    @Override  
    public ServletInputStream getInputStream() throws IOException {  
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);  
        return new ServletInputStream() {

            @Override  
            public int read() throws IOException {  
                return bais.read();  
            }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub

			}
  
        };  
    }  
}
