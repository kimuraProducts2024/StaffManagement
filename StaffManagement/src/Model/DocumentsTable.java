package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DocumentsTable {
	private IntegerProperty documentsid;
	private StringProperty documentkinds;
	private StringProperty document1;
	private StringProperty document2;
	private StringProperty document3;

	public DocumentsTable(IntegerProperty documentsid, StringProperty documentkinds) {
		super();
		this.documentsid = documentsid;
		this.documentkinds = documentkinds;
	}

	public DocumentsTable(int documentsid, String documentkinds, String document1, String document2, String document3) {
		super();
		this.documentsid = new SimpleIntegerProperty(documentsid);
		this.documentkinds = new SimpleStringProperty(documentkinds);
		this.document1 = new SimpleStringProperty(document1);
		this.document2 = new SimpleStringProperty(document2);
		this.document3 = new SimpleStringProperty(document3);
	}
	public IntegerProperty getDocumentsid() {
		return documentsid;
	}
	public void setDocumentsid(IntegerProperty documentsid) {
		this.documentsid = documentsid;
	}
	public StringProperty getDocumentkinds() {
		return documentkinds;
	}
	public void setDocumentkinds(StringProperty documentkinds) {
		this.documentkinds = documentkinds;
	}
	public StringProperty getDocument1() {
		return document1;
	}
	public void setDocument1(StringProperty document1) {
		this.document1 = document1;
	}
	public StringProperty getDocument2() {
		return document2;
	}
	public void setDocument2(StringProperty document2) {
		this.document2 = document2;
	}
	public StringProperty getDocument3() {
		return document3;
	}
	public void setDocument3(StringProperty document3) {
		this.document3 = document3;
	}
}
