package AStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Heuristics.Heuristic;

/**
 * This is the template for a class that performs A* search on a given rush hour
 * puzzle with a given heuristic. The main search computation is carried out by
 * the constructor for this class, which must be filled in. The solution (a path
 * from the initial state to a goal state) is returned as an array of
 * <tt>State</tt>s called <tt>path</tt> (where the first element
 * <tt>path[0]</tt> is the initial state). If no solution is found, the
 * <tt>path</tt> field should be set to <tt>null</tt>. You may also wish to
 * return other information by adding additional fields to the class.
 */
public class AStar {

    /** The solution path is stored here */
    public State[] path;
    
    private List<HeuristicsNode> open = new ArrayList<HeuristicsNode>();
    private List<HeuristicsNode> closed = new ArrayList<HeuristicsNode>();

    /**
     * This is the constructor that performs A* search to compute a
     * solution for the given puzzle using the given heuristic.
     */
    public AStar(Puzzle puzzle, Heuristic heuristic) {
    	
    	// Set sort variable, to indicate if open list needs to be sorted
    	boolean sort = false;
    	
    	// Initialize root node w/ heuristics and path costs
    	int h = heuristic.getValue(puzzle.getInitNode().getState());
    	int g = puzzle.getInitNode().getDepth();
    	HeuristicsNode root = new HeuristicsNode(puzzle.getInitNode(), g, h);
    	
    	open.add(root);	// Add the root node to the open list
    	
    	while(!open.isEmpty()) {
    		
    		if (sort) {					// Check if open list needs to be sorted,
    			Collections.sort(open);	// If so, do it.
    			sort = false;
    		}					
    		
    		Node current = open.remove(0);
    		
    		if (current.getState().isGoal()) {
    			// TODO: Check if correct: save solution path in path array
    			
    			// Set the path array size to depth of goal state;
    			// The +1 should be necessary to also include root node.
    			path = new State[current.getDepth() + 1];
    			
    			// Set the current node to pathNode
    			Node pathNode = current;
    			
    			// Get state for every node and store it in the path array,
    			// then override current path node with its parent node until parent is null.
    			while (pathNode != null) {
    				path[pathNode.getDepth()] = pathNode.getState();
    				pathNode = pathNode.getParent();
    			}
    			
    			// Break while loop when finished.
    			break;
    		}
    		
    		for (Node successor : current.expand()) {
    			if (shouldSkip(successor)) {
    				continue;
    			}
    			
    			// Add the successor of current node to open list,
    			// Set path costs and heuristics accordingly
    			h = heuristic.getValue(successor.getState());
    			g = successor.getDepth();
    			open.add(new HeuristicsNode(successor, g, h));
    			sort = true;
    		}

    		closed.add(new HeuristicsNode(current));
    	}

    }
    
    private boolean shouldSkip(Node successor) {
    	// TODO: http://web.mit.edu/eranki/www/tutorials/search/
    	return closed.contains(successor) || open.contains(successor);
    }

}
