import hlt.language.util.Table;

public class TableTest
{
  public static void main (String[] args)
  {
    Table table = new Table();

    table.put("foo","bar");
    table.put("baz","buz");

    System.out.println("table[foo] = "+table.get("foo"));
    System.out.println("table[baz] = "+table.get("baz"));
    System.out.println("table[bar] = "+table.get("bar"));
  }
}
    
