package framework;

public class User {

    private String login;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String email;

    private TestData dataReader = new TestData();

    public User() {
    }

    private User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private User(String login, String password, String confirmPassword, String fullName, String email) {
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public User getUserForLogination() {
        return new User(dataReader.getParameter("realLogin"), dataReader.getParameter("realPassword"));
    }

    public User getTestUser() {
        return new User(dataReader.getParameter("testUsername"), dataReader.getParameter("testPassword"),
                dataReader.getParameter("testPasswordConfirmation"), dataReader.getParameter("testFullName"),
                dataReader.getParameter("testEmail"));
    }

    public User getSomeUser(String login, String password, String confirmPassword, String fullName, String email) {
        return new User(login, password, confirmPassword, fullName, email);
    }
}
