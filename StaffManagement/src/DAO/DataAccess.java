package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//DB操作クラス
public class DataAccess {

	final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=JST";
	final String USER = "kimura";
	final String PASS = "kimura0222";
	Connection conn;
	PreparedStatement ps;
	private static DataAccess da;

	// コンストラクタ
	// 引数
	// なし
	public DataAccess() throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PASS);
	}

	// インスタンス生成
	// 引数
	// なし
	// 戻り値
	// DataAccess型変数のインスタンス
	public static DataAccess createInstance() throws SQLException {
		da = new DataAccess();
		return da;
	}

	// SELECT文実行
	// 引数
	// strSql：SQL文字列
	// 戻り値
	// ResultSet：SQL実行したResultSet
	public ResultSet getData(String strSql) throws SQLException {

		ps = conn.prepareStatement(strSql);

	    return ps.executeQuery();
	}

	// INSERT文実行
	// 引数
	// strStartDateTime：開始日時
	// String strEndDateTime：終了日時
	// intInterviewerId1：担当者1
	// intInterviewerId2：担当者2
	// intInterviewerId3：担当者3
	// txtApplicant：応募者名
	// txtRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int insertData(String strStartDateTime, String strEndDateTime, int intInterviewerId1, int intInterviewerId2,
								int intInterviewerId3, String txtApplicant, String txtRemarks) throws SQLException {

		int applicantId = 0;
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO ");
		buf.append(" APPLICANT (APPLICANTNAME, REMARKS, CREATEDATE) ");
		buf.append(" VALUES ");
		buf.append(" (?, ?, CURRENT_TIMESTAMP) ");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, txtApplicant);
		ps.setString(2, txtRemarks);
		ps.executeUpdate();

		ResultSet rs = getData("SELECT MAX(APPLICANTID) APPLICANTID FROM APPLICANT");
		while(rs.next()) {
			applicantId = rs.getInt("APPLICANTID");
		}

		buf.setLength(0);
		buf.append(" INSERT INTO INTERVIEWSCHEDULE ( ");
		buf.append(" STARTDATETIME, ENDDATETIME, ");
		buf.append(" STAFFID1, STAFFID2, STAFFID3, ");
		buf.append(" APPLICANTID, REMARKS, CREATEDATE) ");
		buf.append(" VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, strStartDateTime);
		ps.setString(2, strEndDateTime);
		ps.setInt(3, intInterviewerId1);
		ps.setInt(4, intInterviewerId2);
		ps.setInt(5, intInterviewerId3);
		ps.setInt(6, applicantId);
		ps.setString(7, txtRemarks);

	    return ps.executeUpdate();
	}
}
