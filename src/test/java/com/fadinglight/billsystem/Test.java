package com.fadinglight.billsystem;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws IOException {
        var yaml = new Yaml();
        Map map = yaml.load(Test.class.getClassLoader().getResourceAsStream("datasource.yaml"));

        System.out.println(map);
    }
}
