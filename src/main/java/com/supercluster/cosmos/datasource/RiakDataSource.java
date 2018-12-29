package com.supercluster.cosmos.datasource;

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
			return RiakClient.newClient(riakPort, riakHost);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
}
