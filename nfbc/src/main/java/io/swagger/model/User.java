package io.swagger.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
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
public class User {

    @JsonProperty("userId")
    @Id
    @SequenceGenerator(name = "userId_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId_seq")
    private Long userId = Long.valueOf(0);

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("streetname")
    private String streetname;

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("addressnumber")
    private Integer addressnumber;

    @JsonProperty("appendix")
    private String appendix;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("accounts")
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;

    @JsonProperty("isEmployee")
    private Boolean isEmployee;

    @JsonProperty("isActive")
    private Boolean isActive;

    public User() {
    }

    public User(String name, String hash, String streetname, String zipcode, Integer addressnumber, String appendix,
            String phoneNumber, List<Account> accounts, boolean isEmployee, boolean isActive) {
        // this.userId = userId;
        this.name = name;
        this.hash = hash;
        this.streetname = streetname;
        this.zipcode = zipcode;
        this.addressnumber = addressnumber;
        this.appendix = appendix;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
        this.isEmployee = isEmployee;
        this.isActive = isActive;
    }

    // public User userId(long userId) {
    // this.userId = userId;
    // return this;
    // }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")

    public long getuserId() {
        return userId;
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

    public void generateHash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        // Get complete hashed password in hex format
        hash = sb.toString();
    }

    public boolean compareHash(String password) throws NoSuchAlgorithmException {
        // System.out.println(hash);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        // Get complete hashed password in hex format
        // System.out.println(sb.toString());
        return hash.equals(sb.toString());
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

    public User Accounts(List<Account> accountList) {
        this.accounts = accountList;
        return this;
    }

    public User addAccount(Account account) {
        if (this.accounts == null) {
            this.accounts = new ArrayList<Account>();
        }
        this.accounts.add(account);
        return this;
    }

    @ApiModelProperty(value = "")
    @Valid
    public List<Account> getAccounts(String query) throws Exception {
        ArrayList<Account> res = new ArrayList<>();

        for (Account acc : accounts) {
            if (query == null) {
                if (acc.getIsActive()) {
                    res.add(acc);
                }
            } else {
                switch (query) {
                case "all":
                    res.add(acc);
                    break;
                case "disabled":
                    if (!acc.getIsActive()) {
                        res.add(acc);
                    }
                    break;
                default:
                    throw new Exception("Faulty query: " + query);
                }
            }
        }

        return res;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public User isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     * Get isEmployee
     *
     * @return isEmployee
     **/
    @ApiModelProperty(value = "")

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Boolean getIsEmployee() {
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
        return Objects.equals(this.userId, user.userId) && Objects.equals(this.name, user.name)
                && Objects.equals(this.email, user.email) && Objects.equals(this.hash, user.hash)
                && Objects.equals(this.streetname, user.streetname) && Objects.equals(this.zipcode, user.zipcode)
                && Objects.equals(this.addressnumber, user.addressnumber)
                && Objects.equals(this.appendix, user.appendix) && Objects.equals(this.phoneNumber, user.phoneNumber)
                && Objects.equals(this.accounts, user.accounts) && Objects.equals(this.isEmployee, user.isEmployee)
                && Objects.equals(this.isActive, user.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, hash, streetname, zipcode, addressnumber, appendix, phoneNumber,
                accounts, isEmployee, isActive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    id: ").append(toIndentedString(userId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
        sb.append("    streetname: ").append(toIndentedString(streetname)).append("\n");
        sb.append("    zipcode: ").append(toIndentedString(zipcode)).append("\n");
        sb.append("    addressnumber: ").append(toIndentedString(addressnumber)).append("\n");
        sb.append("    appendix: ").append(toIndentedString(appendix)).append("\n");
        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
        sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
        sb.append("    isEmployee: ").append(toIndentedString(isEmployee)).append("\n");
        sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
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
