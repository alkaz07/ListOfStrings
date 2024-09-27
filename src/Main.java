import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //прочитать строки из файла в список строк
        List<String> txt = loadText("a.txt");
        System.out.println(txt);
        //получить список уникальных имен, прочитанный из файла
        List<String> unique = new ArrayList<>(new HashSet<>(txt));
        System.out.println(unique);
        List<String> unique2 = new ArrayList<>(new TreeSet<>(txt));
        System.out.println(unique2);

        List<String> unique3 = txt.stream().distinct().toList();
        System.out.println(unique3);
        //подсчитать, сколько раз в файле содержится строка "Вася Пупкин"
        int k = countVasyaPupkin(txt, "Вася Пупкин");
        System.out.println("k = " + k);
    }

    private static int countVasyaPupkin(List<String> txt, String x) {
        int counter=0;
        for (String s: txt)
            if (s.equals(x))
                counter++;
        return counter;
    }

    private static List<String> loadText(String filename) {
        //создаем пустой список
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))){ //открываем сканером файл
            //циклом вычитываем строки и добавляем в список
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                lines.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("файл не открылся");
            System.out.println(e.getMessage());
        }

        return lines;
    }
}