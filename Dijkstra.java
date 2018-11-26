import java.util.PriorityQueue;

public class Dijkstra
{
	int[][] connections;
	Node[] nodes;

	PriorityQueue<Node> nextNode = new PriorityQueue<>();

	static class Node implements Comparable<Node>
	{
		int nodeID;
		int curDistance;
		int finalDistance;
		String route;

		public Node()
		{
			route = "";
			curDistance = Integer.MAX_VALUE - 1;
			finalDistance = Integer.MAX_VALUE;
		}

		@Override
		public int compareTo(Node o)
		{
			return Integer.compare(this.curDistance, o.curDistance);
		}
	}

	public void setTest()
	{
		nodes = new Node[10];
		for (int i = 0; i < nodes.length; i++)
		{
			nodes[i] = new Node();
			nodes[i].nodeID = i;
		}
		//if>0 the number is the length of the connection if = 0 there is no connection
		connections = new int[][]
		{
				{ 0, 1, 0, 0, 8, 5, 11, 0, 0, 0 },
				{ 1, 0, 3, 5, 0, 0, 0, 0, 0, 0 },
				{ 0, 3, 0, 7, 0, 0, 0, 0, 0, 0 },
				{ 0, 5, 7, 0, 0, 0, 0, 0, 0, 0 },
				{ 8, 0, 0, 0, 0, 0, 0, 4, 0, 0 },
				{ 5, 0, 0, 0, 0, 0, 3, 3, 0, 0 },
				{ 11, 0, 0, 0, 0, 3, 0, 0, 2, 0 },
				{ 0, 0, 0, 0, 4, 3, 0, 0, 0, 15 },
				{ 0, 0, 0, 0, 0, 0, 2, 0, 0, 4 },
				{ 0, 0, 0, 0, 0, 0, 0, 15, 4, 0 } };
	}

	public void pathfind(int start, int end)
	{
		nodes[start].curDistance = 0;
		nodes[start].route += nodes[start].nodeID;
		nextNode.add(nodes[start]);
		while (true)
		{
			int curNode = nextNode.peek().nodeID;
			if (curNode == end)
			{
				System.out.println("Distance is: " + nodes[end].curDistance);
				System.out.println("And the route is: " + nodes[end].route);
				break;
			}

			for (int i = 0; i < nodes.length; i++)
			{
				if (connections[curNode][i] > 0 && nodes[i].curDistance != nodes[i].finalDistance)
				{
					if (nodes[i].curDistance == Integer.MAX_VALUE - 1)
					{
						nodes[i].curDistance = nodes[curNode].curDistance + connections[curNode][i];
						nodes[i].route = nodes[curNode].route + "-" + nodes[i].nodeID;
						nextNode.add(nodes[i]);
					}
					else
					{
						if (nodes[curNode].curDistance + connections[curNode][i] < nodes[i].curDistance)
						{
							nodes[i].curDistance = nodes[curNode].curDistance + connections[curNode][i];
							nodes[i].route = nodes[curNode].route + "-" + nodes[i].nodeID;
						}
					}
				}
			}
			nodes[curNode].finalDistance = nodes[curNode].curDistance;
			nextNode.poll();
		}

	}

	public static void main(String[] args)
	{
		Dijkstra algorithm = new Dijkstra();
		algorithm.setTest();
		int n = 0;//starting point
		int m = 9;//end point
		algorithm.pathfind(n, m);
	}
}
