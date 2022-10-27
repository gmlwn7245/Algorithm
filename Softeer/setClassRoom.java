package Softeer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class setClassRoom {
	static class Room {
		int start;
		int end;
		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
    private static int N;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        ArrayList<Room> rooms = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            //시작시간 S
            int s = Integer.parseInt(st.nextToken());
            //종료시간 F
            int f = Integer.parseInt(st.nextToken());
            
            Room r = new Room(s,f);
            rooms.add(r);
        }
        
        // end가 작은 순서대로 !!!!
        rooms.sort((Room r1, Room r2)-> r1.end - r2.end);
        
        int end = rooms.get(0).end;
        int cnt = 1;
        	
        for(Room r : rooms) {
        	if(end <= r.start) {
        		cnt++;
        		end = r.end;
        	}
        }        

        bw.write(""+cnt);
        bw.flush();
        bw.close();
    }
}
