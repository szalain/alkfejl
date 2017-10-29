package hu.elte.alkfelj.dataman.dataman.Entity.Request;

public class AddRoleRequest {
    private String roleName;
    private int level;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
