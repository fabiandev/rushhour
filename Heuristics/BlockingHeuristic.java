package Heuristics;

import AStar.Puzzle;
import AStar.State;

public class BlockingHeuristic implements Heuristic {

	private Puzzle puzzle;
	private int numCars;
	private int carPosFixed;
	private int carSize;

	public BlockingHeuristic(Puzzle puzzle) {
		this.puzzle = puzzle;
		this.numCars = this.puzzle.getNumCars();
		this.carPosFixed = puzzle.getFixedPosition(0);
		this.carSize = this.puzzle.getCarSize(0);
	}

	public int getValue(State state) {

		if (state.isGoal()) {
			return 0;
		}

		int blocking = 1;

		int carPosFront = state.getVariablePosition(0) + this.carSize;

		for (int i = 1; i < this.numCars; i++) {

			if (!this.puzzle.getCarOrient(i)) {
				continue;
			}

			if (this.puzzle.getFixedPosition(i) < carPosFront) {
				continue;
			}

			int currentCarPos = state.getVariablePosition(i);
			int currentCarPosFront = currentCarPos + this.puzzle.getCarSize(i);

			if (this.carPosFixed >= currentCarPos && this.carPosFixed < currentCarPosFront) {
				blocking++;
			}
		}

		return blocking;
	}

}
