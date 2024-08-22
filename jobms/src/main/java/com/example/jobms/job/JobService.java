package com.example.jobms.job;


import java.util.List;


public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO getJobById(int id);
    String DeleteJobById(int id);

    boolean updateJob(int id, Job updatedJob);
}
