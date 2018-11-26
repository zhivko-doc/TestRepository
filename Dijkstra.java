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

		public Node(int ID)
		{
			route = "";
			nodeID = ID;
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
		nodes = new Node[]
		{ new Node(0), new Node(1), new Node(2), new Node(3), new Node(4) };
		connections = new int[][]
		{
				{ 0, 3, 5, 17, 0 },
				{ 3, 0, 0, 2, 0 },
				{ 5, 0, 0, 0, 4 },
				{ 17, 2, 0, 0, 5 },
				{ 0, 0, 4, 5, 0 } };
	}

	public void pathfind(int begin, int end)
	{
		nodes[begin].curDistance = 0;
		nodes[begin].route += nodes[begin].nodeID;
		nextNode.add(nodes[begin]);
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
		algorithm.pathfind(2, 3);
	}
}
