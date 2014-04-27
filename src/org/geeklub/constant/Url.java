package org.geeklub.constant;

public class Url {

	/**维修员:表示已经维修的列表*/
	public static final String  REPAIR_MAN_HAVE_REPAIR_LIST      = "api/oasystem/lifemanage/repair/list?status=2";
	/**维修员:表示还未维修的列表*/
	public static final String  REPAIR_MAN_HAVE_NOT_REPAIR_LIST  = "api/oasystem/lifemanage/repair/list?status=1";
	/**维修员:用于提交维修是否完成的url*/
	public static final String  REPAIR_MAN_SUBMIT_IS_FINISH      = "api/oasystem/lifemanage/repair/finish?";


	/**生活老师:表示已审核的列表*/
	public static final String  LIFE_TEACHER_HAVE_CHECK          = "api/oasystem/lifemanage/repair/list?status=1";
	/**生活老师:表示还未审核的列表*/
	public static final String  LIFE_TEACHER_HAVE_NOT_CHECK      = "api/oasystem/lifemanage/repair/list?status=0";
	/**生活老师:表示审核拒绝的列表*/
	public static final String  LIFE_TEACHER_CHECK_REFUSED       = "api/oasystem/lifemanage/repair/list?status=3";
	/**生活老师:该条维修申请是否通过*/
	public static final String  LIFE_TEACHER_SUBMIT_IF_PASS      = "api/oasystem/lifemanage/repair/approve?";




	/**学生:表示全部历史列表*/
	public static final String STUDENT_REPAIR_RECORD_ALL         = "api/oasystem/lifemanage/repair/record?status=-1";
	/**学生:表示报修的历史列表*/
	public static final String STUDENT_REPAIR_RECORD_FOR_REPAIR  = "api/oasystem/lifemanage/repair/record?status=0";
	/**学生:表示已经通过审核的列表*/
	public static final String STUDENT_REPAIR_RECORD_PASS        = "api/oasystem/lifemanage/repair/record?status=1";
	/**学生:表示已经维修完成的列表*/
	public static final String STUDENT_REPAIR_RECORD_FINISHED    = "api/oasystem/lifemanage/repair/record?status=2";
	/**学生:表示提交报修表格的url*/
	public static final String STUDENT_REPAIR_SUBMIT_FORM        = "api/oasystem/lifemanage/repair?";


	/**学生：请求当前的状态*/
	public static final String Student_PART_JOB_REQUEST_STATUS                  = "api/oasystem/stworkmanage/status";
	/**学生：提交贫困证明*/
	public static final String Student_PART_JOB_REQUEST_SUBMIT_PICTURE          = "api/oasystem/stworkmanage/submit";
	/**学生：提交志愿*/
	public static final String Student_PART_JOB_SUBMIT_WISH                     = "api/oasystem/stworkmanage/apply";
	/**学生：显示审核结果的*/
	public static final String Student_PART_JOB_CHECK_RESULT                    = "api/oasystem/stworkmanage/showInfo";
	/**学生：访问部门列表*/
	public static final String Student_PART_JOB_REQUEST_DEPARTMENT_LIST       = "api/oasystem/stworkmanage/depart/list";


	/**老师:表示该老师所有的课程的列表*/
	public static final String TEACHER_COURSE_LIST                      = "api/oasystem/coursemanage/list?";
	/**老师:表示该课程的已签到学生的列表*/
	public static final String TEACHER_COURSE_STUDENT_HAVE_SIGN_IN_LIST = "api/oasystem/coursemanage/sign/list?";
	/**审核老师：贫困证明待审核列表*/
	public static final String TEACHER_PART_JOB_POVERTY_CHECK_LIST = "api/oasystem/stworkmanage/list?status=1";
	/**审核老师：志愿待审核列表*/
	public static final String TEACHER_PART_JOB_WISH_CHECK_LIST = "api/oasystem/stworkmanage/list?status=4";
	/**审核老师：全部列表*/
	public static final String TEACHER_PART_JOB_CHECK_ALL_LIST = "api/oasystem/stworkmanage/list?status=-1";

	/**审核老师：图片审核url*/
	public static final String TEACHER_PART_JOB_CHECK_PICTURE = "api/oasystem/stworkmanage/approve";
	/**审核老师：志愿审核url*/
	public static final String TEACHER_PART_JOB_CHECK_WISH = "api/oasystem/stworkmanage/submit/approve";


	/**学生:表示该学生的签到记录*/
	public static final String STUDENT_HAVE_SIGN_IN_LIST                = "api/oasystem/coursemanage/sign/record?status=1";


	/**老师：表示添加课程的url*/
	public static final String TEACHER_SUBMIE_COURSE_INFO = "api/oasystem/coursemanage/make";



	/**推送初始化*/
	public static final String PUSH_INIT = "api/oasystem/usermanage/push/init?";
	/**用户：登陆用的url*/
	public static final String USER_LOGIN = "api/oasystem/usermanage/login";


} 