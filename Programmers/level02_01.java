package Programmers;

public class level02_01 {
	private static int ans = 0;
	public static void main(String[] args) {
		int n = 15;
		getNum(1,0,n);
		System.out.println(ans);
	}
	
	public static void getNum(int idx, int sum, int n){
        for(int i=idx; i<=n-sum; i++){
            if(sum+i == n)
                ans++;
                
            else if(sum + i < n)
                getNum(i+1, sum+i, n);
                
                
        }
    }
}
