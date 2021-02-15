package com.toy.todolist.board.service;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.CardRepository;
import com.toy.todolist.board.dto.CardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;

    @Transactional
    public Long save(CardRequestDto cardRequestDto){

        Card card = cardRequestDto.toEntity();
        Card saveCard = cardRepository.save(card);

        return saveCard.getId();
    }
}
