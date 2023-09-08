package Leetcode;

import java.util.ArrayList;
import java.util.List;

class leetcode118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>()); answer.get(0).add(1);

        for(int i=1; i<numRows; i++){
            List<Integer> now = new ArrayList<>();
            now.add(1);

            for(int j=0; j<i-1; j++){
                now.add(answer.get(i-1).get(j)+answer.get(i-1).get(j+1));
            }

            now.add(1);
            answer.add(now);
        }

        return answer;
    }
}
