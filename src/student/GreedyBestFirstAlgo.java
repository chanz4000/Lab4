/**
 * 
 */
package student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 */
public class GreedyBestFirstAlgo implements IInformedSearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node>frontie= new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node>explored = new ArrayList<Node>();
		frontie.add(root);
		while (!frontie.isEmpty()) {
			Node current = frontie.poll();
			if(current.getLabel().equals(goal))return current;
			explored.add(current);
			List<Edge>children = current.getChildren();
			for (int i = 0; i < children.size(); i++) {
				Node child=children.get(i).getEnd();
				child.setParent(current);
				if(!frontie.contains(child)&& !explored.contains(child)) {
					frontie.add(child);
				}else if(frontie.contains(child)){
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
				PriorityQueue<Node>frontie= new PriorityQueue<Node>(new NodeComparatorByHn());
				List<Node>explored = new ArrayList<Node>();
				frontie.add(root);
				boolean started=false;
				while (!frontie.isEmpty()) {
					Node current = frontie.poll();
                    
					if(current.getLabel().equals(start)) {
						started=true;
						frontie.clear();
						explored.clear();
						current.setParent(null);
					}
					
					if(current.getLabel().equals(goal)&&started)return current;
					explored.add(current);
					
					List<Edge>children = current.getChildren();
					for (int i = 0; i < children.size(); i++) {
						Node child=children.get(i).getEnd();
						child.setParent(current);
						if(!frontie.contains(child)&& !explored.contains(child)) {
							frontie.add(child);
						}else if(frontie.contains(child)){
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

}
