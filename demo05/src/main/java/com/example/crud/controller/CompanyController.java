package com.example.crud.controller;

import com.example.crud.model.Company;
import com.example.crud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public String listCompanies(Model model) {
        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);
        return "company/list";
    }

    @GetMapping("/create")
    public String createCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "company/form";
    }

    @PostMapping
    public String saveCompany(@ModelAttribute Company company) {
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyForm(@PathVariable Long id, Model model) {
        Company company = companyService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
        model.addAttribute("company", company);
        return "company/form";
    }

    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, @ModelAttribute Company company) {
        company.setId(id);
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:/companies";
    }
}
