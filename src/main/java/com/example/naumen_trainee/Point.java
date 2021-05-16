package com.example.naumen_trainee;

import java.nio.channels.Pipe;

class Point {

    public int X;
    public int Y;

    Point (int x, int y){
        this.X = x;
        this.Y = y;
    }

    void Add(Point point){
        this.X = this.X + point.X;
        this.Y = this.Y + point.Y;
    }

    boolean isEqual (Point point){
        return this.X == point.X && this.Y == point.Y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + X;
        result = prime * result + Y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        return ((Point)obj).X == this.X && ((Point)obj).Y == this.Y;
    }
}
