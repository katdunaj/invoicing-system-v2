package pl.futurecollars.invoicing.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.IfProfileValue
import pl.futurecollars.invoicing.repository.InvoiceRepository
import pl.futurecollars.invoicing.repository.generic.GenericRepository
import pl.futurecollars.invoicing.service.InvoiceService

@DataJpaTest
@IfProfileValue(name = "invoicing-system.genericRepository", value = "jpa")
class InvoiceRepositoryTest extends GenericRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    GenericRepository getDatabaseInstance() {
        assert invoiceRepository != null
        new InvoiceService(invoiceRepository)
    }
}
