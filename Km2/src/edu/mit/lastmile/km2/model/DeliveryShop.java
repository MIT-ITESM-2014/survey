/*
 * 
 * Delivery Shop model is internal for offline reference keeping only
 * Delivery Tracking data requires 5 shops to proceed
 * 
 */
package edu.mit.lastmile.km2.model;

public class DeliveryShop {
	
	private long id;
	private long kmId;
	private String shopId;
	
	public DeliveryShop(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKmId() {
		return kmId;
	}

	public void setKmId(long kmId) {
		this.kmId = kmId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "DeliveryShop [id=" + id + ", kmId=" + kmId + ", shopId="
				+ shopId + "]";
	}
	
}
