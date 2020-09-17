package tree;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树状结构顶层元素
 * 
 * @author gewx
 **/
@Getter
@Setter
public class Node implements Serializable {

	private static final long serialVersionUID = -4405388942387953237L;

	/**
	 * Id
	 **/
	protected String id;

	/**
	 * 父级Id
	 **/
	protected String parentId;

	/**
	 * 排序
	 **/
	protected Integer sortNum;

	/**
	 * 关联子节点
	 **/
	protected List<Node> children = new ArrayList<>();

	@Override
	public final boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		}

		if (this == otherObject) {
			return true;
		}

		Node otherNode = (Node) otherObject;
		if (getClass() != otherNode.getClass()) {
			return false;
		}

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.id, otherNode.id);
		return builder.isEquals();
	}

	@Override
	public final int hashCode() {
		return this.id.hashCode() * 31;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		sb.append("id", this.id);
		sb.append("parentId", this.parentId);
		sb.append("sortNum", this.sortNum);
		sb.append("children", this.children);
		return sb.build();
	}
}
