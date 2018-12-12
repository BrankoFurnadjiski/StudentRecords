package StudentRecords;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Module {
    private String module;
    private long grade10;
    private long grade9;
    private long grade8;
    private long grade7;
    private long grade6;

    public Module(String module, Set<Student> students){
        this.module = module;
        this.grade10 = students.stream().mapToInt(student -> student.grade10()).sum();
        this.grade9 = students.stream().mapToInt(student -> student.grade9()).sum();
        this.grade8 = students.stream().mapToInt(student -> student.grade8()).sum();
        this.grade7 = students.stream().mapToInt(student -> student.grade7()).sum();
        this.grade6 = students.stream().mapToInt(student -> student.grade6()).sum();
    }

    public long getGrade10() {
        return grade10;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(module).append("\n");
        String gr6 = IntStream.range(1, (int)grade6/10) .mapToObj(i -> "*").collect(Collectors.joining(""));
        sb.append(String.format("%2d | %s(%d)", 6, gr6, grade6)).append("\n");

        String gr7 = IntStream.range(1, (int)grade7/10) .mapToObj(i -> "*").collect(Collectors.joining(""));
        sb.append(String.format("%2d | %s(%d)", 7, gr7, grade7)).append("\n");

        String gr8 = IntStream.range(1, (int)grade8/10) .mapToObj(i -> "*").collect(Collectors.joining(""));
        sb.append(String.format("%2d | %s(%d)", 8, gr8, grade8)).append("\n");

        String gr9 = IntStream.range(1, (int)grade9/10) .mapToObj(i -> "*").collect(Collectors.joining(""));
        sb.append(String.format("%2d | %s(%d)", 9, gr9, grade9)).append("\n");

        String gr10 = IntStream.range(1, (int)grade10/10) .mapToObj(i -> "*").collect(Collectors.joining(""));
        sb.append(String.format("%2d | %s(%d)", 10, gr10, grade10)).append("\n");

        return sb.toString();
    }
}
