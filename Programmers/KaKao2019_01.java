package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class KaKao2019_01 {
	private static int[] idCnt;
	private static ArrayList<HashSet<String>> arr;
	private static HashSet<String> res = new HashSet<>();
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
		
		idCnt = new int[banned_id.length];
		arr = new ArrayList<>();
		
		for(int i=0; i<banned_id.length; i++) {
			arr.add(new HashSet<>());
		}
		
		// �ҷ� ����� ���� ���̵� ����
		setBannedList(banned_id, user_id);
		
		
		// �̹� �Ҵ�� user list
		HashSet<String> users = new HashSet<>();
		
		// ����� �� ���ϱ�
		getAllCase(0, users);
		
		
		System.out.println(res.size());
	}
	
	public static void getAllCase(int dep, HashSet<String> users) {
		if(dep == idCnt.length) {
			ArrayList<String> a = new ArrayList<>(users);
			Collections.sort(a);
			
			StringBuilder sb = new StringBuilder();
			for(String s: a) {
				sb.append(s);
			}
			
			res.add(sb.toString());
			return ;
		}
		
		HashSet<String> hs = arr.get(dep);
		if(hs.size()==0)
			getAllCase(dep+1,users);
		for(String str : hs) {
			if(users.contains(str))
				continue;
			//System.out.println("str==>"+str);
			users.add(str);
			getAllCase(dep+1, users);
			users.remove(str);
		}
	}
	
	public static void setBannedList(String[] banned_id, String[] user_id) {
		for(int i=0; i<banned_id.length; i++) {
			String banned = banned_id[i];
			for(String user : user_id) {
				// ���� ��
				if(banned.length() != user.length())
					continue;
				//System.out.println("banned==>"+banned+"//user==>"+user);
				
				// ���� ��
				boolean isSame = true;
				for(int j=0; j<user.length(); j++) {
					if(banned.charAt(j)=='*')
						continue;
					if(banned.charAt(j)!=user.charAt(j)) {
						isSame = false;
						break;
					}
				}
				
				
				// ��ġ�ϴ� ���� �ֱ�
				if(isSame) {
					idCnt[i]++;
					arr.get(i).add(user);
				}
			}
		}
	}
}
