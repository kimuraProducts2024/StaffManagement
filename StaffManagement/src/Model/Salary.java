package Model;

public class Salary {
	private int salaryid;
	private int salary;

	public Salary(int salaryid, int salary) {
		super();
		this.salaryid = salaryid;
		this.salary = salary;
	}

	public int getSalaryid() {
		return salaryid;
	}

	public void setSalaryid(int salaryid) {
		this.salaryid = salaryid;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		if(salary == 0) {
			return "";
		}
		return String.valueOf(salary);
	}
}
