import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Member;
import java.util.Arrays;
import java.io.*;
import static java.lang.System.out;

public class Testing {

    public int age = 25;
    public String firstname = "Jack";
    public String[] names = {"Jack", "William", "Smith"};
    public double height = 178.5;
    public Category gender = Category.MALE;
  
   

    public static void main(String... args) throws Exception{
      Object t = new Testing();
      Class<?> c = t.getClass();
      //try{
        //Field age = c.getDeclaredField("age");
      //} catch (NoSuchFieldException x) {
        //x.printStackTrace();
      //}
      //System.out.println(c.getCanonicalName());
      //System.out.println("age: " + t.age);
      Member[] members = c.getFields();
      for (Member m : members) {
        //System.out.println(m.toString());
        String delims = "[ ]";
        String[] string = m.toString().split(delims);
        //for (String s : string) {
          //System.out.println(s);
        //}
        int l = string.length;
        //System.out.println(l);
        System.out.println(string[l-1]);
        int left = string[l-1].indexOf(".");
        int end = string[l-1].length();
        //System.out.println(left);
        String string1 = string[l-1].substring(left+1, end);
        System.out.println(string1);
        Field field = c.getField(string1);

        //System.out.println(string1 + ": " + t.field);
        //System.out.println(age.getInt(t));
        Class clazzType = field.getType();
        System.out.println(clazzType.toString());
        if (clazzType.toString().equals("double"))
          System.out.println(string1 + ": " + field.getDouble(t));
        else if (clazzType.toString().equals("int"))
          System.out.println(string1 + ": " + field.getInt(t));
      }
      
      /*
      Object t = new Testclass();
      Class<?> objClass = t.getClass();

      Field[] fields = objClass.getFields();
      for(Field field : fields) {
        String name = field.getName();
        Object value = field.get(t);

        System.out.println(name + ": " + value.toString());
      }
      */
    }
    
    //public static class Testclass{
      //public int age = 25;
      //public String[] names = {"Jack", "William", "Smith"};
      //public double height = 178.5;
      //public Category gender = Category.MALE;
    //}
}
    