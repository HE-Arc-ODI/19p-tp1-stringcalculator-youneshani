import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CorrectionTest {
  private StringCalculator sc;

  @Before
  public void setup() {
    this.sc = new StringCalculator();
  }

  //Requirement 1.1
  @Test
  public void whenStringIsEmptyThenZero() {
    Assert.assertEquals("When the string is empty then zero is returned", 0, this.sc.Add(""));
  }

  // Requirement 1.2
  @Test
  public void whenOneNumberIsUsedThenReturnValueIsThatSameNumber() {
    Assert.assertEquals("When a single number is used, the value returned is the number himself",4, this.sc.Add("4"));
  }

  // Requirement 1.3
  @Test
  public void whenTwoNumbersAreUsedThenReturnValueIsTheirSum() {
    Assert.assertEquals("When two number are used, the value returned is their sum", 7, this.sc.Add("3,4"));
  }

  // Requirement 2
  @Test
  public void whenAnyNumberOfNumbersIsUsedThenReturnValuesAreTheirSums() {
    Assert.assertEquals("When multiple numbers are used, the value returned is their sum", 3+6+15+18+46+33, this.sc.Add("3,6,15,18,46,33"));
  }

  // Requirement 3
  @Test
  public void whenNewLineIsUsedBetweenNumbersThenReturnValuesAreTheirSums() {
    Assert.assertEquals("When a new line is used between numbers, the value returned is their sum", 3+7+8, this.sc.Add("3,7\n8"));
  }

  // Requirement 4
  @Test
  public void whenDelimiterIsSpecifiedThenItIsUsedToSeparateNumbers() {
    Assert.assertEquals("When a delimiter is specified, it is used to separate the numbers," +
        "the value returned is their sum",9+5+2, this.sc.Add("//;\n9;5;2"));
  }

  // Requirement 5
  @Test(expected = RuntimeException.class)
  public void whenNegativeNumberIsUsedThenRuntimeExceptionIsThrown() {
    this.sc.Add("9,3,-2,3");
  }

  @Test
  public void whenNegativeNumbersAreUsedThenRuntimeExceptionIsThrown() {
    RuntimeException exception = null;
    try {
      this.sc.Add("3,4,-2,3,-5,2,-1");
    } catch (RuntimeException e) {
      exception = e;
    }
    Assert.assertEquals("When a negative number (or more) are used, an exception is thrown containing " +
        "all the negative numbers", "negatives not allowed -2;-5;-1", exception.getMessage());
  }

  // Requirement 6
  @Test
  public void whenOneOrMoreNumbersAreGreaterThan1000IsUsedThenItIsNotIncludedInSum() {
    Assert.assertEquals("When one or more number are greater than 1000, they are not included" +
        "in the sum", 1003, this.sc.Add("1000,1,1001,2,1203"));
  }

  // Requirement 7
  @Test
  public void whenDelimiterIsGreaterThan1CharacterThenItIsUsedToSeparateNumbers() {
    Assert.assertEquals("When a delimiter is greater than one character, then it is use to separate numbers",
        7, this.sc.Add("//[¢¢¢]\n3¢¢¢4"));
  }

  // Requirement 8
  @Test
  public void whenMultipleDelimitersAreUsedThenTheyAreUsedToSeparateNumbers() {
    Assert.assertEquals("When multiple delimiters are used, then they are used to separate the numbers",
        10, this.sc.Add("//[=][:][,]\n1,3=2:4"));
  }

  // Requirement 9
  @Test
  public void whenMultipleDelimitersOfMultipleCharactersAreUsedThenTheyAreUsedToSeparateNumbers() {
    Assert.assertEquals("When multiple delimiters of multiple characters are used, then they are used to separate the numbers",
        10, this.sc.Add("//[;;;;][::::][,,,,]\n1;;;;3,,,,2::::4"));
  }
}
