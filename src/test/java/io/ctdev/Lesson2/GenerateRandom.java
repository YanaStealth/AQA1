package io.ctdev.Lesson2;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertTrue;

public class GenerateRandom {

    @Test
    public void checkRandomBounds() {
        Random rand = new Random(); //instance of random class
        int upperbound = 25;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);
        double double_random = rand.nextDouble();
        float float_random = rand.nextFloat();

        System.out.println("Random integer value from 0 to" + (upperbound - 1) + " : " + int_random);
        System.out.println("Random float value between 0.0 and 1.0 : " + float_random);
        System.out.println("Random double value between 0.0 and 1.0 : " + double_random);
        //driver.get("http://prom.ua");
        //  String expectedTitle = "Prom.ua — маркетплейс Украины";
        //  String actualTitle = driver.getTitle();
        // Thread.sleep(3000);
        // Assert.assertEquals(actualTitle, expectedTitle, "The title is not Google");
        assertTrue(int_random <= 24 && int_random > 0, int_random + "int_random is out of [0;24] range");
        assertTrue(double_random <= 24 && double_random > 0, double_random + "double_random is out of [0;24] range");
        assertTrue(float_random <= 24 && float_random > 0, float_random + "float_random is out of [0;24] range");

    }
}

