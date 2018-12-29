package com.supercluster.cosmos.repository;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.basho.riak.client.api.RiakClient;

@Component
public class RiakDataSource {

	@Value("${spring.riak.host}")
	String riakHost;
	@Value("${spring.riak.port}")
	int riakPort;
	
	public RiakClient getReactClient()
	{
		try {
			return RiakClient.newClient(riakPort, riakHost); //8087, "127.0.0.1"
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
