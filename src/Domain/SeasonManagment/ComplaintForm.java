package Domain.SeasonManagment;

import Domain.Users.Fan;

public class ComplaintForm {

    private Fan fanSubmitingForm;
    private String complaint;

    public ComplaintForm(String complaint) {
        this.complaint = complaint;
    }

    public void setFanSubmitingForm(Fan fanSubmitingForm) {
        this.fanSubmitingForm = fanSubmitingForm;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
