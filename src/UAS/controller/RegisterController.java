package UAS.controller;

import UAS.model.classes.Customer;

public class RegisterController {

    public boolean isFilled(String password, String name, String address, String phone, String email) {
        return (!password.trim().isEmpty() && !name.trim().isEmpty() && !address.trim().isEmpty()
                && !phone.trim().isEmpty() && !email.trim().isEmpty());
    }

    public boolean register(Customer customer) {
        return Customer.Register(customer);
    }
}
