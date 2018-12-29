package com.supercluster.cosmos.controller;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.UnresolvedConflictException;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.supercluster.cosmos.repository.RiakDataSource;

@RestController
public class TestEmployeeController {

	@Autowired
	RiakDataSource datasource;
	
	@GetMapping("/gettest")
	public String getEmployee() {
		
		try {

			RiakClient client = datasource.getReactClient();
			Location location = new Location(new Namespace("TestBucket"),"TestKey");

			FetchValue fv = new FetchValue.Builder(location).build();
			FetchValue.Response response = client.execute(fv);

			// Fetch object as String
			String value = response.getValue(String.class);
			System.out.println("Value is :" + value);

			client.shutdown();
			
			return value;
			
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
		return null;
	
	}
}
