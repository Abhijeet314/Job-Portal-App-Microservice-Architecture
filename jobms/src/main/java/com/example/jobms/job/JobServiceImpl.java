package com.example.jobms.job;

import com.example.jobms.job.clients.CompanyClient;
import com.example.jobms.job.clients.ReviewClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;
    CompanyClient companyClient;
    ReviewClient reviewClient;
    public int id = 0;
    int attempts = 0;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyCircuitBreaker")
    @Retry(name = "companyBreaker", fallbackMethod = "companyCircuitBreaker")
    public List<JobDTO> findAll() {
        System.out.println("Attempt : " + ++attempts);
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        for(Job job: jobs) {
            JobDTO dto = new JobDTO();
            dto.setJob(job);
            Company company = companyClient.getCompany(job.getCompanyId());
            dto.setCompany(company);
            List<Review> reviewList = reviewClient.getReviews(job.getCompanyId());
            dto.setReviews(reviewList);
            jobDTOS.add(dto);
        }
        return jobDTOS;
    }

    public List<String> companyCircuitBreaker(Exception e) {
        List<String> list = new ArrayList<>();
        list.add("Good Day");
        return  list;
    }

    @Override
    public  void createJob(Job job) {
        id = id + 1;
        job.setId(id);
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(int id) {
        JobDTO dto = new JobDTO();
        Company company = companyClient.getCompany(id);
        Job job = jobRepository.findById(id).orElse(null);
        List<Review> reviewList = reviewClient.getReviews(id);
        dto.setReviews(reviewList);
        dto.setJob(job);
        dto.setCompany(company);
        return dto;
    }

    @Override
    public String DeleteJobById(int id) {
//        for(Job job: jobs) {
//            if(job.getId() == id) {
//                jobs.remove(job);
//                return "Job Deleted Succesfully";
//            }
//        }
//        return "";
        try {
            jobRepository.deleteById(id);
            return "Job Deleted Succesfully";
        } catch (Exception e) {
            return  "";
        }
    }

    @Override
    public boolean updateJob(int id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
