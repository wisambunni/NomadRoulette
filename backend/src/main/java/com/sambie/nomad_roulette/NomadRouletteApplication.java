package com.sambie.nomad_roulette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Make sure Spring scans your new packages!
@SpringBootApplication(scanBasePackages = "com.sambie")
public class NomadRouletteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NomadRouletteApplication.class, args);
    }
}
