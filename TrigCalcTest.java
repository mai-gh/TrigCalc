import java.lang.Math;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class TrigCalcTest {

  // Equilateral or Equiangular Triangle: When all sides and angles of a triangle are equal, it is called an equilateral or equiangular triangle.
  public static Map<Character,Double> equilateral = Map.of(
    'A', 60.0,
    'B', 60.0,
    'C', 60.0,
    'a', 10.0,
    'b', 10.0,
    'c', 10.0
  );

  // Acute Isosceles Triangle: A triangle in which all 3 angles are acute angles and 2 sides measure the same is called an acute isosceles triangle.
  public static Map<Character,Double> acuteIsosceles = Map.of(
    'A', 80.0,
    'B', 50.0,
    'C', 50.0,
    'a', 12.86,
    'b', 10.0,
    'c', 10.0
  );

  // Obtuse Isosceles Triangle: A triangle in which 2 sides are equal and one angle is an obtuse angle is called an obtuse isosceles triangle.
  public static Map<Character,Double> obtuseIsosceles = Map.of(
    'A', 120.0,
    'B', 30.0,
    'C', 30.0,
    'a', 17.32,
    'b', 10.0,
    'c', 10.0
  );

  // Right Isosceles Triangle: A triangle in which 2 sides are equal and one angle is 90° is called an right isosceles triangle. So, in an right isosceles triangle, two sides and two acute angles are congruent.
  public static Map<Character,Double> rightIsosceles = Map.of(
    'A', 45.0,
    'B', 45.0,
    'C', 90.0,
    'a', 10.0,
    'b', 10.0,
    'c', 14.14
  );

  // Acute Scalene Triangle: A triangle that has 3 unequal sides and 3 acute angles is called an acute scalene triangle.
  public static Map<Character,Double> acuteScalene = Map.of(
    'A', 56.0,
    'B', 74.0,
    'C', 50.0,
    'a', 10.82,
    'b', 12.55,
    'c', 10.0
  );

  // Obtuse Scalene Triangle: A triangle with an obtuse angle with sides of different measures is called an obtuse scalene triangle.
  public static Map<Character,Double> obtuseScalene = Map.of(
    'A', 47.0,
    'B', 96.0,
    'C', 37.0,
    'a', 12.15,
    'b', 16.53,
    'c', 10.0
  );

  // Right Scalene Triangle: A triangle in which any one of the angles is a right angle and all the 3 sides are unequal, is called a right scalene triangle.
  public static Map<Character,Double> rightScalene = Map.of(
    'A', 56.0,
    'B', 90.0,
    'C', 34.0,
    'a', 10.0,
    'b', 12.06,
    'c', 6.75
  );

  public static Set<Map<Character,Double>> triangles = Set.of(
    equilateral,
    acuteIsosceles,
    obtuseIsosceles,
    rightIsosceles,
    acuteScalene,
    obtuseScalene,
    rightScalene
  );

  public boolean testSolutionType(solutionTypeEnum o, solutionTypeEnum s) {
    if (o == s) { 
      return true;
    } else {
      System.out.println(" >>> FAILURE: Output and Solution did not match for SolutionType: " + o + " != " + s );
      return false;
    }
  }

  public boolean testSSAType(SSATypeEnum o, SSATypeEnum s, String name) {
    if (o == s) { 
      return true;
    } else {
      System.out.println(" >>> FAILURE: Output and Solution did not match for SSAType: " + o + " != " + s );
      return false;
    }
  }

  public boolean testVal(double o, double s, String name) {
    //if (o == s) { 
    double diff = o - s;
    if (diff < 0) diff = diff * (-1);
    diff = BigDecimal.valueOf(diff).setScale(2, RoundingMode.FLOOR).doubleValue();
    //if (diff <= 0.01) { 
    if (diff <= 0.04) { 
      return true;
    } else {
      System.out.println(" >>> FAILURE: Output and Solution did not match for " + name + ": " + o + " != " + s + " diff:" + diff);
      return false;
    }
  }

  public boolean testAllVals(Map<Character,Double> output, Map<Character,Double> solution){
    boolean r_A = testVal(output.get('A'), solution.get('A'), "A");
    boolean r_B = testVal(output.get('B'), solution.get('B'), "B");
    boolean r_C = testVal(output.get('C'), solution.get('C'), "C");
    boolean r_a = testVal(output.get('a'), solution.get('a'), "a");
    boolean r_b = testVal(output.get('b'), solution.get('b'), "b");
    boolean r_c = testVal(output.get('c'), solution.get('c'), "c");
    boolean result = (r_A && r_B && r_C && r_a && r_b && r_c);
    return result;
  }

  public void test_AAA(Map<Character,Double> solutionMap) {
    String name = "AAA";
    char combo[] = { 'A', 'B', 'C' };
    String section = name + "_" + String.valueOf(combo);
    System.out.println("Now Testing: " + section);
    Map<Character,Double> query = Map.of(
      combo[0], solutionMap.get(combo[0]), 
      combo[1], solutionMap.get(combo[1]), 
      combo[2], solutionMap.get(combo[2]), 
      'a', 0.0,
      'b', 0.0,
      'c', 0.0
    );
    TrigCalc tc = new TrigCalc();
    tc.setVals(query);
    tc.determineSolutionType();
    boolean typeMatch = testSolutionType(tc.returnSolutionType(), solutionTypeEnum.AAA);
    tc.solveAAA();
    tc.roundVals();
    Map<Character,Double> outMap = tc.returnVals();
    Map<Character,Double> modifiedSolutionMap = new HashMap<Character,Double>();
    modifiedSolutionMap.putAll(solutionMap);
    modifiedSolutionMap.put('a', 0.0);
    modifiedSolutionMap.put('b', 0.0);
    modifiedSolutionMap.put('c', 0.0);
    boolean varsMatch = testAllVals(outMap, modifiedSolutionMap);
    if (typeMatch && varsMatch) {
      System.out.println("PASSED: " + section);
    } else {
      System.out.println(" -> FAILED: " + section);
    }
  }

  public void test_AAS(Map<Character,Double> solutionMap) {
    String name = "AAS";
    char[][] combos = { 
      {'A', 'B', 'a'},
      {'A', 'B', 'b'},
      {'A', 'C', 'a'},
      {'A', 'C', 'c'},
      {'B', 'C', 'b'},
      {'B', 'C', 'c'},
    };
    for (char[] combo: combos) {
      String section = name + "_" + String.valueOf(combo);
      System.out.println("Now Testing: " + section);
      Map<Character,Double> query = Map.of(
        combo[0], solutionMap.get(combo[0]), 
        combo[1], solutionMap.get(combo[1]), 
        combo[2], solutionMap.get(combo[2]) 
      );
      TrigCalc tc = new TrigCalc();
      tc.setVals(query);
      tc.determineSolutionType();
      boolean typeMatch = testSolutionType(tc.returnSolutionType(), solutionTypeEnum.AAS);
      tc.solveAAS();
      tc.roundVals();
      Map<Character,Double> outMap = tc.returnVals();
      boolean varsMatch = testAllVals(outMap, solutionMap);
      if (typeMatch && varsMatch) {
        System.out.println("PASSED: " + section);
      } else {
        System.out.println(" -> FAILED: " + section);
      }
    }
  }
  
  public void test_ASA(Map<Character,Double> solutionMap) {
    String name = "ASA";
    char[][] combos = { 
      {'A', 'B', 'c'},
      {'A', 'C', 'b'},
      {'B', 'C', 'a'},
    };
    for (char[] combo: combos) {
      String section = name + "_" + String.valueOf(combo);
      System.out.println("Now Testing: " + section);
      Map<Character,Double> query = Map.of(
        combo[0], solutionMap.get(combo[0]), 
        combo[1], solutionMap.get(combo[1]), 
        combo[2], solutionMap.get(combo[2]) 
      );
      TrigCalc tc = new TrigCalc();
      tc.setVals(query);
      tc.determineSolutionType();
      boolean typeMatch = testSolutionType(tc.returnSolutionType(), solutionTypeEnum.ASA);
      tc.solveAAS();
      tc.roundVals();
      Map<Character,Double> outMap = tc.returnVals();
      boolean varsMatch = testAllVals(outMap, solutionMap);
      if (typeMatch && varsMatch) {
        System.out.println("PASSED: " + section);
      } else {
        System.out.println(" -> FAILED: " + section);
      }
    }
  }

  public void test_SAS(Map<Character,Double> solutionMap) {
    String name = "SAS";
    char[][] combos = { 
      {'A', 'b', 'c'},
      {'B', 'a', 'c'},
      {'C', 'a', 'b'},
    };
    for (char[] combo: combos) {
      String section = name + "_" + String.valueOf(combo);
      System.out.println("Now Testing: " + section);
      Map<Character,Double> query = Map.of(
        combo[0], solutionMap.get(combo[0]), 
        combo[1], solutionMap.get(combo[1]), 
        combo[2], solutionMap.get(combo[2]) 
      );
      TrigCalc tc = new TrigCalc();
      tc.setVals(query);
      tc.determineSolutionType();
      boolean typeMatch = testSolutionType(tc.returnSolutionType(), solutionTypeEnum.SAS);
      tc.solveSAS();
      tc.roundVals();
      Map<Character,Double> outMap = tc.returnVals();
      boolean varsMatch = testAllVals(outMap, solutionMap);
      if (typeMatch && varsMatch) {
        System.out.println("PASSED: " + section);
      } else {
        System.out.println(" -> FAILED: " + section);
      }
    }
  }
  
  public void test_SSS(Map<Character,Double> solutionMap) {
    String name = "SSS";
    char combo[] = { 'a', 'b', 'c' };
    String section = name + "_" + String.valueOf(combo);
    System.out.println("Now Testing: " + section);
    Map<Character,Double> query = Map.of(
      combo[0], solutionMap.get(combo[0]), 
      combo[1], solutionMap.get(combo[1]), 
      combo[2], solutionMap.get(combo[2]) 
    );
    TrigCalc tc = new TrigCalc();
    tc.setVals(query);
    tc.determineSolutionType();
    boolean typeMatch = testSolutionType(tc.returnSolutionType(), solutionTypeEnum.SSS);
    tc.solveSSS();
    tc.roundVals();
    Map<Character,Double> outMap = tc.returnVals();
    boolean varsMatch = testAllVals(outMap, solutionMap);
    if (typeMatch && varsMatch) {
      System.out.println("PASSED: " + section);
    } else {
      System.out.println(" -> FAILED: " + section);
    }
  }

  public void test_InvalidSSA(Map<Character,Double> solutionMap) {}
  public void test_RightSSA(Map<Character,Double> solutionMap) {}
  public void test_ObliqueSSA(Map<Character,Double> solutionMap) {}
  public void test_AmbiguousSSA(Map<Character,Double> solutionMap) {}

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
    for (Map<Character,Double> triangle : triangles) {
      tct.test_AAA(triangle);
      tct.test_AAS(triangle);
      tct.test_ASA(triangle);
      tct.test_SAS(triangle);
      tct.test_SSS(triangle);
    }
  }
}
