package collection.map.message;

public class MessageNotification1 {

	private KakaoTalkMessageSender katalkSender = new KakaoTalkMessageSender();
	private EmailMessageSender emailSender = new EmailMessageSender();
	private SmsMessageSender smsSender = new SmsMessageSender();
	
	public void notice(Customer customer, String from, String title, String content) {
		String[] messagesTypes = customer.getMessages();
	
		for(String messagesType : messagesTypes) {

			if("sms".equals(messagesType)) {
				smsSender.sendMessage(from, customer.getName(), title, content);

			} else if("katalk".equals(messagesType)) {
				katalkSender.sendMessage(from, customer.getName(), title, content);
				
			} else if("mail".equals(messagesType)) {
				emailSender.sendMessage(from, customer.getName(), title, content);
				
			}
			
		}
	}

	
}
