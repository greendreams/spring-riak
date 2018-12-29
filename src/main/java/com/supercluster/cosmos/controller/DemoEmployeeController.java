package com.supercluster.cosmos.controller;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.UnresolvedConflictException;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.supercluster.cosmos.datasource.RiakDataSource;

@RestController
public class DemoEmployeeController {

	@Autowired
	RiakDataSource datasource;
	@GetMapping("/testwrite")
	public String addEmployee() {
		
		try {
			RiakClient client = datasource.getReactClient();

			Location location = new Location(new Namespace("TestBucket"), "TestKey");
			String myData = "This is my test data";

			StoreValue sv = new StoreValue.Builder(myData).withLocation(location).build();
			StoreValue.Response svResponse = client.execute(sv);

			client.shutdown();
			
			System.out.println("Complete");
			
			return "Write complete";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Write failed. Exception happened";
		}
	}
	
	@GetMapping("/testread")
	public String getEmployee() {
		
		try {

			RiakClient client = datasource.getReactClient();
			Location location = new Location(new Namespace("TestBucket"),"TestKey");

			FetchValue fv = new FetchValue.Builder(location).build();
			FetchValue.Response response = client.execute(fv);

			String value = response.getValue(String.class);
			System.out.println("Value is :" + value);

			client.shutdown();
			
			return value;
			
		} catch (Exception e) {
			System.out.println("Exception happened");
			e.printStackTrace();

			return "Read failed. Exception happened";
		}
	
	}
}
