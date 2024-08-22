package com.example.jobms.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class JobController {
    JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("job added succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable int id) {
        JobDTO job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> DeleteJobById(@PathVariable int id) {
        String job = jobService.DeleteJobById(id);
        if(!Objects.equals(job, "")){
            return new ResponseEntity<>("Job deleted Succesfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable int id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if(updated) {
            return  new ResponseEntity<>("Jod updated Sucessfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
