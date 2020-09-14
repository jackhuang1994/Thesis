import java.lang.reflect.Field;
import java.util.Arrays;
import static java.lang.System.out;

enum Category { MALE, FEMALE, OTHER }

public class Human {
  public int age = 25;
  public String[] names = {"Jack", "William", "Smith"};
  public double height = 178.5;
  public Category gender = Category.MALE;
  
  public static void main(String... args) {
      Human h = new Human();
      String fmt = "%6S:  %-10s = %s%n";

      try {
        Class<?> c = h.getClass();

        Field age = c.getDeclaredField("age");
        out.format(fmt, "before", "age", h.age);
        age.setInt(h, 32);
        out.format(fmt, "after", "age", age.getInt(h));

        Field names = c.getDeclaredField("names");
        out.format(fmt, "before", "names",
                   Arrays.asList(h.names));
        String[] newNames = { "Emma", "William", "Smith"};
        names.set(h, newNames);
        out.format(fmt, "after", "names",
                   Arrays.asList(h.names));
        
        Field height = c.getDeclaredField("height");
        out.format(fmt, "before", "height", h.height);
        height.setDouble(h, 180.5);
        out.format(fmt, "after", "height", height.getDouble(h));
        
        Field ca = c.getDeclaredField("gender");
        out.format(fmt, "before", "gender", h.gender);
        ca.set(h, Category.FEMALE);
        out.format(fmt, "after", "gender", ca.get(h));
        
        // production code should handle these exceptions more gracefully
      } catch (NoSuchFieldException x) {
        x.printStackTrace();
      } catch (IllegalAccessException x) {
        x.printStackTrace();
      }
    }
}