package DataModels;

import java.time.LocalDateTime;
import java.util.List;

public class Report {
    private String id;
    private String title;
    private String reportType;
    private String content;
    private LocalDateTime generatedAt;
    private String generatedBy;
    private String format;
    private List<String> filters;

    public Report(String reportId, String title, String reportType, String content,
                  LocalDateTime generatedAt, String generatedBy, String format,
                  List<String> filters) {
        this.id = reportId;
        this.title = title;
        this.reportType = reportType;
        this.content = content;
        this.generatedAt = generatedAt;
        this.generatedBy = generatedBy;
        this.format = format;
        this.filters = filters;
    }

    public String getReportId() {
        return id;
    }

    public void setReportId(String reportId) {
        this.id = reportId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public String getFormat() {
        return format;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public String getReportType() {
        return reportType;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }
}


