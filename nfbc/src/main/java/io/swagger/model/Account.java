package io.swagger.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.validation.annotation.Validated;

import io.swagger.CustomIbanGenerator;
import io.swagger.annotations.ApiModelProperty;

/**
 * Account
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = SavingsAccount.class, name = "savings"),
    @Type(value = CurrentAccount.class, name = "current") })
@Entity
@Validated
@Entity
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public abstract class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iban_seq")
  @GenericGenerator(name = "iban_seq", strategy = "io.swagger.CustomIbanGenerator", parameters = {
      @Parameter(name = CustomIbanGenerator.COUNTRY_CODE_PARAMETER, value = "NL"),
      @Parameter(name = CustomIbanGenerator.BANK_CODE_PARAMETER, value = "INHO0") })
  @ManyToOne
  @JoinColumn(name="id", nullable = false)
  private User user;
  @JsonProperty("IBAN")
  @Id
  private String IBAN = null;

  @JsonProperty("Balance")
  private BigDecimal balance = null;

  @JsonProperty("TransactionLimit")
  private BigDecimal transactionLimit = null;

  @JsonProperty("AbsoluteLimit")
  protected BigDecimal absoluteLimit = null;

  @JsonProperty("DailyLimit")
  private Integer dailyLimit = null;

  @JsonProperty("Active")
  private Boolean active = null;

  @JsonProperty("AccountType")
  private String accountType = null;

  // Without iban, for new accounts
  protected Account(BigDecimal balance, BigDecimal transactionLimit, BigDecimal absoluteLimit, Integer dailyLimit,
      Boolean active, String accountType) {
    this.balance = balance;
    this.transactionLimit = transactionLimit;
    this.absoluteLimit = absoluteLimit;
    this.dailyLimit = dailyLimit;
    this.active = active;
    this.accountType = accountType;
  }

  // With iban, for existing accounts
  protected Account(String iban, BigDecimal balance, BigDecimal transactionLimit, BigDecimal absoluteLimit,
      Integer dailyLimit, Boolean active, String accountType) {
    this.IBAN = iban;
    this.balance = balance;
    this.transactionLimit = transactionLimit;
    this.absoluteLimit = absoluteLimit;
    this.dailyLimit = dailyLimit;
    this.active = active;
    this.accountType = accountType;
  }

  protected Account() {
  }

  public Account IBAN(String IBAN) {
    this.IBAN = IBAN;
    return this;
  }

  /**
   * Get IBAN
   * 
   * @return IBAN
   **/
  @ApiModelProperty(value = "")

  @Pattern(regexp = "NL\\d{2}INHO0\\d{9}")
  public String getIBAN() {
    return IBAN;
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

  public Account active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * 
   * @return active
   **/
  @ApiModelProperty(value = "")

  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
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

  public String accountType() {
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
    return Objects.equals(this.IBAN, account.IBAN) && Objects.equals(this.balance, account.balance)
        && Objects.equals(this.transactionLimit, account.transactionLimit)
        && Objects.equals(this.absoluteLimit, account.absoluteLimit)
        && Objects.equals(this.dailyLimit, account.dailyLimit) && Objects.equals(this.active, account.active)
        && Objects.equals(this.accountType, account.accountType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(IBAN, balance, transactionLimit, absoluteLimit, dailyLimit, active, accountType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");

    sb.append("    IBAN: ").append(toIndentedString(IBAN)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    transactionLimit: ").append(toIndentedString(transactionLimit)).append("\n");
    sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
    sb.append("    dailyLimit: ").append(toIndentedString(dailyLimit)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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
