package com.dao;

import java.io.Serializable;

import org.json.JSONObject;

public interface DynamodbDAO<E, K extends Serializable, R extends Serializable> {

	E get(K key);
	
	E get(K hashKey, R rangeKey);
	
	/**
	 * get JSON Object from DB
	 * @param key primary key
	 * @return JSON object
	 */
	JSONObject getJSON(K key);
	
	/**
	 * get JSON Object from DB
	 * @param hashKey partition key
	 * @param rangeKey sort key
	 * @return JSON object
	 */
	JSONObject getJSON(K hashKey, R rangeKey);

	void save(E entity) throws Exception;

	E save(String jsonString) throws Exception;

	void delete(E e);

	void delete(String jsonString) throws Exception;


}
