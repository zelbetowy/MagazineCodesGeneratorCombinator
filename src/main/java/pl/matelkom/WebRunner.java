package pl.matelkom;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile("web")
@Service
public class WebRunner implements ProfileRunner {

    public void start() {
        System.out.println("Uruchomilem sie na wersji WEB!");
    }
}


































//    public static class MappingBolts {
//        public static void main(String[] args) {
//            String inputFilename = "combinationsbolt.txt";
//            String outputFilename = "outputbolt.txt";
//            Map<String, String> mapping1 = new HashMap<>();
//            Map<String, String> mapping2 = new HashMap<>();
//            Map<String, String> mapping3 = new HashMap<>();
//            Map<String, String> mapping4= new HashMap<>();
//            Map<String, String> mapping5 = new HashMap<>();
//
//            mapping1.put("00913", "DIN 913 Śruba doc. imbus");
//            mapping2.put("N", "A2");
//            mapping3.put("00", " ");
//
//            mapping4.put("060", "M6");
//            mapping4.put("080", "M8");
//            mapping4.put("100", "M10");
//            mapping4.put("120", "M12");
//            mapping5.put("020", "20");
//            mapping5.put("025", "25");
//            mapping5.put("030", "30");
//            mapping5.put("045", "45");
//            mapping5.put("050", "50");
//            mapping5.put("055", "55");
//
//            try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
//                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] parts = line.split("-");
//                    String s1 = parts[0];
//                    String s2 = parts[1];
//                    String s3 = parts[2];
//                    String s4 = parts[3];
//                    String s5 = parts[4];
//
//                    // Zastąpienie wartości w blokach S1-S5 odpowiednimi wartościami z mapy
//                    s1 = mapping1.getOrDefault(s1, s1);
//                    s2 = mapping2.getOrDefault(s2, s2);
//                    s3 = mapping3.getOrDefault(s3, s3);
//                    s4 = mapping4.getOrDefault(s4, s4);
//                    s5 = mapping5.getOrDefault(s5, s5);
//
//                    // Skonstruowanie nowej linii zastępując bloki S1-S5 odpowiednimi wartościami
//                    String newLine = s1 + " " + s2 + " " + s3 + "  " + s4 + "x " + s5;
//
//                    // Zapisanie nowej linii do pliku output.txt
//                    writer.write(newLine);
//                    writer.newLine();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static class DataCargo {
//    }
//
//    @RestController
//    @RequestMapping("/api")
//    public static class FileGenerationController {
//
//    //    @PostMapping("/generateOptions")
//    ////    public ResponseEntity<String> generateFile(@RequestBody RequestData requestData) {
//    ////        ResponseEntity.ok();
//    ////        return null;
//    //    }
//    }
//
//    public static class fileGenerationService {
//
//        public static void generateFiles() {
//        }
//    }
//
//    public static class ElementsCombinator {
//
//        public void bolts() {
//            System.out.println("Generating combinations for bolts");
//            try {
//                HttpClient httpClient = HttpClient.newHttpClient();
//
//                HttpRequest build = HttpRequest.newBuilder().uri(new URI("http://localhost:5000/read_bolts")).GET().build();
//
//               httpClient.send(build, HttpResponse.BodyHandlers.ofString());
//                HttpResponse<String> send = httpClient.send(build, HttpResponse.BodyHandlers.ofString());
//
//                System.out.println(send.body());
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (URISyntaxException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        public void nuts() {
//        }
//
//        public void washers() {
//        }
//    }
//}



