package com.mat.imgwpp.robot;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mat.imgwpp.util.Log;

public class Robot {
	
	String url = "https://www.reddit.com/r/ImaginaryCastles";
	String regexAllowedImages = ".*\\.jpg.*";
	String attrUsedToFindLinks = "data-href-url";
	String classUsedToFindLinks = "title may-blank outbound";
	String pathSaveFile = "C:\\wpp-castle\\";
	
	public void run() throws IOException {
		System.out.println("...:: INICIANDO SCRIPT WPP-CASTLES ::...");
				
	    URLConnection connection = new URL(this.url).openConnection();
	    connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	    connection.connect();

	    BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
	    StringBuilder sb = new StringBuilder();
	    
	    String line;
	    while ((line = r.readLine()) != null) {
	        sb.append(line);
	    }
	    
	    Document doc = Jsoup.parse(sb.toString());	    
	    Elements elements = doc.getElementsByClass(this.classUsedToFindLinks);
	    Log log = new Log();
        for(Element element : elements){

        	if(element.attr(this.attrUsedToFindLinks).matches(this.regexAllowedImages)){
        	
	        	URL url = new URL(element.attr(this.attrUsedToFindLinks));
	        	if(!log.checkExist(url.toString())){
		        	try{
			        	InputStream in = new BufferedInputStream(url.openStream());
			        	ByteArrayOutputStream out = new ByteArrayOutputStream();
			        	byte[] buf = new byte[1024];
			        	int n = 0;
			        	
			        	while (-1!=(n=in.read(buf))){
			        	   out.write(buf, 0, n);
			        	}
			        	
			        	out.close();
			        	in.close();
			        	byte[] response = out.toByteArray();
			        	File dirSave = new File(this.pathSaveFile);
			        	dirSave.mkdir();			        			        
			        	
			        	FileOutputStream fos = new FileOutputStream(this.pathSaveFile + new Date().getTime() + ".jpg");
			        	fos.write(response);
			        	fos.close();
			        	
		        	}catch(Exception e){
		        		e.printStackTrace();
		        	}
	        	}
        	}
        }
        System.out.println("...:: FINALIZANDO SCRIPT WPP-CASTLES ::...");
	}
}
