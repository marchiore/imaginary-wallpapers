package com.mat.imgwpp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

public class Log {
	
	String json = "";
	List<Link> links; 
	String pathSaveFile = "C:\\wpp-castle\\log.json";
		
	public int writeFile() throws FileNotFoundException {
		
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
		    public HierarchicalStreamWriter createWriter(Writer writer) {
		        return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
		    }
		});
		
		xstream.setMode(XStream.NO_REFERENCES);
        
        json = xstream.toXML(this.links); 
        
        //LIMPANDO ARQUIVO TEXTO
        PrintWriter writer = new PrintWriter(this.pathSaveFile);
        writer.print("");
        writer.print(json);
        writer.close();        
        
		return 0;
	}
	
	public void readJson() throws IOException {		
		String jsonItens = "";
		try(BufferedReader br = new BufferedReader(new FileReader(this.pathSaveFile))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    jsonItens = sb.toString();
		}
		
		TypeToken<List<Link>> tipoTeste = new TypeToken<List<Link>>(){};
		Gson gSon = new GsonBuilder().create();
        this.links = gSon.fromJson(jsonItens, tipoTeste.getType());					
	}
	
	public boolean checkExist(String url){
		boolean exist = false;
		try {
			
			if (!new File(pathSaveFile).exists()) {
				new File(pathSaveFile).createNewFile();
			}
			
			this.readJson();
			
			Link linkNovo = new Link();
			linkNovo.setLink(url);
									
			if(this.links != null && this.links.size() > 0){
				for (Link link : this.links) {					
					if (url.equals(link.getLink())){
						exist = true;
						break;
					}				
				}
			}
			if(!exist){
				List<Link> newList;
				
				if(this.getLinks() == null)
					newList = new ArrayList<Link>();
				else
					newList = this.getLinks();	
								
				newList.add(linkNovo);				
				this.setLinks(newList);
				this.writeFile();	
			}
			
			return exist;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return exist;
	}	

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
