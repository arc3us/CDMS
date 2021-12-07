// Java implementation of the approach
import java.util.*;

class courier
{
	// Function to find the minimum weight
	// Hamiltonian Cycle
	// static int tsp(int[][] graph, boolean[] v,
	// 			int currPos, int n,
	// 			int count, int cost, int ans)
	// {

	// 	// If last node is reached and it has a link
	// 	// to the starting node i.e the source then
	// 	// keep the minimum value out of the total cost
	// 	// of traversal and "ans"
	// 	// Finally return to check for more possible values
	// 	if (count == n && graph[currPos][0] > 0)
	// 	{
	// 		ans = Math.min(ans, cost + graph[currPos][0]);
	// 		return ans;
	// 	}

	// 	// BACKTRACKING STEP
	// 	// Loop to traverse the adjacency list
	// 	// of currPos node and increasing the count
	// 	// by 1 and cost by graph[currPos,i] value
	// 	for (int i = 0; i < n; i++)
	// 	{
	// 		if (v[i] == false && graph[currPos][i] > 0)
	// 		{

	// 			// Mark as visited
	// 			v[i] = true;
	// 			ans = tsp(graph, v, i, n, count + 1,
	// 					cost + graph[currPos][i], ans);

	// 			// Mark ith node as unvisited
	// 			v[i] = false;
	// 		}
	// 	}
	// 	return ans;
	// }
	int[][] distance = new int[9][9];
	int[][] parent = new int[9][9];
	final int INFINITY = 9999;
	int num,min = INFINITY;
	List<Integer> finalPath;
	Integer[] mustpass;

	public void preCompute(int[][] graph) {
		// applying dijkstra for every node
		for(int i=0;i<9;i++) {
			dijkstra(graph,i);
		}
	}

	//function to implement dijkstra's algorithm
	void dijkstra(int G[][], int startNode)
	{
		for(int node=0;node<9;node++){
			int cost[][] = new int [9][9];
			int count, minDistance, nextNode=0, i, j;
			boolean visited[] = new boolean[9];
			for (i = 0; i < 9; i++)
				for (j = 0; j < 9; j++)
					if (G[i][j] == 0)
						cost[i][j] = INFINITY;
					else
						cost[i][j] = G[i][j];

			for (i = 0; i < 9; i++)
			{
				distance[node][i] = cost[startNode][i];
				parent[node][i] = startNode;
				visited[i] = false;
			}
			distance[node][startNode] = 0;
			visited[startNode] = true;
			count = 1;
			while (count < 9 - 1)
			{
				minDistance = INFINITY;
				for (i = 0; i < 9; i++)
					if (distance[node][i] < minDistance && !visited[i])
					{
						minDistance = distance[node][i];
						nextNode = i;
					}
				visited[nextNode] = true;
				for (i = 0; i < 9; i++)
					if (!visited[i])
						if (minDistance + cost[nextNode][i] < distance[node][i])
						{
							distance[node][i] = minDistance + cost[nextNode][i];
							parent[node][i] = nextNode;
						}
				count++;
			}

			// for (i = 0; i < 9; i++)
			// 	if (i != startNode)
			// 	{
			// 		printf("\nDistance of %d = %d", i, distance[i]);
			// 		printf("\nPath = %d", i);
			// 		j = i;
			// 		do
			// 		{
			// 			j = parent[j];
			// 			printf(" <-%d", j);
			// 		} while (j != startNode);
			// 	}
		}
	}

	// Function to take inputs
	void takeInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input number of stops: ");
		num = sc.nextInt();
		mustpass = new Integer[num];
		System.out.println("Input all stops: ");
		for(int i=0;i<num;i++) {
			mustpass[i] = sc.nextInt();
		}
		sc.close();
	}

	//Permute
	public void permute(java.util.List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            System.out.println(java.util.Arrays.toString(arr.toArray()));
			calcCost(arr);
        }
    }

	// Calculate Cost of permuted path:
	void calcCost(java.util.List<Integer> arr) {
		int cost = 0;
		int prev = 0;
		for(int i=0; i<arr.size();i++) {
			cost += distance[prev][arr.get(i)];
			prev = i;
		}
		cost += distance[prev][8];
		if(min>cost) {
			min = cost;
			finalPath = arr;
		}
	}

	// Print Output
	public void giveOutput () {
		System.out.println("0 "+Arrays.toString(finalPath.toArray())+" 8");
		System.out.println("Total Distance: "+min);
	}

	// Driver code
	public static void main(String[] args)
	{

		courier obj = new courier();
		// n is the number of nodes i.e. V
		int n = 4;

		int[][] graph = {{0,4,0,0,0,0,0,8,0},
						{4,0,8,0,0,0,0,11,0},
						{0,8,0,7,0,4,0,0,2},
						{0,0,7,0,9,4,0,0,0},
						{0,0,0,9,0,10,0,0,0},
						{0,0,4,14,10,0,2,0,0},
						{0,0,0,0,0,2,0,1,6},
						{8,11,0,0,0,0,1,0,7},
						{0,0,2,0,0,0,6,7,0}};


		// Print City:
		// Work in progress

		// precompute:
		obj.preCompute(graph);

		// take input:
		obj.takeInput();

		//Permute:
		obj.permute(java.util.Arrays.asList(obj.mustpass), 0);

		//Print Output:
		obj.giveOutput();

		// Boolean array to check if a node
		// has been visited or not
		boolean[] v = new boolean[n];

		// Mark 0th node as visited
		v[0] = true;
		int ans = Integer.MAX_VALUE;

		// Find the minimum weight Hamiltonian Cycle
		// ans = tsp(graph, v, 0, n, 1, 0, ans);

		// ans is the minimum weight Hamiltonian Cycle
		System.out.println(ans);
	}
}

// This code is contributed by Rajput-Ji
