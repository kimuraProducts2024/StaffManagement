package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import DAO.DataAccess;
import Model.Staff;

// 処理を実行するクラス
public class DataManageService {

	ResultSet rs;
	String strSql = "";
	private static DataAccess da;
	private static DataManageService service;

	// インスタンス生成
	// 引数
	// なし
	// 戻り値
	// DataManageService型変数のインスタンス
	public static DataManageService createInstance() throws SQLException {

		da = DataAccess.createInstance();
		service = new DataManageService();
		return service;
	}

	// テーブルデータ取得
	// 引数
	// strSqlPart：sql文の一部
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getData(String strSqlPart) throws SQLException {

		strSql = "SELECT * FROM " + strSqlPart;

		return da.getData(strSql);
	}

	// ユーザの存在チェック
	// 引数
	// strUserName：ユーザ名テキスト
	// 戻り値
	// true：データが存在する
	// faluse：データが存在しない
	public boolean existUserData(String strUserName) throws SQLException {

		strSql = "SELECT COUNT(*) UNUM FROM USER WHERE USERID = '" + strUserName + "' AND DELFLG = 0";
		rs = da.getData(strSql);

		while(rs.next()) {
			// データが存在する場合
			if(rs.getInt("UNUM") == 1) {
				return true;
			}
			// データが存在しない場合
			else {
				return false;
			}
		}

		// データ取得できなかった場合
		return false;
	}

	// ログイン画面の入力値チェック
	// 引数
	// strUserName：ユーザ名テキスト
	// strPassWord：パスワードテキスト
	// 戻り値
	// true：パスワード一致
	// faluse：パスワード不一致
	public boolean checkPassWord(String strUserName, String strPassWord) throws SQLException {

		strSql = "SELECT * FROM USER WHERE USERID = '" + strUserName + "' AND DELFLG = 0";
		rs = da.getData(strSql);

		while(rs.next()) {
			// パスワードが一致した場合
			if(rs.getString("PASSWORD").equals(strPassWord)) {
				return true;
			}
			// パスワードが一致しなかった場合
			else {
				return false;
			}
		}

		// データ取得できなかった場合
		return false;
	}

	// カレンダー予定用データ取得
	// 引数
	// strNowYearMonth：開始日（yyyy-MM形式）
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getScheduleData(String strNowYearMonth) throws SQLException {

		strSql = "SELECT SCHEDULEID, STARTDATETIME  "
				+ "FROM INTERVIEWSCHEDULE WHERE "
				+ "DATE_FORMAT(STARTDATETIME, '%Y-%m')='" + strNowYearMonth + "' "
				+ "AND DELETEDATE IS NULL";

		return da.getData(strSql);
	}

	// 面接日程テーブルのID（MAX+1）取得
	// 引数
	// なし
	// 戻り値
	// int：取得したIDの値
	public int getScheduleIdData() throws SQLException {

		int intResult = 0;
		strSql = "SELECT MAX(SCHEDULEID) + 1 SCHEDULEID FROM INTERVIEWSCHEDULE WHERE DELETEDATE IS NULL";

		ResultSet rs = da.getData(strSql);

		while(rs.next()) {
			intResult = rs.getInt("SCHEDULEID");
		}

		return intResult;
	}

	// 担当者コンボボックス設定用データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getStaffData() throws SQLException {

		strSql = "SELECT * FROM STAFF "
				+ "WHERE DEPARTMENTID IN (30100, 30101, 30102) "
				+ "AND DELETEDATE IS NULL ORDER BY STAFFID";

