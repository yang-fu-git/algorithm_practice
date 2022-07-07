package de.unistuttgart.dsass2022.ex02.p4;

import static org.junit.Assert.*;
import org.junit.*;
import org.w3c.dom.ls.LSOutput;

public class ComplexityTest {
    @Test
    public static void main(String[] args){

        assert Complexity.couldBeBetter2(5)==Complexity.isDoneBetter2(5);
//        assert Complexity.couldBeBetter3(5)==Complexity.isDoneBetter3(5);

    }

}
