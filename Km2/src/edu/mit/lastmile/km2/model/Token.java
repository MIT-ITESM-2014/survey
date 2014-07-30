package edu.mit.lastmile.km2.model;

public class Token {

	private String token;
	private String secret;
	
	public Token(String token, String secret) {
		this.token = token;
		this.secret = secret;
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
	
}
