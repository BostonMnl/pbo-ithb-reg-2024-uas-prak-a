package UAS.model.classes;

public class SingletonManager {
    private static SingletonManager instance;
    private Customer user;

    SingletonManager() {
    }

    public static SingletonManager getInstance() {
        if (instance == null) {
            instance = new SingletonManager();
        }
        return instance;
    }
    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
    public void clearUser() {
        this.user = null;
    }

}

