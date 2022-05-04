package pl.futurecollars.invoicing.fixtures

import pl.futurecollars.invoicing.model.Company

class CompanyFixture {

    static company(int id) {
        Company.builder()
                .companyId(UUID.randomUUID())
                .name("Company $id")
                .taxIdentificationNumber("123456${id}819")
                .address("Ul. Ogrodowa 13/$id, 01-098 Kampinos")
                .healthyInsurance(1000.00)
                .pensionInsurance(500.97)
                .build()
    }
}