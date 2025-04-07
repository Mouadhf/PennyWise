package DataModels;

import java.time.LocalDateTime;

public class Alert {
    private String alertId;
    private String userId;
    private String type;
    private String message;
    private LocalDateTime triggeredAt;
    private boolean isRead;
    private String severity;
    private String actionLink;

    public Alert(String alertId, String userId, String type, String message,
                 LocalDateTime triggeredAt, boolean isRead, String severity,
                 String actionLink) {
        this.alertId = alertId;
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.triggeredAt = triggeredAt;
        this.isRead = isRead;
        this.severity = severity;
        this.actionLink = actionLink;
    }

    public String getAlertId() {
        return alertId;
    }


    public String getUserId() {
        return userId;
    }

    public String getActionLink() {
        return actionLink;
    }

    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }

    public String getSeverity() {
        return severity;
    }
    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}