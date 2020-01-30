package textAndFileMailSender;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class SendFileEmail {
    public static void main(String [] args) {
        // Recipient's email ID needs to be mentioned
        // Необходимо указать адрес электронной почты получателя
        String to = "abcd@gmail.com";

        // Sender's email ID needs to be mentioned
        // Необходимо указать адрес электронной почты отправителя
        String from = "web@gmail.com";

        // Assuming you are sending email from localhost
        // Предполагая, что вы отправляете электронную почту от localhost
        String host = "localhost";

        // Get system properties
        // Получить системные свойства
        Properties properties = System.getProperties();

        // Setup mail server
        // Настройка почтового сервера
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object
        // Получить объект Session по умолчанию
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object
            // Создать объект MimeMessage по умолчанию
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            // Set From: поле заголовка заголовка
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header
            // Set To: поле заголовка заголовка
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            // Set Subject: header field
            // Set Subject: поле заголовка
            message.setSubject("This is the Subject Line!");

            // Create the message part
            // Создать часть сообщения
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            // Заполните сообщение
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            // Создать составное сообщение
            Multipart multipart = new MimeMultipart();

            // Set text message part
            // Установить часть текстового сообщения
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            // Часть вторая это вложение
            messageBodyPart = new MimeBodyPart();
            String filename = "file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            // Отправить полное сообщение части
            message.setContent(multipart );

            // Send message
            // Отправить сообщение
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
