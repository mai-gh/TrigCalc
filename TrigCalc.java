import java.lang.Math;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Map;

enum solutionTypeEnum { NULL, AAA, AAS, ASA, SAS, SSA, SSS }
enum SSATypeEnum { NULL, INVALID, RIGHT, AMBIGUOUS, OBLIQUE };

public class TrigCalc {
  private double A, B, C, a, b, c; 
  private solutionTypeEnum solutionType = solutionTypeEnum.NULL;
  private SSATypeEnum SSAType = SSATypeEnum.NULL;
  
  


  public void parseArgs(String[] args) {
    this.A = this.B = this.C = this.a = this.b = this.c = 0;
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-A":
          i++;
          this.A = Double.parseDouble(args[i]);
          break;
        case "-B":
          i++;
          this.B = Double.parseDouble(args[i]);
          break;
        case "-C":
          i++;
          this.C = Double.parseDouble(args[i]);
          break;
        case "-a":
          i++;
          this.a = Double.parseDouble(args[i]);
          break;
        case "-b":
          i++;
          this.b = Double.parseDouble(args[i]);
          break;
        case "-c":
          i++;
          this.c = Double.parseDouble(args[i]);
          break;
        default:
         System.out.println("Invalid arguement");
         System.exit(1);
      }
    }
  }

  public static void printDiagram() {
    String diagram = "                                                 \n" +
                     "                     ..                          \n" +
                     "                    .   .                        \n" +
                     "                   .  C   .                      \n" +
                     "                  .         .                    \n" +
                     "                 .            .                  \n" +
                     "                .               .                \n" +
                     "            b  .                  .  a           \n" +
                     "              .                     .            \n" +
                     "             .                        .          \n" +
                     "            .                           .        \n" +
                     "           .                              .      \n" +
                     "          .                                 .    \n" +
                     "         . A                               B  .  \n" +
                     "        ........................................ \n" +
                     "                           c                     \n" +
                     "                                                 " ;
    System.out.println(diagram);
  }

  public void printVals() {
    String s = "   ";
    System.out.println("Type:" + this.solutionType + s +
                       "SSAType:" + this.SSAType + s +
                       "A:" + this.A + s +
                       "B:" + this.B + s +
                       "C:" + this.C + s +
                       "a:" + this.a + s +
                       "b:" + this.b + s +
                       "c:" + this.c
                      );
  }

  public void roundVals() {
    this.A = BigDecimal.valueOf(this.A).setScale(2, RoundingMode.HALF_UP).doubleValue();
    this.B = BigDecimal.valueOf(this.B).setScale(2, RoundingMode.HALF_UP).doubleValue();
    this.C = BigDecimal.valueOf(this.C).setScale(2, RoundingMode.HALF_UP).doubleValue();
    this.a = BigDecimal.valueOf(this.a).setScale(2, RoundingMode.HALF_UP).doubleValue();
    this.b = BigDecimal.valueOf(this.b).setScale(2, RoundingMode.HALF_UP).doubleValue();
    this.c = BigDecimal.valueOf(this.c).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  public Map<Character,Double> returnVals() {
    Map<Character,Double> map = Map.of(
      'A', this.A, 
      'B', this.B,
      'C', this.C,
      'a', this.a,
      'b', this.b,
      'c', this.c
    );
    return map;
  }

  public void setVals(Map<Character,Double> map) {
    if (map.containsKey('A')) this.A = map.get('A');
    if (map.containsKey('B')) this.B = map.get('B');
    if (map.containsKey('C')) this.C = map.get('C');
    if (map.containsKey('a')) this.a = map.get('a');
    if (map.containsKey('b')) this.b = map.get('b');
    if (map.containsKey('c')) this.c = map.get('c');
  }

  public solutionTypeEnum returnSolutionType() {
    return this.solutionType;
  }

  public void determineSolutionType() {
    if (isAAA())      { this.solutionType = solutionTypeEnum.AAA; }
    else if (isAAS()) { this.solutionType = solutionTypeEnum.AAS; }
    else if (isASA()) { this.solutionType = solutionTypeEnum.ASA; }
    else if (isSAS()) { this.solutionType = solutionTypeEnum.SAS; }
    else if (isSSS()) { this.solutionType = solutionTypeEnum.SSS; }
    else if (isSSA()) {
      this.solutionType = solutionTypeEnum.SSA;
      if (isInvalidSSA()) this.SSAType = SSATypeEnum.INVALID;
      if (isRightSSA()) this.SSAType = SSATypeEnum.RIGHT;
      if (isAmbiguousSSA()) this.SSAType = SSATypeEnum.AMBIGUOUS;
      if (isObliqueSSA()) this.SSAType = SSATypeEnum.OBLIQUE;
    }
  }


  public boolean isAAA() {
    return ((this.A > 0) && (this.B > 0) && (this.C > 0) && (this.a == 0) && (this.b == 0) && (this.c == 0)); // A & B & C
  }


  public boolean isAAS() {
    return 
    (
      ((this.A >  0) && (this.B >  0) && (this.C == 0) && (this.a >  0) && (this.b == 0) && (this.c == 0)) || // A & B & a
      ((this.A >  0) && (this.B >  0) && (this.C == 0) && (this.a == 0) && (this.b >  0) && (this.c == 0)) || // A & B & b
      ((this.A >  0) && (this.B == 0) && (this.C >  0) && (this.a >  0) && (this.b == 0) && (this.c == 0)) || // A & C & a
      ((this.A >  0) && (this.B == 0) && (this.C >  0) && (this.a == 0) && (this.b == 0) && (this.c >  0)) || // A & C & c
      ((this.A == 0) && (this.B >  0) && (this.C >  0) && (this.a == 0) && (this.b >  0) && (this.c == 0)) || // B & C & b
      ((this.A == 0) && (this.B >  0) && (this.C >  0) && (this.a == 0) && (this.b == 0) && (this.c >  0))    // B & C & c
    );
  }

  public boolean isASA() {
    return 
    (
      ((this.A >  0) && (this.B >  0) && (this.C == 0) && (this.a == 0) && (this.b == 0) && (this.c >  0)) || // A & B & c
      ((this.A >  0) && (this.B == 0) && (this.C >  0) && (this.a == 0) && (this.b >  0) && (this.c == 0)) || // A & C & b
      ((this.A == 0) && (this.B >  0) && (this.C >  0) && (this.a >  0) && (this.b == 0) && (this.c == 0))    // B & C & a
    );
  }

  public boolean isSAS() { 
    return
    (
      ((this.A >  0) && (this.B == 0) && (this.C == 0) && (this.a == 0) && (this.b >  0) && (this.c >  0)) || // b & c & A
      ((this.A == 0) && (this.B >  0) && (this.C == 0) && (this.a >  0) && (this.b == 0) && (this.c >  0)) || // a & c & B
      ((this.A == 0) && (this.B == 0) && (this.C >  0) && (this.a >  0) && (this.b >  0) && (this.c == 0))    // a & b & C
    );
  }

  public boolean isSSA() {
    return
    (
      ((this.A == 0) && (this.B >  0) && (this.C == 0) && (this.a == 0) && (this.b >  0) && (this.c >  0)) || // b & c & B
      ((this.A == 0) && (this.B == 0) && (this.C >  0) && (this.a == 0) && (this.b >  0) && (this.c >  0)) || // b & c & C
      ((this.A >  0) && (this.B == 0) && (this.C == 0) && (this.a >  0) && (this.b == 0) && (this.c >  0)) || // a & c & A
      ((this.A == 0) && (this.B == 0) && (this.C >  0) && (this.a >  0) && (this.b == 0) && (this.c >  0)) || // a & c & C
      ((this.A >  0) && (this.B == 0) && (this.C == 0) && (this.a >  0) && (this.b >  0) && (this.c == 0)) || // a & b & A
      ((this.A == 0) && (this.B >  0) && (this.C == 0) && (this.a >  0) && (this.b >  0) && (this.c == 0))    // a & b & B
    );
  }

  public boolean isSSS() {
    return ((this.A == 0) && (this.B == 0) && (this.C == 0) && (this.a > 0) && (this.b > 0) && (this.c > 0)); // a & b & c
  }

  public boolean isInvalidSSA() {
    if ((this.A > 0 ) && (this.a > 0) && (this.b > 0)) return (((this.b * Math.sin(Math.toRadians(this.A))) / this.a) >= 1);
    if ((this.A > 0 ) && (this.a > 0) && (this.c > 0)) return (((this.c * Math.sin(Math.toRadians(this.A))) / this.a) >= 1);
    if ((this.B > 0 ) && (this.b > 0) && (this.a > 0)) return (((this.a * Math.sin(Math.toRadians(this.B))) / this.b) >= 1);
    if ((this.B > 0 ) && (this.b > 0) && (this.c > 0)) return (((this.c * Math.sin(Math.toRadians(this.B))) / this.b) >= 1);
    if ((this.C > 0 ) && (this.c > 0) && (this.a > 0)) return (((this.a * Math.sin(Math.toRadians(this.C))) / this.c) >= 1);
    if ((this.C > 0 ) && (this.c > 0) && (this.b > 0)) return (((this.b * Math.sin(Math.toRadians(this.C))) / this.c) >= 1);
    return false;
  }

  public boolean isRightSSA() {
    if ((this.A > 0 ) && (this.a > 0) && (this.b > 0)) return (this.a == (this.b * Math.sin(Math.toRadians(this.A))));
    if ((this.A > 0 ) && (this.a > 0) && (this.c > 0)) return (this.a == (this.c * Math.sin(Math.toRadians(this.A))));
    if ((this.B > 0 ) && (this.b > 0) && (this.a > 0)) return (this.b == (this.a * Math.sin(Math.toRadians(this.B))));
    if ((this.B > 0 ) && (this.b > 0) && (this.c > 0)) return (this.b == (this.c * Math.sin(Math.toRadians(this.B))));
    if ((this.C > 0 ) && (this.c > 0) && (this.a > 0)) return (this.c == (this.a * Math.sin(Math.toRadians(this.C))));
    if ((this.C > 0 ) && (this.c > 0) && (this.b > 0)) return (this.c == (this.b * Math.sin(Math.toRadians(this.C))));

    return false;
  }

  public boolean isAmbiguousSSA() {
    if ((this.A > 0 ) && (this.a > 0) && (this.b > 0)) return (this.a > (this.b * Math.sin(Math.toRadians(this.A))));
    if ((this.A > 0 ) && (this.a > 0) && (this.c > 0)) return (this.a > (this.c * Math.sin(Math.toRadians(this.A))));
    if ((this.B > 0 ) && (this.b > 0) && (this.a > 0)) return (this.b > (this.a * Math.sin(Math.toRadians(this.B))));
    if ((this.B > 0 ) && (this.b > 0) && (this.c > 0)) return (this.b > (this.c * Math.sin(Math.toRadians(this.B))));
    if ((this.C > 0 ) && (this.c > 0) && (this.a > 0)) return (this.c > (this.a * Math.sin(Math.toRadians(this.C))));
    if ((this.C > 0 ) && (this.c > 0) && (this.b > 0)) return (this.c > (this.b * Math.sin(Math.toRadians(this.C))));

    return false;
  }

  public boolean isObliqueSSA() {
    if ((this.A > 0 ) && (this.a > 0) && (this.b > 0)) return (this.a > this.b);
    if ((this.A > 0 ) && (this.a > 0) && (this.c > 0)) return (this.a > this.c);
    if ((this.B > 0 ) && (this.b > 0) && (this.a > 0)) return (this.b > this.a);
    if ((this.B > 0 ) && (this.b > 0) && (this.c > 0)) return (this.b > this.c);
    if ((this.C > 0 ) && (this.c > 0) && (this.a > 0)) return (this.c > this.a);
    if ((this.C > 0 ) && (this.c > 0) && (this.b > 0)) return (this.c > this.b);
    return false;
  }


  public void findLastAngle() {
    if ((this.A > 0) && (this.B > 0) && (this.A >= this.B)) this.C = 180 - this.A - this.B;
    if ((this.A > 0) && (this.B > 0) && (this.B >  this.A)) this.C = 180 - this.B - this.A;
    if ((this.A > 0) && (this.C > 0) && (this.A >= this.C)) this.B = 180 - this.A - this.C;
    if ((this.A > 0) && (this.C > 0) && (this.C >  this.A)) this.B = 180 - this.C - this.A;
    if ((this.B > 0) && (this.C > 0) && (this.B >= this.C)) this.A = 180 - this.B - this.C;
    if ((this.B > 0) && (this.C > 0) && (this.C >  this.B)) this.A = 180 - this.C - this.B;
  }

  public void solveAAA() {
    System.out.println("ERROR: can find lengths for AAA. please provice length of atleast one side.");
  }

  public void solveAAS() {
    findLastAngle();
    if ((this.a == 0) && (this.b > 0)) this.a = (this.b / Math.sin(Math.toRadians(this.B))) * Math.sin(Math.toRadians(this.A));
    if ((this.a == 0) && (this.c > 0)) this.a = (this.c / Math.sin(Math.toRadians(this.C))) * Math.sin(Math.toRadians(this.A));
    if ((this.b == 0) && (this.a > 0)) this.b = (this.a / Math.sin(Math.toRadians(this.A))) * Math.sin(Math.toRadians(this.B));
    if ((this.b == 0) && (this.c > 0)) this.b = (this.c / Math.sin(Math.toRadians(this.C))) * Math.sin(Math.toRadians(this.B));
    if ((this.c == 0) && (this.a > 0)) this.c = (this.a / Math.sin(Math.toRadians(this.A))) * Math.sin(Math.toRadians(this.C));
    if ((this.c == 0) && (this.b > 0)) this.c = (this.b / Math.sin(Math.toRadians(this.B))) * Math.sin(Math.toRadians(this.C));
  }

  public void solveSAS() {
    if (this.A > 0) this.a = Math.sqrt((Math.pow(this.b, 2) + Math.pow(this.c, 2)) - (2 * this.b * this.c * Math.cos(Math.toRadians(this.A))));
    if (this.B > 0) this.b = Math.sqrt((Math.pow(this.a, 2) + Math.pow(this.c, 2)) - (2 * this.a * this.c * Math.cos(Math.toRadians(this.B))));
    if (this.C > 0) this.c = Math.sqrt((Math.pow(this.a, 2) + Math.pow(this.b, 2)) - (2 * this.a * this.b * Math.cos(Math.toRadians(this.C))));
    if ((this.A > 0) && (this.b <= this.c)) this.B = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(this.A)) * this.b) / this.a));
    if ((this.A > 0) && (this.b >  this.c)) this.C = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(this.A)) * this.c) / this.a));
    if ((this.B > 0) && (this.a <= this.c)) this.A = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(this.B)) * this.a) / this.b));
    if ((this.B > 0) && (this.a >  this.c)) this.C = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(this.B)) * this.c) / this.b));
    if ((this.C > 0) && (this.a <= this.b)) this.A = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(this.C)) * this.a) / this.c));
    if ((this.C > 0) && (this.a >  this.b)) this.B = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(this.C)) * this.b) / this.c));
    findLastAngle();
  }

  public void solveSSA() {
     if ((this.B > 0) && (this.b > 0) && (this.c > 0)) this.C = Math.toDegrees(Math.asin( (this.c * Math.sin(Math.toRadians(this.B))) / this.b));
     if ((this.B > 0) && (this.b > 0) && (this.a > 0)) this.A = Math.toDegrees(Math.asin( (this.a * Math.sin(Math.toRadians(this.B))) / this.b));
     findLastAngle();
     if (this.a == 0) this.a = (Math.sin(Math.toRadians(this.A)) * this.b) / Math.sin(Math.toRadians(this.B));
     if (this.b == 0) this.b = (Math.sin(Math.toRadians(this.B)) * this.c) / Math.sin(Math.toRadians(this.C));
     if (this.c == 0) this.c = (Math.sin(Math.toRadians(this.C)) * this.a) / Math.sin(Math.toRadians(this.A));
  }

  public void solveSupplementSSA() {
    if ((this.B > 0) && (this.b > 0) && (this.c > 0)) this.C = 180 - (Math.toDegrees(Math.asin( (this.c * Math.sin(Math.toRadians(this.B))) / this.b)));
    findLastAngle();
    if (this.a == 0) this.a = (Math.sin(Math.toRadians(this.A)) * this.b) / Math.sin(Math.toRadians(this.B));
  }

  public void solve() {
    switch (this.solutionType) {
      case AAA:
        break;
      case AAS:
      case ASA:
        solveAAS();
        break;

      case SAS:
        solveSAS();
        break;
      case SSS:
        break;
      case SSA:
        switch (this.SSAType) {
          case INVALID:
            break;
          case RIGHT:
            break;
          case AMBIGUOUS:
            break;
          case OBLIQUE:
        }
        break;
    }
  }


  public static void main(String[] args) {
    TrigCalc tc = new TrigCalc();
    tc.printDiagram();
    tc.parseArgs(args);
    tc.determineSolutionType();
    tc.solve();

/*
    if ( tc.isAAS() || tc.isASA() ) tc.solveAAS();
    if (tc.isSAS()) tc.solveSAS();
    if (tc.isSSA()) {
      if (tc.isInvalidSSA()) {
        System.out.println("INVALID SSA");
        System.exit(1);
      } else if (tc.isObliqueSSA() || tc.isRightSSA()) {
        tc.solveSSA();
      } else if (tc.isAmbiguousSSA()) {
        tc.solveSSA();
        tc.printVals();
        tc.parseArgs(args);
        tc.solveSupplementSSA();
      }
    }
*/

    tc.roundVals();
    tc.printVals();
  }
}
