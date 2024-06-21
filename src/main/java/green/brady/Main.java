package green.brady;

import green.brady.cli.CliApp;
import green.brady.cli.Prompt;
import green.brady.requests.ServerRequestHandler;

public class Main {

    public static void main(String[] args) {
        CliApp.start(new Prompt(System.in), new ServerRequestHandler());
    }
}