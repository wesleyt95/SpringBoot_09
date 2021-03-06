package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class HomeController {
    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String listJobs(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("job", new Job());
        return "jobform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Job job, BindingResult result){
        if (result.hasErrors()){
            return "jobform";
        }
        jobRepository.save(job);
        return "redirect:/";
    }
}
