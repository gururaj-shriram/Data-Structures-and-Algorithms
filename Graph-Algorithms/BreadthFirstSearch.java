import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearch 
{	
	public enum Color
	{
		WHITE, GRAY, BLACK
	}
	
	public static class Node
	{
		int key;
		Color color;
		int distance;
		Node parent;
		
		public Node() {}
		
		public Node(int key) //automatically initializes nodes
		{
			this.key = key;
			color = Color.WHITE;
			distance = Integer.MAX_VALUE;
			parent = null;
		}
		
		public String toString() 
		{
			if (this.parent == null)
				return ("Key: " + key + ", Color: " + color + ", Distance: " + distance + ", Parent: " + parent);

			return ("Key: " + key + ", Color: " + color + ", Distance: " + distance + ", Parent: " + parent.key);
		}
	}
	
	//List of each row of linked lists
	public static class AdjacencyList 
	{
		LinkedList<Node> list;
		Node head;
	}
	
	public static class Graph
	{
		ArrayList<AdjacencyList> adjList;
		Node[] nodes;
	}
	
	public static void readInLists(Graph graph, String fileName) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine())
		{
			AdjacencyList newList = new AdjacencyList();
			newList.list = new LinkedList<Node>();
			String row = scanner.nextLine();
			Scanner rowScanner = new Scanner(row);
			boolean isHead = true;
			
			while (rowScanner.hasNext())
			{				
				int key = rowScanner.nextInt();
				if (graph.nodes[key] == null)
				{
					Node newNode = new Node(key);
					graph.nodes[key] = newNode;
				}
				
				if (isHead)
				{
					newList.head = graph.nodes[key];
					isHead = false;
				}
				
				newList.list.add(graph.nodes[key]);
			}
			
			graph.adjList.add(newList);
			rowScanner.close();
		}
		
		scanner.close();
	}
	
	//returns adjacency list from array list of adjacency lists
	public static AdjacencyList getAdjList(Graph graph, Node node)
	{
		for (int i = 0; i < graph.adjList.size(); i++)
		{
			Node headOfList = graph.adjList.get(i).head;
			if (headOfList.key == node.key)
				return graph.adjList.get(i);
		}
		
		return null;
	}

	public static int getIndexOfAdjList(Graph graph, Node node)
	{
		for (int i = 0; i < graph.adjList.size(); i++)
		{
			Node headOfList = graph.adjList.get(i).head;
			if (headOfList.key == node.key)
				return i;
		}
		
		return -1; //not there
	}
	
	public static void scaleFreeBFS(Graph graph, Node start)
	{
		Queue<Node> q = new LinkedList<Node>();

		//all nodes are automatically initialized in the constructor
		start.color = Color.GRAY;
		start.distance = 0;
		start.parent = null;
		
		q.add(start);
		
		while (!q.isEmpty())
		{
			Node u = q.poll();
			AdjacencyList adjToNode = getAdjList(graph, u);
			
			if (adjToNode != null)
			{
				for (int i = 0; i < adjToNode.list.size(); i++)
				{
					Node v = adjToNode.list.get(i);
					if (v.color == Color.WHITE)
					{
						v.color = Color.GRAY;
						v.distance = u.distance + 1;
						v.parent = u;
						q.add(v);
					}
				}
			}
			
			u.color = Color.BLACK;
		}
	}
	
	//Necessary because not all nodes have a node in the adjacency list
	public static void randomBFS(Graph graph, Node start)
	{
		Queue<Node> q = new LinkedList<Node>();
		int indexOfNextList = -1;
		boolean firstNode = true;
		
		//all nodes are automatically initialized in the constructor
		if (start.color != Color.BLACK) //if the node has already been discovered through recursion
		{
			start.color = Color.GRAY;
			start.distance = 0;
			start.parent = null;
		}
		
		q.add(start);
		
		while (!q.isEmpty())
		{
			Node u = q.poll();
			AdjacencyList adjToNode = getAdjList(graph, u);
			
			if (adjToNode != null)
			{
				for (int i = 0; i < adjToNode.list.size(); i++)
				{
					if (firstNode)
					{
						indexOfNextList = getIndexOfAdjList(graph, u);
						firstNode = false;
					}
					
					Node v = adjToNode.list.get(i);
					if (v.color == Color.WHITE)
					{
						v.color = Color.GRAY;
						v.distance = u.distance + 1;
						v.parent = u;
						q.add(v);
					}
				}
			}
			
			u.color = Color.BLACK;
		}
		
		//Because the next adjacency list may not have a node in the queue,
		//we do BFS on the next adjacency list
		if (indexOfNextList >= 0 && (indexOfNextList + 1) < graph.adjList.size())
		{
			indexOfNextList++;
			Node nextNode = graph.adjList.get(indexOfNextList).head;
			randomBFS(graph, nextNode);
		}
	}
	
	//Used for testing
	public static void printGraph(Graph graph)
	{
		for (int i = 0; i < graph.nodes.length; i++)
			System.out.println(graph.nodes[i]);
		
		System.out.println();
	}
	
	public static double calculateMeanPath(Graph graph)
	{
		int sum = 0;
		double mean;
		
		for (int i = 0; i < graph.nodes.length; i++)
		{
			if (graph.nodes[i] != null)
				sum += graph.nodes[i].distance;
		}
		
		mean = (double) sum / graph.nodes.length;
		return mean;
	}

	public static void main(String[] args) throws FileNotFoundException 
	{
		Graph graph = new Graph();
		Scanner scanner = new Scanner(System.in);
		double mean = 0;
		
		System.out.println("Enter the file name of the adjacency list: ");
		String fileName = scanner.nextLine(); //in the form of (scaleFree,randomGraph)_N.net
		int numberOfNodes = Integer.parseInt(fileName.replaceAll("[\\D]", "")); //extract number of nodes
		
		graph.nodes = new Node[numberOfNodes];
		graph.adjList = new ArrayList<AdjacencyList>();
		
		readInLists(graph, fileName);
		
		if (fileName.contains("scale"))
			scaleFreeBFS(graph, graph.nodes[0]);
		else if (fileName.contains("random"))
			randomBFS(graph, graph.nodes[0]);
		
		mean = calculateMeanPath(graph);
		
		if (numberOfNodes < 50)
			printGraph(graph);	//used to test
		
		System.out.println("The mean path for the graph with " + numberOfNodes + " nodes is " + mean);
		scanner.close();
	}
}