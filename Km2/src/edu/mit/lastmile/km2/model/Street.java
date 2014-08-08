package edu.mit.lastmile.km2.model;

public class Street {

	private long id;
	private long kmId;
	private long blockId;
	private long researchId; // Actual Street Id
	
	public Street(long id, long kmId, long blockId, long researchId) {
		super();
		this.id = id;
		this.kmId = kmId;
		this.blockId = blockId;
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

	public long getBlockId() {
		return blockId;
	}

	public void setBlockId(long blockId) {
		this.blockId = blockId;
	}
	
	public long getResearchId() {
		return researchId;
	}

	public void setResearchId(long researchId) {
		this.researchId = researchId;
	}

	@Override
	public String toString() {
		return "Street [id=" + id + ", kmId=" + kmId + ", blockId=" + blockId
				+ ", researchId=" + researchId + "]";
	}

}
