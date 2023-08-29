import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserBean {

    @EJB
    private UserService userService;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;

    public String createUser() {
        try {
            userService.createUser(email, password, name, surname, phoneNumber);
            return "success"; // Navigate to success page
        } catch (IllegalArgumentException e) {
            return "error"; // Navigate to error page
        }
    }

	public String loginUser() {
    User loggedInUser = userService.loginUser(email, password);
    if (loggedInUser != null) {
        // Set user session or perform any necessary actions
        return "welcome"; // Navigate to welcome page
    } else {
        return "login-failed"; // Navigate to login failed page
    }
}

    // Getters and setters
}
