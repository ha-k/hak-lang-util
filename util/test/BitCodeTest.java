import hlt.language.util.BitCode;
import java.lang.Math;


public class BitCodeTest
{
  public static void main (String[] args)
    {
      BitCode bot = new BitCode();
      BitCode top = BitCode.allSet();
      // BitCode top = BitCode.not(bot);

      int width = 0;
      
      BitCode foo = new BitCode();
      foo.set(1);
      foo.set(3);
      foo.set(5);
      foo.set(7);
      foo.set(9);

      width = Math.max(width,foo.length());

      BitCode bar = new BitCode();
      bar.set(5);
      bar.set(10);
      bar.set(15);
      bar.set(20);
      bar.set(25);
      
      width = Math.max(width,bar.length());

      BitCode foz = bar.copy();

      BitCode fuz = bar.copy();
      fuz.or(foo);

      BitCode biz = fuz.copy();
      biz.set(18);
      biz.set(22);
      biz.set(30);
      
      width = Math.max(width,fuz.length());

      BitCode.setOnChar('+');
      BitCode.setOffChar('-');

      System.out.println("Top = " + top.toString(width));
      System.out.println("Foo = " + foo.toString(width));
      System.out.println("Bar = " + bar.toString(width));
      System.out.println("Foz = " + foz.toString(width));
      System.out.println("Fuz = " + fuz.toString(width));
      System.out.println("Biz = " + biz.toString(width));
      System.out.println("Bot = " + bot.toString(width));
      System.out.println();
      System.out.println("Foz == Bar ? --> " + foz.equals(bar));
      System.out.println("Fuz == Foo ? --> " + fuz.equals(foo));
      System.out.println("Fuz <= Foo ? --> " + fuz.isContainedIn(foo));
      System.out.println("Fuz == Biz ? --> " + fuz.equals(biz));
      System.out.println("Fuz <= Biz ? --> " + fuz.isContainedIn(biz));
      System.out.println("Fuz <  Biz ? --> " + fuz.isStrictlyContainedIn(biz));
    }
}
