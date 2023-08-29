import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public void createUser(String email, String password) {
        if (isSecurePassword(password)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            em.persist(user);
        } else {
            throw new IllegalArgumentException("Password does not meet security requirements.");
        }
    }

    public User loginUser(String email, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        
        try {
            User user = query.getSingleResult();
            if (user != null && user.getPassword().equals(password)) {
                return user; // Successful login
            }
        } catch (Exception e) {
            // User not found or other exception
        }

        return null; // Invalid login
    }

    private boolean isSecurePassword(String password) {
        // Add your password security checks here (e.g., minimum length)
        return password.length() >= 8;
    }

public User loginUser(String email, String password) {
    TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
    query.setParameter("email", email);
    
    try {
        User user = query.getSingleResult();
        if (user != null && user.getPassword().equals(password)) {
            return user; // Successful login
        }
    } catch (Exception e) {
        // User not found or other exception
    }

    return null; // Invalid login
}
}
