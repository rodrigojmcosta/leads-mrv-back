package br.com.mrv.leads_mrv.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FakeEmailService implements EmailService {
    @Override
    public void sendLeadAcceptedNotification(String to, String subject, String body) {
        String content = "TO: " + to + "\nSUBJECT: " + subject + "\nBODY: " + body + "\n---\n";
        try {
            Files.write(Paths.get("vendas@test.txt"), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
