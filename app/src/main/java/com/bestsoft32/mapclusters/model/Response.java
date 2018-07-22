package com.bestsoft32.mapclusters.model;

import java.util.List;

public class Response{
	private List<LocationDataItem> locationData;
	private boolean error;
	private String status;

	public void setLocationData(List<LocationDataItem> locationData){
		this.locationData = locationData;
	}

	public List<LocationDataItem> getLocationData(){
		return locationData;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"locationData = '" + locationData + '\'' + 
			",error = '" + error + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}