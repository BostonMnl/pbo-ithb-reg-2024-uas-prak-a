package UAS.controller;

import UAS.model.classes.Transaction;

public class TransactionController {
    public boolean isFilled(String nama, String alamat, String noTelp, double beratPaket, String tipePaket) {
        return (!nama.trim().isEmpty() && !alamat.trim().isEmpty() && !noTelp.trim().isEmpty() && beratPaket >= 0
                && !tipePaket.trim().isEmpty());
    }

    public boolean insertTransactionData(Transaction transaction){
        return Transaction.insertTransaction(transaction);
    }
}