		return da.getData(strSql);
	}

	// 面接日程テーブル、応募者テーブル情報取得
	// 引数
	// intTargetScheduleID：検索対象のID
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getScheduleApplicantData(int intTargetScheduleID) throws SQLException {

		strSql = "SELECT " +
				"	ISC.SCHEDULEID, ISC.STARTDATETIME, ISC.ENDDATETIME," +
				"   ISC.IMPORTANCE, ISC.EXECPLACE, ISC.STAFFID1, " +
				"	ISC.STAFFID2, ISC.STAFFID3, ISC.APPLICANTID, " +
				"   APL.APPLICANTNAME, ISC.REMARKS " +
				"FROM " +
				"	INTERVIEWSCHEDULE ISC, APPLICANT APL " +
				"WHERE " +
				"	ISC.SCHEDULEID = " + String.valueOf(intTargetScheduleID) + " " +
				"AND " +
				"	ISC.APPLICANTID = APL.APPLICANTID " +
				"AND " +
				"   ISC.DELETEDATE IS NULL " +
				"ORDER BY ISC.STARTDATETIME";

		return da.getData(strSql);
	}

	// 面接日程テーブル、応募者テーブル情報取得
	// 引数
	// intScheduleID：面接日程テーブルのID
	// scheduleDate：開始日時
	// startHourMinute：実施時分（hh：mm）
	// intApplicantId：応募者ID
	// strApplicantName：応募者名
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getScheduleListData(String intScheduleID, LocalDate scheduleDate, String startHourMinute,
							String intApplicantId, String strApplicantName) throws SQLException {

		strSql = "SELECT " +
				"	ISC.SCHEDULEID, ISC.STARTDATETIME, ISC.ENDDATETIME," +
				"   ISC.IMPORTANCE, ISC.EXECPLACE, ISC.STAFFID1, " +
				"	ISC.STAFFID2, ISC.STAFFID3, ISC.APPLICANTID, " +
				"   APL.APPLICANTNAME, ISC.REMARKS " +
				 "FROM " +
				 "	INTERVIEWSCHEDULE ISC, APPLICANT APL " +
				 "WHERE " +
				 "	ISC.DELETEDATE IS NULL " +
				 "AND " +
				 "	ISC.APPLICANTID = APL.APPLICANTID ";
				 if(!intScheduleID.equals("")) {
					 strSql += "AND ISC.SCHEDULEID = " + intScheduleID + " ";
				 }
				 if(scheduleDate != null) {
					 strSql += "AND ISC.STARTDATETIME >= '" + scheduleDate + "' AND ISC.STARTDATETIME < '" + scheduleDate.plusDays(1L) + "' ";
				 }
				 if(!startHourMinute.equals(":")) {
					 strSql += "AND TIME(ISC.STARTDATETIME) = '" + startHourMinute + ":00' ";
				 }
				 if(!intApplicantId.equals("")) {
					 strSql += "AND ISC.APPLICANTID = " + intApplicantId + " ";
				 }
				 if(!strApplicantName.equals("")) {
					 strSql += "AND APL.APPLICANTNAME LIKE '%" + strApplicantName + "%' ";
				 }

		strSql +=  "ORDER BY ISC.STARTDATETIME";

		return da.getData(strSql);
	}

	// 面接日程テーブル、応募者テーブル情報登録処理
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
	public int insertScheduleApplicantData(String strStartDateTime, String strEndDateTime, int intInterviewerId1, int intInterviewerId2,
								int intInterviewerId3, String txtApplicant, String txtRemarks) throws SQLException {

		return da.insertScheduleApplicantData(strStartDateTime, strEndDateTime, intInterviewerId1,
												intInterviewerId2, intInterviewerId3, txtApplicant, txtRemarks);
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
	public int updateScheduleApplicantData(int intTargetScheduleID, String strStartDateTime, String strEndDateTime, int intInterviewerId1, int intInterviewerId2,
								int intInterviewerId3, String strApplicant, String strRemarks) throws SQLException {

		return da.updateScheduleApplicantData(intTargetScheduleID, strStartDateTime, strEndDateTime, intInterviewerId1,
												intInterviewerId2, intInterviewerId3, strApplicant, strRemarks);
	}

	// 面接日程テーブル削除処理
	// 引数
	// intTargetScheduleID：削除対象の面接日程テーブルID
	// 戻り値
	// int：処理成功した件数
	public int deleteScheduleData(int intTargetScheduleID) throws SQLException {

		return da.deleteScheduleData(intTargetScheduleID);
	}

	// 面接日程テーブル、応募者テーブル詳細情報登録処理
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
	public int insertScheduleAplcantDetail(int scheduleID, String strStartDateTime, String strEndDateTime, String strImportance,
								String strExecPlace, int intInterviewerId1, int intInterviewerId2,
								int intInterviewerId3, String strApplicantName, String strRemarks) throws SQLException {

		return da.insertScheduleApplicantData(strStartDateTime, strEndDateTime, intInterviewerId1,
												intInterviewerId2, intInterviewerId3, strApplicantName, strRemarks);
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

		return da.updateScheduleAplcantDetail(scheduleID, strStartDateTime, strEndDateTime, strImportance,
								strExecPlace, intInterviewerId1, intInterviewerId2,
								intInterviewerId3, strApplicantName, strRemarks);
	}

	// 応募者テーブル情報取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getApplicantData() throws SQLException {

		return getApplicantData(0, "", "", 0, "", "");
	}

	// 応募者テーブル情報取得
	// 引数
	// intTargetApplicantID：検索対象のID
	// strApplicantName：応募者名
	// strConducted：面接実施
	// strResult：合否
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getApplicantData(int intTargetApplicantID, String strApplicantName,
							String strConducted, String strResult) throws SQLException {

		return getApplicantData(intTargetApplicantID,strApplicantName, "", 0, strConducted, strResult);
	}

	// 応募者テーブル情報取得
	// 引数
	// intTargetApplicantID：検索対象のID
	// strApplicantName：応募者名
	// strGender：性別
	// intAge：年齢
	// strConducted：面接実施
	// strResult：合否
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getApplicantData(int intTargetApplicantID, String strApplicantName, String strGender,
										int intAge, String strConducted, String strResult) throws SQLException {

		strSql = "SELECT " +
				"	APPLICANTID, APPLICANTNAME, GENDER, AGE, NATIONALITY, " +
				"   TELEPHONE, CONDUCTED, RESULT, REMARKS, DOCUMENTKINDS " +
				"FROM " +
				"	APPLICANT " +
				"WHERE " +
				"	DELETEDATE IS NULL ";
		if(intTargetApplicantID != 0) {
			strSql += 	"AND APPLICANTID = " + String.valueOf(intTargetApplicantID) + " ";
		}
		if(!strApplicantName.equals("")) {
			strSql +=	"AND APPLICANTNAME LIKE '%" + strApplicantName + "%' ";
		}
		if(!strGender.equals("")) {
			strSql +=	"AND GENDER = '" + strGender + "' ";
		}
		if(intAge != 0) {
			strSql += 	"AND AGE = " + String.valueOf(intAge) + " ";
		}
		if(!strConducted.equals("")) {
			strSql +=	"AND CONDUCTED = '" + strConducted + "' ";
		}
		if(!strResult.equals("")) {
			strSql +=	"AND RESULT = '" + strResult + "' ";
		}
		strSql += "ORDER BY APPLICANTID";

		return da.getData(strSql);
	}

	// 書類区分コンボボックス設定用データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getDocumentsPartData() throws SQLException {

		strSql = "SELECT DOCUMENTSID, DOCUMENTKINDS FROM DOCUMENTS GROUP BY DOCUMENTKINDS";

		return da.getData(strSql);
	}

	// 書類データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getDocumentsData() throws SQLException {

		strSql = "SELECT * FROM DOCUMENTS";

		return da.getData(strSql);
	}

	// 応募者テーブルのID（MAX+1）取得
	// 引数
	// なし
	// 戻り値
	// int：取得したIDの値
	public int getApplicantIdData() throws SQLException {

		int intResult = 0;
		strSql = "SELECT MAX(APPLICANTID) + 1 APPLICANTID FROM APPLICANT WHERE DELETEDATE IS NULL";

		ResultSet rs = da.getData(strSql);

		while(rs.next()) {
			intResult = rs.getInt("APPLICANTID");
		}

		return intResult;
	}

	// 応募者テーブル情報取得
	// 引数
	// intTargetApplicantId：検索対象のID
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getApplicantData(int intTargetApplicantId) throws SQLException {

		strSql = "SELECT " +
				"	APPLICANTID, APPLICANTNAME, IFNULL(GENDER, '') GENDER, IFNULL(AGE, 0) AGE, " +
				"   IFNULL(NATIONALITY, '') NATIONALITY, IFNULL(TELEPHONE, '') TELEPHONE, " +
				"   IFNULL(CONDUCTED, '') CONDUCTED, IFNULL(RESULT, '') RESULT, " +
				"	REMARKS, IFNULL(DOCUMENTKINDS, '') DOCUMENTKINDS " +
				"FROM " +
				"	APPLICANT " +
				"WHERE " +
				"	APPLICANTID = " + String.valueOf(intTargetApplicantId) + " " +
				"AND " +
				"   DELETEDATE IS NULL " +
				"ORDER BY APPLICANTID";

		return da.getData(strSql);
	}

	// 応募者テーブル情報登録
	// 引数
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
	// int：処理成功した件数
	public int insertScheduleAplcantDetail(int intApplicantId, String strApplicantName, String strGender,
			int intAge, String strNationality, String strTelephone, String strConducted,
			String strResult, String strDocumentKinds, String strRemarks) throws SQLException {

		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO APPLICANT ( ");
		buf.append(" APPLICANTID, APPLICANTNAME, ");
		buf.append(" GENDER, AGE, NATIONALITY, ");
		buf.append(" TELEPHONE, CONDUCTED, RESULT, ");
		buf.append(" DOCUMENTKINDS, REMARKS, ");
		buf.append(" CREATEDATE) ");
		buf.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ");

	    return da.insertUpdateApplicantData(1, buf.toString(), intApplicantId, strApplicantName, strGender,
								intAge, strNationality, strTelephone, strConducted,
								strResult, strDocumentKinds, strRemarks);
	}

	// 応募者テーブル情報更新
	// 引数
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
	// int：処理成功した件数
	public int updateScheduleAplcantDetail(int intApplicantId, String strApplicantName, String strGender,
			int intAge, String strNationality, String strTelephone, String strConducted,
			String strResult, String strDocumentKinds, String strRemarks) throws SQLException {

		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE ");
		buf.append("APPLICANT SET  ");
		buf.append("APPLICANTNAME = ?, GENDER = ?, ");
		buf.append("AGE = ?, NATIONALITY = ?, ");
		buf.append("TELEPHONE = ?, CONDUCTED = ?, RESULT = ?, ");
		buf.append("DOCUMENTKINDS = ?, REMARKS = ?, ");
		buf.append("UPDATEDATE = CURRENT_TIMESTAMP ");
		buf.append("WHERE APPLICANTID = ?");

	    return da.insertUpdateApplicantData(2, buf.toString(), intApplicantId, strApplicantName, strGender,
								intAge, strNationality, strTelephone, strConducted,
								strResult, strDocumentKinds, strRemarks);
	}

	// 応募者テーブル情報削除
	// 引数
	// intTargetScheduleID：削除対象の面接日程テーブルID
	// 戻り値
	// int：処理成功した件数
	public int deleteApplicantData(int intTargetApplicantID) throws SQLException {

		String strSql = "UPDATE APPLICANT SET DELETEDATE = CURRENT_TIMESTAMP WHERE APPLICANTID = " + intTargetApplicantID;

		return da.deleteApplicantData(strSql, intTargetApplicantID);
	}

	// 書類テーブル情報取得
	// 引数
	// intTargetDocumentsId：検索対象のID
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getDocumentsData(int intTargetDocumentsId) throws SQLException {

		strSql = "SELECT DOCUMENTSID, DOCUMENTKINDS, " +
				 "DOCUMENT1, DOCUMENT2, DOCUMENT3 " +
				 "FROM DOCUMENTS " +
				 "WHERE " +
				 "	DOCUMENTSID = " + String.valueOf(intTargetDocumentsId) + " " +
				 "ORDER BY DOCUMENTSID";

		return da.getData(strSql);
	}

	// 書類テーブル情報取得
	// 引数
	// intTargetDocumentsId：検索対象のID
	// txtSearchDocKinds：書類区分
	// txtSearchDoc1：書類1
	// txtSearchDoc2：書類2
	// txtSearchDoc3：書類3
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getDocumentsData(int intTargetDocumentsId, String txtSearchDocKinds, String txtSearchDoc1,
										String txtSearchDoc2, String txtSearchDoc3) throws SQLException {

		// 条件追加フラグ true:追加あり、false:追加なし
		boolean startAdd = false;

		strSql = "SELECT DOCUMENTSID, DOCUMENTKINDS, " +
				 "DOCUMENT1, DOCUMENT2, DOCUMENT3 " +
				 "FROM DOCUMENTS " +
				 "WHERE ";
		if(intTargetDocumentsId != 0) {
			strSql += 	"DOCUMENTSID = " + String.valueOf(intTargetDocumentsId) + " ";
			startAdd = true;
		}
		if(!txtSearchDocKinds.equals("")) {
			// 条件追加ありの場合
			if(startAdd) {
				strSql +=	"AND ";
			}
			strSql +=	"DOCUMENTKINDS LIKE '%" + txtSearchDocKinds + "%' ";
			startAdd = true;
		}
		if(!txtSearchDoc1.equals("")) {
			// 条件追加ありの場合
			if(startAdd) {
				strSql +=	"AND ";
			}
			strSql +=	"(DOCUMENT1 LIKE '%" + txtSearchDoc1 + "%' " +
						"OR DOCUMENT2 LIKE '%" + txtSearchDoc1 + "%' " +
						"OR DOCUMENT3 LIKE '%" + txtSearchDoc1 + "%') ";
			startAdd = true;
		}
		if(!txtSearchDoc2.equals("")) {
			// 条件追加ありの場合
			if(startAdd) {
				strSql +=	"AND ";
			}
			strSql +=	"(DOCUMENT1 LIKE '%" + txtSearchDoc2 + "%' " +
					"OR DOCUMENT2 LIKE '%" + txtSearchDoc2 + "%' " +
					"OR DOCUMENT3 LIKE '%" + txtSearchDoc2 + "%') ";
			startAdd = true;
		}
		if(!txtSearchDoc3.equals("")) {
			// 条件追加ありの場合
			if(startAdd) {
				strSql +=	"AND ";
			}
			strSql +=	"(DOCUMENT1 LIKE '%" + txtSearchDoc3 + "%' " +
					"OR DOCUMENT2 LIKE '%" + txtSearchDoc3 + "%' " +
					"OR DOCUMENT3 LIKE '%" + txtSearchDoc3 + "%') ";
		}
		strSql += "ORDER BY DOCUMENTSID";

		return da.getData(strSql);
	}

	// 書類テーブル情報登録
	// 引数
	// intDocumentsId：書類ID
	// strDocumentKinds：書類区分
	// strDocument1：書類1
	// strDocument2：書類2
	// strDocument3：書類3
	// 戻り値
	// int：処理成功した件数
	public int insertDocumentsData(int intDocumentsId, String strDocumentKinds, String strDocument1,
			String strDocument2, String strDocument3) throws SQLException {

		StringBuffer buf = new StringBuffer();
		buf.append("INSERT INTO DOCUMENTS ( ");
		buf.append("   DOCUMENTSID, DOCUMENTKINDS, DOCUMENT1, DOCUMENT2, DOCUMENT3 ");
		buf.append(") ");
		buf.append("VALUES (?, ?, ?, ?, ?) ");

	    return da.insertUpdateDocumentsData(1, buf.toString(), intDocumentsId, strDocumentKinds,
	    						strDocument1, strDocument2, strDocument3);
	}

	// 書類テーブル情報更新
	// 引数
	// intDocumentsId：書類ID
	// strDocumentKinds：書類区分
	// strDocument1：書類1
	// strDocument2：書類2
	// strDocument3：書類3
	// 戻り値
	// int：処理成功した件数
	public int updateDocumentsData(int intDocumentsId, String strDocumentKinds, String strDocument1,
			String strDocument2, String strDocument3) throws SQLException {

		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE ");
		buf.append("DOCUMENTS SET ");
		buf.append("DOCUMENTKINDS = ?, ");
		buf.append("DOCUMENT1 = ?, DOCUMENT2 = ?, DOCUMENT3 = ? ");
		buf.append("WHERE DOCUMENTSID = ?");

	    return da.insertUpdateDocumentsData(2, buf.toString(), intDocumentsId, strDocumentKinds,
	    						strDocument1, strDocument2, strDocument3);
	}

	// 書類テーブル情報削除
	// 引数
	// intTargetDocumentsId：削除対象の書類テーブルID
	// 戻り値
	// int：処理成功した件数
	public int deleteDocumentsData(int intTargetDocumentsId) throws SQLException {

		String strSql = "DELETE FROM DOCUMENTS  WHERE DOCUMENTSID = " + intTargetDocumentsId;

		return da.deleteApplicantData(strSql, intTargetDocumentsId);
	}

	// 手続きステータスコンボボックス設定用データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getJoinStatusData() throws SQLException {

		strSql = "SELECT * FROM JOINSTATUS";

		return da.getData(strSql);
	}

	// 手続データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getJoiningProcessData() throws SQLException {

		strSql = "SELECT "
				+ "   APL.APPLICANTID, APL.APPLICANTNAME, APL.DOCUMENTKINDS, JOINSTS.JOINSTATUSNAME, "
				+ "   APL.TELEPHONE, APL.SHORTAGEDOC, APL.REMARKS "
				+ "FROM APPLICANT APL, JOINSTATUS JOINSTS "
				+ "WHERE "
				+ "   APL.DELETEDATE IS NULL "
				+ "AND APL.RESULT = '採用' "
				+ "AND APL.JOININGSTATUS = JOINSTS.JOINSTATUSID";

		return da.getData(strSql);
	}


	// 応募者テーブル情報取得
	// 引数
	// intTargetApplicantID：検索対象のID
	// strApplicantName：応募者名
	// strDocumentKinds：書類区分
	// intJoiningStatusId：手続きステータスID
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getJoiningProcessData(int intTargetApplicantID) throws SQLException {
		return getJoiningProcessData(intTargetApplicantID, "", "", 0);
	}

	// 応募者テーブル情報取得
	// 引数
	// intTargetApplicantID：検索対象のID
	// strApplicantName：応募者名
	// strDocumentKinds：書類区分
	// intJoiningStatusId：手続きステータスID
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getJoiningProcessData(int intTargetApplicantID, String strApplicantName, String strDocumentKinds,
										int intJoiningStatusId) throws SQLException {

		strSql = "SELECT "
				+ "   APL.APPLICANTID, APL.APPLICANTNAME, APL.DOCUMENTKINDS, "
				+ "   JOINSTS.JOINSTATUSNAME, APL.TELEPHONE, APL.SHORTAGEDOC, APL.REMARKS "
				+ "FROM APPLICANT APL, JOINSTATUS JOINSTS "
				+ "WHERE "
				+ "   APL.DELETEDATE IS NULL "
				+ "AND APL.RESULT = '採用' "
				+ "AND APL.JOININGSTATUS = JOINSTS.JOINSTATUSID ";
		if(intTargetApplicantID != 0) {
			strSql += 	"AND APL.APPLICANTID = " + String.valueOf(intTargetApplicantID) + " ";
		}
		if(!strApplicantName.equals("")) {
			strSql +=	"AND APL.APPLICANTNAME LIKE '%" + strApplicantName + "%' ";
		}
		if(!strDocumentKinds.equals("")) {
			strSql +=	"AND APL.DOCUMENTKINDS = '" + strDocumentKinds + "' ";
		}
		if(intJoiningStatusId != 0) {
			strSql +=	"AND JOINSTS.JOINSTATUSID = " + intJoiningStatusId + " ";
		}
		strSql += "ORDER BY APL.APPLICANTID";

		return da.getData(strSql);
	}

	// 応募者テーブル情報更新
	// 引数
	// intApplicantId：応募者ID
	// intJoinStatus：手続きステータス
	// strShortageDoc：不足資料
	// strRemarks：備考
	// 戻り値
	// int：処理成功した件数
	public int updateJoiningProcess(int intApplicantId, int intJoinStatus, String strShortageDoc,
								String strRemarks) throws SQLException {

		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE ");
		buf.append("APPLICANT SET  ");
		buf.append("JOININGSTATUS = ?, SHORTAGEDOC = ?, REMARKS = ?, ");
		buf.append("UPDATEDATE = CURRENT_TIMESTAMP ");
		buf.append("WHERE APPLICANTID = ?");

	    return da.updateApplicantData(buf.toString(), intApplicantId, intJoinStatus, strShortageDoc, strRemarks);
	}

	// 手続き完了処理
	// 引数
	// intApplicantId：応募者ID
	// 戻り値
	// int：処理成功した件数
	public boolean completeJoiningProcess(int intApplicantId) throws SQLException {

		// 応募者情報の更新
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE ");
		buf.append("APPLICANT SET  ");
		buf.append("JOININGSTATUS = ?, SHORTAGEDOC = ?, REMARKS = ?, ");
		buf.append("UPDATEDATE = CURRENT_TIMESTAMP, ");
		buf.append("DELETEDATE = CURRENT_TIMESTAMP ");
		buf.append("WHERE APPLICANTID = ?");

	    int resultCount = da.updateApplicantData(buf.toString(), intApplicantId, 80, "", "");

	    // 更新失敗の場合
	    if(resultCount <= 0) {
	    	return false;
	    }

	    // 応募者情報検索
	    String strSql = "SELECT APPLICANTNAME, GENDER, AGE, NATIONALITY, TELEPHONE "
	    					+ "FROM APPLICANT WHERE APPLICANTID = " + intApplicantId;
	    ResultSet rs = da.getData(strSql);

	    // 社員情報登録
	    strSql = null;
	    resultCount = 0;
	    strSql = "INSERT STAFF (STAFFNAME, GENDER, AGE, NATIONALITY, TELEPHONE, CREATEDATE) "
	    		+ "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

	    while(rs.next()) {
		    resultCount = da.insertApplicantData(strSql, rs.getString("APPLICANTNAME"), rs.getString("GENDER"),
					rs.getInt("AGE"), rs.getString("NATIONALITY"), rs.getString("TELEPHONE"));
	    }

	    // 更新失敗の場合
	    if(resultCount <= 0) {
	    	return false;
	    }

	    return true;
	}

	// 部署コンボボックス設定用データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getDepartmentData() throws SQLException {

		strSql = "SELECT * FROM DEPARTMENT";

		return da.getData(strSql);
	}

	// 役職コンボボックス設定用データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getPostData() throws SQLException {

		strSql = "SELECT * FROM POST";

		return da.getData(strSql);
	}

	// 従業員データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getStaffListData() throws SQLException {

		strSql = "SELECT STF.STAFFID, STF.STAFFNAME, "
			   + "   STF.GENDER, STF.AGE, STF.TELEPHONE, "
			   + "   DEPT.DEPARTMENTNAME, POS.POSTNAME "
			   + "FROM STAFF STF "
			   + "LEFT JOIN DEPARTMENT DEPT "
			   + "ON STF.DEPARTMENTID = DEPT.DEPARTMENTID "
			   + "LEFT JOIN POST POS "
			   + "ON STF.POSTID = POS.POSTID ORDER BY STF.STAFFID";

		return da.getData(strSql);
	}

	// 従業員データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getStaffListData(int intTargetStaffId, String strStaffName,
											int intDepartmentId, int intPostId) throws SQLException {

		// 条件追加フラグ true:追加あり、false:追加なし
		boolean startAdd = false;

		strSql = "SELECT STF.STAFFID, STF.STAFFNAME, "
				   + "   STF.GENDER, STF.AGE, STF.TELEPHONE, "
				   + "   DEPT.DEPARTMENTNAME, POS.POSTNAME "
				   + "FROM STAFF STF "
				   + "LEFT JOIN DEPARTMENT DEPT "
				   + "ON STF.DEPARTMENTID = DEPT.DEPARTMENTID "
				   + "LEFT JOIN POST POS "
				   + "ON STF.POSTID = POS.POSTID ";
		if(intTargetStaffId != 0) {
			strSql += 	"WHERE STF.STAFFID = " + String.valueOf(intTargetStaffId) + " ";
			startAdd = true;
		}
		if(!strStaffName.equals("")) {
			// 条件追加ありの場合
			if(startAdd) {
				strSql +=	"AND STF.STAFFNAME LIKE '%" + strStaffName + "%' ";
			} else
			{
				strSql +=	"WHERE STF.STAFFNAME LIKE '%" + strStaffName + "%' ";
			}
			startAdd = true;
		}
		if(intDepartmentId != 0) {
			// 条件追加ありの場合
			if(startAdd) {
				strSql +=	"AND STF.DEPARTMENTID = " + String.valueOf(intDepartmentId) + " ";
			} else
			{
				strSql +=	"WHERE STF.DEPARTMENTID = " + String.valueOf(intDepartmentId) + " ";
			}
			startAdd = true;
		}
		if(intPostId != 0) {
			// 条件追加ありの場合
			if(startAdd) {
				strSql +=	"AND STF.POSTID = " + String.valueOf(intPostId) + " ";
			} else
			{
				strSql +=	"WHERE STF.POSTID = " + String.valueOf(intPostId) + " ";
			}
		}
		strSql += "ORDER BY STF.STAFFID";

		return da.getData(strSql);
	}

	// 給料コンボボックス設定用データ取得
	// 引数
	// なし
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getSalaryData() throws SQLException {

		strSql = "SELECT * FROM SALARY";

		return da.getData(strSql);
	}

	// 従業員テーブル情報取得
	// 引数
	// intTargetDocumentsId：検索対象のID
	// 戻り値
	// ResultSet：取得したデータの値
	public ResultSet getStaffData(int intTargetDocumentsId) throws SQLException {

		strSql = "SELECT * " +
				 "FROM STAFF " +
				 "WHERE " +
				 "	STAFFID = " + String.valueOf(intTargetDocumentsId);

		return da.getData(strSql);
	}

	// 書類テーブル情報登録
	// 引数
	// staff：画面項目のStaff
	// 戻り値
	// int：処理成功した件数
	public int insertStaffData(Staff staff) throws SQLException {

		StringBuffer buf = new StringBuffer();
		buf.append("INSERT INTO STAFF (");
		buf.append("STAFFNAME,");
		buf.append("GENDER,");
		buf.append("AGE,");
		buf.append("NATIONALITY,");
		buf.append("TELEPHONE,");
		buf.append("EMAIL,");
		buf.append("POSTCODE,");
		buf.append("PREFECTURES,");
		buf.append("MUNICIPALITIES,");
		buf.append("ADDRESS,");
		buf.append("MYNUMBER,");
		buf.append("DEPARTMENTID,");
		buf.append("POSTID ,");
		buf.append("SALARYID,");
		buf.append("JOINDATE,");
		buf.append("RETIREDATE,");
		buf.append("REMARKS,");
		buf.append("EMERGENCYNAME,");
		buf.append("EMERGENCYSEQUEL,");
		buf.append("EMERGENCYPOSTCODE,");
		buf.append("EMERGENCYTELEPHONE,");
		buf.append("EMERGENCYPREFECTURES,");
		buf.append("EMERGENCYMUNICIPALITIES,");
		buf.append("EMERGENCYADDRESS,");
		buf.append("SPOUSE,");
		buf.append("SUPPORT,");
		buf.append("EDUCATION,");
		buf.append("QUALIFICATION1,");
		buf.append("QUALIFICATION2,");
		buf.append("QUALIFICATION3,");
		buf.append("QUALIFICATION4,");
		buf.append("QUALIFICATION5,");
		buf.append("QUALIFICATION6,");
		buf.append("QUALIFICATION7,");
		buf.append("SOCIALINSURANCENUMBER,");
		buf.append("BASICPENSIONNUMBER,");
		buf.append("FAINANCIALINSTITUDE,");
		buf.append("FAINANCIALINSTITUDEKANA,");
		buf.append("FAINANCIALINSTITUDECODE,");
		buf.append("BRANCHNAME,");
		buf.append("BRANCHNAMEKANA,");
		buf.append("BRANCHCODE,");
		buf.append("DEPOSITKIND,");
		buf.append("ACCOUNTNUMBER,");
		buf.append("ACCOUNTNAME,");
		buf.append("ACCOUNTNAMEKANA,");
		buf.append("RECIPIENTNAME,");
		buf.append("RECIPIENTNAMEKANA,");
		buf.append("STAFFIMAGE,");
		buf.append("CREATEDATE");
		buf.append(") VALUES (");
		buf.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
		buf.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
		buf.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");

	    return da.insetUpdateStaffData(buf.toString(), staff);
	}

	// 書類テーブル情報更新
	// 引数
	// intTargetStaffId:更新対象のID
	// staff：画面項目のStaff
	// 戻り値
	// int：処理成功した件数
	public int updateStaffData(int intTargetStaffId, Staff staff) throws SQLException {

		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE STAFF SET ");
		buf.append("STAFFNAME = ?, ");
		buf.append("GENDER = ?,");
		buf.append("AGE = ?,");
		buf.append("NATIONALITY = ?,");
		buf.append("TELEPHONE = ?,");
		buf.append("EMAIL = ?,");
		buf.append("POSTCODE = ?,");
		buf.append("PREFECTURES = ?,");
		buf.append("MUNICIPALITIES = ?,");
		buf.append("ADDRESS = ?,");
		buf.append("MYNUMBER = ?,");
		buf.append("DEPARTMENTID = ?,");
		buf.append("POSTID = ?,");
		buf.append("SALARYID = ?,");
		buf.append("JOINDATE = ?,");
		buf.append("RETIREDATE = ?,");
		buf.append("REMARKS = ?,");
		buf.append("EMERGENCYNAME = ?,");
		buf.append("EMERGENCYSEQUEL = ?,");
		buf.append("EMERGENCYPOSTCODE = ?,");
		buf.append("EMERGENCYTELEPHONE = ?,");
		buf.append("EMERGENCYPREFECTURES = ?,");
		buf.append("EMERGENCYMUNICIPALITIES = ?,");
		buf.append("EMERGENCYADDRESS = ?,");
		buf.append("SPOUSE = ?,");
		buf.append("SUPPORT = ?,");
		buf.append("EDUCATION = ?,");
		buf.append("QUALIFICATION1 = ?,");
		buf.append("QUALIFICATION2 = ?,");
		buf.append("QUALIFICATION3 = ?,");
		buf.append("QUALIFICATION4 = ?,");
		buf.append("QUALIFICATION5 = ?,");
		buf.append("QUALIFICATION6 = ?,");
		buf.append("QUALIFICATION7 = ?,");
		buf.append("SOCIALINSURANCENUMBER = ?,");
		buf.append("BASICPENSIONNUMBER = ?,");
		buf.append("FAINANCIALINSTITUDE = ?,");
		buf.append("FAINANCIALINSTITUDEKANA = ?,");
		buf.append("FAINANCIALINSTITUDECODE = ?,");
		buf.append("BRANCHNAME = ?,");
		buf.append("BRANCHNAMEKANA = ?,");
		buf.append("BRANCHCODE = ?,");
		buf.append("DEPOSITKIND = ?,");
		buf.append("ACCOUNTNUMBER = ?,");
		buf.append("ACCOUNTNAME = ?,");
		buf.append("ACCOUNTNAMEKANA = ?,");
		buf.append("RECIPIENTNAME = ?,");
		buf.append("RECIPIENTNAMEKANA = ?,");
		buf.append("STAFFIMAGE = ?,");
		buf.append("UPDATEDATE = CURRENT_TIMESTAMP ");
		buf.append("WHERE STAFFID = " + intTargetStaffId);

	    return da.insetUpdateStaffData(buf.toString(), staff);
	}

	// 応募者テーブル情報削除
	// 引数
	// intTargetScheduleID：削除対象の面接日程テーブルID
	// 戻り値
	// int：処理成功した件数
	public int deleteStaffData(int intTargetStaffID) throws SQLException {

		String strSql = "UPDATE STAFF SET RETIREDATE = CURRENT_TIMESTAMP, "
			   		   + "DELETEDATE = CURRENT_TIMESTAMP WHERE STAFFID = " + intTargetStaffID;

		return da.executeQuery(strSql);
	}
}
