import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Assignment 2 : Question 1
 * 
 * @author Arjun Thimmareddy
 * Unity Id : athimma
 * Student Id : 200105939
 * Email: athimma@ncsu.edu
 */
public class SearchUSA {
	
	public String searchType;
	public String startingPoint;
	public String endingPoint;
	public static final String ASTAR="astar";
	public static final String GREEDYFIRST="greedy";
	public static final String UNIFORMCOST="uniform";
	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) { 
		if(args.length == 0){ System.out.println("Please enter agruments - SearchType StartingPoint EndingPoint"); return;}
		
		if(args[0] !=null && args[0].length() > 0 && (ASTAR.equals(args[0]) || GREEDYFIRST.equals(args[0]) || UNIFORMCOST.equals(args[0])) )
		{
			if(args[1] !=null && args[2] !=null && args[1].length()>0 && args[2].length()>0)
			{
				SearchUSA searchUSA = new SearchUSA(args[0], args[1],args[2]);
				searchUSA.evaluatePaths();
			}else
			{
				System.out.println("Specify both Starting & Ending Point");
			}
		}else
		{
			System.out.println("Search Type not Specified - Pls enter astar/greedy/uniform");
		}
	}
	
	public SearchUSA(String searchType,String start, String end)
	{
		this.startingPoint = start;
		this.endingPoint = end;
		this.searchType = searchType;
	}

	private void evaluatePaths()
	 {
		 long startTime = System.currentTimeMillis();
		 System.out.println("Starting search Type - "+searchType+" | Source - "+startingPoint + " | EndingPoint - "+endingPoint );
		 startTime = System.currentTimeMillis();
		 if(searchType.equals(ASTAR))
		 {
			 evaluateAStarSearch();
		 }else if(searchType.equals(GREEDYFIRST))
		 {
			 evaluateGreedyFirstSearch();
		 }else  if(searchType.equals(UNIFORMCOST))
		 {
			 evaluateUniformCostSearch();
		 }
		 System.out.println("Time Taken for the search in MilliSecs - "+(System.currentTimeMillis()-startTime));
	 }
	
	/**
	 * evaluate Uniform Cost
	 */
	private void evaluateUniformCostSearch()
	{
		UniformCostComparator uniformComp = new UniformCostComparator();
		PriorityQueue<Path> queue = new PriorityQueue<Path>(10,uniformComp);
		Path initialPath = new Path();
		initialPath.setPathCost(0);
		initialPath.getTraversalPath().add(startingPoint);
		queue.add(initialPath);
		System.out.print("\nPaths picked for Exapnsion in below order\n");
		int nodesExpanded = 0;
		while(!queue.isEmpty())
		{
			Path tempPath = queue.poll(); 
			String currentNode = tempPath.traversalPath.peekLast();
			System.out.print("\n Path - [");
			printExpandedPath(tempPath.traversalPath);
			System.out.print("     Cost is "+tempPath.pathCost);
			 if(currentNode != null && currentNode.equalsIgnoreCase(endingPoint))
			 {
				 System.out.print("\n\nThe number of nodes expanded = "+nodesExpanded+"\n");
				 printSolutionPath(tempPath.traversalPath);
				 System.out.println("The total distance from " +startingPoint+ " to "+ endingPoint +" in the solution path "+tempPath.pathCost);
				 return;//For the first Solution In Hand
			 }
			nodesExpanded++;
			Set<String> succNodes = RouteHelper.getInstance().getPossibleSucessors(currentNode);
			for(String succNode : succNodes)
			{
				Path newPath = new Path();
				newPath.traversalPath.addAll(tempPath.traversalPath);
				if(newPath.traversalPath.contains(succNode)) continue; //if the node already exists on the path
				newPath.traversalPath.add(succNode);
				newPath.pathCost = tempPath.pathCost + RouteHelper.getInstance().getPathCost(currentNode, succNode);
				queue.add(newPath);
				deleteDuplicatePathsWithHighUniformCost(queue,newPath);
			}
		}
	}
	
	/**
	 * evaluate Greedy First Search
	 */
	private void evaluateGreedyFirstSearch()
	{
		GreedyFirstCostComparator gredyFirstComparator = new GreedyFirstCostComparator();
		PriorityQueue<Path> queue = new PriorityQueue<Path>(10,gredyFirstComparator);
		Path initialPath = new Path();
		initialPath.setPathCost(0);
		initialPath.setHeurisiticCost(RouteHelper.getInstance().getHeuristicEstimate(startingPoint, endingPoint));
		initialPath.getTraversalPath().add(startingPoint);
		queue.add(initialPath);
		System.out.print("\nPaths picked for Exapnsion in below order\n");
		int nodesExpanded = 0;
		while(!queue.isEmpty())
		{
			Path tempPath = queue.poll(); 
			String currentNode = tempPath.traversalPath.peekLast();
			System.out.print("\nExpanded Path - [");
			printExpandedPath(tempPath.traversalPath);
			System.out.print("   Heuristic Cost is "+tempPath.heurisiticCost);
			 if(currentNode != null && currentNode.equalsIgnoreCase(endingPoint))
			 {
				 System.out.print("\n\nThe number of nodes expanded = "+nodesExpanded+"\n");
				 printSolutionPath(tempPath.traversalPath);
				 System.out.println("The total distance from " +startingPoint+ " to "+ endingPoint +" in the solution path "+tempPath.pathCost);
				 return;//For the first Solution In Hand
			 }
			 nodesExpanded++;
			 Set<String> succNodes = RouteHelper.getInstance().getPossibleSucessors(currentNode);
			for(String succNode : succNodes)
			{
				Path newPath = new Path();
				newPath.traversalPath.addAll(tempPath.traversalPath);
				if(newPath.traversalPath.contains(succNode)) continue; //if the node already exists on the path
				newPath.traversalPath.add(succNode);
				newPath.pathCost = tempPath.pathCost + RouteHelper.getInstance().getPathCost(currentNode, succNode);
				newPath.heurisiticCost = RouteHelper.getInstance().getHeuristicEstimate(succNode, endingPoint);
				queue.add(newPath);
				deleteDuplicatePathsWithHighUniformCost(queue,newPath);
			}
		}
	}
	
	/**
	 * evaluate A Star Search
	 */
	private void evaluateAStarSearch()
	{
		AstarCostComparator atsarComp = new AstarCostComparator();
		PriorityQueue<Path> queue = new PriorityQueue<Path>(10,atsarComp);
		Path initialPath = new Path();
		initialPath.setPathCost(0);
		initialPath.setHeurisiticCost(RouteHelper.getInstance().getHeuristicEstimate(startingPoint, endingPoint));
		initialPath.getTraversalPath().add(startingPoint);
		queue.add(initialPath);
		System.out.print("\nPaths picked for Exapnsion in below order\n");
		int nodesExpanded = 0;
		while(!queue.isEmpty())
		{
			Path tempPath = queue.poll(); 
			String currentNode = tempPath.traversalPath.peekLast();
			System.out.print("\n Path - [");
			printExpandedPath(tempPath.traversalPath);
			System.out.print(" PathCost="+tempPath.pathCost+" HeuristicEstimate="+tempPath.heurisiticCost+"  Cost is="+tempPath.getCost());
			 if(currentNode != null && currentNode.equalsIgnoreCase(endingPoint))
			 {
				 System.out.print("\n\nThe number of nodes expanded = "+nodesExpanded+"\n");
				 printSolutionPath(tempPath.traversalPath);
				 System.out.println("The total distance from " +startingPoint+ " to "+ endingPoint +" in the solution path "+tempPath.getCost());
				 return;//For the first Solution In Hand
			 }
			nodesExpanded++;
			Set<String> succNodes = RouteHelper.getInstance().getPossibleSucessors(currentNode);
			for(String succNode : succNodes)
			{
				Path newPath = new Path();
				newPath.traversalPath.addAll(tempPath.traversalPath);
				if(newPath.traversalPath.contains(succNode)) continue; //if the node already exists on the path
				newPath.traversalPath.add(succNode);
				newPath.pathCost = tempPath.pathCost + RouteHelper.getInstance().getPathCost(currentNode, succNode);
				newPath.heurisiticCost = RouteHelper.getInstance().getHeuristicEstimate(succNode, endingPoint);
				queue.add(newPath);
				deleteDuplicatePathsWithHighUniformCost(queue,newPath);
			}
		}
	}
	
	
	 private void printSolutionPath(LinkedList<String> solutionPath)
	 {
		 Iterator<String> iter = solutionPath.iterator();
		 if(iter.hasNext() ==false) System.out.print("No Path exists - city names should be in lowercase"); 
		 System.out.print("\n*************************SOLUTION PATH BELOW*************************\n");
		 System.out.print("\nSolution Path - [");
		 while(iter.hasNext())
		 {
			 System.out.print(iter.next());
			 if(iter.hasNext())System.out.print(",");
		 }
		 System.out.print("]");
		 System.out.print("\n");
		 System.out.println("Number of Nodes in the Solution Path - "+solutionPath.size() );
	 }
	 
	 private void printExpandedPath(LinkedList<String> expandedPath)
	 {
		 Iterator<String> iter = expandedPath.iterator();
		 while(iter.hasNext())
		 {
			 System.out.print(iter.next());
			 if(iter.hasNext())System.out.print(",");
		 }
		 System.out.print("]");
		 //System.out.print("\n");
	 }
		/**
		 * 
		 * Delete duplicate paths with high Cost
		 * 
		 */
		public void deleteDuplicatePathsWithHighUniformCost(PriorityQueue<Path> originalQueue, Path newPathAdded)
		{
			double minCost = newPathAdded.pathCost;
			String lastNode = newPathAdded.traversalPath.peekLast();
			Iterator<Path> pathIterator = originalQueue.iterator();
			Path tempPath = null;
			while(pathIterator.hasNext())
			{
				tempPath = pathIterator.next();
				if(tempPath.traversalPath.peekLast().equalsIgnoreCase(lastNode))
				{
					if(minCost < tempPath.pathCost)
					{
						pathIterator.remove();
					}else
					{
						minCost = tempPath.pathCost;
					}
				}
			}
		}	
	
}
