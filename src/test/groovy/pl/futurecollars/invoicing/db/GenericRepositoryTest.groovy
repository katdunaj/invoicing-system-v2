package pl.futurecollars.invoicing.db

import pl.futurecollars.invoicing.fixtures.InvoiceFixture
import pl.futurecollars.invoicing.model.Company
import pl.futurecollars.invoicing.model.Invoice
import pl.futurecollars.invoicing.model.InvoiceEntry
import pl.futurecollars.invoicing.repository.generic.GenericRepository
import spock.lang.Specification
import java.time.LocalDate

abstract class GenericRepositoryTest extends Specification {

    abstract GenericRepository getDatabaseInstance();

    def issuer = new Company (UUID.randomUUID(),"112-425-567-89", "ul.Ogrodowa 3 Kampinos", "Telnet", 1000.00, 1000.00)
    def receiver = new Company(UUID.randomUUID(),"112-425-567-69", "ul.Ogrodowa 6 Kampinos", "Netplus", 1000.00, 1000.00)
    def date = LocalDate.of(2017, 7, 21)
    def invoiceNumber = "2021/09/30/000123"
    def entries = new ArrayList<InvoiceEntry>()
    def invoice = new Invoice(UUID.randomUUID(),invoiceNumber,date, issuer, receiver, entries)
    GenericRepository genericRepository

    def setup() {
        genericRepository = getDatabaseInstance()
        genericRepository.clear()
    }

    def "should save invoice in to database"() {
        when:
        def result = genericRepository.save(invoice)

        then:
        genericRepository.getById(result.getId()) != null
        genericRepository.getById(result.getId()).getIssuerID().getName() == "XXX"


    }

    def "should get invoice from database by Id"() {
        setup:
        genericRepository.save(invoice)

        when:
        def result = genericRepository.getById(invoice.getInvoiceId())

        then:
        result != null
        result.getIssuerID().getName() == "XXX"
    }

    def "should get list of all invoices from database"() {
        setup:
        def invoice2 = InvoiceFixture.invoice(1)
        def invoice3 = InvoiceFixture.invoice(3)
        genericRepository.save(invoice)
        genericRepository.save(invoice2)
        genericRepository.save(invoice3)

        when:
        def result = genericRepository.getAll()

        then:
        result.size() == 3
    }

    def "should update invoice in the database"() {
        setup:
        def issuerUpdated = new Company(UUID.randomUUID(),"112-425-567-89", "ul.Ogrodowa 3 Kampinos", "Telnet", 1000.00, 1000.00)
        def invoiceUpdated = new Invoice(UUID.randomUUID(),invoiceNumber,date, issuer, receiver, entries)
        genericRepository.save(invoice)
        invoiceUpdated.setInvoiceId(invoice.getInvoiceId())

        when:
        def result = genericRepository.update(invoiceUpdated)

        then:
        genericRepository.getById(result.getInvoiceId()) != null
        genericRepository.getById(result.getInvoiceId()).getIssuerID().getName() == "CCC"
    }

    def "should delete invoice from database"() {
        setup:
        genericRepository.save(invoice)

        when:
        def result = genericRepository.delete(invoice.getInvoiceId())

        then:
        result
        genericRepository.getAll().size() == 0
    }

    def "should delete not existing invoice from database"() {
        when:
        def result = genericRepository.delete(UUID.randomUUID())

        then:
        !result
        genericRepository.getAll().size() == 0
    }

    def "should remove all invoices form database"() {
        setup:
        def invoice2 = InvoiceFixture.invoice(1)
        def invoice3 = InvoiceFixture.invoice(3)
        genericRepository.save(invoice)
        genericRepository.save(invoice2)
        genericRepository.save(invoice3)

        when:
        def result = genericRepository.clear()

        then:
        genericRepository.getAll().size() == 0
    }
}