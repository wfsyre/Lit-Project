public abstract class Node {
	protected boolean isDependent;
	protected boolean isLocked;
	protected String name;
	protected String pass;
	
	public boolean isDependent() {
		return isDependent;
	}
	
	public boolean isLocked() {
		return isLocked;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setIsDependent(boolean value) {
		isDependent = value;
	}
	
	public void setIsLocked(boolean value) {
		isLocked = value;
	}
}
