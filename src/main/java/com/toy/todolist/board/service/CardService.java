package com.toy.todolist.board.service;

import com.toy.todolist.board.domain.*;
import com.toy.todolist.board.dto.*;
import com.toy.todolist.board.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class CardService {

    private final TopicRepository topicRepository;

    private final CardRepository cardRepository;

    private final LabelRepository labelRepository;

    private final CheckListRepository checkListRepository;

    private final CheckItemRepository checkItemRepository;

    @Transactional
    public Long saveCard(CardRequestDto cardRequestDto){

        long topicId = cardRequestDto.getTopicId();
        Topic topic = topicRepository.findByIdAndDelFlag(topicId, "N").orElseThrow(() -> new IllegalArgumentException("해당 Topic가 없습니다. id=" + topicId));

        Card card = cardRequestDto.toEntity();
        Card saveCard = cardRepository.save(card);

        saveCard.changeTopic(topic);
        return saveCard.getId();
    }

    @Transactional(readOnly = true)
    public CardResponseDto findCard(Long id){

        Card card = findCardById(id);

        return new CardResponseDto(card);
    }

    private Card findCardById(Long id) {
        return cardRepository.findByIdAndDelFlag(id,"N").orElseThrow(() -> new IllegalArgumentException("해당 Card가 없습니다. id=" + id));
    }

    @Transactional
    public Long updateCard(Long id, CardRequestDto cardRequestDto){
        Card card = findCardById(id);

        card.update(cardRequestDto.getCardName(), cardRequestDto.getDescription(), cardRequestDto.getDelFlag());

        return card.getId();
    }

    @Transactional
    public Long saveLabel(LabelRequestDto labelRequestDto){

        Label label = labelRequestDto.toEntity();
        Label saveLabel = labelRepository.save(label);

        return saveLabel.getId();
    }

    @Transactional(readOnly = true)
    public List<LabelResponseDto> findAllLabels(){

        List<Label> allLabels = labelRepository.findAllByDelFlag("N");

        List<LabelResponseDto> labelResponseDtoList =
                allLabels.stream()
                        .map(label -> new LabelResponseDto(label))
                        .collect(toList());

        return labelResponseDtoList;
    }

    @Transactional
    public void updateLabel(Long id, LabelRequestDto labelResponseDto){

        Label label = findLabelById(id);

        label.update(labelResponseDto.getLabelName(), labelResponseDto.getColor(), labelResponseDto.getDelFlag());
    }

    @Transactional
    public Long registerLabel(CardLabelRequestDto cardLabelRequestDto){

        Card card = findCardById(cardLabelRequestDto.getCard_id());

        Label label = findLabelById(cardLabelRequestDto.getLabel_id());

        card.addCardLabel(new CardLabel(card, label));

        return label.getId();
    }

    private Label findLabelById(Long id) {
        Label label = labelRepository.findByIdAndDelFlag(id,"N").orElseThrow(() -> new IllegalArgumentException("해당 Label가 없습니다. id=" + id));

        return label;
    }

    @Transactional(readOnly = true)
    public List<CardLabelQueryDto> findCardLabels(Long cardId){

        List<CardLabelQueryDto> result = labelRepository.findCardLabels(cardId);

        return result;
    }

    @Transactional
    public void updateCardLabel(CardLabelRequestDto cardLabelRequestDto){

        long cardId = cardLabelRequestDto.getCard_id();
        Card card = cardRepository.findByIdAndDelFlag(cardId, "N").orElseThrow(() -> new IllegalArgumentException("해당 Card가 없습니다. id=" + cardId));

        List<CardLabel> cardLabels = card.getCardLabels();

        if(cardLabelRequestDto.getCheckFlag().equals("true")){
            boolean isHaving = false;

            for (CardLabel cardLabel : cardLabels) {
                if(cardLabel.getLabel().getId() == cardLabelRequestDto.getLabel_id()){
                        cardLabel.update("N");
                        isHaving = true;
                }
            }
            if(!isHaving){
                Label label = labelRepository.findById(cardLabelRequestDto.getLabel_id()).orElseThrow(() -> new IllegalArgumentException("해당 Label가 없습니다."));
                CardLabel addCardLabel = new CardLabel(card, label);

                card.addCardLabel(addCardLabel);
            }

        } else{
            for (CardLabel cardLabel : cardLabels) {
                if(cardLabel.getLabel().getId() == cardLabelRequestDto.getLabel_id()){
                    cardLabel.update("Y");
                }
            }
        }

    }

    @Transactional
    public Long saveCheckList(CheckListRequestDto checkListRequestDto){
        Card card = findCardById(checkListRequestDto.getCardId());

        CheckList checkList = checkListRequestDto.toEntity();

        card.addCheckList(checkList);

        return checkList.getId();
    }

    @Transactional
    public void updateCheckList(Long id, CheckListRequestDto checkListRequestDto){
        CheckList checkList = findCheckListById(id);

        checkList.update(checkListRequestDto.getCheckListTitle(), checkListRequestDto.getDelFlag());
    }

    private CheckList findCheckListById(Long id) {
        CheckList checkList = checkListRepository.findByIdAndDelFlag(id, "N").orElseThrow(() -> new IllegalArgumentException("해당 체크리스트가 없습니다. id=" + id));
        return checkList;
    }

    @Transactional
    public Long addCheckItem(CheckItemRequestDto checkItemRequestDto){
        CheckList checkList = findCheckListById(checkItemRequestDto.getCheckListId());

        CheckItem checkItem = checkItemRequestDto.toEntity();

        checkList.addCheckItem(checkItem);

        return checkItem.getId();
    }

    @Transactional
    public void updateCheckItem(Long id, CheckItemRequestDto checkItemRequestDto) {
        CheckItem checkItem = checkItemRepository.findByIdAndDelFlag(id, "N").orElseThrow(() -> new IllegalArgumentException("해당 체크아이템이 없습니다. id=" + id));

        checkItem.update(checkItemRequestDto.getCheckItemName(), checkItemRequestDto.getDelFlag(), checkItemRequestDto.getCheckFlag());
    }
}
