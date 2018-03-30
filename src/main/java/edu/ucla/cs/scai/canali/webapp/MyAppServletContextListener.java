/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucla.cs.scai.canali.webapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import edu.ucla.cs.scai.canali.core.index.TokenIndex;

/**
 *
 * @author Giuseppe M. Mazzeo <mazzeo@cs.ucla.edu>
 */
public class MyAppServletContextListener
        implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {        
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    	
    	String path = "C:/Users/seven/Documents/RITA/Universita/workspace/TIA/src/main/resources/index";
//    	String path = "D:/index";
		System.out.println("PERCORSO = " + path);
    	System.setProperty("kb.index.dir", path);
    	System.setProperty("sparql.endpoint", path+"/TIAInstancesAdjusted.owl");
    	
    	//TIAIndex.main(path);
    	
        new TokenIndex();
        System.out.println("ServletContextListener started");
    }
}