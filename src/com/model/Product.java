package com.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "PRODUCT")
public class Product {

	@DynamoDBHashKey(attributeName="PRODUCT_ID")
	protected int productID;
	@DynamoDBAttribute(attributeName="NAME")
	protected String name;
		
	public static final String TABLE_NAME = "PRODUCT";
	public static final String PRODUCT_ID = "productID";
	public static final String NAME = "name";
		
	public Product() {

	}
	
	public Product(int productID, String name) {
		super();
//		if (productID != null && !productID.equals("")) {
//			this.productID = productID;
//		}
		this.productID = productID;
		this.name = name;
	}
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JSONObject toJSON() throws JSONException {
        
		JSONObject productJSON = new JSONObject();
		productJSON.put(PRODUCT_ID, getProductID());
		productJSON.put(NAME, getName());
        return productJSON;
	}
}