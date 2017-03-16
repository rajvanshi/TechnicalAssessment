package com.dao;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractDAO<E, K extends Serializable, R extends Serializable>
		implements DynamodbDAO<E, K, R> {

	private static DynamoDBMapper dbMapper;

	public List<E> getAll() {
		
		return getDynamoDB().scan(getClazz(), new DynamoDBScanExpression());
		
	}
 
	public static DynamoDBMapper getDynamoDB() {

		if (dbMapper == null) {

		}
		return dbMapper;
	}

	public E get(K key) {

		
		E load = (E) getDynamoDB().load(getClazz(), key);	
		return load;
		
	}

	@Override
	public E get(K hashKey, R rangeKey) {
		
		E load = (E) getDynamoDB().load(getClazz(), hashKey, rangeKey);
		return load;
		
	}

	@Override
	public JSONObject getJSON(K hashKey, R rangeKey) {
		
		return new JSONObject(get(hashKey, rangeKey));
		
	}

	public JSONObject getJSON(K key) {
		
		E load = get(key);
		return new JSONObject(load);
		
	}

	public void save(E entity) throws Exception {

		getDynamoDB().save(entity);
	}

	public E save(String jsonString) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		E entity = mapper.readValue(jsonString, getClazz());
		save(entity);
		com.amazonaws.http.IdleConnectionReaper.shutdown();
		return entity;
	}

	public void delete(E e) {
		getDynamoDB().delete(e);
	}

	public void delete(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		getDynamoDB().delete(mapper.readValue(jsonString, getClazz()));
		com.amazonaws.http.IdleConnectionReaper.shutdown();
	}

	public abstract Class<E> getClazz();
}
