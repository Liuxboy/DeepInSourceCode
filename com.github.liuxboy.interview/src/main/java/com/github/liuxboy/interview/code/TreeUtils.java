package com.github.liuxboy.interview.code;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/9 <br>
 * Time: 10:29 <br>
 * Desc:
 */
public class TreeUtils {
    public static void main(String[] args) {
        TreeUtils treeUtils = new TreeUtils();
        System.out.println(treeUtils.treeTest("B"));
    }

    /**
     * @param str
     * @return
     * @link /resources/rest/TreeTest.png
     */
    private String treeTest(String str) {
        String srcString = "ABCDEFGHI";
        String subString = "";

        char[] chars = srcString.toCharArray();
        Node[] nodes = new Node[chars.length];
        for (int i = 0; i < chars.length; i++) {
            nodes[i] = new Node(String.valueOf(chars[i]));
        }
        nodes[0].parentId = "0";
        nodes[1].parentId = nodes[0].id;
        nodes[1].level = 1;
        nodes[2].parentId = nodes[0].id;
        nodes[2].level = 1;
        nodes[3].parentId = nodes[0].id;
        nodes[3].level = 1;
        nodes[4].parentId = nodes[1].id;
        nodes[4].level = 2;
        nodes[5].parentId = nodes[2].id;
        nodes[5].level = 2;
        nodes[6].parentId = nodes[2].id;
        nodes[6].level = 2;
        nodes[7].parentId = nodes[2].id;
        nodes[7].level = 2;
        nodes[8].parentId = nodes[3].id;
        nodes[8].level = 2;
        Map<String, List<Node>> listMap = new HashMap<>();
        List<Node> tempChildList;
        for (Node node : nodes) {
            tempChildList = listMap.get(node.parentId);
            if (CollectionUtils.isEmpty(tempChildList)) {
                tempChildList = new ArrayList<>();
                tempChildList.add(node);
            } else {
                tempChildList.add(node);
            }
            listMap.put(node.parentId, tempChildList);
        }
        List<Node> childNodeList = new ArrayList<>();
        findChildren(new Node(str), listMap, childNodeList);

        if (CollectionUtils.isEmpty(childNodeList))
            return srcString.replace(str, "");
        else {
            for (Node node : childNodeList) {
                subString = srcString.replace(node.id, "");
                srcString = subString;
            }
        }
        return srcString.replace(str, "");
    }

    private void findChildren(Node currentNode, Map<String, List<Node>> listMap, List<Node> childNodeList) {
        List<Node> currentNodeChildrenList = listMap.get(currentNode.id);
        if (CollectionUtils.isNotEmpty(currentNodeChildrenList)) {
            childNodeList.addAll(currentNodeChildrenList);
            for (Node node : currentNodeChildrenList) {
                findChildren(node, listMap, childNodeList);
            }
        }
    }
}

class Node {
    String id;
    String parentId;
    int level;

    public Node(String id) {
        this.id = id;
    }
}
