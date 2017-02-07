package Heuristics;

import AStar.Puzzle;
import AStar.State;

public class ZeroHeuristic implements Heuristic {

	public ZeroHeuristic(Puzzle puzzle) {

	}

	public int getValue(State state) {
		return 0;
	}

}
