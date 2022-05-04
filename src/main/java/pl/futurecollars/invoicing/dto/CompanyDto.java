package pl.futurecollars.invoicing.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

  @Id
  @ApiModelProperty(hidden = true)
  @GeneratedValue
  private UUID companyId;

  @NotEmpty
  @NotBlank
  @Size(min = 10, max = 10, message = "Tax identification number must have 10 digits")
  @ApiModelProperty(value = "Tax identification number", required = true, example = "1234567819")
  private String taxIdentificationNumber;

  @NotEmpty
  @NotBlank
  @ApiModelProperty(value = "Company address", required = true, example = "Ogrodowa 3, 05-085 Kampinos")
  @Size(max = 200)
  private String address;

  @NotBlank
  @Size(max = 100)
  @NotEmpty
  @ApiModelProperty(value = "Company name", required = true, example = "CCC")
  private String name;

  @NotNull
  @ApiModelProperty(value = "Company healthy insurance amount", required = true, example = "1000.50")
  private BigDecimal healthyInsurance;

  @NotNull
  @ApiModelProperty(value = "Company pension insurance amount", required = true, example = "500.25")
  private BigDecimal pensionInsurance;

}
