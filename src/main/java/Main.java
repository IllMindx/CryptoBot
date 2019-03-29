import model.Model;
import view.View;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main (String[] args) throws LoginException {
        Model model = Model.getInstance();
        View view = new View(model);
        model.registerObserver(view);
    }
}
