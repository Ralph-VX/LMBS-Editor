/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kien.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author 6316036
 */
public class KienLogger {
    public static Logger logger;
    private static FileHandler file;
    
    public static void error(String message, Exception e) {
    	logger.log(Level.WARNING, message, e);
    }
    
    static{
    	try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("logger.property"));
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        logger = Logger.getLogger("Kien");
		try {
			file = new FileHandler("debug-log.log",10, 1);
			file.setFormatter(new KienFormatter());
			logger.addHandler(file);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
