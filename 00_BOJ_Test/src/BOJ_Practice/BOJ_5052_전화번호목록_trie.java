/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5052_전화번호목록_trie {
	static class Trie {
	    boolean end;
	    boolean pass;
	    Trie[] child;

	    Trie() {
	        end = false;
	        pass = false;
	        child = new Trie[10];
	    }

	    public boolean insert(String str, int idx) {

	        //끝나는 단어 있으면 false 종료
	        if(end) return false;

	        //idx가 str만큼 왔을때
	        if(idx == str.length()) {
	            end = true;
	            if(pass) return false; // 더 지나가는 단어 있으면 false 종료
	            else return true;
	        }
	        //아직 안왔을 때
	        else {
	            int next = str.charAt(idx) - '0';
	            if(child[next] == null) {
	                child[next] = new Trie();
	                pass = true;
	            }
	            return child[next].insert(str, idx+1);
	        }
	    }
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			Trie trie = new Trie();
			boolean flag = true;
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				if(!flag) continue;
				if(!trie.insert(str, 0)) {
					flag = false;
					System.out.println("NO");
				}
			}
			if(flag) {
				System.out.println("YES");
			}
		}
	}
}
