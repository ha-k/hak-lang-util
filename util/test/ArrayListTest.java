import hlt.language.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest
{
  public static void main (String[] args)
    {
      ArrayList s = new ArrayList();

      System.out.println(s);

      s.add("1");
      s.add("2");
      s.add("3");
      s.add("1");
      s.add("5");

      System.out.println(s);

      for (Iterator i = s.iterator(); i.hasNext();)
        System.out.println(i.next());

      for (int i=s.size(); i-->0;)
        if (i != s.indexOf(s.get(i)))
          throw new RuntimeException("duplicate entry at index: "+ i + " --> " +s.get(i));
    }
}
