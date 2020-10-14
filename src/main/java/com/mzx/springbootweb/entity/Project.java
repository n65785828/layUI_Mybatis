//package com.mzx.springbootweb.entity;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//
//import java.time.LocalDate;
//public class Project {
//
//
//	private String id;
//	private String code;//项目编码
//	private String name;//项目名称
//	private int type;//项目类型  1部门立项 2公司立项
//	private int priority;//优先级  1   2   3   4
//	private String applicantUnit;//申请单位
//	private String applicant;//申请人
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private LocalDate applicantDate;//申请日期
//	private boolean contract;//是否有合同
//	private String contractNum;//合同号
//	private float contractAmount;//合同金额
//	private int stage;//项目阶段  1评估阶段  2研发阶段  3测试阶段  4发布
//	private int status;//项目状态
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private LocalDate statusDate;//状态生效日期
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private LocalDate approvalDate;//立项日期
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private LocalDate planStartDate;//项目计划起始日期
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private LocalDate planEndDate;//计划结束日期
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private LocalDate finishDate;//完工日期
//	private int deploy;//是否需要部署
//	private String deployInfo;//部署信息
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private LocalDate deployDate;//部署日期
//	private String precondition;//前置条件
//	private String remark;//备注
//	//项目需求
//	private String profile;//项目简介
//	private String annexFile;//立项申请书附件（可选）
//	private String reqText;//项目需求
//	private String reqFile;//项目需求附件（可选）
//	//项目参与人员
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="project")
//	@OrderBy("planHours desc")
//	private List<WorkTime> workTimes;//工时
//	//关联项目产品
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="project")
//	private List<ProductRelation> productRelations;//关联产品
//	private boolean closed;//是否关闭
//	private boolean examine;//是否审核
//
//	private String productId;//所属产品类型
//	private float planTotalWork;// 项目计划总工时
//	private float totalWork;// 项目实际总工时
//	private boolean finish;//项目是否完结
//	private LocalDate startDate;//项目实际开始日期
//	private LocalDate endDate;//项目实际完结日期
//
//	public String getStageStr() {
//		String stageStr = "";
//		switch (stage) {
//			case 1:
//				stageStr = "立项";
//				break;
//			case 2:
//				stageStr = "研发阶段";
//				break;
//			case 3:
//				stageStr = "测试阶段";
//				break;
//			case 4:
//				stageStr = "发布";
//				break;
//		}
//		return stageStr;
//	}
//
//	public String getStatusStr() {
//		String statusStr = "";
//		switch (status) {
//			case 1:
//				statusStr = "<span style=\"color:#808080;\">新项目</span>";
//				break;
//			case 2:
//				statusStr = "<span style=\"color:#7030A0;\">待确认/回复</span>";
//				break;
//			case 3:
//				statusStr = "<span style=\"color:#808080;\">待评估</span>";
//				break;
//			case 4:
//				statusStr = "<span style=\"color:#00B0F0;\">待立项</span>";
//				break;
//			case 5:
//				statusStr = "<span style=\"color:#00B050;\">已立项</span>";
//				break;
//			case 6:
//				statusStr = "<span style=\"color:#FF0000;\">不立项</span>";
//				break;
//			case 7:
//				statusStr = "<span style=\"color:#00B050;\">需求已确认</span>";
//				break;
//			case 10:
//				statusStr = "<span style=\"color:#808080;\">研发已计划</span>";
//				break;
//			case 11:
//				statusStr = "<span style=\"color:#00B0F0;\">研发中</span>";
//				break;
//			case 12:
//				statusStr = "<span style=\"color:#FFC000;\">暂停研发</span>";
//				break;
//			case 13:
//				statusStr = "<span style=\"color:#FF0000;\">终止研发</span>";
//				break;
//			case 14:
//				statusStr = "<span style=\"color:#5B9BD5;\">研发完成</span>";
//				break;
//			case 15:
//				statusStr = "<span style=\"color:#00B050;\">已提交测试</span>";
//				break;
//			case 20:
//				statusStr = "<span style=\"color:#808080;\">测试已计划</span>";
//				break;
//			case 21:
//				statusStr = "<span style=\"color:#00B0F0;\">测试中</span>";
//				break;
//			case 22:
//				statusStr = "<span style=\"color:#FFC000;\">测试暂停</span>";
//				break;
//			case 23:
//				statusStr = "<span style=\"color:#00B050;\">测试完成</span>";
//				break;
//			case 30:
//				statusStr = "<span style=\"color:#00B050;\">已发布</span>";
//				break;
//			case 31:
//				statusStr = "<span style=\"color:#FFC000;\">等待部署</span>";
//				break;
//			case 32:
//				statusStr = "<span style=\"color:#00B050;\">已部署</span>";
//				break;
//		}
//		return statusStr;
//	}
//
//	public Object[] getData() {
//		Object[] data=new Object[11];
//		data[0]=code;
//		data[1]=name;
//		data[2]=type == 1?"部门立项":"公司立项";
//		data[3]=applicantDate;
//		data[4]=approvalDate;
//		data[5]=applicantUnit;
//		data[6]=getStageStr();
//		int last = getStatusStr().lastIndexOf("<");
//		int begin = getStatusStr().indexOf(">")+1;
//		data[7]=getStatusStr().substring(begin,last);
//		data[8]=finishDate;
//		data[9]=deployInfo;
//		data[10]=priority;
//		return data;
//	}
//
//	public float getPlanTotalWork() {
//		return planTotalWork;
//	}
//	public void setPlanTotalWork(float planTotalWork) {
//		this.planTotalWork = planTotalWork;
//	}
//	public List<WorkTime> getWorkTimes() {
//		return workTimes;
//	}
//	public void setWorkTimes(List<WorkTime> workTimes) {
//		this.workTimes = workTimes;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getProductId() {
//		return productId;
//	}
//	public void setProductId(String productId) {
//		this.productId = productId;
//	}
//	public String getCode() {
//		return code;
//	}
//	public void setCode(String code) {
//		this.code = code;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getApplicant() {
//		return applicant;
//	}
//	public void setApplicant(String applicant) {
//		this.applicant = applicant;
//	}
//	public String getProfile() {
//		return profile;
//	}
//	public void setProfile(String profile) {
//		this.profile = profile;
//	}
//	public String getReqText() {
//		return reqText;
//	}
//	public void setReqText(String reqText) {
//		this.reqText = reqText;
//	}
//	public String getReqFile() {
//		return reqFile;
//	}
//	public void setReqFile(String reqFile) {
//		this.reqFile = reqFile;
//	}
//	public boolean isFinish() {
//		return finish;
//	}
//	public void setFinish(boolean finish) {
//		this.finish = finish;
//	}
//	public String getAnnexFile() {
//		return annexFile;
//	}
//	public void setAnnexFile(String annexFile) {
//		this.annexFile = annexFile;
//	}
//	public LocalDate getPlanStartDate() {
//		return planStartDate;
//	}
//	public void setPlanStartDate(LocalDate planStartDate) {
//		this.planStartDate = planStartDate;
//	}
//	public LocalDate getPlanEndDate() {
//		return planEndDate;
//	}
//	public void setPlanEndDate(LocalDate planEndDate) {
//		this.planEndDate = planEndDate;
//	}
//	public LocalDate getStartDate() {
//		return startDate;
//	}
//	public void setStartDate(LocalDate startDate) {
//		this.startDate = startDate;
//	}
//	public LocalDate getEndDate() {
//		return endDate;
//	}
//	public void setEndDate(LocalDate endDate) {
//		this.endDate = endDate;
//	}
//	public int getType() { return type; }
//	public void setType(int type) { this.type = type; }
//	public int getPriority() { return priority; }
//	public void setPriority(int priority) { this.priority = priority; }
//	public String getApplicantUnit() { return applicantUnit; }
//	public void setApplicantUnit(String applicantUnit) { this.applicantUnit = applicantUnit; }
//	public LocalDate getApplicantDate() { return applicantDate; }
//	public void setApplicantDate(LocalDate applicantDate) { this.applicantDate = applicantDate; }
//	public boolean isContract() { return contract; }
//	public void setContract(boolean contract) { this.contract = contract; }
//	public String getContractNum() { return contractNum; }
//	public void setContractNum(String contractNum) { this.contractNum = contractNum; }
//	public float getContractAmount() { return contractAmount; }
//	public void setContractAmount(float contractAmount) { this.contractAmount = contractAmount; }
//	public int getStage() { return stage; }
//	public void setStage(int stage) { this.stage = stage; }
//	public int getStatus() { return status; }
//	public void setStatus(int status) { this.status = status; }
//	public LocalDate getApprovalDate() { return approvalDate; }
//	public void setApprovalDate(LocalDate approvalDate) { this.approvalDate = approvalDate; }
//	public LocalDate getFinishDate() { return finishDate; }
//	public void setFinishDate(LocalDate finishDate) { this.finishDate = finishDate; }
//	public int getDeploy() { return deploy; }
//	public void setDeploy(int deploy) { this.deploy = deploy; }
//	public String getDeployInfo() { return deployInfo; }
//	public void setDeployInfo(String deployInfo) { this.deployInfo = deployInfo; }
//	public String getPrecondition() { return precondition; }
//	public void setPrecondition(String precondition) { this.precondition = precondition; }
//	public String getRemark() { return remark; }
//	public void setRemark(String remark) { this.remark = remark; }
//	public List<ProductRelation> getProductRelations() { return productRelations; }
//	public void setProductRelations(List<ProductRelation> productRelations) { this.productRelations = productRelations; }
//	public boolean isClosed() { return closed; }
//	public void setClosed(boolean closed) { this.closed = closed; }
//
//	public boolean isExamine() {
//		return examine;
//	}
//
//	public void setExamine(boolean examine) {
//		this.examine = examine;
//	}
//
//	public LocalDate getStatusDate() {
//		return statusDate;
//	}
//
//	public void setStatusDate(LocalDate statusDate) {
//		this.statusDate = statusDate;
//	}
//
//	public LocalDate getDeployDate() {
//		return deployDate;
//	}
//
//	public void setDeployDate(LocalDate deployDate) {
//		this.deployDate = deployDate;
//	}
//
//	public float getTotalWork() {
//		return totalWork;
//	}
//
//	public void setTotalWork(float totalWork) {
//		this.totalWork = totalWork;
//	}
//}
