/**
 * 
 */
package student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 
 */
public class AStarSearchAlgo implements IInformedSearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node>frontie = new PriorityQueue<Node>(new NodeComparatorByHnAndGn());
		List<Node>explored =new ArrayList<Node>();
		frontie.add(root);
		while (!frontie.isEmpty()) {
			Node current = frontie.poll();
			if(current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Edge>childrent = current.getChildren();
			for (int i = 0; i < childrent.size(); i++) {
				Node child = childrent.get(i).getEnd();
				child.setParent(current);
				if(!frontie.contains(child)&& !explored.contains(child)) {
					frontie.add(child);
					
				}else if(frontie.contains(child)) {
					for (Node node : frontie) {
						if(node.getLabel().equals(child.getLabel())) {
							frontie.remove(node);
							frontie.add(child);
							break;
						}
					}
				}
			}
			
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node>frontie = new PriorityQueue<Node>(new NodeComparatorByHnAndGn());
		List<Node>explored=new ArrayList<Node>();
		boolean started = false;
		frontie.add(root);
		while(!frontie.isEmpty()) {
			Node current = frontie.poll();
			if(current.getLabel().equals(start)) {
				started = true;
				frontie.clear();
				explored.clear();
				current.setParent(null);
			}
			if(current.getLabel().equals(goal)&&started) {
				return current;
			}
			
			explored.add(current);
			List<Edge>children=current.getChildren();
			for (int i = 0; i < children.size(); i++) {
				Node child = children.get(i).getEnd();
				child.setParent(current);
				if(!frontie.contains(child)&&!explored.contains(child)) {
					frontie.add(child);
				}else if(frontie.contains(child)) {
					for (Node node : frontie) {
						if(child.getLabel().equals(node.getLabel())) {
							frontie.remove(node);
							frontie.add(child);
							break;
						}
					}
				}
			}
		}
		return null;
	}
	
	public boolean isAdmissibleH(Node root, String goal) {
		Queue<Node>frontier = new LinkedList<Node>();
		Set<Node>explored = new HashSet<Node>();
		boolean result = true;
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			Node node = execute(current, goal);
			if(node==null) {
				return false;
			}
			if(current.getH()>node.getG()) {
				return false;
			}
			for(Node child: current.getChildrenNodes()) {
				if(!explored.contains(child)&& !frontier.contains(child)) {
					child.setParent(null);
					child.setG(0);
					result = result&& isAdmissibleH(child, goal);
					if(result==false)return result;
				}
			}
		}
		return false;
	}
	

}
