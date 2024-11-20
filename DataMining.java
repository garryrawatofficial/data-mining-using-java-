
import java.io.*;
import java.util.*;

public class DataMining {

    // Function to load data from a CSV file
    public static List<Map<String, String>> loadData(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = br.readLine().split(","); // Read the header row
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], values[i]);
                }
                data.add(row);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }

    // Function to filter data based on a condition
    public static List<Map<String, String>> filterData(List<Map<String, String>> data, String column, String value) {
        List<Map<String, String>> filteredData = new ArrayList<>();
        for (Map<String, String> row : data) {
            if (row.get(column).equals(value)) {
                filteredData.add(row);
            }
        }
        return filteredData;
    }

    // Function to calculate basic statistics
    public static Map<String, Double> calculateStatistics(List<Map<String, String>> data, String column) {
        List<Double> values = new ArrayList<>();
        for (Map<String, String> row : data) {
            try {
                values.add(Double.parseDouble(row.get(column)));
            } catch (NumberFormatException e) {
                // Skip invalid numbers
            }
        }

        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (double val : values) {
            sum += val;
            if (val > max) max = val;
            if (val < min) min = val;
        }

        double mean = sum / values.size();
        Map<String, Double> stats = new HashMap<>();
        stats.put("Mean", mean);
        stats.put("Max", max);
        stats.put("Min", min);
        return stats;
    }

    public static void main(String[] args) {
        String filePath = "data.csv"; // Path to your CSV file

        // Step 1: Load data
        List<Map<String, String>> data = loadData(filePath);
        System.out.println("Loaded Data: " + data);

        // Step 2: Filter data (e.g., filter rows where "Category" = "A")
        String filterColumn = "Category";
        String filterValue = "A";
        List<Map<String, String>> filteredData = filterData(data, filterColumn, filterValue);
        System.out.println("Filtered Data: " + filteredData);

        // Step 3: Calculate statistics (e.g., on the "Sales" column)
        String statsColumn = "Sales";
        Map<String, Double> statistics = calculateStatistics(data, statsColumn);
        System.out.println("Statistics: " + statistics);
    }
}
