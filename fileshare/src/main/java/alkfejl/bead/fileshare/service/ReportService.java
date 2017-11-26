package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.Report;
import alkfejl.bead.fileshare.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Report createReport(Report report) {
        reportRepository.save(report);
        return report;
    }

    public Iterable<Report> listReports() {
        return reportRepository.findAll();
    }

    public Report getReport(Long id) {
        return reportRepository.findById(id);
    }

    public void deleteReport(Report report) {
        reportRepository.delete(report);
    }

}