package vo;

public class Departments {
	private String deptNo;
	private String deptName;
	
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Departments [deptNo=" + deptNo + ", deptName=" + deptName + "]";
	}
}
