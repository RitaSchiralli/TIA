package edu.ucla.cs.scai.canali.webapp.utils;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class WebAppUtils {
//private static final Logger LOGGER = Logger.getLogger( WebAppUtils.class.getName() );

	static public void printParameters(String servletName, HttpServletRequest request) {
            
		System.out.println("Print request parameters of " + servletName);
               // LOGGER.log( Level.SEVERE, "Print request parameters of " + servletName);
                
		Enumeration<String> parametersNames = request.getParameterNames();
		while(parametersNames.hasMoreElements()) {
			String param = parametersNames.nextElement();
			System.out.println(param + " = " + request.getParameter(param));
		}
	}
}
