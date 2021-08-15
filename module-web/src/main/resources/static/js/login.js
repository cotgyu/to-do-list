const deleteBoard = function (boardId){

    if(confirm('정말로 삭제하시겠습니까?')){
        const topicName = $('#board'+boardId).val();

        const data = {
            id:boardId,
            boardName: topicName,
            delFlag: 'Y'
        }

        $.ajax({
            type: 'PUT',
            url: '/api/board/'+boardId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function () {
            location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    }

}

const updateBoardMode = function (boardId){

    $('#boardView'+boardId).css('display', 'none');
    $('#editBoardNameInput'+boardId).css('display', '');
    $('#editBoardNameButton'+boardId).css('display', 'none');
    $('#saveBoardNameButton'+boardId).css('display', '');

    $('#editBoardNameInput'+boardId).focus();
    $('#editBoardNameInput'+boardId).on('focusout', function (){
        if($('#editBoardNameInput'+boardId).val() == ''){
            location.reload();
        }
    });

    $('#editBoardNameInput'+boardId).keypress(function(e){
        if(e.keyCode == 13){
            if($('#editBoardNameInput'+boardId).val() == ''){
                location.reload();
            }else {
                updateBoard(boardId);
            }
        }
    });
}

const updateBoard = function (boardId){
    const boardName = $('#editBoardNameInput'+boardId).val();

    if(boardName == ''){
        location.reload();
        return;
    }

    const data = {
        id:boardId,
        boardName: boardName,
        delFlag: 'N'
    }

    $.ajax({
        type: 'PUT',
        url: '/api/board/'+boardId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        location.reload();
    }).fail(function (error){

        if(error.status == 400){
            alert('잘못된 요청입니다.');
        }

        if(error.status == 401){
            alert('보드 수정 권한이 없습니다.');
        }

    });
}

const addBoardMode = function (){

    $('#addBoardButton').css('display','none');
    $('#addBoardNameInput').css('display','');
    $('#saveBoardButton').css('display','');

    $('#addBoardNameInput').focus();
    $('#addBoardNameInput').on('focusout', function (){
        if($('#addBoardNameInput').val() == ''){
            location.reload();
        }
    });

    $('#addBoardNameInput').keypress(function(e){
        if(e.keyCode == 13){
            if($('#addBoardNameInput').val() == ''){
                location.reload();
            }else {
                saveBoard();
            }
        }
    });

}

const saveBoard = function (){

    const boardName = $('#addBoardNameInput').val();

    const data = {
        boardName: boardName,
        delFlag: 'N'
    }

    $.ajax({
        type: 'POST',
        url: '/api/board',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}