/**
 * 
 */
package student;

import java.util.Comparator;

/**
 * 
 */
public class NodeComparatorByHnAndGn implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		int result;
		double h1 = o1.getG()+o1.getH();
		double h2 = o2.getG()+o2.getH();
		result= Double.compare(h1, h2);
		if(result==0) {
			result=o1.getLabel().compareTo(o2.getLabel());
		}
		return result;
	}

}
