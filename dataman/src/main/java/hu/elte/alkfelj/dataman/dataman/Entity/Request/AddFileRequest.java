package hu.elte.alkfelj.dataman.dataman.Entity.Request;

public class AddFileRequest {
    private String path;
    private int editLevel;
    private int viewLevel;
    private int owner;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getEditLevel() {
        return editLevel;
    }

    public void setEditLevel(int editLevel) {
        this.editLevel = editLevel;
    }

    public int getViewLevel() {
        return viewLevel;
    }

    public void setViewLevel(int viewLevel) {
        this.viewLevel = viewLevel;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
