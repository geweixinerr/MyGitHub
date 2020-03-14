package tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 树状结构顶层元素
 * 
 * @author gewx
 **/
public class Node implements Serializable {

	private static final long serialVersionUID = -4405388942387953237L;

	/**
	 * Id
	 **/
	@Getter
	@Setter
	protected String id;

	/**
	 * 父级Id
	 **/
	@Getter
	@Setter
	protected String parentId;

	/**
	 * 排序
	 **/
	@Getter
	@Setter
	protected Integer sortNum;

	/**
	 * 关联子节点
	 **/
	@Getter
	@Setter
	protected List<Node> children = new ArrayList<>();
}
