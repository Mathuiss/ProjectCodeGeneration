package io.swagger.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * Transaction
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
public class Transaction {
  @JsonProperty("TransactionId")
  @Id
  @SequenceGenerator(name = "transaction_sequence", initialValue = 0)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
  private long transactionId = 0;

  @JsonProperty("sender")
  private String sender = null;

  @JsonProperty("reciever")
  private String reciever = null;

  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("timestamp")
  private String timestamp = null;

  public Transaction transactionId(Integer transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public Transaction() {
  }

  public Transaction(long id, String sender, String reciever, long userId, double amount, String timestamp) {
    this.transactionId = id;
    this.sender = sender;
    this.reciever = reciever;
    this.userId = userId;
    this.amount = BigDecimal.valueOf(amount);
    this.timestamp = timestamp;
  }

  /**
   * Get transactionId
   * 
   * @return transactionId
   **/
  @ApiModelProperty(value = "")

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  public Transaction sender(String sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Get sender
   * 
   * @return sender
   **/
  @ApiModelProperty(value = "")

  // @Pattern(regexp = "NL\\d{2}INHO0\\d{9}")
  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public Transaction reciever(String reciever) {
    this.reciever = reciever;
    return this;
  }

  /**
   * Get reciever
   * 
   * @return reciever
   **/
  @ApiModelProperty(value = "")

  // @Pattern(regexp = "NL\\d{2}INHO0\\d{9}")
  public String getReciever() {
    return reciever;
  }

  public void setReciever(String reciever) {
    this.reciever = reciever;
  }

  public Transaction userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * 
   * @return userId
   **/
  @ApiModelProperty(value = "")

  public Long getUserId() {
    return userId;
  }

  public void setUser(Long userId) {
    this.userId = userId;
  }

  public Transaction amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * 
   * @return amount
   **/
  @ApiModelProperty(value = "")

  @Valid
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Transaction timestamp(String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * 
   * @return timestamp
   **/
  @ApiModelProperty(value = "")

  @Valid
  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.transactionId, transaction.transactionId)
        && Objects.equals(this.sender, transaction.sender) && Objects.equals(this.reciever, transaction.reciever)
        && Objects.equals(this.userId, transaction.userId) && Objects.equals(this.amount, transaction.amount)
        && Objects.equals(this.timestamp, transaction.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, sender, reciever, userId, amount, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");

    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    reciever: ").append(toIndentedString(reciever)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
