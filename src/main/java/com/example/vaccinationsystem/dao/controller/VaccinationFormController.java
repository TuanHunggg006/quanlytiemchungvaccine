package com.example.vaccinationsystem.dao.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vaccinationsystem.dto.VaccinationFormCreateRequest;
import com.example.vaccinationsystem.dto.VaccinationFormInfoDTO;
import com.example.vaccinationsystem.dto.VaccinationFormVaccineDTO;
import com.example.vaccinationsystem.service.VaccinationFormService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VaccinationFormController {
    private final VaccinationFormService formService;

    public VaccinationFormController(VaccinationFormService formService) {
        this.formService = formService;
    }
@GetMapping("/vaccination-forms")
public ResponseEntity<?> getAllForms() {
    try {
        return ResponseEntity.ok(formService.getAllFormsInfo());
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500)
                .body(Map.of(
                        "message",
                        "ERR getAllForms: " + e.getClass().getName() + " - " + e.getMessage()
                ));
    }
}

    @PostMapping(value = "/vaccination-forms", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createForm(@Valid @RequestBody VaccinationFormCreateRequest request) {
        try {
            return ResponseEntity.ok(formService.createVaccinationForm(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("ERR createForm: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    @GetMapping("/vaccination-forms/{id}/vaccines")
    public ResponseEntity<?> getVaccinesFromForm(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(formService.getVaccinesFromForm(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("ERR getVaccinesFromForm: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    @GetMapping("/vaccination-forms/{id}/customer-name")
    public ResponseEntity<?> getCustomerNameFromForm(@PathVariable("id") String id) {
        try {
            Optional<String> result = formService.getCustomerNameFromForm(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("ERR getCustomerNameFromForm: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    @GetMapping("/vaccination-forms/{id}/payment-status")
    public ResponseEntity<?> hasBillForForm(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(formService.hasBillForForm(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("ERR hasBillForForm: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    @DeleteMapping("/vaccination-forms/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable("id") String id) {
        try {
            formService.deleteForm(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("ERR deleteForm: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
}
