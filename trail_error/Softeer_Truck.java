package trail_error;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Softeer_Truck
{
    static class Customer {
        int A;
        int[] S;
        int[] P;
        public Customer(int A){
            this.A = A;
            S = new int[A];
            P = new int[A];
        }
    }

    public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    ArrayList<Customer> cus = new ArrayList<>();
    StringTokenizer st;

    // n 받아옴
    int n = Integer.parseInt(br.readLine());

    // 최소 최대 사이즈
    HashSet<Integer> hs = new HashSet<>();

    for(int i=0; i<n; i++){
        st = new StringTokenizer(br.readLine());

        // A제안수 S크기 P가격
        int A = Integer.parseInt(st.nextToken());
        Customer c = new Customer(A);
        
        // S 값 넣기
        for(int j=0; j<A; j++){
            c.S[j] = Integer.parseInt(st.nextToken());
            hs.add(c.S[j]);
        }

        // P 값 넣기
        for(int j=0; j<A; j++){
            c.P[j] = Integer.parseInt(st.nextToken());
        }

        cus.add(c);
    }

    ArrayList<Integer> sArr = new ArrayList<>(hs);
    Collections.sort(sArr);

    // Q값 받아오기
    int m = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    st = new StringTokenizer(br.readLine());

    for(int i=0; i<m; i++){
        int Q = Integer.parseInt(st.nextToken());
        int minSize = 0;
        System.out.println("Q===>"+Q);
        // 각 사이즈 별로 돌림
        for(int k : sArr){
        	System.out.println("k==>"+k);
            int totPrice = 0;
            for(Customer c : cus){
            	ArrayList<Integer> pList= new ArrayList<>();
                
                for(int j=0; j<c.A; j++){
                	if(c.S[j]<k)
                		continue;
                	pList.add(c.P[j]);
                }
                
                int max = 0;
                for(int j : pList)
                	max = Math.max(max, minSize);
                
                totPrice += max;
                
                if(totPrice >= Q){
                    minSize=k;
                    System.out.println("min==>"+minSize);
                    break;
                }
            }

            if(totPrice >= Q){
                minSize=k;
                System.out.println("min==>"+minSize);
                break;
            }
        }
        if(minSize == 0){
            sb.append("-1 ");
        }else{
            sb.append(minSize+" ");
        }
    }

        System.out.println(sb.toString().trim());
    }

}