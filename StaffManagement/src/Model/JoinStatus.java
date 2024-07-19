package Model;

public class JoinStatus {
	private int joinstatusid;
	private String joinstatusname;
	public JoinStatus(int joinstatusid, String joinstatusname) {
		super();
		this.joinstatusid = joinstatusid;
		this.joinstatusname = joinstatusname;
	}
	public int getJoinstatusid() {
		return joinstatusid;
	}
	public void setJoinstatusid(int joinstatusid) {
		this.joinstatusid = joinstatusid;
	}
	public String getJoinstatusname() {
		return joinstatusname;
	}
	public void setJoinstatusname(String joinstatusname) {
		this.joinstatusname = joinstatusname;
	}
	@Override
	public String toString() {
		return joinstatusname;
	}
}
