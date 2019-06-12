package io.swagger.model;

import java.util.Objects;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;

/**
 * SavingsAccount
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public class SavingsAccount extends Account {

  public SavingsAccount(BigDecimal balance, BigDecimal transactionLimit, BigDecimal absoluteLimit, Integer dailyLimit,
      Boolean active, String accountType) {
    super(balance, transactionLimit, absoluteLimit, dailyLimit, active, accountType);
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
