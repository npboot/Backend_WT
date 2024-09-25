package WTproject.boekenWT.models.DTO;

import java.util.Date;

public class CopyHistoryDTO {
    private Date date;
    private String action;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
