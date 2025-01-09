package UAS.controller;

import UAS.model.classes.Customer;
import UAS.model.classes.SingletonManager;

public class LoginController {

    public boolean cekLogin(String phone, String password) {

        if (Customer.Login(phone, password)) {
            Customer User = Customer.getData(phone, password);
            SingletonManager.getInstance().setUser(User);
            return true;
        }

        return false;
    }
}
