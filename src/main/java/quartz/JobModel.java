package quartz;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author gewx 2019.10.18 JobModel, Job模型目前主要设计为两块: 1.本地模式/2.远程模式 
 *        本地模式:系统内部服务提供的任务执行主体.譬如: xxxxClass.xxxxMethod 
 *        远程模式:外部系统提供的任务执行主体. 譬如: http://xxxx/xxx
 **/

@Setter
@Getter
@ToString
public class JobModel implements Serializable {

	private static final long serialVersionUID = 5031891631713341737L;

	/**
	 * 任务分组Id
	 **/
	@Setter(lombok.AccessLevel.NONE)
	private String groupId;
	
	/**
	 * 任务Id
	 **/
	private String taskId;
	
	/**
	 * 任务名称
	 **/
	private String taskName;

	/**
	 * cron任务表达式
	 **/
	private String cronExpression;

	/**
	 * 服务契约
	 * **/
	private String serviceContract;

	/**
	 * 服务名
	 * **/
	private String serviceName;
	
	/**
	 * 任务创建时间
	 **/
	private Date createTime;

	/**
	 * 任务创建人
	 **/
	private String createMan;

	/**
	 * 任务修改时间
	 **/
	private String modifyTime;
	
	public void setGroup(TaskPlatformGroupEnum platform) {
		this.groupId = platform.getGroupId();
	}
}
