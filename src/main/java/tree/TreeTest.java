package tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TreeTest {

	public static void main(String[] args) throws JsonProcessingException {
		List<Node> array = new ArrayList<>();
		array.add(new TreeNode("CODE_00", "用户管理", "M", "0101", "#", "0", 0, "0", "system"));
		array.add(new TreeNode("CODE_01", "管理界面", "M", "0102", "#", "0", 1, "0", "system"));
		array.add(new TreeNode("CODE_02", "权限管理", "C", "010101", "http://www.baidu.com", "CODE_00", 0, "0", "system"));
		array.add(new TreeNode("CODE_02", "内部管理", "C", "010101", "http://www.baidu.com", "CODE_00", 0, "0", "system"));

		array.add(
				new TreeNode("CODE_04", "内部用户权限", "C", "010201", "http://www.baidu.com", "CODE_02", 0, "0", "system"));
		array.add(
				new TreeNode("CODE_05", "外部用户权限", "C", "010201", "http://www.baidu.com", "CODE_02", 1, "0", "system"));
		array.add(new TreeNode("CODE_06", "子权限", "C", "010201", "http://www.baidu.com", "CODE_04", 0, "0", "system"));
		array.add(
				new TreeNode("CODE_07", "子系统内部管理", "C", "010201", "http://www.baidu.com", "CODE_06", 0, "0", "system"));
		
		ObjectMapper jsonMapper = new ObjectMapper();

		/*
		// 解析树
		List<Node> nodeList = TreeUtils.parse(array);
		System.out.println("原始树: " + jsonMapper.writeValueAsString(nodeList));

		// 检索节点下所有子节点
		Node rootNode = new Node();
		rootNode.setId("CODE_04");
		TreeUtils.getNodeJson(rootNode, array);

		// 获取根节点下所有节点Id
		List<String> arrayNodeId = new ArrayList<>();
		TreeUtils.searchNodeId(rootNode, arrayNodeId);

		System.out.println("节点Id: " + arrayNodeId);
*/
		System.out.println("========================节点检索=============================");
		Node searchNode = new Node();
		searchNode.setId("CODE_04");
		searchNode.setParentId("CODE_02");
		
		List<Node> resultDownList = new ArrayList<>();
		TreeUtils.searchNodeDown(searchNode, array, resultDownList);
		System.out.println("向下检索结果: " + jsonMapper.writeValueAsString(TreeUtils.parse(resultDownList)));

		List<Node> resultUpList = new ArrayList<>();
		TreeUtils.searchNodeUp(searchNode, array, resultUpList);
		System.out.println("向上检索结果: " + jsonMapper.writeValueAsString(TreeUtils.parse(resultUpList)));
	}
}
