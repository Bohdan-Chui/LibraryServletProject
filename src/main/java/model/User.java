package model;

public class User extends Entity {
    private String patronymic;
    private String firstname;
    private String secondname;
    private String email;
    private String password;
    private String role;
    private boolean blocked;

    public User(int id, String patronymic, String firstname, String secondname, String email, String password, String role, boolean blocked) {
        this.setId(id);
        this.patronymic = patronymic;
        this.firstname = firstname;
        this.secondname = secondname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
    }

    public User(){ }


    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
