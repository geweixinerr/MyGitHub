package tree;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class TreeTest {

	public static void main(String[] args) {
		List<Node> array = new ArrayList<>();
		array.add(new TreeNode("CODE_00", "用户管理", "M", "0101", "#", "0", 0));
		array.add(new TreeNode("CODE_01", "管理界面", "M", "0102", "#", "0", 1));
		array.add(new TreeNode("CODE_02", "权限管理", "C", "010101", "http://www.baidu.com", "CODE_00", 0));
		array.add(new TreeNode("CODE_03", "内部管理", "C", "010201", "http://www.baidu.com", "CODE_01", 0));

		array.add(new TreeNode("CODE_04", "内部用户权限", "C", "010201", "http://www.baidu.com", "CODE_02", 0));
		array.add(new TreeNode("CODE_05", "外部用户权限", "C", "010201", "http://www.baidu.com", "CODE_02", 1));
		array.add(new TreeNode("CODE_06", "子权限", "C", "010201", "http://www.baidu.com", "CODE_04", 0));
		array.add(new TreeNode("CODE_07", "子系统内部管理", "C", "010201", "http://www.baidu.com", "CODE_03", 0));

		List<Node> list = TreeUtils.parse(array);
		String val = JSON.toJSONString(list);
		System.out.println(val);

		// 取出当下节点下所有数据
		// Node node = new Node();
		TreeNode node = new TreeNode("CODE_04", "内部用户权限", "C", "010201", "http://www.baidu.com", "CODE_02", 0);
		TreeUtils.getNodeJson(node, array);
		System.out.println(JSON.toJSONString(node));
	}
}
