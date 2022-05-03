package pl.futurecollars.invoicing.dto.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.futurecollars.invoicing.dto.InvoiceListDto;
import pl.futurecollars.invoicing.model.Company;
import pl.futurecollars.invoicing.model.Invoice;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-03T18:19:29+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class InvoiceListMapperImpl implements InvoiceListMapper {

    @Override
    public InvoiceListDto invoiceListToDto(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceListDto invoiceListDto = new InvoiceListDto();

        invoiceListDto.setIssuerName( invoiceIssuerName( invoice ) );
        invoiceListDto.setReceiverName( invoiceReceiverName( invoice ) );
        invoiceListDto.setInvoiceNumber( invoice.getInvoiceNumber() );

        return invoiceListDto;
    }

    private String invoiceIssuerName(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Company issuer = invoice.getIssuer();
        if ( issuer == null ) {
            return null;
        }
        String name = issuer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String invoiceReceiverName(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Company receiver = invoice.getReceiver();
        if ( receiver == null ) {
            return null;
        }
        String name = receiver.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
