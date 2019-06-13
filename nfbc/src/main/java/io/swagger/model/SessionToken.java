package io.swagger.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * SessionToken
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public class SessionToken {
  @Id
  @JsonProperty("sessionToken")
  private String sessionToken = null;

  @JsonProperty("user")
  private long userId;

  @JsonProperty("userRole")
  private String userRole = null;

  @JsonProperty("timestamp")
  private String timestamp = null;

  @JsonProperty("isActive")
  private boolean isActive = false;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public SessionToken sessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
    return this;
  }

  public SessionToken() {
  }

  /**
   * Get sessionToken
   * 
   * @return sessionToken
   **/
  @ApiModelProperty(value = "")

  public String getSessionToken() {
    return sessionToken;
  }

  public void setSessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
  }

  public void generateSessionToken(long unique) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.update(Long.toString(unique).getBytes());
    byte[] bytes = digest.digest();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }
    // Get complete hashed password in hex format
    this.sessionToken = sb.toString();
  }

  public SessionToken userRole(String userRole) {
    this.userRole = userRole;
    return this;
  }

  /**
   * Get userRole
   * 
   * @return userRole
   **/
  @ApiModelProperty(value = "")

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionToken sessionToken = (SessionToken) o;
    return Objects.equals(this.sessionToken, sessionToken.sessionToken)
        && Objects.equals(this.userRole, sessionToken.userRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionToken, userRole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionToken {\n");

    sb.append("    sessionToken: ").append(toIndentedString(sessionToken)).append("\n");
    sb.append("    userRole: ").append(toIndentedString(userRole)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public SessionToken(String sessionToken, long userId, String userRole, String timestamp, boolean isActive) {
    this.sessionToken = sessionToken;
    this.userId = userId;
    this.userRole = userRole;
    this.timestamp = timestamp;
    this.isActive = isActive;
  }
}
