package alkfejl.bead.fileshare.api;

import alkfejl.bead.fileshare.model.Report;
import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.ReportService;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static alkfejl.bead.fileshare.model.User.Role.*;

@RestController
@RequestMapping("/api/report")
public class ReportApiController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @Role({USER, MOD, ADMIN})
    @PostMapping
    private ResponseEntity<Report> create(@RequestBody Report report) {
        //return ResponseEntity.ok(reportService.createReport(report));
        System.out.println(report);
        if (userService.isFound(report.getReported())) {
            report.setReported(userService.getUser(report.getReported().getUsername()));
            report.setDate(new Date());
            return ResponseEntity.ok(reportService.createReport(report));
        }
        return ResponseEntity.badRequest().build();
    }

    @Role({MOD, ADMIN})
    @GetMapping("/list")
    private ResponseEntity<Iterable<Report>> list() {
        Iterable<Report> reportList = reportService.listReports();
        return ResponseEntity.ok(reportList);
    }

    @Role({MOD, ADMIN})
    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        reportService.deleteReport(reportService.getReport(id));
        return ResponseEntity.ok(true);
    }

    @Role({MOD, ADMIN})
    @DeleteMapping("/ban/{id}")
    private ResponseEntity banUser(@PathVariable Long id) {
        Report report = reportService.getReport(id);
        report.getReported().setBanned(true);
        //reportService.deleteReport(reportService.getReport(id));
        for(Report r : reportService.listReports()) {
            if (r.getReported().equals(report.getReported())) reportService.deleteReport(r);
        }
        return ResponseEntity.ok(true);
    }

}