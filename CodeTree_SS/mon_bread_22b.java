package CodeTree_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mon_bread_22b {
	static class Person {
		int x, y;
		public Person (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static int N, M;
	public static int[][] map;
	public static Person[] person;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		person = new Person[M+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			
		}
	}
}
