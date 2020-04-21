package Domain.Alerts;

import Domain.SeasonManagment.ComplaintForm;

public class ComplaintAlert {

    ComplaintForm response;

    public ComplaintAlert(ComplaintForm response) {
        this.response = response;
    }

    public ComplaintForm getResponse() {
        return response;
    }
}
