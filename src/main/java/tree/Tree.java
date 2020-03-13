package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 树形结构解析
 * 
 * @author gewx
 **/
public final class Tree {

	/**
	 * 解析数据结构
	 * 
	 * @author gewx
	 * @param nodeList 数据节点集合
	 * @return 解析完成后的树状结果
	 **/
	public static List<Node> parse(List<Node> nodeList) {
		List<Node> resultList = new ArrayList<>(64);
		nodeList.stream().filter(val -> !hasChild(nodeList, val.getMenuId())).collect(Collectors.toList())
				.forEach(val -> {
					reverseRecursion(val, nodeList, resultList);
				});

		Set<Node> set = new HashSet<>(32);
		set.addAll(resultList);

		resultList.clear();
		set.stream().sorted((o1, o2) -> o1.getSortNum().intValue() > o2.getSortNum().intValue() ? 1 : -1)
				.forEach(val -> {
					resultList.add(val);
					recursion(val, nodeList);
				});

		return resultList;
	}

	/**
	 * 取出某个节点直至末尾叶子节点的数据
	 * 
	 * @author gewx
	 * @param node     叶子节点
	 * @param nodeList 数据节点集合
	 * @return 解析完成后的树状结果
	 **/
	public static void getNodeJson(Node node, List<Node> nodeList) {
		recursion(node, nodeList);
	}

	/**
	 * 倒序递归检索所有父节点,由下往上直到所有根节点
	 * 
	 * @author gewx
	 * @param node       末尾叶子节点
	 * @param nodeList   数据节点集合
	 * @param resultList 根节点集合
	 * @return void
	 **/
	private static void reverseRecursion(Node node, List<Node> nodeList, List<Node> resultList) {
		List<Node> parentNodeList = nodeList.stream().filter(val -> val.getMenuId().equals(node.getParentId()))
				.collect(Collectors.toList());
		if (parentNodeList.size() != 0) {
			Node parentNode = parentNodeList.get(0);
			reverseRecursion(parentNode, nodeList, resultList);
		} else {
			resultList.add(node);
		}
	}

	/**
	 * 递归检索所有子节点
	 * 
	 * @author gewx
	 * @param node     根节点
	 * @param nodeList 数据节点集合
	 * @return void
	 **/
	private static void recursion(Node node, List<Node> nodeList) {
		List<Node> childNodeList = nodeList.stream().filter(val -> val.getParentId().equals(node.getMenuId()))
				.sorted((o1, o2) -> o1.getSortNum().intValue() > o2.getSortNum() ? 1 : -1).collect(Collectors.toList());
		if (childNodeList.size() != 0) {
			node.setChildren(childNodeList);
			childNodeList.stream().forEach(val -> {
				recursion(val, nodeList);
			});
		}
	}

	/**
	 * 是否还存在父级元素,找出末尾叶子节点
	 * 
	 * @author gewx
	 * 
	 * @param node   叶子节点
	 * @param menuId 节点标记
	 * @return boolean true 存在, false 不存在
	 **/
	private static boolean hasChild(List<Node> node, String menuId) {
		return node.stream().anyMatch(val -> val.getParentId().equals(menuId));
	}

	/**
	 * 叶子节点数据结构,可与库表做一一映射仅需要保持menuId与parentId映射关系即可
	 **/
	@Getter
	@Setter
	@ToString
	public static class Node {

		/**
		 * 菜单Id
		 **/
		private String menuId;

		/**
		 * 菜单名称
		 **/
		private String menuName;

		/**
		 * 菜单类型
		 **/
		private String menuType;

		/**
		 * 菜单编码
		 **/
		private String menuCode;

		/**
		 * 菜单URL
		 **/
		private String url;

		/**
		 * 菜单父级Id
		 **/
		public String parentId;

		/**
		 * 排序
		 **/
		private Integer sortNum;

		/**
		 * 子菜单
		 **/
		private List<Node> children = new ArrayList<>();

		public Node(String menuId, String menuName, String menuType, String menuCode, String url, String parentId,
				Integer sortNum) {
			this.menuId = menuId;
			this.menuName = menuName;
			this.menuType = menuType;
			this.menuCode = menuCode;
			this.url = url;
			this.parentId = parentId;
			this.sortNum = sortNum;
		}

		@Override
		public final boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}

			Node otherObject = (Node) obj;
			if (getClass() != otherObject.getClass()) {
				return false;
			}

			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.menuId, otherObject.getMenuId());
			return builder.isEquals();
		}

		@Override
		public final int hashCode() {
			return this.menuId.hashCode() * 31;
		}
	}

	public static void main(String[] args) {
		List<Node> array = new ArrayList<>();
		array.add(new Tree.Node("CODE_00", "用户管理", "M", "0101", "#", "0", 0));
		array.add(new Tree.Node("CODE_01", "管理界面", "M", "0102", "#", "0", 1));
		array.add(new Tree.Node("CODE_02", "权限管理", "C", "010101", "http://www.baidu.com", "CODE_00", 0));
		array.add(new Tree.Node("CODE_03", "内部管理", "C", "010201", "http://www.baidu.com", "CODE_01", 0));

		array.add(new Tree.Node("CODE_04", "内部用户权限", "C", "010201", "http://www.baidu.com", "CODE_02", 0));
		array.add(new Tree.Node("CODE_05", "外部用户权限", "C", "010201", "http://www.baidu.com", "CODE_02", 1));
		array.add(new Tree.Node("CODE_06", "子权限", "C", "010201", "http://www.baidu.com", "CODE_04", 0));
		array.add(new Tree.Node("CODE_07", "子系统内部管理", "C", "010201", "http://www.baidu.com", "CODE_03", 0));

		List<Node> list = Tree.parse(array);
		String val = JSON.toJSONString(list);
		System.out.println(val);

		// 取出当下节点下所有数据
		Node node = new Tree.Node("CODE_01x", "系统管理员", "M", "0101", "#", "0", 0);
		Tree.getNodeJson(node, array);
		System.out.println(JSON.toJSONString(node));
	}
}
