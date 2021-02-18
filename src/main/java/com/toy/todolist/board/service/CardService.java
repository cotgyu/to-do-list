package com.toy.todolist.board.service;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.CardRepository;
import com.toy.todolist.board.dto.CardRequestDto;
import com.toy.todolist.board.dto.CardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public CardResponseDto findCard(Long id){

        Card card = cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Card가 없습니다. id=" + id));


        return new CardResponseDto(card);
    }

    @Transactional
    public Long update(Long id, CardRequestDto cardRequestDto){
        Card card = cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Card가 없습니다. id=" + id));

        card.update(cardRequestDto.getCardName());

        return card.getId();
    }
}
