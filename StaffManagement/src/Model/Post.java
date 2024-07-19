package Model;

public class Post {
	private int postit;
	private String postname;
	public int getPostit() {
		return postit;
	}
	public void setPostit(int postit) {
		this.postit = postit;
	}
	public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
	public Post(int postit, String postname) {
		super();
		this.postit = postit;
		this.postname = postname;
	}

	@Override
	public String toString() {
		return postname;
	}
}
