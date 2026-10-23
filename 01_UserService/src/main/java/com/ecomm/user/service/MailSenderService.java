package com.ecomm.user.service;

public interface MailSenderService {

	void sendmail(String to,String subject, String body);
}
