import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //exercise();
        testCSV();
    }

    private static void exercise() {
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

        //подсчитать, сколько в списке строк, которые начинаются на "Вася"
        int n = countLinesWithPrefix(txt, "Вася");
        System.out.println("n = " + n);
    }

    private static void testCSV(){
        List<String> txt = loadCSV("comma separated values.txt");
        System.out.println("txt = " + txt);
    }

    private static List<String> getShortLines(List<String> txt, int maxlen){
        List<String> result = new ArrayList<>();
        for (String s: txt)
            if (s.length() < maxlen)
                result.add(s);
        return result;
    }

    private static int countLinesWithPrefix(List<String> txt, String prefix) {
        int counter=0;
        for (String s: txt)
            if (s.startsWith(prefix))
                counter++;
        return counter;
    }
    
    
    private static int countVasyaPupkin(List<String> txt, String x) {
        int counter=0;
        for (String s: txt)
            if (s.equals(x))
                counter++;
        return counter;
    }
    
    

    private static int countXByStream(List<String> txt, String x) {
        return  (int) txt.stream()
                            .filter(s->s.equals(x))
                            .count();
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

    //Нужно получить из файла список имен. в файле они разделяются символом ;
    private static List<String> loadCSV(String filename) {
        //создаем пустой список
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))){ //открываем сканером файл
            //циклом вычитываем строки и добавляем в список
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] massiv = s.split(";");
                for (String str: massiv) {
                    String trimmedStr = str.trim();
                    lines.add(trimmedStr);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("файл не открылся");
            System.out.println(e.getMessage());
        }

        return lines;
    }
}