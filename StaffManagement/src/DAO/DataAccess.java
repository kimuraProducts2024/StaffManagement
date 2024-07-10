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

	// 面接日程テーブル、応募者テーブル情報登録処理
	// 引数
	// strStartDateTime：開始日時
	// String strEndDateTime：終了日時
	// intInterviewerId1：担当者1
	// intInterviewerId2：担当者2
	// intInterviewerId3：担当者3
	// strApplicant：応募者名
	// strRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int insertScheduleApplicantData(String strStartDateTime, String strEndDateTime, int intInterviewerId1, int intInterviewerId2,
												int intInterviewerId3, String strApplicant, String strRemarks) throws SQLException {

		int applicantId = 0;
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO ");
		buf.append(" APPLICANT (APPLICANTNAME, REMARKS, CREATEDATE) ");
		buf.append(" VALUES ");
		buf.append(" (?, ?, CURRENT_TIMESTAMP) ");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, strApplicant);
		ps.setString(2, strRemarks);
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
		ps.setString(7, strRemarks);

	    return ps.executeUpdate();
	}

	// 面接日程テーブル、応募者テーブル情報更新処理
	// 引数
	// intTargetScheduleID：更新対象の面接日程テーブルID
	// strStartDateTime：開始日時
	// String strEndDateTime：終了日時
	// intInterviewerId1：担当者1
	// intInterviewerId2：担当者2
	// intInterviewerId3：担当者3
	// strApplicant：応募者名
	// strRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int updateScheduleApplicantData(int intTargetScheduleID, String strStartDateTime, String strEndDateTime, int intInterviewerId1,
												int intInterviewerId2, int intInterviewerId3, String strApplicant, String strRemarks) throws SQLException {

		int applicantId = 0;
		StringBuffer buf = new StringBuffer();

		// 面接日程テーブルIDから応募者IDを取得
		buf.append("SELECT ");
		buf.append("    APL.APPLICANTID ");
		buf.append("FROM ");
		buf.append("	INTERVIEWSCHEDULE ISC, APPLICANT APL ");
		buf.append("WHERE ");
		buf.append("	ISC.SCHEDULEID = " + String.valueOf(intTargetScheduleID) + " ");
		buf.append("AND ISC.APPLICANTID = APL.APPLICANTID ");

		ResultSet rs = getData(buf.toString());

		while(rs.next()) {
			applicantId = rs.getInt("APPLICANTID");
		}

		// 応募者情報の更新
		buf.setLength(0);
		buf.append("UPDATE ");
		buf.append("APPLICANT SET APPLICANTNAME = ?, ");
		buf.append("REMARKS = ?, ");
		buf.append("UPDATEDATE = CURRENT_TIMESTAMP ");
		buf.append("WHERE APPLICANTID = ?");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, strApplicant);
		ps.setString(2, strRemarks);
		ps.setInt(3, applicantId);
		ps.executeUpdate();

		// 面接日程情報の更新
		buf.setLength(0);
		buf.append("UPDATE INTERVIEWSCHEDULE ");
		buf.append("SET STARTDATETIME = ? , ENDDATETIME = ?, ");
		buf.append("STAFFID1 = ?, STAFFID2 = ?, STAFFID3 = ?, ");
		buf.append("APPLICANTID = ?, REMARKS = ?, UPDATEDATE = CURRENT_TIMESTAMP ");
		buf.append("WHERE SCHEDULEID = ?");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, strStartDateTime);
		ps.setString(2, strEndDateTime);
		ps.setInt(3, intInterviewerId1);
		ps.setInt(4, intInterviewerId2);
		ps.setInt(5, intInterviewerId3);
		ps.setInt(6, applicantId);
		ps.setString(7, strRemarks);
		ps.setInt(8, intTargetScheduleID);

	    return ps.executeUpdate();
	}

	// 面接日程テーブル削除処理
	// 引数
	// intTargetScheduleID：削除対象の面接日程テーブルID
	// 戻り値
	// int：処理成功した件数
	public int deleteScheduleData(int intTargetScheduleID) throws SQLException {

		String strSql = "UPDATE INTERVIEWSCHEDULE SET DELETEDATE = CURRENT_TIMESTAMP WHERE SCHEDULEID = "
								+ String.valueOf(intTargetScheduleID);
		ps = conn.prepareStatement(strSql);
		return ps.executeUpdate();
	}
}
