package game.moveLogic;
import game.staticData.Constants;
import game.level.TerrainGenerator;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class AStar {
    public static class Cell {
        private int heuristicCost = 0; //Heuristic cost
        private int finalCost = 0; //G+H
        private int x, y;
        private Cell parent;

        private Cell(int x, int y) {
            setX(x);
            setY(y);
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        private void setX(int x) {
            this.x = x;
        }

        private void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + this.x + ", " + this.y + "]";
        }
    }

    //Blocked cells are just null Cell values in grid
    private static Cell[][] grid;
    private static PriorityQueue<Cell> open;
    private static Queue<Cell> path;

    private static boolean closed[][];
    private static int startX, startY;
    private static int endX, endY;

    private static void setBlocked(int x, int y) {
        grid[x][y] = null;
    }

    private static void setStartCell(int x, int y) {
        startX = x;
        startY = y;
    }

    private static void setEndCell(int x, int y) {
        endX = x;
        endY = y;
    }

    private static void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if (t == null || closed[t.x][t.y]) return;
        int t_final_cost = t.heuristicCost + cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || t_final_cost < t.finalCost) {
            t.finalCost = t_final_cost;
            t.parent = current;
            if (!inOpen) {
                open.add(t);
            }
        }
    }

    private static void AStar() {

        //TODO possibly work on a way to cut out calculating the entire A* if zombie is too far from player in the first place.

        //add the start location to open list.
        open.add(grid[startX][startY]);

        Cell current;

        while (true) {
            current = open.poll();
            if (current == null) {
                break;
            }
            closed[current.getX()][current.getY()] = true;

            if (current.equals(grid[endX][endY])) {
                return;
            }

            Cell t;

            //Checks and updates the cost of cells on Top/Bottom/Left and right of the current cell

            if (current.y - 1 >= 0) {
                t = grid[current.getX()][current.getY() - 1];
                checkAndUpdateCost(current, t, current.finalCost + Constants.V_H_COST);
            }

            if (current.y + 1 < grid[0].length) {
                t = grid[current.getX()][current.getY() + 1];
                checkAndUpdateCost(current, t, current.finalCost + Constants.V_H_COST);
            }

            if (current.x - 1 >= 0) {
                t = grid[current.getX() - 1][current.getY()];
                checkAndUpdateCost(current, t, current.finalCost + Constants.V_H_COST);
            }

            if (current.x + 1 < grid.length) {
                t = grid[current.getX() + 1][current.getY()];
                checkAndUpdateCost(current, t, current.finalCost + Constants.V_H_COST);
            }
        }
    }
    /*
        Params :
        tCase = findPath case No.
        levelX, levelY = Board's dimensions
        startX, startY = start location's x and y coordinates
        endX, endY = end location's x and y coordinates
        int[][] blocked = array containing inaccessible cell coordinates
    */

    public static Queue<Cell> findPath(int levelX, int levelY, int startX, int startY, int endX, int endY, int[][] blocked) {
        grid = new Cell[levelX][levelY];
        closed = new boolean[levelX][levelY];
        path = new LinkedBlockingDeque<>(Constants.MAX_DEQUEUE_SIZE);
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell) o1;
            Cell c2 = (Cell) o2;

            return c1.finalCost < c2.finalCost ? -1 :
                    c1.finalCost > c2.finalCost ? 1 : 0;
        });
        //Set start position
        setStartCell(startX, startY);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(endX, endY);

        //update Heuristic Cost of each cell
        for (int x = 0; x < levelX; ++x) {
            for (int y = 0; y < levelY; ++y) {
                grid[x][y] = new Cell(x, y);
                grid[x][y].heuristicCost = Math.abs(x - AStar.endX) + Math.abs(y - AStar.endY);
            }
        }
        grid[startX][startY].finalCost = 0;

        //Set blocked cells. Simply set the cell values to null for blocked cells.
        for (int x = 0; x < blocked.length; x++) {
            for (int y = 0; y < blocked[0].length; y++) {
                if (!Arrays.asList(TerrainGenerator.getPassableObj()).contains(blocked[x][y])) {
                    setBlocked(x, y);
                }
            }
        }

        AStar();

        if (closed[AStar.endX][AStar.endY]) {
            Cell current = grid[AStar.endX][AStar.endY];
            //adds First cell
            path.add(current);
            while (current.parent != null) {
                current = current.parent;
                //if there is no space left in the queue, it will not add the cell and will return an empty path.
                if (!path.offer(current)) {
                    path.clear();
                    return path;
                }
            }
        }
        return path;
    }
}
