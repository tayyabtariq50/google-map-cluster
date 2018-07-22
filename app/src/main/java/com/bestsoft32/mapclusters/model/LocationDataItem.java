package com.bestsoft32.mapclusters.model;

public class LocationDataItem{
	private String image;
	private String country;
	private String companyType;
	private String latitude;
	private String userLocation;
	private boolean newJoined;
	private String addressTwo;
	private String name;
	private String id;
	private String state;
	private String addressOne;
	private String category;
	private String longitude;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCompanyType(String companyType){
		this.companyType = companyType;
	}

	public String getCompanyType(){
		return companyType;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setUserLocation(String userLocation){
		this.userLocation = userLocation;
	}

	public String getUserLocation(){
		return userLocation;
	}

	public void setNewJoined(boolean newJoined){
		this.newJoined = newJoined;
	}

	public boolean isNewJoined(){
		return newJoined;
	}

	public void setAddressTwo(String addressTwo){
		this.addressTwo = addressTwo;
	}

	public String getAddressTwo(){
		return addressTwo;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setAddressOne(String addressOne){
		this.addressOne = addressOne;
	}

	public String getAddressOne(){
		return addressOne;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"LocationDataItem{" + 
			"image = '" + image + '\'' + 
			",country = '" + country + '\'' + 
			",companyType = '" + companyType + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",userLocation = '" + userLocation + '\'' + 
			",newJoined = '" + newJoined + '\'' + 
			",addressTwo = '" + addressTwo + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			",addressOne = '" + addressOne + '\'' + 
			",category = '" + category + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}
