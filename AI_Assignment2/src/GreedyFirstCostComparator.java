import java.util.Comparator;

/**
 * Assignment 2 : Question 1
 * 
 * @author Arjun Thimmareddy
 * Unity Id : athimma
 * Student Id : 200105939
 * Email: athimma@ncsu.edu
 * 
 * To Compare Uniform Cost to sort in Priority Queue
 */
public class GreedyFirstCostComparator implements Comparator<Path>{

	@Override
	public int compare(Path o1, Path o2) {
		if(o1 ==null || o2 ==null)
			return 0;
		if(o1.heurisiticCost < o2.heurisiticCost)
			return -1;
		else if(o1.heurisiticCost > o2.heurisiticCost)
			return 1;
		else if(o1.heurisiticCost == o2.heurisiticCost)
			return 0;
		else
			return 0;
	}

}
