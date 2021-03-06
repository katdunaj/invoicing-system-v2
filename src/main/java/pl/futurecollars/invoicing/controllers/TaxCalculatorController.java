package pl.futurecollars.invoicing.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.futurecollars.invoicing.model.Company;
import pl.futurecollars.invoicing.service.TaxCalculatorService;

@Slf4j
@RequiredArgsConstructor
@Api(tags = {"tax-calculator-controller"})
@CrossOrigin(origins = "*")
@RestController
public class TaxCalculatorController {

  private final TaxCalculatorService taxCalculatorService;

  @PostMapping(path = "/tax", produces = {"application/json;charset=UTF-8"})
  public ResponseEntity<Object> getTaxReport(@RequestBody Company company) {
    log.debug("Generate tax report for company with Tax Identification Number: {} ", company.getTaxIdentificationNumber());
    return ResponseEntity.ok().body(taxCalculatorService.getTaxReport(company));
  }
}

