import javax.ejb.EJB;
import javax.naming.InitialContext;

public class MainApp {

    @EJB
    private static UserService userService;

    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            userService = (UserService) context.lookup("java:global/YourApp/UserService"); // Adjust the JNDI name

            String email = "user@example.com";
            String password = "securepass123";

            try {
                userService.createUser(email, password);
                System.out.println("User created successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println("User creation failed: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
