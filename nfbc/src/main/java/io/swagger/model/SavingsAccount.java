package io.swagger.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * SavingsAccount
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Entity
public class SavingsAccount extends Account{

    @JsonProperty("AbsoluteLimit")
    private BigDecimal absoluteLimit = null;

    public SavingsAccount absoluteLimit(BigDecimal absoluteLimit) {
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

    public void setAbsoluteLimit(BigDecimal absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SavingsAccount savingsAccount = (SavingsAccount) o;
        return Objects.equals(this.absoluteLimit, savingsAccount.absoluteLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(absoluteLimit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SavingsAccount {\n");

        sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
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
