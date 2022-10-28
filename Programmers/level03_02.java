package Programmers;

class level03_02 {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(n>s){
            return new int[] {-1};
        }if(s%n==0){
            int num = s/n;
            for(int i=0; i<n; i++){
                answer[i] = num;
            }
        }else {
            int res = s%n;
            int num = s/n;
            
            for(int i=0; i<n; i++){
                answer[i] = num;
            }
            for(int i=n-1; i>=n-res; i--){
                answer[i] += 1;
            }
        }
        
        return answer;
    }
}
