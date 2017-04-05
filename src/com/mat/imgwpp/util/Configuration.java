package com.mat.imgwpp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;



public class Configuration {

	
	
	public Configuration() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		XStream xstream = new XStream();
		xstream.alias("parameters", Parameters.class);
					
		String xmlParam = "";
		try(BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "Config.xml"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    xmlParam = sb.toString();
		}
		
		Parameters param = (Parameters) xstream.fromXML(xmlParam);
		System.out.println(param.getDir());
		//System.out.println(System.getProperty("user.dir"));
	}
	
	
}
