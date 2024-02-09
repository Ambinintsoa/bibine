package com.commercial.commerce.chat.repository;

import com.commercial.commerce.chat.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findBySenderIdAndReceiverEmail(String SenderId, String receiverEmail);

    List<Message> findByReceiverEmailAndSenderId(String receiverEmail, String SenderId);

    @Query(value = "{'receiverEmail' :  ?0}",sort = "{'date' : -1 }" )
    List<Message> findByReceiverEmailOrderByDateDesc(String receiverEmail, Pageable pageable);

    @Query(value = "{'SenderEmail' : ?0 ,'receiverEmail' :  ?1}",sort = "{'date' : -1 }" )
    List<Message> findBySenderEmailAndReceiverEmailOrderByDateDesc(String SenderEmail, String ReceiverEmail,Pageable Pageable);
    @Query(value = "{'receiverEmail' : ?0 ,'SenderEmail' :  ?1}",sort = "{'date' : -1 }" )
    List<Message> findByReceiverEmailAndSenderEmailOrderByDateDesc(String ReceiverEmail, String SenderEmail,Pageable Pageable);

}
