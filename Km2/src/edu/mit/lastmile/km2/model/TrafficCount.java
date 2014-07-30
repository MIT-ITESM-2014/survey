/*
 *	Traffic Count Model
 *	Mimics original km2 database model for compatibility
 *	km2_v2 uses different naming conventions.
 */
package edu.mit.lastmile.km2.model;

import java.util.Date;

public class TrafficCount {
	
	private int id;
	private Date startedAt;
	private Date endedAt;
	private int cars;
	private int taxi;
	private int pickupTrucks;
	private int articulatedTrucks;
	private int rigidTrucks;
	private int vans;
	private int buses;
	private int bikes;
	private int motorbikes;
	private int pedestrians;
	private int status;
	
	public TrafficCount(){}
	
	public TrafficCount(int id, Date startedAt, Date endedAt, int cars, int taxi,
			int pickupTrucks, int articulatedTrucks, int rigidTrucks, int vans,
			int buses, int bikes, int motorbikes, int pedestrians, int status) {
		this.id = id;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
		this.cars = cars;
		this.taxi = taxi;
		this.pickupTrucks = pickupTrucks;
		this.articulatedTrucks = articulatedTrucks;
		this.rigidTrucks = rigidTrucks;
		this.vans = vans;
		this.buses = buses;
		this.bikes = bikes;
		this.motorbikes = motorbikes;
		this.pedestrians = pedestrians;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(Date endedAt) {
		this.endedAt = endedAt;
	}

	public int getCars() {
		return cars;
	}

	public void setCars(int cars) {
		this.cars = cars;
	}

	public int getTaxi() {
		return taxi;
	}

	public void setTaxi(int taxi) {
		this.taxi = taxi;
	}

	public int getPickupTrucks() {
		return pickupTrucks;
	}

	public void setPickupTrucks(int pickupTrucks) {
		this.pickupTrucks = pickupTrucks;
	}

	public int getArticulatedTrucks() {
		return articulatedTrucks;
	}

	public void setArticulatedTrucks(int articulatedTrucks) {
		this.articulatedTrucks = articulatedTrucks;
	}

	public int getRigidTrucks() {
		return rigidTrucks;
	}

	public void setRigidTrucks(int rigidTrucks) {
		this.rigidTrucks = rigidTrucks;
	}

	public int getVans() {
		return vans;
	}

	public void setVans(int vans) {
		this.vans = vans;
	}

	public int getBuses() {
		return buses;
	}

	public void setBuses(int buses) {
		this.buses = buses;
	}

	public int getBikes() {
		return bikes;
	}

	public void setBikes(int bikes) {
		this.bikes = bikes;
	}
	
	public int getMotorbikes() {
		return motorbikes;
	}

	public void setMotorbikes(int motorbikes) {
		this.motorbikes = motorbikes;
	}

	public int getPedestrians() {
		return pedestrians;
	}

	public void setPedestrians(int pedestrians) {
		this.pedestrians = pedestrians;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TrafficCount [startedAt=" + startedAt + ", endedAt=" + endedAt
				+ ", cars=" + cars + ", taxi=" + taxi + ", pickupTrucks="
				+ pickupTrucks + ", articulatedTrucks=" + articulatedTrucks
				+ ", rigidTrucks=" + rigidTrucks + ", vans=" + vans
				+ ", buses=" + buses + ", bikes=" + bikes + ", motorbikes="
				+ motorbikes + ", pedestrians=" + pedestrians + ", status="
				+ status + "]";
	}

}
