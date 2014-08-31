package edu.mit.lastmile.km2.model;

public class Block {
	
	private long id;
	private long kmId;
	private long researchId; // Actual Block Id
	
	public Block(){}

	public Block(long id, long kmId, long researchId) {
		super();
		this.id = id;
		this.kmId = kmId;
		this.researchId = researchId;
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

	public long getResearchId() {
		return researchId;
	}

	public void setResearchId(long researchId) {
		this.researchId = researchId;
	}

	@Override
	public String toString() {
		return "" + researchId;
	}
	
}
