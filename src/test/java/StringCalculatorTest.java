
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baptiste.andres
 */
public class StringCalculatorTest {
    
    @Test
        public void emptyStringShouldReturnZero() {
        StringCalculator sut = new StringCalculator();
        assertEquals(0, sut.Add(""));
    }
        
    @Test
    public void oneNumberShouldReturnItseft() {
        StringCalculator sut = new StringCalculator();
        assertEquals(3, sut.Add("3"));
    }
    
    @Test
    public void twoNumbersShouldBeAdded() {
        StringCalculator sut = new StringCalculator();
        assertEquals(7, sut.Add("2,5"));
    }
    
    @Test
    public void supportNewLineAsSeparator() {
        StringCalculator sut = new StringCalculator();
        assertEquals(6, sut.Add("1\n2,3"));
    }
    
    @Test
    public void supportForCustomDelimiter() {
        StringCalculator sut = new StringCalculator();
        assertEquals(10, sut.Add(";\n1;2;3;4"));
    }
    
    @Test
    public void negativeNotSupported() {
        StringCalculator sut = new StringCalculator();
        try {
            sut.Add("-1,4");
            fail("exception should have been thrown");
        }
        catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed -1", e.getMessage());
        }
    }
    
    @Test
    public void NumbersGreaterThan1000NotIncludedInSum() {
        StringCalculator sut = new StringCalculator();
        assertEquals(1009, sut.Add("3,1000,1001,6,1234"));
    }
    
}
