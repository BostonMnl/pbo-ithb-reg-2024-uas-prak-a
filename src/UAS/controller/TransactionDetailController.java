package UAS.controller;

import UAS.model.classes.ShipmentDetails;

public class TransactionDetailController {

    public boolean isFilled(int transaction_id, String status, String current_position, String evidence,
            String updated_by) {
        return (transaction_id > 0 && !status.trim().isEmpty() && !current_position.trim().isEmpty()
                && !evidence.trim().isEmpty() && !updated_by.trim().isEmpty());
    }

    public boolean insertDetail(ShipmentDetails details){
        return ShipmentDetails.insertDetails(details);
    }
}
