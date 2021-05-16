package com.example.naumen_trainee;

import java.io.Console;

public class Main {

    public static void main(String[] args){
        String testStr1 = "...@.\n.####\n.....\n####.\n.X...";
        String testStr2 = "....@\n#.###\n.....\n....X\n.....";
        var a = MazeSolver.FindPath(testStr1);
        System.out.println(a);

//        var point = new Point(0,0);
//        point.X = 0;
//        point.Y = 0;
//        MazeSolver.GetNeighbourPoints(point);

        var p = new Point(1,2);
        var p2 = new Point(1,2);
        System.out.println(p.isEqual(p2));
    }
}
