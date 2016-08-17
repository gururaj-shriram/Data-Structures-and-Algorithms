import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinarySearchTree
{
    public static final int MAX_NUMBER = 10000;
    public Node root;
    
    public BinarySearchTree()
    {
    	root = null;
    }
    
    public static class Node
    {
    	int key;
		Node right;
		Node left;
		Node parent;
	
		public Node(int key)
		{
		    this.key = key;
		    left = null;
		    right = null;
		}
	
		public Node() {}
    }

    public static int[] initialize(int[] arr)
    {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++)
		    arr[i] = rand.nextInt(MAX_NUMBER);
		return arr;
    }
    
    public static void inOrderTreeWalk(Node x)
    {
		if (x != null)
		{
			inOrderTreeWalk(x.left);
			System.out.print(x.key + " ");
			inOrderTreeWalk(x.right);
		}
    }	

    public static Node treeSearch(Node x, int k)
    {
		if (x == null || k == x.key)
		    return x;
		if (k < x.key)
		    return treeSearch(x.left, k);
		else
		    return treeSearch(x.right, k);
    }
    
    public static int heightOfTree(Node x)
    {
    	if (x == null)
    		return 0;
    	
    	return 1 + Math.max(heightOfTree(x.left), heightOfTree(x.right));
    }

    public static void treeInsert(BinarySearchTree T, Node z)
    {
		Node y = null;
		Node x = T.root;
		while (x != null)
		{
			y = x;
			if (z.key < x.key)
			    x = x.left;
			else
			    x = x.right;
		}
	
		z.parent = y;
		if (y == null)
		    T.root = z;
		else if (z.key < y.key)
		    y.left = z;
		else
		    y.right = z;
    }
    
    public static void insertNumbersIntoTree(BinarySearchTree T, int[] arr)
    {
    	for (int i = 0; i < arr.length; i++)
    	{
    		int key = arr[i];
    		Node num = new Node(key);
    		treeInsert(T, num);
    	}
    }
