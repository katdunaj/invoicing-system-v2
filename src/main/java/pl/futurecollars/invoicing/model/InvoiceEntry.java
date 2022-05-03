package pl.futurecollars.invoicing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "invoice_entries")
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  @ApiModelProperty(value = "ID generated by application", required = true, example = "74241b56-4629-4f74-b1bd-aff37cd2fbdc")
  private int entryId;

  @ApiModelProperty(value = "Product description", required = true, example = "Młotek")
  private String description;

  @ApiModelProperty(value = "Product unit", required = true, example = "kg")
  private String unit;

  @ApiModelProperty(value = "Product unit price", required = true, example = "12.05")
  private BigDecimal price;

  @ApiModelProperty(value = "Product quantity", required = true, example = "10")
  private BigDecimal quantity;

  @ApiModelProperty(value = "Product total price", required = true, example = "120.5")
  private BigDecimal totalPrice;

  @ApiModelProperty(value = "Product tax rate", required = true, example = "VAT_23")
  @Enumerated(EnumType.STRING)
  private Vat vatRate;

  @ApiModelProperty(hidden = true)
  private BigDecimal vatValue;

  @ApiModelProperty(value = "Mark if car is also used for personal reasons")
  private Boolean carUsedForPersonalReason;

  @ApiModelProperty(value = "Fill car registration number if cost is related to company car",
    example = "H1 00001")
  private String carRegistrationNumber;

  @PrePersist
  public void calculateTotalPriceAndVatValue() {
    this.totalPrice = price.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
    this.vatValue = totalPrice.multiply(new BigDecimal(Float.toString(vatRate.getRate()))).setScale(2, RoundingMode.HALF_UP);
  }
}