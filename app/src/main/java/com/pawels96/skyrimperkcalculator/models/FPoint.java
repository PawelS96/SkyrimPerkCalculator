package com.pawels96.skyrimperkcalculator.models;

/**
 * Class which represents X and Y coordinates in a GraphView.
 * Viable values are floats from 0 to 1 (multipliers of the total width/height).
 */

public class FPoint {

    public float x, y;

    public FPoint(float x, float y){
        this.x = x;
        this.y = y;
    }
}
