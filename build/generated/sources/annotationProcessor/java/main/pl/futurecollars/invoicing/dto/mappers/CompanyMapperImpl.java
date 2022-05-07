package pl.futurecollars.invoicing.dto.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.futurecollars.invoicing.dto.CompanyDto;
import pl.futurecollars.invoicing.dto.CreateCompanyDto;
import pl.futurecollars.invoicing.model.Company;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-07T20:59:31+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto companyToDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setCompanyId( company.getCompanyId() );
        companyDto.setTaxIdentificationNumber( company.getTaxIdentificationNumber() );
        companyDto.setAddress( company.getAddress() );
        companyDto.setName( company.getName() );
        companyDto.setHealthyInsurance( company.getHealthyInsurance() );
        companyDto.setPensionInsurance( company.getPensionInsurance() );

        return companyDto;
    }

    @Override
    public Company dtoToEntity(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setCompanyId( companyDto.getCompanyId() );
        company.setTaxIdentificationNumber( companyDto.getTaxIdentificationNumber() );
        company.setAddress( companyDto.getAddress() );
        company.setName( companyDto.getName() );
        company.setHealthyInsurance( companyDto.getHealthyInsurance() );
        company.setPensionInsurance( companyDto.getPensionInsurance() );

        return company;
    }

    @Override
    public Company createDtoToEntity(CreateCompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setTaxIdentificationNumber( companyDto.getTaxIdentificationNumber() );
        company.setAddress( companyDto.getAddress() );
        company.setName( companyDto.getName() );
        company.setHealthyInsurance( companyDto.getHealthyInsurance() );
        company.setPensionInsurance( companyDto.getPensionInsurance() );

        return company;
    }
}
