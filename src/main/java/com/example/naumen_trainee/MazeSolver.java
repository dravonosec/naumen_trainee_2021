package com.example.naumen_trainee;

import java.util.*;

public class MazeSolver {

    public List<Integer> findPath(char[][] labyrinth){
        int k = labyrinth.length;
        int l = labyrinth[0].length;
        int start = -1;
        int end = -1;

        var flatLabyrinth = new char [k*l];
        // Превращаем двумерный лабиринт в одномерный, чтобы одна точка была задана одним индексом
        // Тогда путь который мы храним будет задан не двумя координатами, а одной, что в теории
        // экономит память в два раза
        for (int x = 0; x < labyrinth.length; x++)
            for (int y = 0; y < labyrinth[0].length; y++) {
                if (labyrinth[x][y] == '#'){
                    flatLabyrinth[l*x+y] = '@';
                    start = l*x + y;
                }
                if (labyrinth[x][y] == '.'){
                    flatLabyrinth[l*x+y] = '@';
                    start = l*x + y;
                }
                if (labyrinth[x][y] == '@'){
                    flatLabyrinth[l*x+y] = '@';
                    start = l*x + y;
                }
                if (labyrinth[x][y] == 'X') {
                    flatLabyrinth[l*x+y] = 'X';
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
            var neighbourPoints = getNeighbourPoints(currentIndex, l, visited, flatLabyrinth );
            for (int neighbourPoint:neighbourPoints
                 ) {

//                if (visited.contains(neighbourPoint)) continue;
//                if (neighbourPoint < 0 ||
//                        neighbourPoint >= map[0].length ||
//                        neighbourPoint.Y <0 ||
//                        neighbourPoint.Y >= map.length
//                )
//                    continue;
//
//                if (labyrinth[neighbourPoint/l][neighbourPoint%l] == '#') continue;
//                var neighbourList = new LinkedList<Integer>();
//
//                neighbourList.add(neighbourPoint);
//                neighbourList.addAll(currentList);

                // придумать как хранить путь
                visited.add(neighbourPoint);

                if (neighbourPoint == end)
                    return neighbourList;

                queue.add(neighbourPoint);
            }
        }
        return null;
    }

//    public String getSolvedMaze(LinkedList<Point> path, char[][] labyrinth){
//
//        for (Point point: path
//             ) {
//            var x = point.X;
//            var y = point.Y;
//            labyrinth[x][y] = '+';
//        }
//        labyrinth[path.getFirst().X][path.getFirst().Y] = 'X';
//        labyrinth[path.getLast().X][path.getLast().Y] = '@';
//
//        String solvedMaze = new String();
//
//        for (int x = 0; x < labyrinth.length; x++)
//            for (int y = 0; y < labyrinth[0].length; y++)
//            {
//                solvedMaze += labyrinth[x][y];
//                if (y == labyrinth[0].length - 1)
//                    solvedMaze += "\n";
//            }
//
//        return solvedMaze;
//    }

    public List<Integer> getNeighbourPoints(int index, int k, HashSet<Integer> visited, char[] labyrinth ){
        List<Integer> neighbourPoints = List.of();
        var l = labyrinth.length;
        // Вроде добавил проверку на выход за карту, но это еще надо проверять
        if (index+k <= l && !visited.contains(index+k) && labyrinth[index+k] != '#' )
            neighbourPoints.add(index+k);
        if (index-k >= l && !visited.contains(index-k) && labyrinth[index-k] != '#' )
            neighbourPoints.add(index-k);
        if (index%k + 1 <= k && !visited.contains(index+1) && labyrinth[index+1] != '#')
            neighbourPoints.add(index+1);
        if (index%k - 1 >= k && !visited.contains(index-1) && labyrinth[index-1] != '#')
            neighbourPoints.add(index-1);
        return neighbourPoints;
    }

    public char[][] mazeParser(String maze){
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
