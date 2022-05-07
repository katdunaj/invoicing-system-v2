package pl.futurecollars.invoicing.dto.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.futurecollars.invoicing.dto.CompanyListDto;
import pl.futurecollars.invoicing.model.Company;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-07T20:59:31+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class CompanyListMapperImpl implements CompanyListMapper {

    @Override
    public CompanyListDto companyListToDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyListDto companyListDto = new CompanyListDto();

        companyListDto.setName( company.getName() );
        companyListDto.setTaxIdentificationNumber( company.getTaxIdentificationNumber() );
        companyListDto.setCompanyId( company.getCompanyId() );

        return companyListDto;
    }
}
