import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Assignment 1 : Question 4
 * 
 * @author Arjun Thimmareddy
 * Unity Id : athimma
 * Student Id : 200105939
 * Email: athimma@ncsu.edu
 */


public class SearchRomania {

	public static HashMap<String,String> roadMap = new HashMap<String,String>(); 
	public static final String breadFirstSearch="BFS";
	public static final String depthFirstSearch="DFS";
	public String searchType;
	public String startingPoint;
	public String endingPoint;
	
	public static void main(String[] args) { 
		if(args.length == 0){ System.out.println("Please enter agruments - SearchType StartingPoint EndingPoint"); return;}
		
		if(args[0] !=null && args[0].length() > 0 && (breadFirstSearch.equals(args[0]) || depthFirstSearch.equals(args[0])))
		{
			if(args[1] !=null && args[2] !=null && args[1].length()>0 && args[2].length()>0)
			{
				SearchRomania searchRomania = new SearchRomania(args[0], args[1],args[2]);
				searchRomania.evaluatePaths();
			}else
			{
				System.out.println("Specify both Starting & Ending Point");
			}
		}else
		{
			System.out.println("Search Type not Specified - Pls enter BFS/DFS");
		}
	}

	 public SearchRomania(String searchType,String start, String end)
	{
		this.startingPoint = start;
		this.endingPoint = end;
		this.searchType = searchType;
		loadRoadMap();
	}
	 
	 private void evaluatePaths()
	 {
		 long startTime = System.currentTimeMillis();
		 System.out.println("Starting search Type - "+searchType+" | Source - "+startingPoint + " | EndingPoint - "+endingPoint );
		 startTime = System.currentTimeMillis();
		 if(searchType.equals(breadFirstSearch))
		 {
			 evaluateBreadthFirstSearch();
		 }else
		 {
			 evaluateDepthFirstSearch();
		 }
		 System.out.println("Time Taken for the search in MilliSecs - "+(System.currentTimeMillis()-startTime));
	 }
	 
	 /**
	  * Evaluate BFS paths
	  * 
	  */
	 private void evaluateBreadthFirstSearch()
	 {
		 LinkedList<String> InitialNode = new LinkedList<String>();
		 InitialNode.add(startingPoint);
		 LinkedBlockingQueue<LinkedList<String>> queueOfPaths = new LinkedBlockingQueue<LinkedList<String>>();
		 queueOfPaths.add(InitialNode);
		 LinkedList<String> tmpPath = null;
		 String currentNode = "";
		 List<String> tmpPossibleNxtNodes;
		 while(!queueOfPaths.isEmpty())
		 {
			 tmpPath = queueOfPaths.poll();
			 if(!tmpPath.isEmpty()){
				 currentNode = tmpPath.peekLast();
				 if(currentNode != null && currentNode.equalsIgnoreCase(endingPoint))
				 {
					 printSolutionPath(tmpPath);
					 return;//For the first Solution In Hand
				 }else
				 {
					 tmpPossibleNxtNodes = getNextPossibleNodes(currentNode);
					 Iterator<String> nodeIterator = tmpPossibleNxtNodes.listIterator();
					 String tmpBuff = null;
					 while(nodeIterator.hasNext())
					 {
						 LinkedList<String> tmpVar = new LinkedList<String>();
						 tmpVar.addAll(tmpPath);
						 tmpBuff= nodeIterator.next();
						 if(tmpVar.contains(tmpBuff))
							 continue;
						 else
							 tmpVar.add(tmpBuff);
						 queueOfPaths.offer(tmpVar);
					 }
				 }
			 }
		 }
	 }
	
