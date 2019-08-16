import hlt.language.util.IntToIntMap;

public class IntToIntMapTest
{
  public static void main (String[] args)
  {
    IntToIntMap table = new IntToIntMap();

    table.put(4,2);
    table.put(3,6);
    table.put(2,8);
    table.put(1,0);

    System.out.println("table[1] = "+table.get(1));
    System.out.println("table[2] = "+table.get(2));
    System.out.println("table[3] = "+table.get(3));
    System.out.println("table[4] = "+table.get(4));
    System.out.println("table[5] = "+table.get(5));
  }
}
    
