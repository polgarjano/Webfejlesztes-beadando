package hu.inf.unideb.webfejlesztes.beadando.controller;



import hu.inf.unideb.webfejlesztes.beadando.model.Job;

import hu.inf.unideb.webfejlesztes.beadando.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobsController {
    private JobService jobService;

    @Autowired
    public void jobService(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public String jobsList(Model model) {

        model.addAttribute("jobsList", jobService.listAll());
        return "jobs.html";
    }

    @GetMapping(value = "/job/{id}")
    public String jobPage(@PathVariable(value = "id") Long id, Model model) throws Exception {

        Job job = jobService.getJobById(id);
        if (job == null) {
            throw new Exception("Nincs ilyen id-val munka");
        }

        model.addAttribute("job", job);

        return "jobPage.html";
    }

    @GetMapping("/addJob")
    public String getWorkerAddPage(Model model) {

        model.addAttribute("job", new Job());

        return "addJob.html";
    }

    @PostMapping("/jobs")
    public String addJob(@ModelAttribute("job") Job job, Model model) {

        jobService.saveJob(job);
        model.addAttribute("jobsList", jobService.listAll());
        return "jobs.html";
    }

    @PostMapping("/jobs/delet")
    public String DeletWorker(@ModelAttribute("job") Job job, Model model) {
        jobService.deleteById(job.getId());
        model.addAttribute("jobsList", jobService.listAll());
        return "jobs.html";
    }
}
