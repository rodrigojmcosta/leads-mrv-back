package br.com.mrv.leads_mrv.service;

public interface EmailService {
    void sendLeadAcceptedNotification(String to, String subject, String body);
}
