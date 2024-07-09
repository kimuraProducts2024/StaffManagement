package Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DataAccess;

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
				"	ISC.SCHEDULEID, ISC.STARTDATETIME, ISC.STAFFID1, " +
				"	ISC.STAFFID2, ISC.STAFFID3, ISC.APPLICANTID, " +
				"   APL.APPLICANTNAME, ISC.REMARKS " +
				"FROM " +
				"	INTERVIEWSCHEDULE ISC, APPLICANT APL " +
				"WHERE " +
				"	ISC.SCHEDULEID = " + String.valueOf(intTargetScheduleID) + " " +
				"AND " +
				"	ISC.APPLICANTID = APL.APPLICANTID";

		return da.getData(strSql);
	}

	// 面接日程テーブル、応募者テーブル情報登録処理
	// 引数
	// intTargetScheduleID：検索対象のID
	// 戻り値
	// true：処理成功
	// false：処理失敗
	public ResultSet insertScheduleApplicantData() throws SQLException {

		strSql = "SELECT " +
				"	ISC.SCHEDULEID, ISC.STARTDATETIME, ISC.STAFFID1, " +
				"	ISC.STAFFID2, ISC.STAFFID3, ISC.APPLICANTID, " +
				"   APL.APPLICANTNAME, ISC.REMARKS " +
				"FROM " +
				"	INTERVIEWSCHEDULE ISC, APPLICANT APL " +
				"WHERE " +
				"	ISC.SCHEDULEID = " + String.valueOf(0) + " " +
				"AND " +
				"	ISC.APPLICANTID = APL.APPLICANTID";

		return da.getData(strSql);
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
		return da.insertData(strStartDateTime, strEndDateTime, intInterviewerId1,
				intInterviewerId2, intInterviewerId3, txtApplicant, txtRemarks);
	}

}
