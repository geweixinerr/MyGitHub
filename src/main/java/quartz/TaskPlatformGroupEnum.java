package quartz;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;

/**
 * @author gewx 调度任务平台任务分组枚举
 **/
@Getter
@ToString
public enum TaskPlatformGroupEnum {

	JOB_001("STORE_GROUP", "馆主端运营数据统计");

	TaskPlatformGroupEnum(String _groupId, String _comment) {
		this.groupId = _groupId;
		this.comment = _comment;
	}

	private String groupId;

	private String comment;

	public static boolean isValid(String groupId) {
		return Stream.of(TaskPlatformGroupEnum.values()).anyMatch(val -> val.getGroupId().equals(groupId));
	}

	public static TaskPlatformGroupEnum getObject(String groupId) {
		Optional<TaskPlatformGroupEnum> opt = Stream.of(TaskPlatformGroupEnum.values())
				.filter(val -> val.getGroupId().equals(groupId)).findFirst();
		return opt.orElseGet(() -> null);
	}
}
