package student;

import java.util.Comparator;

public class NodeComparatorByHn implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		Double h1 = o1.getH();
		Double h2 = o2.getH();
		int result = h1.compareTo(h2);
		if(result==0) {
			result = o1.getLabel().compareTo(o2.getLabel());
		}
		return result;
	}

}
