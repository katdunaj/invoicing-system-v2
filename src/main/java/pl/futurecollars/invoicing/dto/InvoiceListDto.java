package pl.futurecollars.invoicing.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
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

}
