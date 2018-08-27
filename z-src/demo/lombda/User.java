package lombda;

import java.util.List;

public class User {
    public User(String name, Integer age, List<Position> positions) {
        this.name = name;
        this.age = age;
        this.positions = positions;
    }

    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<Position> getPositions() {
        return positions;
    }

    List<Position> positions;
}
