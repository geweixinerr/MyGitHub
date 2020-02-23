package quartz;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author gewx 2019.10.18 JobModel
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
	private Long taskId;

	/**
	 * 任务名称
	 **/
	private String taskName;

	/**
	 * cron任务表达式
	 **/
	private String cronExpression;

	/**
	 * 任务状态
	 **/
	private String state;

	/**
	 * 服务契约
	 **/
	private String serviceContract;

	/**
	 * 服务名
	 **/
	private String serviceName;
}
