package iostream;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileReader
{
    static final String fileName = "/Users/guohui/GitHub/java-snippets/linked-dir/test.txt"; //"src/main/resources/samples/sampleData.txt";

    private static void close(final Closeable os)
    {
        if (os != null)
        {
            try
            {
                os.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void readBytes() throws IOException
    {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        System.out.println("readBytes >>");
        System.out.println(content);
    }

    /**
     * Remove EOL (LFCR) chars
     *
     * @throws IOException
     */
    public static void readLines() throws IOException
    {
        Stream<String> stream = Files.lines(Paths.get(fileName));
        StringBuilder sb = new StringBuilder();
        stream.forEach(s -> sb.append(s));
        System.out.println("readByLine >>");
        System.out.println(sb);
    }

    public static void readBufferReader()
    {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName)))
        {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            System.out.println("readBufferReader >>");
            System.out.println(sb);
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void readBufferReaderCollector() throws IOException
    {
        //List<String> str = (new BufferedReader(new java.io.FileReader(fileName))).lines().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        (new BufferedReader(new java.io.FileReader(fileName))).lines().forEach(s -> sb.append(s));
        System.out.println("readBufferReaderCollector >>");
        System.out.println(sb);
    }

    public static void scanner(String fileName) throws IOException
    {
        FileInputStream is = null;
        Scanner scanner = null;
        try
        {
            is = new FileInputStream(fileName);
            scanner = new Scanner(is);
            while (scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }
        } finally
        {
            if (scanner != null)
                scanner.close();

            if (is != null) {
                is.close();
            }

        }
    }

    public static void main(String[] args) throws IOException
    {
        readBytes();
        readLines();
        readBufferReader();
        readBufferReaderCollector();
        scanner(fileName);

        String a = "";
    }
}

