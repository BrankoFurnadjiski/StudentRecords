package StudentRecords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private String id;
    private String module;
    private List<Integer> grades;

    public Student(String row){
        String[] parts = row.split(" ");
        id = parts[0];
        module = parts[1];
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(parts));
        temp.remove(1);
        temp.remove(0);

        grades = new ArrayList<>();
        grades = temp.stream()
                        .mapToInt(grade -> Integer.parseInt(grade))
                        .boxed()
                        .collect(Collectors.toList());
    }

    public String getModule() {
        return this.module;
    }

    public double getAverage() {
        return grades.stream()
                        .mapToInt(number -> number.intValue())
                        .sum()*1.0 / grades.size();
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return String.format("%s %.2f\n", id, getAverage());
    }

    public int grade10() {
        return (int)grades.stream()
                    .filter(grade -> grade == 10)
                    .count();
    }

    public int grade9() {
        return (int)grades.stream()
                .filter(grade -> grade == 9)
                .count();
    }

    public int grade8() {
        return (int)grades.stream()
                .filter(grade -> grade == 8)
                .count();
    }

    public int grade7() {
        return (int)grades.stream()
                .filter(grade -> grade == 7)
                .count();
    }

    public int grade6() {
        return (int)grades.stream()
                .filter(grade -> grade == 6)
                .count();
    }
}
