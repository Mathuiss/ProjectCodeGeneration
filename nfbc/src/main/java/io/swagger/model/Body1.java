package io.swagger.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * Body1
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public class Body1 {
  @Id
  @JsonProperty("sessionToken")
  @SequenceGenerator(name = "SessionToken_Sequence", initialValue = 0)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SessionToken_Sequence")
  private String sessionToken = null;

  @JsonProperty("userRole")
  private String userRole = null;

  public Body1 sessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
    return this;
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

  public void setSessionToken(long unique) throws NoSuchAlgorithmException {
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

  public Body1 userRole(String userRole) {
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
    Body1 body1 = (Body1) o;
    return Objects.equals(this.sessionToken, body1.sessionToken) && Objects.equals(this.userRole, body1.userRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionToken, userRole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body1 {\n");

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
}
