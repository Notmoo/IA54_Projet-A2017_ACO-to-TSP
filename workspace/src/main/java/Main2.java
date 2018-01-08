import javafx.util.Pair;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    private static String DEFAULT_FILE = "";

    public static void main(String args[]){
        int choice;
        List<Pair<Double, Double>> points = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        do{
            choice = -1;

            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("\t-\t-\t-");
            System.out.println("Current list of points  :");
            if(points.size()<25) {
                for (int i = 0; i < points.size(); i++) {
                    System.out.println(String.format("%d -> %f %f", (i + 1), points.get(i).getKey(), points.get(i).getValue()));
                }
            }else{
                System.out.println(points.size()+" points in list");
            }
            System.out.println("\t-\t-\t-");
            if(DEFAULT_FILE.isEmpty())
                System.out.println("Default file : [NONE]");
            else
                System.out.println("Default file : "+DEFAULT_FILE);
            System.out.println("\t-\t-\t-");
            System.out.println("Choose an action  :");
            System.out.println("\t 0 -> exit");
            System.out.println("\t 1 -> compute distance matrix");
            System.out.println("\t 2 -> clear list");
            System.out.println("\t 3 -> load custom file");
            System.out.println("\t 4 -> load default file");
            System.out.println("\t 5 -> change default file");
            System.out.println("\t 6 -> clear, load default file and compute distance matrix");

            System.out.println("\t-\t-\t-");
            choice = in.nextInt();
            switch(choice){
                case 0:break;
                case 1:
                    computeDistanceMatrix(points);
                    break;
                case 2:
                    points.clear();
                    break;
                case 3:
                    System.out.println("Please enter the path to the file which has to be loaded :");
                    String str = in.next();
                    Path path = Paths.get(str);
                    loadFile(path, points);
                    break;
                case 4:
                    if(DEFAULT_FILE.isEmpty())
                        System.out.println("Default file not set!");
                    else
                        loadFile(Paths.get(DEFAULT_FILE), points);
                    break;
                case 5:
                    System.out.println("Enter new default file absolute path : ");
                    DEFAULT_FILE = in.next();
                    break;
                case 6:
                    if(DEFAULT_FILE.isEmpty())
                        System.out.println("Default file not set!");
                    else {
                        points.clear();
                        loadFile(Paths.get(DEFAULT_FILE), points);
                        computeDistanceMatrix(points);
                    }
                default:
                    choice = -1;
                    break;
            }
        }while(choice!=0);
    }

    private static void loadFile(Path path, List<Pair<Double, Double>> list){
        try {
            Files.lines(path).forEach(line->{
                try {
                    String data[] = line.split("\\s");
                    Double key = Double.parseDouble(data[0]);
                    Double val = Double.parseDouble(data[1]);
                    list.add(new Pair<>(key, val));
                }catch(InputMismatchException e){
                    System.err.println("Error!");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void computeDistanceMatrix(List<Pair<Double, Double>> points){
        System.out.println("Processing of distance matrix start ! ");
        Double[][] dist = new Double[points.size()][points.size()];
        for(int i = 0; i<points.size(); i++){
            System.out.print(" * line ... "+i);
            for(int j = 0; j<points.size(); j++){
                if(i==j)
                    dist[i][j] = 0d;
                else
                    dist[i][j] = Math.sqrt(Math.pow(points.get(j).getKey()-points.get(i).getKey(), 2)+Math.pow(points.get(j).getValue()-points.get(i).getValue(), 2));
            }
            System.out.println("END!");
        }
        System.out.println("Processing of distance matrix end ! ");

        System.out.println("\t-\t-\t-");
        StringBuffer sb = new StringBuffer("");
        for(int i = 0; i<points.size(); i++){
            for(int j = 0; j<points.size(); j++){
                if(j!=0)
                    sb.append(" ");
                sb.append(dist[i][j]);
            }
            sb.append("\n");
        }
        copyToClipboard(sb.toString());
        System.out.println("\t-\t-\t-");
    }

    private static void copyToClipboard(String str){
        StringSelection selection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        System.out.println(" <*-- String copied to clipboard --*>");
    }
}
