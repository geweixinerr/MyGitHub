package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 树形结构解析
 * 
 * @author gewx
 **/
public final class TreeUtils {

	/**
	 * 解析数据结构
	 * 
	 * @author gewx
	 * @param nodeList 数据节点集合
	 * @return 解析完成后的树状结果
	 **/
	public static List<Node> parse(List<Node> nodeList) {
		List<Node> resultList = new ArrayList<>(64);
		nodeList.stream().filter(val -> !hasChild(nodeList, val.getId())).collect(Collectors.toList()).forEach(val -> {
			reverseRecursion(val, nodeList, resultList);
		});

		Set<Node> set = new HashSet<>(16);
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
	 * 取出某个节点直至末尾叶子节点的数据并解析为树状结构
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
	 * 取出某个节点直至末尾叶子节点对象
	 * 
	 * @author gewx
	 * @param node       节点
	 * @param nodeList   检索目标
	 * @param resultList 结果
	 * @return 节点对象集合
	 **/
	public static void searchNodeDown(Node node, List<Node> nodeList, List<Node> resultList) {
		List<Node> childNodeList = nodeList.stream().filter(val -> val.getParentId().equals(node.getId())).distinct()
				.sorted((o1, o2) -> o1.getSortNum().intValue() > o2.getSortNum() ? 1 : -1).collect(Collectors.toList());
		if (childNodeList.size() != 0) {
			resultList.addAll(childNodeList);
			childNodeList.stream().forEach(val -> {
				searchNodeDown(val, nodeList, resultList);
			});
		}
	}

	/**
	 * 取出某个节点直至根父节点对象
	 * 
	 * @author gewx
	 * @param node       节点
	 * @param nodeList   检索目标
	 * @param resultList 结果
	 * @return 节点对象集合
	 **/
	public static void searchNodeUp(Node node, List<Node> nodeList, List<Node> resultList) {
		List<Node> parentNodeList = nodeList.stream().filter(val -> val.getId().equals(node.getParentId()))
				.collect(Collectors.toList());		
		if (parentNodeList.size() != 0) {
			Node parentNode = parentNodeList.get(0);
			resultList.add(parentNode);
			searchNodeUp(parentNode, nodeList, resultList);
		}
	}
	
	/**
	 * 检索树当中所有节点Id
	 * 
	 * @author gewx
	 * @param rootNode
	 * @return 节点Id集合
	 **/
	public static void searchNodeId(Node node, List<String> arrayNode) {
		arrayNode.add(node.getId());
		if (!node.getChildren().isEmpty()) {
			node.getChildren().forEach(val -> {
				searchNodeId(val, arrayNode);
			});
		}
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
		List<Node> parentNodeList = nodeList.stream().filter(val -> val.getId().equals(node.getParentId()))
				.collect(Collectors.toList());
		if (parentNodeList.size() != 0) {
			Node parentNode = parentNodeList.get(0);
			reverseRecursion(parentNode, nodeList, resultList);
		} else {
			resultList.add(node);
		}
	}

	/**
	 * 递归检索所有子节点,节点合并去重
	 * 
	 * @author gewx
	 * @param node     根节点
	 * @param nodeList 数据节点集合
	 * @return void
	 **/
	private static void recursion(Node node, List<Node> nodeList) {
		List<Node> childNodeList = nodeList.stream().filter(val -> val.getParentId().equals(node.getId())).distinct()
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
}
