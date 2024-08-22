package com.example.jobms.job.clients;

import com.example.jobms.job.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS", url = "${companyms.url}")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable int id);

}
