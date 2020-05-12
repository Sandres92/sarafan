package com.kor.sarafan.service;

import com.kor.sarafan.domain.Message;
import com.kor.sarafan.domain.User;
import com.kor.sarafan.domain.Views;
import com.kor.sarafan.dto.EventType;
import com.kor.sarafan.dto.MessagePageDto;
import com.kor.sarafan.dto.MetaDto;
import com.kor.sarafan.dto.ObjectType;
import com.kor.sarafan.repo.MessageRepo;
import com.kor.sarafan.util.WsSender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {
    private static String URL_PETTERN = "https?:\\/\\/?[\\w\\d\\._\\-%\\/\\?=&#]+";
    private static String IMG_PETTERN = "\\.(jpeg|jpg|gif|png)$";

    private static Pattern URL_REGEX = Pattern.compile(URL_PETTERN, Pattern.CASE_INSENSITIVE);
    private static Pattern IMG_REGEX = Pattern.compile(IMG_PETTERN, Pattern.CASE_INSENSITIVE);

    @Autowired
    private MessageRepo messageRepo;

    private final BiConsumer<EventType, Message> wsSender;

    public MessageService(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.idName.class);
    }

    private void fillMeta(Message message) throws IOException {
        String text = message.getText();

        Matcher matcher = URL_REGEX.matcher(text);

        if (matcher.find()) {
            String url = text.substring(matcher.start(), matcher.end());

            matcher = IMG_REGEX.matcher(url);
            message.setLink(url);

            if (matcher.find()) {
                message.setLinkCover(url);
            } else if (!url.contains("youtu")) {
                MetaDto metaDto = getMeta(url);


                message.setLinkTitle(metaDto.getTitle());
                message.setLinkDescription(metaDto.getDescription());
                message.setLinkCover(metaDto.getCover());
            }
        }
    }

    private MetaDto getMeta(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements title = doc.select("meta[name$=title],meta[property$=title]");
        Elements description = doc.select("meta[name$=description],meta[property$=description]");
        Elements cover = doc.select("meta[name$=image],meta[property$=image]");

        return new MetaDto(getContent(title.first()), getContent(description.first()), getContent(cover.first()));
    }

    private String getContent(Element element) {
        return element == null ? "" : element.attr("content");
    }

    public Message create(Message message, User user) throws IOException {
        message.setCreationDate(LocalDateTime.now());
        fillMeta(message);
        message.setAuthor(user);

        Message updateMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, updateMessage);

        return updateMessage;
    }

    public Message update(Message messageFromDb, Message message) throws IOException {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        fillMeta(message);

        Message updateMessage = messageRepo.save(messageFromDb);

        wsSender.accept(EventType.UPDATE, updateMessage);

        return updateMessage;
    }

    public void delete(Message message) {
        System.out.println("test   " + message.getId() + "   " + message.getText());
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    public MessagePageDto findAll(Pageable pageable) {
        Page<Message> page = messageRepo.findAll(pageable);
        return new MessagePageDto(page.getContent(), pageable.getPageNumber(), page.getTotalPages());
    }
}
