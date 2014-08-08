package edu.mit.lastmile.km2.model;

import android.net.Uri;

public class Shop {
	
	private long id;
	private int kmId; // FIXME change to long
	private long streetId;
	private long blockId;
	private String name;
	private String shopType;
	private String shopSize;
	private double frontLength;
	private int totalFloors;
	private int hasLoadingArea; // boolean int
	private int loadingAreaType; // category int
	private double lat;
	private double lng;
	private String notes;
	private Uri image;
	private String shopId; // unique string code
	
	public Shop(){}
	
	public Shop(long id, int kmId, long streetId, long blockId, String name,
			String shopType, String shopSize, double frontLength,
			int totalFloors, int hasLoadingArea, int loadingAreaType,
			int lat, int lng, String notes, Uri image, String shopId) {
		this.id = id;
		this.kmId = kmId;
		this.streetId = streetId;
		this.blockId = blockId;
		this.name = name;
		this.shopType = shopType;
		this.shopSize = shopSize;
		this.frontLength = frontLength;
		this.totalFloors = totalFloors;
		this.hasLoadingArea = hasLoadingArea;
		this.loadingAreaType = loadingAreaType;
		this.lat = lat;
		this.lng = lng;
		this.notes = notes;
		this.image = image;
		this.shopId = shopId;
	}
	
	public boolean hasImage(){
		return image != null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getKmId() {
		return kmId;
	}

	public void setKmId(int kmId) {
		this.kmId = kmId;
	}

	public long getStreetId() {
		return streetId;
	}

	public void setStreetId(long streetId) {
		this.streetId = streetId;
	}

	public long getBlockId() {
		return blockId;
	}

	public void setBlockId(long blockId) {
		this.blockId = blockId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getShopSize() {
		return shopSize;
	}

	public void setShopSize(String shopSize) {
		this.shopSize = shopSize;
	}

	public double getFrontLength() {
		return frontLength;
	}

	public void setFrontLength(double frontLength) {
		this.frontLength = frontLength;
	}

	public int getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {
		this.totalFloors = totalFloors;
	}

	public int getHasLoadingArea() {
		return hasLoadingArea;
	}

	public void setHasLoadingArea(int hasLoadingArea) {
		this.hasLoadingArea = hasLoadingArea;
	}

	public int getLoadingAreaType() {
		return loadingAreaType;
	}

	public void setLoadingAreaType(int loadingAreaType) {
		this.loadingAreaType = loadingAreaType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Uri getImage() {
		return image;
	}

	public void setImage(Uri image) {
		this.image = image;
	}

	public void setImage(String image){
		if(image != null){
			this.image = Uri.parse(image);
		}
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", kmId=" + kmId + ", streetId=" + streetId
				+ ", blockId=" + blockId + ", name=" + name + ", shopType="
				+ shopType + ", shopSize=" + shopSize + ", frontLength="
				+ frontLength + ", totalFloors=" + totalFloors
				+ ", hasLoadingArea=" + hasLoadingArea + ", loadingAreaType="
				+ loadingAreaType + ", lat=" + lat + ", lng=" + lng
				+ ", notes=" + notes + ", image=" + image + ", shopId="
				+ shopId + "]";
	}
	
}
