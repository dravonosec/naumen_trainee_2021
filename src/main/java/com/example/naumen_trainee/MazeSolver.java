package com.example.naumen_trainee;

import java.util.*;

public class MazeSolver  {

    public static char[][] findRoute(char[][] labyrinth){
        int k = labyrinth.length;
        int l = labyrinth[0].length;
        int start = -1;
        int end = -1;
        int[] parents = new int[k*l];

        // Превращаем двумерный лабиринт в одномерный, чтобы одна точка была задана одним индексом
        // Тогда путь который мы храним будет задан не двумя координатами, а одной, что в теории
        // экономит память в два раза
        for (int x = 0; x < labyrinth.length; x++)
            for (int y = 0; y < labyrinth[0].length; y++) {
                if (labyrinth[x][y] == '@'){
                    start = l*x + y;
                }
                if (labyrinth[x][y] == 'X') {
                    end = l*x + y;
                }
            }

        var queue = new ArrayDeque<Integer>();
        var visited = new HashSet<Integer>();
        visited.add(start);
        queue.add(start);

        while (queue.size() != 0){
            var currentIndex = queue.poll();
            // индекс k или l?
            var neighbourPoints = getNeighbourPoints(currentIndex, l, k,  visited, labyrinth );
            for (int neighbourPoint:neighbourPoints
                 ) {

//                var neighbourList = new LinkedList<Integer>();
//
//                neighbourList.add(neighbourPoint);
//                neighbourList.addAll(currentList);

                // придумать как хранить путь
                visited.add(neighbourPoint);
                parents[neighbourPoint] = currentIndex;
                if (neighbourPoint == end) {
                    int current = parents[neighbourPoint];
                    while (current != start) {
                        labyrinth[current / k][current % l] = '+';
                        current = parents[current];
                    }
                    return labyrinth;
                }
                queue.add(neighbourPoint);
            }
        }
        return null;
    }

    public static List<Integer>  getNeighbourPoints(int index, int l,int k, HashSet<Integer> visited, char[][] labyrinth ){
        List<Integer> neighbourPoints = new ArrayList<>();
        var m = k*l;
        // Вроде добавил проверку на выход за карту, но это еще надо проверять
        if (index+l < m && !visited.contains(index+l) && labyrinth[(index+l)/k][(index+l)%l] != '#' )
            neighbourPoints.add(index+l);
        if (index-l > 0 && !visited.contains(index-l) && labyrinth[(index-l)/k][(index-l)%l] != '#' )
            neighbourPoints.add(index-l);
        if (index%l < l-1 && !visited.contains(index+1) && labyrinth[(index+1)/k][(index+1)%l] != '#')
            neighbourPoints.add(index+1);
        if (index%l > 0 && !visited.contains(index-1) && labyrinth[(index-1)/k][(index-1)%l] != '#')
            neighbourPoints.add(index-1);
        return neighbourPoints;
    }

    public static char[][] mazeParser(String maze){
        String[] splitMaze = maze.split("\n");
        char[][] mazeChar = new char[splitMaze[0].length()][splitMaze.length];
        for (int i = 0; i< splitMaze[0].length();i++ ){
            for (int j = 0; j < splitMaze.length;j++)
            {
                mazeChar[i][j] = splitMaze[i].charAt(j);
            }
        }
        return mazeChar;
    }
}
