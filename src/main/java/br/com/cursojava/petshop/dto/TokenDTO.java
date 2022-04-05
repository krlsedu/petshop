package br.com.cursojava.petshop.dto;

public class TokenDTO {
	private String accesToken;
	
	private Integer expiresIn;
	
	public String getAccessToken() {
		return accesToken;
	}

	public void setAccess_token(String access_token) {
		this.accesToken = access_token;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpires_in(Integer expires_in) {
		this.expiresIn = expires_in;
	}
	
}
