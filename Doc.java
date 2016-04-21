
public abstract class Doc {
    protected String pass;
    protected boolean isLocked;
    protected String name;
    protected boolean isDependent;

    public boolean isLocked() {
        return isLocked;
    }

    public String getName() {
        return name;
    }

    public void setIsLocked(boolean value) {
        isLocked = value;
    }

    public boolean getIsDependent() {
        return isDependent;
    }

    public void setIsDependent(boolean value) {
        if (isLocked) {
            isDependent = false;
        } else {
            isDependent = value;
        }
    }
}
