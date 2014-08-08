package edu.mit.lastmile.km2.model;

public class Token {

	private long id;
	private String token;
	private String secret;
	
	public Token() {}
	
	public Token(String token, String secret){
		setToken(token);
		setSecret(secret);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", token=" + token + ", secret=" + secret
				+ ", toString()=" + super.toString() + "]";
	}
	
}
