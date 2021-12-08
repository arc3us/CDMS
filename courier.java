// Java implementation of the approach
import java.util.*;

class courier
{
	int[][] distance = new int[9][9];
	int[][] parent = new int[9][9];
	final int INFINITY = 9999;
	int num,min = INFINITY;
	int[] finalPath;
	Integer[] mustpass;

	public void preCompute(int[][] graph) {
		// applying dijkstra for every node
		for(int i=0;i<9;i++) {
			dijkstra(graph,i);
		}
		// for(int i=0;i<9;i++) {
		// 	for(int j=0;j<9;j++) {
		// 		System.out.print(distance[i][j]+ " ");
		// 	}
		// 	System.out.println();
		// }
	}

	//function to implement dijkstra's algorithm
	void dijkstra(int G[][], int startNode)
	{
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
				distance[startNode][i] = cost[startNode][i];
				parent[startNode][i] = startNode;
				visited[i] = false;
			}
			distance[startNode][startNode] = 0;
			visited[startNode] = true;
			count = 1;
			while (count < 9 - 1)
			{
				minDistance = INFINITY;
				for (i = 0; i < 9; i++)
					if (distance[startNode][i] < minDistance && !visited[i])
					{
						minDistance = distance[startNode][i];
						nextNode = i;
					}
				visited[nextNode] = true;
				for (i = 0; i < 9; i++)
					if (!visited[i])
						if (minDistance + cost[nextNode][i] < distance[startNode][i])
						{
							distance[startNode][i] = minDistance + cost[nextNode][i];
							parent[startNode][i] = nextNode;
						}
				count++;
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
            // System.out.println(java.util.Arrays.toString(arr.toArray()));
			calcCost(arr);
        }
    }

	// Calculate Cost of permuted path:
	void calcCost(java.util.List<Integer> arr) {
		int cost = 0;
		int prev = 0;
		int[] temp = new int[arr.size()+2];
		for(int i=0; i<arr.size();i++) {
			temp[i]=prev;
			cost += distance[prev][arr.get(i)];
			prev = arr.get(i);
		}
		temp[arr.size()]=prev;
		temp[arr.size()+1]=8;
		cost += distance[prev][8];
		// System.out.println("cost: : "+cost+ ": Perm : : "+arr);
		if(min>cost) {
			min = cost;
			finalPath = temp;
		}
	}

	// Print Output
	public void giveOutput () {
		for(int i=0;i<finalPath.length;i++) {
			System.out.print(finalPath[i]+" ");
		}
		System.out.println("\nTotal Distance: "+min);
	}

	// Driver code
	public static void main(String[] args)
	{

		courier obj = new courier();

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
	}
}
