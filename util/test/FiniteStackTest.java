import hlt.language.util.FiniteStack;
import java.util.Iterator;

public class FiniteStackTest
{
  public static void main (String[] args)
    {
      FiniteStack s = new FiniteStack();

      System.out.println(s);
      s.push("1");
      s.push("2");
      s.push("3");
      s.push("4");
      s.push("5");
      System.out.println(s);
//       System.out.println("Dropping: "+s.drop());
//       System.out.println("Dropping: "+s.drop());
//       System.out.println("Dropping: "+s.drop());
//       System.out.println(s);
      s.push("6");
      s.push("7");
      s.push("8");
      s.push("9");
      s.push("10");
      s.push("11");
      s.push("12");
      s.push("13");
      s.push("14");
//       System.out.println(s);
//       System.out.println("Dropping: "+s.drop());
//       System.out.println("Dropping: "+s.drop());
//       System.out.println("Dropping: "+s.drop());
//       System.out.println("Dropping: "+s.drop());
//       System.out.println("Dropping: "+s.drop());
//       System.out.println("Dropping: "+s.drop());
      s.push("15");
      s.push("16");
      s.push("17");
      s.push("18");
      s.push("19");
      s.push("20");
      s.push("21");
      s.push("22");
      s.push("23");
      System.out.println(s);
    }
}
