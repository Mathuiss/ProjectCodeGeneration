package io.swagger.model;

import java.util.Objects;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;

/**
 * CurrentAccount
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public class CurrentAccount extends Account {

  public CurrentAccount(String iban, BigDecimal balance, BigDecimal transactionLimit, BigDecimal absoluteLimit,
      Integer dailyLimit, Boolean active, String accountType) {
    super(iban, balance, transactionLimit, absoluteLimit, dailyLimit, active, accountType);
  }

  @Override
  public void setAbsoluteLimit(BigDecimal absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
  }

  // @Override
  // public boolean equals(java.lang.Object o) {
  // if (this == o) {
  // return true;
  // }
  // if (o == null || getClass() != o.getClass()) {
  // return false;
  // }
  // CurrentAccount currentAccount = (CurrentAccount) o;
  // return Objects.equals(this.absoluteLimit, currentAccount.absoluteLimit);
  // }

  // @Override
  // public int hashCode() {
  // return Objects.hash(absoluteLimit);
  // }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrentAccount {\n");
    sb.append("}");
    return sb.toString();
  }
}
