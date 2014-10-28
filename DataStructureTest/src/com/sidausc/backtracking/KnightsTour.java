package com.sidausc.backtracking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sidausc.helper.Helper;

public class KnightsTour {
	/**
	 * @param p
	 *            knight's point
	 * @param N
	 *            size of board
	 * @return avail point for knight at point p
	 */
	public static final int N = 5;
	static int count = 2;
	private static Map<Point, List<Point>> availMap = loadMap();
	private static int[][] board = new int[N][N];

	static List<Point> getAvailPoints(Point p) {
		ArrayList<Point> availPoints = new ArrayList<Point>();
		int x = (int) p.getX();
		int y = (int) p.getY();
		// clock wise from upper right
		if (isInBoard(x + 1, y - 2)) {
			availPoints.add(new Point(x + 1, y - 2));
		}
		if (isInBoard(x + 2, y - 1)) {
			availPoints.add(new Point(x + 2, y - 1));
		}
		if (isInBoard(x + 2, y + 1)) {
			availPoints.add(new Point(x + 2, y + 1));
		}
		if (isInBoard(x + 1, y + 2)) {
			availPoints.add(new Point(x + 1, y + 2));
		}
		if (isInBoard(x - 1, y + 2)) {
			availPoints.add(new Point(x - 1, y + 2));
		}
		if (isInBoard(x - 2, y + 1)) {
			availPoints.add(new Point(x - 2, y + 1));
		}
		if (isInBoard(x - 2, y - 1)) {
			availPoints.add(new Point(x - 2, y - 1));
		}
		if (isInBoard(x - 1, y - 2)) {
			availPoints.add(new Point(x - 1, y - 2));
		}
		return availPoints;
	}

	public static boolean isInBoard(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	public boolean isInBoard(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		return x >= 0 && x < N && y > 0 && y < N;
	}

	private static Map<Point, List<Point>> loadMap() {
		Map<Point, List<Point>> map = new HashMap<Point, List<Point>>();
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				Point p = new Point(x, y);
				map.put(p, getAvailPoints(p));
			}
		}
		return map;
	}

	public static List<Point> runKnightsTour() {
		List<Point> result = new ArrayList<Point>();
		Point start = new Point(0,0);
		board[0][0] = 1;
		result.add(start);
		if(!knightsTourEngine(result, start)) return null;
		return result;
	}

	private static boolean knightsTourEngine(List<Point> result, Point chosen) {

		int x = (int) chosen.getX();
		int y = (int) chosen.getY();

		if (result.size() == N * N) {
			return true;
		}

		List<Point> availPos = availMap.get(new Point(x, y));

		for (Point choose : availPos) {
			if (board[choose.x][choose.y] == 0) {
				result.add(choose);
				board[choose.x][choose.y] = count++;
				if (knightsTourEngine(result, choose))
					return true;
				else {
					result.remove(result.size() - 1); // backtrack
					board[choose.x][choose.y] = 0;
					count--;
				}
			}
		}
		// result.remove(result.size() - 1);// backtrack
		return false;
	}

	public static void main(String[] args) {
		List<Point> result = runKnightsTour();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + "   ");
			}
			System.out.println();

		}
		System.out.println(result);
		Helper.printMatrix(board);
	}
}
