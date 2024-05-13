package ee.tbot.apartmentbot.factorytest;

public class ShapeFactory {

  public static Shape getShape(final String action) {
    if (action.equals("circle")){
      return new Circle();
    } else if (action.equals("square")) {
      return new Square();
    } else {
     return new Rectangle();
    }
  }
}
