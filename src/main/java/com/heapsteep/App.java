package com.heapsteep;

import java.util.LinkedHashMap;
import java.util.logging.Logger;
import java.util.List;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.UpdateServiceRequest;
import com.amazonaws.services.ecs.model.UpdateServiceResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class App implements RequestHandler<Object,Object>{
	private final static Logger LOGGER = Logger.getLogger(App.class.getName());
	public Object handleRequest(Object input, Context context) {
		String resource=(String)((List)((LinkedHashMap)input).get("resources")).get(0);//Get the value of the 'resources' attribute from input event.
		String[] resourceArr=resource.split("/");
				
		if(resourceArr[1].equals("Stop_ECS")) {
			AmazonECS client=AmazonECSClientBuilder.standard().withRegion("ap-south-1").build();
	        UpdateServiceRequest request=new UpdateServiceRequest().withCluster("heapsteep-cluster-3").withService("heapsteep-service-3").withDesiredCount(0);
	        
	        UpdateServiceResult response=client.updateService(request);	       
	        LOGGER.info("===>Message:" + response.getService().getEvents().get(3).getMessage());
	        
	        LOGGER.info("*******All ECS Services are Stopped as part of Weekend Shutdown********");
			return "All ECS Services are Stopped as part of Weekend Shutdown";			
		}else if(resourceArr[1].equals("Start_ECS")) {
			AmazonECS client=AmazonECSClientBuilder.standard().withRegion("ap-south-1").build();
	        UpdateServiceRequest request=new UpdateServiceRequest().withCluster("heapsteep-cluster-3").withService("heapsteep-service-3").withDesiredCount(2);
	        
	        UpdateServiceResult response=client.updateService(request);			
	        LOGGER.info("===>Message:" + response.getService().getEvents().get(3).getMessage());
	        
	        LOGGER.info("*******All ECS Services are Started as part of Weekday Startup*******");
			return "All ECS Services are Started as part of Weekday Startup";			
		}		
		return null;
	}
}
