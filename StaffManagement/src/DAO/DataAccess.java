package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Staff;

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

	// SQL文実行
	// 引数
	// strSql：SQL文字列
	// 戻り値
	// int：処理成功した件数
	public int executeQuery(String strSql) throws SQLException {

		ps = conn.prepareStatement(strSql);

	    return ps.executeUpdate();
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
		buf.append(" APPLICANT (APPLICANTNAME, NATIONALITY, DOCUMENTKINDS, REMARKS,  CREATEDATE) ");
		buf.append(" VALUES ");
		buf.append(" (?, '日本', 1, ?, CURRENT_TIMESTAMP) ");

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

	// 面接日程テーブル、応募者テーブル詳細情報登録処理
	// 引数
	// scheduleID：面接日程ID
	// strStartDateTime：開始日時
	// String strEndDateTime：終了日時
	// strImportance：重要度
	// strExecPlace：実施場所
	// intInterviewerId1：担当者1
	// intInterviewerId2：担当者2
	// intInterviewerId3：担当者3
	// txtApplicant：応募者名
	// txtRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int insertScheduleAplcantDetail(int scheduleID, String strStartDateTime, String strEndDateTime, String strImportance,
								String strExecPlace, int intInterviewerId1, int intInterviewerId2,
								int intInterviewerId3, String strApplicantName, String strRemarks) throws SQLException {

		int applicantId = 0;
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO ");
		buf.append(" APPLICANT (APPLICANTNAME, REMARKS, CREATEDATE) ");
		buf.append(" VALUES ");
		buf.append(" (?, ?, CURRENT_TIMESTAMP) ");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, strApplicantName);
		ps.setString(2, strRemarks);
		ps.executeUpdate();

		ResultSet rs = getData("SELECT MAX(APPLICANTID) APPLICANTID FROM APPLICANT");
		while(rs.next()) {
			applicantId = rs.getInt("APPLICANTID");
		}

		buf.setLength(0);
		buf.append(" INSERT INTO INTERVIEWSCHEDULE ( ");
		buf.append(" STARTDATETIME, ENDDATETIME, ");
		buf.append(" IMPORTANCE, EXECPLACE, ");
		buf.append(" STAFFID1, STAFFID2, STAFFID3, ");
		buf.append(" APPLICANTID, REMARKS, CREATEDATE) ");
		buf.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, strStartDateTime);
		ps.setString(2, strEndDateTime);
		ps.setString(3, strImportance);
		ps.setString(4, strExecPlace);
		ps.setInt(5, intInterviewerId1);
		ps.setInt(6, intInterviewerId2);
		ps.setInt(7, intInterviewerId3);
		ps.setInt(8, applicantId);
		ps.setString(9, strRemarks);

	    return ps.executeUpdate();
	}

	// 面接日程テーブル、応募者テーブル詳細情報更新処理
	// 引数
	// scheduleID：面接日程ID
	// strStartDateTime：開始日時
	// String strEndDateTime：終了日時
	// strImportance：重要度
	// strExecPlace：実施場所
	// intInterviewerId1：担当者1
	// intInterviewerId2：担当者2
	// intInterviewerId3：担当者3
	// txtApplicant：応募者名
	// txtRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int updateScheduleAplcantDetail(int scheduleID, String strStartDateTime, String strEndDateTime, String strImportance,
								String strExecPlace, int intInterviewerId1, int intInterviewerId2,
								int intInterviewerId3, String strApplicantName, String strRemarks) throws SQLException {

		int applicantId = 0;
		StringBuffer buf = new StringBuffer();

		// 面接日程テーブルIDから応募者IDを取得
		buf.append("SELECT ");
		buf.append("    APL.APPLICANTID ");
		buf.append("FROM ");
		buf.append("	INTERVIEWSCHEDULE ISC, APPLICANT APL ");
		buf.append("WHERE ");
		buf.append("	ISC.SCHEDULEID = " + String.valueOf(scheduleID) + " ");
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
		ps.setString(1, strApplicantName);
		ps.setString(2, strRemarks);
		ps.setInt(3, applicantId);
		ps.executeUpdate();

		// 面接日程情報の更新
		buf.setLength(0);
		buf.append("UPDATE INTERVIEWSCHEDULE ");
		buf.append("SET STARTDATETIME = ? , ENDDATETIME = ?, ");
		buf.append("IMPORTANCE = ?, EXECPLACE = ?, ");
		buf.append("STAFFID1 = ?, STAFFID2 = ?, STAFFID3 = ?, ");
		buf.append("APPLICANTID = ?, REMARKS = ?, UPDATEDATE = CURRENT_TIMESTAMP ");
		buf.append("WHERE SCHEDULEID = ?");

		ps = conn.prepareStatement(buf.toString());
		ps.setString(1, strStartDateTime);
		ps.setString(2, strEndDateTime);
		ps.setString(3, strImportance);
		ps.setString(4, strExecPlace);
		ps.setInt(5, intInterviewerId1);
		ps.setInt(6, intInterviewerId2);
		ps.setInt(7, intInterviewerId3);
		ps.setInt(8, applicantId);
		ps.setString(9, strRemarks);
		ps.setInt(10, scheduleID);

	    return ps.executeUpdate();
	}

	// 応募者テーブル登録、更新
	// 引数
	// intPropType：1 Insert、2 Update
	// strSql：SQL文字列
	// intApplicantId：応募者ID
	// strApplicantName：応募者名
	// strGender：性別
	// intAge：年齢
	// strNationality：国籍
	// strTelephone：電話番号
	// strConducted：面接実施
	// strResult：合否
	// strDocumentKinds：書類区分
	// strRemarks：備考
	// 戻り値
	// ResultSet：SQL実行したResultSet
	public int insertUpdateApplicantData(int intPropType, String strSql, int intApplicantId, String strApplicantName, String strGender,
				int intAge, String strNationality, String strTelephone, String strConducted,
				String strResult, String strRemarks, String strDocumentKinds) throws SQLException {

					ps = conn.prepareStatement(strSql);
					// Insert文の場合
					if(intPropType == 1) {
						ps.setInt(1, intApplicantId);
						ps.setString(2, strApplicantName);
						ps.setString(3, strGender);
						ps.setInt(4, intAge);
						ps.setString(5, strNationality);
						ps.setString(6, strTelephone);
						ps.setString(7, strConducted);
						ps.setString(8, strResult);
						ps.setString(9, strDocumentKinds);
						ps.setString(10, strRemarks);
					}
					// Update文の場合
					else {
						ps.setString(1, strApplicantName);
						ps.setString(2, strGender);
						ps.setInt(3, intAge);
						ps.setString(4, strNationality);
						ps.setString(5, strTelephone);
						ps.setString(6, strConducted);
						ps.setString(7, strResult);
						ps.setString(8, strDocumentKinds);
						ps.setString(9, strRemarks);
						ps.setInt(10, intApplicantId);
					}


				    return ps.executeUpdate();
	}

	// 応募者テーブル情報削除
	// 引数
	// strSql：SQL文
	// intTargetScheduleID：削除対象の面接日程テーブルID
	// 戻り値
	// int：処理成功した件数
	public int deleteApplicantData(String strSql, int intTargetApplicantID) throws SQLException {

		ps = conn.prepareStatement(strSql);

		return ps.executeUpdate();
	}

	// 書類テーブル情報登録、更新
	// 引数
	// intPropType：1 Insert、2 Update
	// strSql:SQL分
	// intDocumentsId：書類ID
	// strDocumentKinds：書類区分
	// strDocument1：書類1
	// strDocument2：書類2
	// strDocument3：書類3
	// 戻り値
	// int：処理成功した件数
	public int insertUpdateDocumentsData(int intPropType, String strSql, int intDocumentsId, String strDocumentKinds,
			String strDocument1,String strDocument2, String strDocument3) throws SQLException {

		ps = conn.prepareStatement(strSql);
		if(intPropType == 1) {
			ps.setInt(1, intDocumentsId);
			ps.setString(2, strDocumentKinds);
			ps.setString(3, strDocument1);
			ps.setString(4, strDocument2);
			ps.setString(5, strDocument3);
		}
		else {
			ps.setString(1, strDocumentKinds);
			ps.setString(2, strDocument1);
			ps.setString(3, strDocument2);
			ps.setString(4, strDocument3);
			ps.setInt(5, intDocumentsId);
		}

	    return ps.executeUpdate();
	}

	// 応募者テーブル更新
	// 引数
	// strSql：SQL文字列
	// intApplicantId：応募者ID
	// intJoinStatus：手続きステータス
	// strShortageDoc：不足資料
	// strRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int updateApplicantData(String strSql, int intApplicantId, int intJoinStatus, String strShortageDoc,
								String strRemarks) throws SQLException {

					ps = conn.prepareStatement(strSql);
					ps.setInt(1, intJoinStatus);
					ps.setString(2, strShortageDoc);
					ps.setString(3, strRemarks);
					ps.setInt(4, intApplicantId);

				    return ps.executeUpdate();
	}

	// 応募者テーブル更新
	// 引数
	// strSql：SQL文字列
	// intApplicantId：応募者ID
	// intJoinStatus：手続きステータス
	// strShortageDoc：不足資料
	// strRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int insertApplicantData(String strSql, String strApplicantName, String strGender, int intAge,
								String strNationality, String strTelephone) throws SQLException {

					ps = conn.prepareStatement(strSql);
					ps.setString(1, strApplicantName);
					ps.setString(2, strGender);
					ps.setInt(3, intAge);
					ps.setString(4, strNationality);
					ps.setString(5, strTelephone);

				    return ps.executeUpdate();
	}

	// 従業員テーブル更新
	// 引数
	// strSql：SQL文字列
	// intApplicantId：応募者ID
	// intJoinStatus：手続きステータス
	// strShortageDoc：不足資料
	// strRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int insetUpdateStaffData(String strSql, Staff staff) throws SQLException {

		java.sql.Date dateSqlJoin = null;
		java.sql.Date dateSqlRetire = null;

		if(staff.getJoindate() != null) {
			Date dateJoin = staff.getJoindate();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedJoinDate = simpleDateFormat.format(dateJoin);
			dateSqlJoin = java.sql.Date.valueOf(formattedJoinDate);
		}

		if(staff.getRetiredate() != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateRetire = staff.getRetiredate();
			String formattedRetireDate = simpleDateFormat.format(dateRetire);
			dateSqlRetire = java.sql.Date.valueOf(formattedRetireDate);
		}

		ps = conn.prepareStatement(strSql);
		ps.setString(1, staff.getStaffName());
		ps.setString(2, staff.getGender());
		ps.setInt(3, staff.getAge());
		ps.setString(4, staff.getNationality());
		ps.setString(5, staff.getTelephone());
		ps.setString(6, staff.getEmail());
		ps.setInt(7, staff.getPostcode());
		ps.setString(8, staff.getPrefectures());
		ps.setString(9, staff.getMunicipalities());
		ps.setString(10, staff.getAddress());
		ps.setFloat(11, staff.getMynumber());
		ps.setInt(12, staff.getDepartmentid());
		ps.setInt(13, staff.getPostid());
		ps.setInt(14, staff.getSalaryid());
		ps.setDate(15, dateSqlJoin);
		ps.setDate(16, dateSqlRetire);
		ps.setString(17, staff.getRemarks());
		ps.setString(18, staff.getEmergencyname());
		ps.setString(19, staff.getEmergencysequel());
		ps.setFloat(20, staff.getEmergencypostcode());
		ps.setString(21, staff.getEmergencytelephone());
		ps.setString(22, staff.getEmergencyprefectures());
		ps.setString(23, staff.getEmergencymunicipalities());
		ps.setString(24, staff.getEmergencyaddress());
		ps.setString(25, staff.getSpouse());
		ps.setString(26, staff.getSupport());
		ps.setString(27, staff.getEducation());
		ps.setString(28, staff.getQualification1());
		ps.setString(29, staff.getQualification2());
		ps.setString(30, staff.getQualification3());
		ps.setString(31, staff.getQualification4());
		ps.setString(32, staff.getQualification5());
		ps.setString(33, staff.getQualification6());
		ps.setString(34, staff.getQualification7());
		ps.setFloat(35, staff.getSocialinsurancenumber());
		ps.setFloat(36, staff.getBasicpensionnumber());
		ps.setString(37, staff.getFainancialinstitude());
		ps.setString(38, staff.getFainancialinstitudekana());
		ps.setInt(39, staff.getFainancialinstitudecode());
		ps.setString(40, staff.getBranchname());
		ps.setString(41, staff.getBranchnamekana());
		ps.setInt(42, staff.getBranchcode());
		ps.setString(43, staff.getDepositkind());
		ps.setInt(44, staff.getAccountnumber());
		ps.setString(45, staff.getAccountname());
		ps.setString(46, staff.getAccountnamekana());
		ps.setString(47, staff.getRecipientname());
		ps.setString(48, staff.getRecipientnamekana());
		ps.setString(49, staff.getStaffimage());

		return ps.executeUpdate();
	}
}
