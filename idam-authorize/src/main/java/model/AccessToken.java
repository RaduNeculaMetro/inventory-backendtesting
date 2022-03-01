package model;

public class AccessToken {
  private String grant_type;
  private String client_id;
  private String realm_id;
  private String user_type;
  private String username;
  private String password;

  public String getGrant_type() {
    return grant_type;
  }

  public void setGrant_type(String grant_type) {
    this.grant_type = grant_type;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public String getRealm_id() {
    return realm_id;
  }

  public void setRealm_id(String realm_id) {
    this.realm_id = realm_id;
  }

  public String getUser_type() {
    return user_type;
  }

  public void setUser_type(String user_type) {
    this.user_type = user_type;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public AccessToken setPassword(String password) {
    this.password = password;
    return this;
  }

  public static class Builder {
    private String grant_type;
    private String client_id;
    private String realm_id;
    private String user_type;
    private String username;
    private String password;

    public Builder withGrant_type(String grant_type) {
      this.grant_type = grant_type;
      return this;
    }

    public Builder withClient_id(String client_id) {
      this.client_id = client_id;
      return this;
    }

    public Builder withRealm_id(String realm_id) {
      this.realm_id = realm_id;
      return this;
    }

    public Builder withUser_type(String user_type) {
      this.user_type = user_type;
      return this;
    }

    public Builder withUsername(String username) {
      this.username = username;
      return this;
    }

    public Builder withPassword(String password) {
      this.password = password;
      return this;
    }

    public AccessToken build() {
      AccessToken accessToken = new AccessToken();
      accessToken.client_id = client_id;
      accessToken.grant_type = grant_type;
      accessToken.user_type = user_type;
      accessToken.username = username;
      accessToken.password = password;
      accessToken.realm_id = realm_id;

      return accessToken;
    }
  }
}

