package io.turntabl.springwebservice.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MathsTest {
    private Maths maths  = new Maths();

    @Test
    public void add_Zero() {
        int result = maths.add(0, 0);
        assertEquals(0, result);
    }

    @Test
    public void add_Negatives() {
        int result = maths.add(-1, -1);
        assertEquals(-2, result);
    }

    @Test
    public void add_positives() {
        int result = maths.add(1, 2);
        assertEquals(3, result);
    }
    @Test
    public void add_pos_and_negative() {
        int result = maths.add(1, -1);
        assertEquals(0, result);
    }

    @Test
    public void subtract_zero() {
        int result = maths.subtract(0, 0);
        assertEquals(0, result);
    }

    @Test
    public void subtract_positives() {
        int result = maths.subtract(3, 2);
        assertEquals(1, result);
    }

    @Test
    public void subtract_negatives() {
        int result = maths.subtract(-2, -1);
        assertEquals(-1, result);
    }

    @Test
    public void subtract_pos_and_neg() {
        int result = maths.subtract(3, -1);
        assertEquals(4, result);
    }
}