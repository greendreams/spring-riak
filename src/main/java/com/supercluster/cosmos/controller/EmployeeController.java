package com.supercluster.cosmos.controller;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.UnresolvedConflictException;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;

@RestController
public class EmployeeController {


	@GetMapping("/add")
	public void addEmployee() {
		
		try {
			RiakClient client = RiakClient.newClient(8087, "127.0.0.1");

			Location location = new Location(new Namespace("TestBucket"), "TestKey");
			String myData = "This is my data";

			StoreValue sv = new StoreValue.Builder(myData).withLocation(location).build();
			StoreValue.Response svResponse = client.execute(sv);

			client.shutdown();
			
			System.out.println("Complete");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/get")
	public void getEmployee() {
		
		try {
			RiakClient client = RiakClient.newClient(8087, "127.0.0.1");
			Location location = new Location(new Namespace("TestBucket"),"TestKey");

			FetchValue fv = new FetchValue.Builder(location).build();
			FetchValue.Response response = client.execute(fv);

			// Fetch object as String
			String value = response.getValue(String.class);
			System.out.println("Value is :" + value);

			client.shutdown();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnresolvedConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
