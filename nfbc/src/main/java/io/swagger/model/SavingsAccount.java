package io.swagger.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;

/**
 * SavingsAccount
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public class SavingsAccount extends Account {

  @JsonProperty("AbsoluteLimit")
  private BigDecimal absoluteLimit = null;

  public SavingsAccount(Long userId, /* User user, */ String iban, BigDecimal balance, BigDecimal transactionLimit,
      BigDecimal absoluteLimit, Integer dailyLimit, Boolean isActive, String accountType) {
    super(userId, /* user, */ iban, balance, transactionLimit, absoluteLimit, dailyLimit, isActive, accountType);
  }

  public SavingsAccount() {
  }

  // absolute limit must be at least 0 for a Savings Account;
  @Override
  public void setAbsoluteLimit(BigDecimal absoluteLimit) throws Exception {
    if (absoluteLimit.compareTo(BigDecimal.ZERO) >= 0) {
      this.absoluteLimit = absoluteLimit;
    } else {
      throw new Exception(
          "The given limit: " + absoluteLimit + "is too low. The absolute limit of savings accounts is 0.");
    }
  }

  // @Override
  // public boolean equals(java.lang.Object o) {
  // if (this == o) {
  // return true;
  // }
  // if (o == null || getClass() != o.getClass()) {
  // return false;
  // }
  // SavingsAccount savingsAccount = (SavingsAccount) o;
  // return Objects.equals(this.absoluteLimit, savingsAccount.absoluteLimit);
  // }

  // @Override
  // public int hashCode() {
  // return Objects.hash(absoluteLimit);
  // }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SavingsAccount {\n");
    sb.append("}");
    return sb.toString();
  }
}
