package pl.futurecollars.invoicing.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceListDto {

  @ApiModelProperty
  private String invoiceNumber;

  @ApiModelProperty
  private LocalDate date;

  @ApiModelProperty
  private String issuerName;

  @ApiModelProperty
  private String receiverName;

  @ApiModelProperty(value = "UUID generated  by app", required = true, example = "ihfdqouihfwqepo679820")
  private UUID companyId;
}
