package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * User
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User {
  @JsonProperty("id")
  @Id
  private Integer id;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("hash")
  private String hash = null;

  @JsonProperty("streetname")
  private String streetname = null;

  @JsonProperty("zipcode")
  private String zipcode = null;

  @JsonProperty("addressnumber")
  private Integer addressnumber = null;

  @JsonProperty("appendix")
  private String appendix = null;

  @JsonProperty("PhoneNumber")
  private String phoneNumber = null;

  @JsonProperty("CurrentAccounts")
  @Valid
  @OneToMany(mappedBy = "user")
  private List<CurrentAccount> currentAccounts;

  @JsonProperty("SavingsAccounts")
  @Valid
  @OneToMany(mappedBy = "user")
  private List<SavingsAccount> savingsAccounts;

  @JsonProperty("IsEmployee")
  private Boolean isEmployee = null;

  public User id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * 
   * @return id
   **/
  @ApiModelProperty(value = "")

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * 
   * @return name
   **/
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * 
   * @return email
   **/
  @ApiModelProperty(value = "")

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User hash(String hash) {
    this.hash = hash;
    return this;
  }

  /**
   * Get hash
   * 
   * @return hash
   **/
  @ApiModelProperty(value = "")

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public User streetname(String streetname) {
    this.streetname = streetname;
    return this;
  }

  /**
   * Get streetname
   * 
   * @return streetname
   **/
  @ApiModelProperty(value = "")

  public String getStreetname() {
    return streetname;
  }

  public void setStreetname(String streetname) {
    this.streetname = streetname;
  }

  public User zipcode(String zipcode) {
    this.zipcode = zipcode;
    return this;
  }

  /**
   * Get zipcode
   * 
   * @return zipcode
   **/
  @ApiModelProperty(value = "")

  @Pattern(regexp = "\\d{4}[A-Z]{2}")
  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public User addressnumber(Integer addressnumber) {
    this.addressnumber = addressnumber;
    return this;
  }

  /**
   * Get addressnumber
   * 
   * @return addressnumber
   **/
  @ApiModelProperty(value = "")

  public Integer getAddressnumber() {
    return addressnumber;
  }

  public void setAddressnumber(Integer addressnumber) {
    this.addressnumber = addressnumber;
  }

  public User appendix(String appendix) {
    this.appendix = appendix;
    return this;
  }

  /**
   * Get appendix
   * 
   * @return appendix
   **/
  @ApiModelProperty(value = "")

  public String getAppendix() {
    return appendix;
  }

  public void setAppendix(String appendix) {
    this.appendix = appendix;
  }

  public User phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * 
   * @return phoneNumber
   **/
  @ApiModelProperty(value = "")

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public User currentAccounts(List<CurrentAccount> currentAccounts) {
    this.currentAccounts = currentAccounts;
    return this;
  }

  public User addCurrentAccountsItem(CurrentAccount currentAccountsItem) {
    if (this.currentAccounts == null) {
      this.currentAccounts = new ArrayList<CurrentAccount>();
    }
    this.currentAccounts.add(currentAccountsItem);
    return this;
  }

  /**
   * Get currentAccounts
   * 
   * @return currentAccounts
   **/
  @ApiModelProperty(value = "")
  @Valid
  public List<CurrentAccount> getCurrentAccounts() {
    return currentAccounts;
  }

  public void setCurrentAccounts(List<CurrentAccount> currentAccounts) {
    this.currentAccounts = currentAccounts;
  }

  public User savingsAccounts(List<SavingsAccount> savingsAccounts) {
    this.savingsAccounts = savingsAccounts;
    return this;
  }

  public User addSavingsAccountsItem(SavingsAccount savingsAccountsItem) {
    if (this.savingsAccounts == null) {
      this.savingsAccounts = new ArrayList<SavingsAccount>();
    }
    this.savingsAccounts.add(savingsAccountsItem);
    return this;
  }

  /**
   * Get savingsAccounts
   * 
   * @return savingsAccounts
   **/
  @ApiModelProperty(value = "")
  @Valid
  public List<SavingsAccount> getSavingsAccounts() {
    return savingsAccounts;
  }

  public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
    this.savingsAccounts = savingsAccounts;
  }

  public User isEmployee(Boolean isEmployee) {
    this.isEmployee = isEmployee;
    return this;
  }

  /**
   * Get isEmployee
   * 
   * @return isEmployee
   **/
  @ApiModelProperty(value = "")

  public Boolean isIsEmployee() {
    return isEmployee;
  }

  public void setIsEmployee(Boolean isEmployee) {
    this.isEmployee = isEmployee;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
        && Objects.equals(this.email, user.email) && Objects.equals(this.hash, user.hash)
        && Objects.equals(this.streetname, user.streetname) && Objects.equals(this.zipcode, user.zipcode)
        && Objects.equals(this.addressnumber, user.addressnumber) && Objects.equals(this.appendix, user.appendix)
        && Objects.equals(this.phoneNumber, user.phoneNumber)
        && Objects.equals(this.currentAccounts, user.currentAccounts)
        && Objects.equals(this.savingsAccounts, user.savingsAccounts)
        && Objects.equals(this.isEmployee, user.isEmployee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, hash, streetname, zipcode, addressnumber, appendix, phoneNumber,
        currentAccounts, savingsAccounts, isEmployee);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    streetname: ").append(toIndentedString(streetname)).append("\n");
    sb.append("    zipcode: ").append(toIndentedString(zipcode)).append("\n");
    sb.append("    addressnumber: ").append(toIndentedString(addressnumber)).append("\n");
    sb.append("    appendix: ").append(toIndentedString(appendix)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    currentAccounts: ").append(toIndentedString(currentAccounts)).append("\n");
    sb.append("    savingsAccounts: ").append(toIndentedString(savingsAccounts)).append("\n");
    sb.append("    isEmployee: ").append(toIndentedString(isEmployee)).append("\n");
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

  public User(Integer id, String name, String email, String hash, String streetname, String zipcode,
      Integer addressnumber, String appendix, String phoneNumber, @Valid List<CurrentAccount> currentAccounts,
      @Valid List<SavingsAccount> savingsAccounts, Boolean isEmployee) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.hash = hash;
    this.streetname = streetname;
    this.zipcode = zipcode;
    this.addressnumber = addressnumber;
    this.appendix = appendix;
    this.phoneNumber = phoneNumber;
    this.currentAccounts = currentAccounts;
    this.savingsAccounts = savingsAccounts;
    this.isEmployee = isEmployee;
  }

  public User() {
  }
}
