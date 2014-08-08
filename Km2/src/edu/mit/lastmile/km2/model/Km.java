package edu.mit.lastmile.km2.model;

public class Km {

	private long id;
	private long kmId; // Web System Db
	private String name;
	private String location;
	private String comments;
	
	public Km(){}
	
	public Km(long id, long kmId, String name, String location, String comments) {
		super();
		this.id = id;
		this.kmId = kmId;
		this.name = name;
		this.location = location;
		this.comments = comments;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Km [id=" + id + ", kmId=" + kmId + ", name=" + name
				+ ", location=" + location + ", comments=" + comments + "]";
	}
	
}
