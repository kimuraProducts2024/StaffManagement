package Model;

public class Documents {
	private int documentsid;
	private String documentkinds;
	private String document1;
	private String document2;
	private String document3;

	public Documents(int documentsid, String documentkinds) {
		super();
		this.documentsid = documentsid;
		this.documentkinds = documentkinds;
	}

	public Documents(int documentsid, String documentkinds, String document1, String document2, String document3) {
		super();
		this.documentsid = documentsid;
		this.documentkinds = documentkinds;
		this.document1 = document1;
		this.document2 = document2;
		this.document3 = document3;
	}
	public int getDocumentsid() {
		return documentsid;
	}
	public void setDocumentsid(int documentsid) {
		this.documentsid = documentsid;
	}
	public String getDocumentkinds() {
		return documentkinds;
	}
	public void setDocumentkinds(String documentkinds) {
		this.documentkinds = documentkinds;
	}
	public String getDocument1() {
		return document1;
	}
	public void setDocument1(String document1) {
		this.document1 = document1;
	}
	public String getDocument2() {
		return document2;
	}
	public void setDocument2(String document2) {
		this.document2 = document2;
	}
	public String getDocument3() {
		return document3;
	}
	public void setDocument3(String document3) {
		this.document3 = document3;
	}
	@Override
	public String toString() {
		return documentkinds;
	}
}
