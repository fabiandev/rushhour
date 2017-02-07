package Heuristics;

import AStar.State;

public interface Heuristic {
	public int getValue(State state);
}
