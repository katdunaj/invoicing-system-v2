package pl.futurecollars.invoicing.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
public class Company {

  @Id
  @ApiModelProperty(hidden = true)
  @GeneratedValue
  private UUID companyId;

  @ApiModelProperty(value = "Tax identification number", required = true, example = "123-45-61-819")
  private String taxIdentificationNumber;

  @ApiModelProperty(value = "Company address", required = true, example = "Ul. Ogrodowa 14A/6, 00-111 Błonie")
  private String address;

  @ApiModelProperty(value = "Company name", required = true, example = "Telnet")
  private String name;

  @ApiModelProperty(value = "Company healthy insurance amount", required = true, example = "1000.50")
  private BigDecimal healthyInsurance;

  @ApiModelProperty(value = "Company pension insurance amount", required = true, example = "500.25")
  private BigDecimal pensionInsurance;

}