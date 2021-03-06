package alkfejl.bead.fileshare.controller;


import alkfejl.bead.fileshare.model.Report;
import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.ReportService;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static alkfejl.bead.fileshare.model.User.Role.ADMIN;
import static alkfejl.bead.fileshare.model.User.Role.MOD;
import static alkfejl.bead.fileshare.model.User.Role.USER;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

    @Role({ADMIN, MOD, USER})
    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute(new Report());
        return "report";
    }

    @Role({ADMIN, MOD, USER})
    @PostMapping("/report")
    public String report(@ModelAttribute Report report, Model model) {
        if (userService.isFound(report.getReported())) { ;
            report.setReported(userService.getUser(report.getReported().getUsername()));
            reportService.createReport(report);
            return "redirect:/user/greet?name=";
        }
        model.addAttribute("reportFailed", true);
        return "report";
    }

    @Role({ADMIN, MOD})
    @GetMapping("/reportlist")
    public String getReports(Model model) {
        Iterable<Report> r = reportService.listReports();
        model.addAttribute("reports", r);
        return "reportlist";
    }

    @Role({ADMIN, MOD})
    @GetMapping("/reportlist/ban")
    public String banUser(@RequestParam(value = "id") Long id) {
        //model.addAttribute("name", name);
        User u = userService.getUserById(id);
        u.setBanned(true);
        userService.register(u);
        System.out.println("User bannolva: " + u.getUsername());
        return "redirect:/reportlist";
    }

    @Role({ADMIN, MOD})
    @GetMapping("/reportlist/delete")
    public String deleteReport(@RequestParam(value = "id") Long id) {
        Report r = reportService.getReport(id);
        reportService.deleteReport(r);
        return "redirect:/reportlist";
    }
}
