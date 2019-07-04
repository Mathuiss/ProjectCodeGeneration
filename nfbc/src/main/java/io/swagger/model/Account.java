package io.swagger.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Random;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.validation.annotation.Validated;

import io.swagger.CustomIbanGenerator;
import io.swagger.annotations.ApiModelProperty;

/**
 * Account
 */

// @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
// property = "type")
// @JsonSubTypes({ @Type(value = SavingsAccount.class, name = "savings"),
// @Type(value = CurrentAccount.class, name = "current") })
@Validated
@Entity
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public abstract class Account {
  Random random = new Random();

  @JsonProperty("userId")
  private Long userId = null;

  // @ManyToOne()
  // @JoinColumn(name = "user")
  // @JsonProperty("user")
  // private User user = null;

  @JsonProperty("iban")
  @Id
  private String iban = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  @JsonProperty("transactionLimit")
  private BigDecimal transactionLimit = null;

  @JsonProperty("absoluteLimit")
  protected BigDecimal absoluteLimit = null;

  @JsonProperty("dailyLimit")
  private Integer dailyLimit = null;

  @JsonProperty("isActive")
  private Boolean isActive = null;

  @JsonProperty("accountType")
  private String accountType = null;

  protected Account(Long userId/* , User user */, String iban, BigDecimal balance, BigDecimal transactionLimit,
      BigDecimal absoluteLimit, Integer dailyLimit, Boolean isActive, String accountType) {
    this.userId = userId;
    // this.user = user;
    this.iban = iban;
    this.balance = balance;
    this.transactionLimit = transactionLimit;
    this.absoluteLimit = absoluteLimit;
    this.dailyLimit = dailyLimit;
    this.isActive = isActive;
    this.accountType = accountType;
  }

  protected Account() {
  }

  public Account userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * 
   * @return userId
   **/
  @ApiModelProperty(value = "")

  @Valid
  public Long getUserId() {
    return userId;
  }

  public void setuserId(Long userId) {
    this.userId = userId;
  }

  // public User getUser() {
  // return user;
  // }

  // public void setUser(User user) {
  // this.user = user;
  // }

  public Account iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * Get iban
   * 
   * @return iban
   **/
  @ApiModelProperty(value = "")

  @Pattern(regexp = "NL\\d{2}INHO0\\d{9}")
  public String getIban() {
    return iban;
  }

  // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iban_seq")
  // @GenericGenerator(name = "iban_seq", strategy =
  // "io.swagger.CustomIbanGenerator", parameters = {
  // @Parameter(name = CustomIbanGenerator.COUNTRY_CODE_PARAMETER, value = "NL"),
  // @Parameter(name = CustomIbanGenerator.BANK_CODE_PARAMETER, value = "INHO0")
  // })
  public void setIban() {
    final String COUNTRY_CODE = "NL";
    NumberFormat controlFormatter = new DecimalFormat("00");
    String controlCode = controlFormatter.format(random.nextInt(99));
    final String BANK_CODE = "INHO0";
    NumberFormat accountFormatter = new DecimalFormat("00");
    String accountCode = accountFormatter.format(random.nextInt(999999999));

    this.iban = COUNTRY_CODE + controlCode + BANK_CODE + accountCode;
  }

  public Account balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * 
   * @return balance
   **/
  @ApiModelProperty(value = "")

  @Valid
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Account transactionLimit(BigDecimal transactionLimit) {
    this.transactionLimit = transactionLimit;
    return this;
  }

  /**
   * Get transactionLimit
   * 
   * @return transactionLimit
   **/
  @ApiModelProperty(value = "")

  @Valid
  public BigDecimal getTransactionLimit() {
    return transactionLimit;
  }

  public void setTransactionLimit(BigDecimal transactionLimit) {
    this.transactionLimit = transactionLimit;
  }

  public Account absoluteLimit(BigDecimal absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
    return this;
  }

  /**
   * Get absoluteLimit
   * 
   * @return absoluteLimit
   **/
  @ApiModelProperty(value = "")

  @Valid
  public BigDecimal getAbsoluteLimit() {
    return absoluteLimit;
  }

  public abstract void setAbsoluteLimit(BigDecimal absoluteLimit) throws Exception;

  public Account dailyLimit(Integer dailyLimit) {
    this.dailyLimit = dailyLimit;
    return this;
  }

  /**
   * Get dailyLimit
   * 
   * @return dailyLimit
   **/
  @ApiModelProperty(value = "")

  public Integer getDailyLimit() {
    return dailyLimit;
  }

  public void setDailyLimit(Integer dailyLimit) {
    this.dailyLimit = dailyLimit;
  }

  public Account isActive(Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * 
   * @return isActive
   **/
  @ApiModelProperty(value = "")

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Account accountType(String accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Get accountType
   * 
   * @return accountType
   **/
  @ApiModelProperty(value = "")

  public String getAccountType() {
    return accountType;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.userId, account.userId) && Objects.equals(this.iban, account.iban)
        && Objects.equals(this.balance, account.balance)
        && Objects.equals(this.transactionLimit, account.transactionLimit)
        && Objects.equals(this.absoluteLimit, account.absoluteLimit)
        && Objects.equals(this.dailyLimit, account.dailyLimit) && Objects.equals(this.isActive, account.isActive)
        && Objects.equals(this.accountType, account.accountType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, iban, balance, transactionLimit, absoluteLimit, dailyLimit, isActive, accountType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");

    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    transactionLimit: ").append(toIndentedString(transactionLimit)).append("\n");
    sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
    sb.append("    dailyLimit: ").append(toIndentedString(dailyLimit)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
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
