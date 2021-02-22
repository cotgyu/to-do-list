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

    @Transactional(readOnly = true)
    public CardResponseDto findCard(Long id){

        Card card = findById(id);

        return new CardResponseDto(card);
    }

    private Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Card가 없습니다. id=" + id));
    }

    @Transactional
    public Long update(Long id, CardRequestDto cardRequestDto){
        Card card = findById(id);

        card.update(cardRequestDto.getCardName(), cardRequestDto.getDescription());

        return card.getId();
    }
}
