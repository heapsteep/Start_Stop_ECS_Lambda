package com.heapsteep.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Manager {
	public static void main(String[] args) throws IOException{
				
		Yaml yaml=new Yaml();
		String path=".\\src\\main\\java\\com\\heapsteep\\resources\\application.yaml";
		Object obj=yaml.load(new FileInputStream(new File(path)));
		System.out.println(obj);
		List fullList=(ArrayList)obj;
				
		for (Object obj2 : fullList) { 
			Map map=(Map)obj2;
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry set = (Map.Entry) it.next();
				//System.out.println(set.getKey() + " = " + set.getValue());
				String clusterName=(String)set.getKey();
				List servicesList=(List)set.getValue();
				for (Object obj3 : servicesList) { 
					Map map2=(Map)obj3;	
					Iterator it2 = map2.entrySet().iterator();
					while (it2.hasNext()) {
						Map.Entry set2 = (Map.Entry) it2.next();
						//System.out.println(set2.getKey() + " => " + set2.getValue());
						System.out.println("Cluster:" + clusterName + ",Service:"+set2.getKey()+",desiredCount:"+set2.getValue());
					}
				}
			}  		
	    }
		
		
		
	      
	}
}
