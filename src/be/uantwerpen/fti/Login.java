package be.uantwerpen.fti;


import java.util.UUID;

public class Login {
    private static Login single_instance = null;
    private UUID currentUser;

    private Login() {
    }


    public static Login getInstance() {
        if (single_instance == null)
            single_instance = new Login() {
            };
        return single_instance;
    }


    public UUID getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UUID currentUser) {
        this.currentUser = currentUser;
    }

}
