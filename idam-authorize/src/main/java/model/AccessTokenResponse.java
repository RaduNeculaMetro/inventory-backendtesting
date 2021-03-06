package model;

public class AccessTokenResponse {

  String access_token;
  String token_type;
  String expires_in;
  String refresh_token;

  public AccessTokenResponse() {

  }

  public AccessTokenResponse (String access_token) {
    this.access_token = access_token;
  }

  public String getAccess_token() {
    return access_token;
  }

  public AccessTokenResponse setAccess_token(String access_token) {
    this.access_token = access_token;
    return this;
  }

  public String getToken_type() {
    return token_type;
  }

  public AccessTokenResponse setToken_type(String token_type) {
    this.token_type = token_type;
    return this;
  }

  public String getExpires_in() {
    return expires_in;
  }

  public AccessTokenResponse setExpires_in(String expires_in) {
    this.expires_in = expires_in;
    return this;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public AccessTokenResponse setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
    return this;
  }
}