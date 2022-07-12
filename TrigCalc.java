import java.lang.Math;

public class TrigCalc {
  private double A, B, C, a, b, c; 

  public void parseArgs(String[] args) {
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
    System.out.println("A = "    + this.A + 
                       ";  B = " + this.B + 
                       ";  C = " + this.C + 
                       ";  a = " + this.a + 
                       ";  b = " + this.b +
                       ";  c = " + this.c
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

  public void solveAAS() {
    if ((this.A > 0) && (this.B > 0) && (this.A >= this.B)) this.C = 180 - this.A - this.B;
    if ((this.A > 0) && (this.B > 0) && (this.B >  this.A)) this.C = 180 - this.B - this.A;
    if ((this.A > 0) && (this.C > 0) && (this.A >= this.C)) this.B = 180 - this.A - this.C;
    if ((this.A > 0) && (this.C > 0) && (this.C >  this.A)) this.B = 180 - this.C - this.A;
    if ((this.B > 0) && (this.C > 0) && (this.B >= this.C)) this.A = 180 - this.B - this.C;
    if ((this.B > 0) && (this.C > 0) && (this.C >  this.B)) this.A = 180 - this.C - this.B;
    if ((this.a == 0) && (this.b > 0)) this.a = (this.b / Math.sin(Math.toRadians(this.B))) * Math.sin(Math.toRadians(this.A));
    if ((this.a == 0) && (this.c > 0)) this.a = (this.c / Math.sin(Math.toRadians(this.C))) * Math.sin(Math.toRadians(this.A));
    if ((this.b == 0) && (this.a > 0)) this.b = (this.a / Math.sin(Math.toRadians(this.A))) * Math.sin(Math.toRadians(this.B));
    if ((this.b == 0) && (this.c > 0)) this.b = (this.c / Math.sin(Math.toRadians(this.C))) * Math.sin(Math.toRadians(this.B));
    if ((this.c == 0) && (this.a > 0)) this.c = (this.a / Math.sin(Math.toRadians(this.A))) * Math.sin(Math.toRadians(this.C));
    if ((this.c == 0) && (this.b > 0)) this.c = (this.b / Math.sin(Math.toRadians(this.B))) * Math.sin(Math.toRadians(this.C));
  }

  public static void main(String[] args) {
    TrigCalc tc = new TrigCalc();
    tc.printDiagram();
    tc.parseArgs(args);
    if (tc.isAAS()) tc.solveAAS();
    tc.printVals();
  }
}
