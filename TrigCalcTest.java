import java.util.Map;

public class TrigCalcTest {

  public boolean testVal(double o, double s, String name) {
    if (o == (double)s) { 
      return true;
    } else {
      System.out.println(" >>> FAILURE: Output and Solution did not match for " + name + ": " + o + " != " + s );
      return false;
    }
  }

  public boolean testAllVals(Map<String,Double> output, Map<String,Double> solution, String name){

    System.out.println("Now Testing: " + name);

    boolean r_A = testVal(output.get("A"), solution.get("A"), "A");
    boolean r_B = testVal(output.get("B"), solution.get("B"), "B");
    boolean r_C = testVal(output.get("C"), solution.get("C"), "C");
    boolean r_a = testVal(output.get("a"), solution.get("a"), "a");
    boolean r_b = testVal(output.get("b"), solution.get("b"), "b");
    boolean r_c = testVal(output.get("c"), solution.get("c"), "c");

    boolean result = (r_A && r_B && r_C && r_a && r_b && r_c);

    if (result) System.out.println("PASSED: " + name);

    return result;
  }

  public void test_solveAAS() {
    // https://www.mathsisfun.com/algebra/trig-solving-aas-triangles.html
    TrigCalc tc = new TrigCalc();
    tc.parseArgs("-A 35 -C 62 -c 7".split(" "));
    tc.solveAAS();
    tc.roundVals();
    Map<String,Double> map = tc.returnVals();
    boolean rr = testAllVals(tc.returnVals(), Map.of(
      "A", 35.0,
      "B", 83.0,
      "C", 62.0,
      "a", 4.55,
      "b", 7.87,
      "c", 7.0
    ), "test_solveAAS");
  }

  public static void enforceAssertions() {
    try {
      assert false; // If assertions are on, this will throw an error
      System.out.println("ASSERTIONS ARE DISABLED");
      System.out.println("enable them with `java -ea TrigCalcTest");
      System.exit(1);
    } catch (AssertionError e) {
    }
  } 

  public static void main(String[] args) {
    enforceAssertions();
    TrigCalcTest tct = new TrigCalcTest();
    tct.test_solveAAS();
  }

}
