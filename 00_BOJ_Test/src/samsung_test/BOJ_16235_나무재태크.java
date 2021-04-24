/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16235_나무재태크 {
	static class Tree {
		int y, x, age;

		public Tree(int y, int x, int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}

		@Override
		public String toString() {
			return "age: " + this.age;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stt.nextToken());
		int M = Integer.parseInt(stt.nextToken());
		int K = Integer.parseInt(stt.nextToken());

		int[][] robot = new int[N + 1][N + 1];
		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			stt = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				robot[i][j] = Integer.parseInt(stt.nextToken());
				map[i][j] = 5;
			}
		}

		// 해당 좌표에 나무 저장 관리 PQ
		PriorityQueue<Tree> tree_pQueue = new PriorityQueue<Tree>((o1, o2) -> (o1.age - o2.age));

		for (int i = 0; i < M; i++) {
			stt = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stt.nextToken());
			int x = Integer.parseInt(stt.nextToken());
			int age = Integer.parseInt(stt.nextToken());
			tree_pQueue.offer(new Tree(y, x, age));
		}

		int res = process(map, tree_pQueue, robot, N, K);

		System.out.println(res);
	}

	private static int process(int[][] map, PriorityQueue<Tree> tree_pQueue, int[][] robot, int n, int k) {
		Queue<Tree> dead = new LinkedList<Tree>();
		Queue<Tree> asc_age_queue = new LinkedList<Tree>();
		// K년 반복
		for (int i = 0; i < k; i++) {
			// 봄
			tree_pQueue = spring(map, tree_pQueue, asc_age_queue, dead, n);

			// 여름
			summer(map, dead, n);

			// 가을
			autumn(tree_pQueue, asc_age_queue, n);

			// 겨울
			winter(map, robot, n);
		}

		// 반복이 모두 끝나고 난 후 나무의 갯수 카운팅
		int res = count_trees(tree_pQueue, n);
		return res;
	}

	// 남은 나무의 수를 카운팅
	private static int count_trees(PriorityQueue<Tree> tree_pQueue, int n) {
		int sum = tree_pQueue.size();
		return sum;
	}

	// 겨울. 로봇이 양분을 뿌린다
	private static void winter(int[][] map, int[][] robot, int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] += robot[i][j];
			}
		}
	}

	// 가을. 나이가 5의 배수인 나무는 8방향으로 씨를 뿌린다.
	private static void autumn(PriorityQueue<Tree> tree_pQueue, Queue<Tree> asc_age_queue, int n) {
		// 북쪽부터 반시계방향
		int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };

		while (!asc_age_queue.isEmpty()) {
			Tree tree = asc_age_queue.poll();
			int y = tree.y;
			int x = tree.x;

			for (int d = 0; d < 8; d++) {
				int next_y = y + dy[d];
				int next_x = x + dx[d];

				if (rangeCheck(next_y, next_x, n))
					continue;
				tree_pQueue.offer(new Tree(next_y, next_x, 1));
			}
		}
	}

	// 범위 체크
	private static boolean rangeCheck(int next_y, int next_x, int n) {
		return next_y <= 0 || next_y >= n + 1 || next_x <= 0 || next_x >= n + 1;
	}

	// 여름. 죽은 나무의 나이/2 한만큼 양분이 늘어난다.
	private static void summer(int[][] map, Queue<Tree> dead, int n) {
		while (!dead.isEmpty()) {
			Tree tree = dead.poll();
			int y = tree.y;
			int x = tree.x;
			int age = tree.age;
			map[y][x] += age;
		}
	}

	// 봄. 양분과 나이를 먹는다
	private static PriorityQueue<Tree> spring(int[][] map, PriorityQueue<Tree> tree_pQueue, Queue<Tree> asc_age_queue,
			Queue<Tree> dead, int n) {
		// 모든 나무 순회
		PriorityQueue<Tree> tmp_pQueue = new PriorityQueue<Tree>((o1, o2) -> o1.age - o2.age);
		while (!tree_pQueue.isEmpty()) {
			Tree tree = tree_pQueue.poll();
			int y = tree.y;
			int x = tree.x;
			int age = tree.age;
			if (age > map[y][x]) {// 나이 확인
				// 양분을 먹지 못하면 나무는 죽음
				dead.offer(new Tree(y, x, age / 2));
				continue;
			}
			// 양분 먹을 수 있음
			map[y][x] -= age; // 양분 감소
			tree.age++; // 나이 증가
			if (tree.age % 5 == 0) // 여릉메 성장 가능
				asc_age_queue.offer(tree);
			tmp_pQueue.offer(tree);

		} // 나무 순회 end
		return tmp_pQueue;
	}
}
