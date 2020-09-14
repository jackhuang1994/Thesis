import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Member;
import java.util.Arrays;
import static java.lang.System.out;

enum ObjectMember { CONSTRUCTOR, FIELD, METHOD, CLASS, ALL }

public class ObjectReader {
  public static void main(String... args) throws Exception{
    try {
      Class<?> c = Class.forName(args[0]);
      out.format("Class:%n  %s%n%n", c.getCanonicalName());

      Package p = c.getPackage();
      out.format("Package:%n  %s%n%n",
                 (p != null ? p.getName() : "-- No Package --"));

      for (int i = 1; i < args.length; i++) {
        switch (ObjectMember.valueOf(args[i])) {
          case CONSTRUCTOR:
            printMembers(c.getConstructors(), "Constructor");
            break;
          case FIELD:
            printMembers(c.getFields(), "Fields");
            printValues(c, c.getFields());
            break;
          case METHOD:
            printMembers(c.getMethods(), "Methods");
            break;
          case CLASS:
            printClasses(c);
            break;
          case ALL:
            printMembers(c.getConstructors(), "Constuctors");
            printMembers(c.getFields(), "Fields");
            printMembers(c.getMethods(), "Methods");
            printClasses(c);
            break;
          default:
            assert false;
        }
      }

      // production code should handle these exceptions more gracefully
    } catch (ClassNotFoundException x) {
      x.printStackTrace();
    } 
  }

  private static void printMembers(Member[] mbrs, String s) {
    out.format("%s:%n", s);
    for (Member mbr : mbrs) {
      if (mbr instanceof Field)
        out.format("  %s%n", ((Field)mbr).toGenericString());
      else if (mbr instanceof Constructor)
        out.format("  %s%n", ((Constructor)mbr).toGenericString());
      else if (mbr instanceof Method)
        out.format("  %s%n", ((Method)mbr).toGenericString());
    }
    if (mbrs.length == 0)
      out.format("  -- No %s --%n", s);
    out.format("%n");
  }
  
  
  private static void printValues(Class<?> c, Member[] mbrs) throws Exception{
    Constructor<?> constructor = c.getConstructor();
    //Object t = new Testing();
    Object t = constructor.newInstance();
    Class<?> c1 = t.getClass();
    for (Member m : mbrs) {
        //System.out.println(m.toString());
        String delims = "[ ]";
        String[] string = m.toString().split(delims);
        //for (String s : string) {
          //System.out.println(s);
        //}
        int l = string.length;
        //System.out.println(l);
        //System.out.println(string[l-1]);
        int left = string[l-1].indexOf(".");
        int end = string[l-1].length();
        //System.out.println(left);
        String string1 = string[l-1].substring(left+1, end);
        //System.out.println(string1);
        Field field = c.getField(string1);

        //System.out.println(string1 + ": " + t.field);
        //System.out.println(age.getInt(t));
        Class classType = field.getType();
        //System.out.println(classType);
        
        if (classType == Double.TYPE)
          System.out.println(string1 + ": " + field.getDouble(t));
        else if (classType == Integer.TYPE)
          System.out.println(string1 + ": " + field.getInt(t));
        else if (classType == String.class)
          System.out.println(string1 + ": " + (String) field.get(t));
        else if (classType == String[].class) 
          System.out.println(string1 + ": " + Arrays.asList((String[]) field.get(t)));
        
        //switch (classType) {
          //case Double.TYPE:
            //System.out.println(string1 + ": " + field.getDouble(t));
            //break;
          //case Integer.TYPE:
            //System.out.println(string1 + ": " + field.getInt(t));
            //break;
          //case String.class:
            //String str = (String) field.get (t);
            //System.out.println(string1 + ": " + str);
            //break;
          //case "java.lang.String[]":
            //String[] str = (
            
        //}
        
      }
    out.format("%n");
  }
  

  private static void printClasses(Class<?> c) {
    out.format("Classes:%n");
    Class<?>[] clss = c.getClasses();
    for (Class<?> cls : clss)
      out.format("  %s%n", cls.getCanonicalName());
    if (clss.length == 0)
      out.format("  -- No member interfaces, classes, or enums --%n");
    out.format("%n");
  }
}