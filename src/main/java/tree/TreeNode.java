package tree;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 实体映射叶子节点
 *
 * @author gewx
 **/
@Getter
@Setter
public final class TreeNode extends Node implements Serializable {

    private static final long serialVersionUID = 3564872935020099986L;

    public TreeNode() {

    }

    public TreeNode(String id, String menuName, String menuType, String menuCode, String url, String parentId,
                    Integer sortNum, String visible, String systemName) {
        this.id = id;
        this.parentId = parentId;
        this.sortNum = sortNum;
        this.menuName = menuName;
        this.menuType = menuType;
        this.menuCode = menuCode;
        this.url = url;
        this.visible = visible;
        this.systemName = systemName;
    }

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
     * 是否显示
     **/
    private String visible;

    /**
     * 菜单所属系统名称
     **/
    private String systemName;

    /**
     * 打开方式
     * **/
    private String openType;
    
    @Override
    public String toString() {
        return "TreeNode{" +
                "menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", url='" + url + '\'' +
                ", visible='" + visible + '\'' +
                ", systemName='" + systemName + '\'' +
                ", id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", sortNum=" + sortNum +
                ", children=" + children +
                '}';
    }
}
