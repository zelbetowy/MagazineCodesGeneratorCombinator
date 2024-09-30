package pl.matelkom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/PythonApi")
public class PythonController {

    private Process pythonProcess1Main;

    @GetMapping("/run-python-0MAIN")
    public String runPythonScript0MAIN() {
        try {
            if (pythonProcess1Main == null || !pythonProcess1Main.isAlive()) {
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "cmd.exe", "/c", "start", "cmd.exe", "/k", "python D:/#SOFT/JAVA/Kutarate/Kutarate/PythonScripts/0MAIN.py"
                );
                pythonProcess1Main = processBuilder.start();
                return "Python script 0MAIN started successfully.";
            } else {
                return "Python script 0MAIN is already running.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to start script 0MAIN.";
        }
    }
}


