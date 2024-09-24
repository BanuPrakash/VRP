package callableex;
// immutable object
// constructor, hashCode(), equals(), getters; no setters
public record Hotel(String name) {
}

//
//public class Hotel {
//    private  String name;
//    Hotel(String name) {
//        this.name = name;
//    }
//
//    String getName() {
//        return this.name;
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//}