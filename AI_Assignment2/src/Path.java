import java.util.LinkedList;

/**
 * Assignment 2 : Question 1
 * 
 * @author Arjun Thimmareddy
 * Unity Id : athimma
 * Student Id : 200105939
 * Email: athimma@ncsu.edu
 */

public class Path {
		
	double pathCost;
	double heurisiticCost;
	LinkedList<String> traversalPath = new LinkedList<String>();
	
	public LinkedList<String> getTraversalPath() {
		return traversalPath;
	}
	public void setTraversalPath(LinkedList<String> traversalPath) {
		this.traversalPath = traversalPath;
	}
	public double getCost() {
		return pathCost+heurisiticCost;
	}
	public double getPathCost() {
		return pathCost;
	}
	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}
	public double getHeurisiticCost() {
		return heurisiticCost;
	}
	public void setHeurisiticCost(double heurisiticCost) {
		this.heurisiticCost = heurisiticCost;
	}

}
