package tree;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 实体映射叶子节点
 * 
 * @author gewx
 **/
@ToString
public final class TreeNode extends Node implements Serializable {

	private static final long serialVersionUID = 3564872935020099986L;

	/**
	 * 菜单名称
	 **/
	@Getter
	@Setter
	private String menuName;

	/**
	 * 菜单类型
	 **/
	@Getter
	@Setter
	private String menuType;

	/**
	 * 菜单编码
	 **/
	@Getter
	@Setter
	private String menuCode;

	/**
	 * 菜单URL
	 **/
	@Getter
	@Setter
	private String url;

	public TreeNode(String id, String menuName, String menuType, String menuCode, String url, String parentId,
			Integer sortNum) {
		this.id = id;
		this.parentId = parentId;
		this.sortNum = sortNum;
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuCode = menuCode;
		this.url = url;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		TreeNode otherObject = (TreeNode) obj;
		if (getClass() != otherObject.getClass()) {
			return false;
		}

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.id, otherObject.id);
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		return this.id.hashCode() * 31;
	}
}
