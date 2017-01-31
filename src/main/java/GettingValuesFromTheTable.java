import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * Created by adrianmaryniewski on 31.01.2017.
 */
public class GettingValuesFromTheTable {
    public static void getValuesFromTable() throws IOException {

        File input = new File("src/html_source_code_task_2.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        String newOutput = "output.txt";
        File file = new File(newOutput);


        boolean fileExists = file.exists();
        fileExists = createFileIfItDoesNotExist(file, fileExists);

        if(fileExists) {
            System.out.println("File " + newOutput + " exists, or was created.");
        }


        for (Element table : doc.select("table")) {
            selectValuesFromTableAndPutItInNewFile(newOutput, table);
        }


        int actualEnergyConsumption = 0;
        int forecastEnergyConsumption = 0;
        int amountOfColumnsInTable = 14;

        String line;
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));

        while((line = reader.readLine()) != null)  {
            String[] splittedFile = line.split(" ");

            actualEnergyConsumption += Integer.parseInt(splittedFile[0]);

            for (int i = 1; i <= amountOfColumnsInTable; i++) {
                forecastEnergyConsumption += Integer.parseInt(splittedFile[i]);
            }
        }

        System.out.println("20 Jan, actual: "+actualEnergyConsumption+ " MW");
        System.out.println("The rest of days (forecast): "+forecastEnergyConsumption+ " MW");
    }

    
    private static boolean createFileIfItDoesNotExist(File file, boolean fileExists) {
        if(!fileExists) {
            try {
                fileExists = file.createNewFile();
            } catch (IOException e) {
                System.out.println("The file cannot be created.");
            }
        }
        return fileExists;
    }


    private static void selectValuesFromTableAndPutItInNewFile(String newOutput, Element table) {
        String tableValues;
        for (Element row : table.select("tr")) {
            Elements tds = row.select("td[style=width:60px]");
            tableValues = tds.text().trim();

            try (
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newOutput, true));
            ) {
                writer.write(tableValues);
                writer.newLine();
            } catch(IOException e) {
                System.err.println("Error while saving file.");
            }
        }
    }
}