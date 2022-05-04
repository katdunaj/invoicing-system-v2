package pl.futurecollars.invoicing.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import pl.futurecollars.invoicing.file.JsonService
import pl.futurecollars.invoicing.fixtures.CompanyFixture
import pl.futurecollars.invoicing.model.Company

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WithMockUser
@SpringBootTest
@Stepwise
@AutoConfigureMockMvc
class CompanyControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private JsonService<Company> jsonService

    @Autowired
    private JsonService<Company[]> jsonListService

    @Shared
    def company = CompanyFixture.company(1)

    @Shared
    def updatedCompany = CompanyFixture.company(1)

    @Shared
    UUID id

    def "should add single company"() {
        given:
        def companyAsJson = jsonService.convertToJson(company)

        when:
        def response = mockMvc.perform(
                post("/companies").content(companyAsJson).contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        id = jsonService.convertToObject(response, Company.class).getCompanyId()
        company.setCompanyId(id)

        then:
        company == jsonService.convertToObject(response, Company.class)
    }

    def "should return list of companies"() {
        when:
        def response = mockMvc.perform(get("/companies").with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        def companies = jsonListService.convertToObject(response, Company[].class)

        then:
        companies.size() > 0
        companies[0] == company
    }

    def "should update company"() {
        given:
        updatedCompany.setCompanyId(id)
        def updatedInvoiceAsJson = jsonService.convertToJson(updatedCompany)

        when:
        def response = mockMvc.perform(
                put("/companies").content(updatedInvoiceAsJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        then:
        updatedCompany == jsonService.convertToObject(response, Company.class)
    }

    def "should return updatedCompany by id"() {
        when:
        def response = mockMvc.perform(get("/companies/" + id).with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        then:
        updatedCompany == jsonService.convertToObject(response, Company.class)
    }

    def "should delete company by id"() {
        when:
        def response = mockMvc.perform(delete("/companies/" + id).with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        then:
        response == "true"
    }

    def "should return empty list of companies"() {
        when:
        def response = mockMvc.perform(get("/companies").with(csrf()))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        def companies = jsonListService.convertToObject(response, Company[].class)

        then:
        companies.size() == 0
        response == "[]"
    }
}