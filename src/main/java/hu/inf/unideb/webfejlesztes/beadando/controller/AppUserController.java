package hu.inf.unideb.webfejlesztes.beadando.controller;

import hu.inf.unideb.webfejlesztes.beadando.model.AppUser;
import hu.inf.unideb.webfejlesztes.beadando.service.AppUserService;
import hu.inf.unideb.webfejlesztes.beadando.service.JobService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public void setAppUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/worker/{id}")
    public String userPage(@PathVariable(value = "id") Long id, Model model) throws Exception {

        AppUser aU = appUserService.getAppUserById(id);
        if (aU == null) {
            throw new Exception("Nincs ilyen id-val felhasználó");
        }

        model.addAttribute("appUser", aU);

        return "appUsersPage.html";
    }

    @GetMapping("/addUser")
    public String getWorkerAddPage(Model model) {

        model.addAttribute("user", new AppUser());

        return "addUser.html";
    }

    @PostMapping("/Workers")
    public String addUserSubmit(@ModelAttribute("user") AppUser user, Model model) {

        appUserService.saveUser(user);
        model.addAttribute("userList", appUserService.appUserList());
        return "appUser.html";
    }

    @GetMapping("updateWorker/{id}")
    public String updateWorker(@PathVariable(value = "id") Long id, Model model) throws NotFoundException {

        AppUser actual = appUserService.getAppUserById(id);
        actual.setEncryptedPassword(null);
        model.addAttribute("user", actual);

        return "addUser.html";
    }


    @GetMapping("/Workers")
    public String WorkersList(Model model) {

        model.addAttribute("userList", appUserService.appUserList());
        return "appUser.html";
    }

    @PostMapping("/Workers/delet/")
    public String DeletWorker(@ModelAttribute("appUser") AppUser user, Model model) {
        appUserService.deleteById(user.getId());
        model.addAttribute("userList", appUserService.appUserList());
        return "appUser.html";
    }

    @GetMapping("/Workers/addJob/{id}")
    public String listJobToWorker(@PathVariable(value = "id") Long id, Model model) throws NotFoundException {

        model.addAttribute("user",appUserService.getAppUserById(id));
        model.addAttribute("jobs",jobService.listAll());

        return "addJobToWorker.html";
    }
    @GetMapping("/Workers/addJob/{userId}/{jobId}")
    public String addJobToWorker(@PathVariable(value = "userId") Long userId,@PathVariable(value = "jobId") Long jobId, Model model) throws NotFoundException {

        appUserService.addJobToUser(userId,jobId);
        model.addAttribute("user",appUserService.getAppUserById(userId));
        model.addAttribute("jobs",jobService.listAll());

        return "addJobToWorker.html";
    }
}