	 /**
	  * Evaluate DFS paths
	  * 
	  */
	 private void evaluateDepthFirstSearch()
	 {
		 LinkedList<String> InitialNode = new LinkedList<String>();
		 InitialNode.add(startingPoint);
		 Stack<LinkedList<String>> stackOfPaths = new Stack<LinkedList<String>>();
		 stackOfPaths.push(InitialNode);
		 LinkedList<String> tmpPath = null;
		 String currentNode = "";
		 List<String> tmpPossibleNxtNodes;
		 while(!stackOfPaths.empty())
		 {
			 tmpPath = stackOfPaths.pop();
			 if(!tmpPath.isEmpty()){
				 currentNode = tmpPath.peekLast();
				 if(currentNode != null && currentNode.equalsIgnoreCase(endingPoint))
				 {
					 printSolutionPath(tmpPath);
					 return;//For the first Solution In Hand
				 }else
				 {
					 tmpPossibleNxtNodes = getNextPossibleNodes(currentNode);
					 Iterator<String> nodeIterator = tmpPossibleNxtNodes.listIterator();
					 String tmpBuff = null;
					 while(nodeIterator.hasNext())
					 {
						 LinkedList<String> tmpVar = new LinkedList<String>();
						 tmpVar.addAll(tmpPath);
						 tmpBuff= nodeIterator.next();
						 if(tmpVar.contains(tmpBuff))
							 continue;
						 else
							 tmpVar.add(tmpBuff);
						 stackOfPaths.push(tmpVar);
					 }
				 }
			 }
		 }
	 }
	
	 private void printSolutionPath(LinkedList<String> solutionPath)
	 {
		 Iterator<String> iter = solutionPath.iterator();
		 if(iter.hasNext() ==false) System.out.print("No Path exists - city names should be in lowercase"); 
		 System.out.print("[");
		 while(iter.hasNext())
		 {
			 System.out.print(iter.next());
			 if(iter.hasNext())System.out.print("-->");
		 }
		 System.out.print("]");
		 System.out.print("\n");
		 System.out.println("Number of Nodes in the Solution Path - "+solutionPath.size() );
	 }
	 
	 
	 /**
	  * 
	  * Possible expansions for a Node in bidirectional way
	  * Example:  For 'arad'  -->"sibiu,timisoara,zerind are the possible expansion nodes
	  * 
	  */
	private static void loadRoadMap()
	{
		roadMap.put("arad","sibiu|timisoara|zerind");
		roadMap.put("bucharest","fagaras|giurgiu|pitesti|urziceni");
		roadMap.put("craiova","dobreta|pitesti|rimnicu_vilcea");
		roadMap.put("dobreta","craiova|mehadia");
		roadMap.put("eforie","hirsova");
		roadMap.put("fagaras","bucharest|sibiu");
		roadMap.put("giurgiu","bucharest");
		roadMap.put("hirsova","eforie|urziceni");
		roadMap.put("iasi","neamt|vaslui");
		roadMap.put("lugoj","mehadia|timisoara");
		roadMap.put("mehadia","dobreta|lugoj");
		roadMap.put("neamt","iasi");
		roadMap.put("oradea","sibiu|zerind");
		roadMap.put("pitesti","craiova|bucharest|rimnicu_vilcea");
		roadMap.put("rimnicu_vilcea","craiova|pitesti|sibiu");
		roadMap.put("sibiu","fagaras|rimnicu_vilcea|arad|oradea");
		roadMap.put("timisoara","lugoj|arad");
		roadMap.put("urziceni","bucharest|hirsova|vaslui");
		roadMap.put("vaslui","iasi|urziceni");
		roadMap.put("zerind","arad|oradea");
	}
	
	/**
	 * 	 
	 * @param currentNode
	 * @return possible next set of nodes in sorted order
	 */
	private List<String> getNextPossibleNodes(String currentNode)
	{
		if(roadMap.get(currentNode) != null && roadMap.get(currentNode).length()>0)
		{
			String[] nxtStrNodes = (roadMap.get(currentNode)).split("\\|");
			ArrayList<String> nxtNodeList = new ArrayList<String>(Arrays.asList(nxtStrNodes));
			Collections.sort(nxtNodeList);
			if(searchType.equalsIgnoreCase(depthFirstSearch))Collections.reverse(nxtNodeList);
			return nxtNodeList;
		}
		return Collections.emptyList();
	}
	
}
