package com.tarasiuk.votehub.controller;

import com.tarasiuk.votehub.processor.VoteProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/vote")
@RestController
public class SimpleProtocolVoteController {

    private final VoteProcessor defaultAbstractSimpleProtocolVoteProcessor;

    @PostMapping
    public ResponseEntity<Void> voteForCandidate(@RequestBody String encryptedMessage) {

        defaultAbstractSimpleProtocolVoteProcessor.processVote(encryptedMessage);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
