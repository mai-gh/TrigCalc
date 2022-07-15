import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TrigCalc {
  private double A, B, C, a, b, c; 
  private String solutionMethod = "NNN";

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
    DecimalFormat df = new DecimalFormat("#.00");
    df.setRoundingMode(RoundingMode.HALF_UP);
    System.out.println("    M = " + solutionMethod    + 
                       "    A = " + df.format(this.A) + 
                       "    B = " + df.format(this.B) + 
                       "    C = " + df.format(this.C) + 
                       "    a = " + df.format(this.a) + 
                       "    b = " + df.format(this.b) +
                       "    c = " + df.format(this.c) +
                       "\n"
                      );
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
    assert true; // do nothing
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



  public static void main(String[] args) {
    TrigCalc tc = new TrigCalc();
    tc.printDiagram();
    tc.parseArgs(args);
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


    tc.printVals();
  }
}
