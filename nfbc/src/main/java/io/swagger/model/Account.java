package io.swagger.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public class Account {
  @JsonProperty("IBAN")
  private String IBAN = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  @JsonProperty("TransactionLimit")
  private BigDecimal transactionLimit = null;

  @JsonProperty("DailyLimit")
  private Integer dailyLimit = null;

  @JsonProperty("Active")
  private Boolean active = null;

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

  public void setIBAN(String IBAN) {
    this.IBAN = IBAN;
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
        && Objects.equals(this.dailyLimit, account.dailyLimit) && Objects.equals(this.active, account.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(IBAN, balance, transactionLimit, dailyLimit, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");

    sb.append("    IBAN: ").append(toIndentedString(IBAN)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    transactionLimit: ").append(toIndentedString(transactionLimit)).append("\n");
    sb.append("    dailyLimit: ").append(toIndentedString(dailyLimit)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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
