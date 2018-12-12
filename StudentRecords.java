package StudentRecords;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Collectors;

public class StudentRecords {
    private Map<String , Set<Student>> modules;

    public StudentRecords() {
        modules = new HashMap<>();
    }

    public int readRecords(InputStream in) {
        int count = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        List<Student> students = bufferedReader.lines()
                        .map(Student::new)
                        .collect(Collectors.toList());

        students.stream()
                .forEach(student -> {
                    String module = student.getModule();
                    if(!modules.containsKey(module)){
                        modules.put(module, new TreeSet<>(Comparator.comparing(Student::getAverage).reversed().thenComparing(Student::getId)));
                    }
                    modules.get(module).add(student);
                });

        return students.size();
    }

    public void writeTable(PrintStream out) {

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));

        modules.keySet().stream()
                        .forEach(module -> {
                            Set<Student> students = modules.get(module);
                            try {
                                bufferedWriter.write(module);
                                bufferedWriter.write("\n");
                                students.stream()
                                        .forEach(student -> {
                                            try {
                                                bufferedWriter.write(student.toString());
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
        try {
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDistribution(OutputStream outputStream) {
        TreeSet<Module> temps = new TreeSet<>(Comparator.comparing(Module::getGrade10).reversed());
        modules.keySet().stream()
                        .map(module -> new Module(module, modules.get(module)))
                        .forEach(module -> temps.add(module));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

        temps.forEach(module -> {
            try {
                bufferedWriter.write(module.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
