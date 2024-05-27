package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;
@Command(name = "gendiff", version = "Groovy picocli v4.0 demo",
        mixinStandardHelpOptions = true, // add --help and --version options
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @CommandLine.Parameters(index = "0", description = "path to second file")
    private String filepath2;
    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format = "stylish";
    @Override
    public Integer call() throws Exception {
        System.out.println("Hello World!");

        return 0;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
